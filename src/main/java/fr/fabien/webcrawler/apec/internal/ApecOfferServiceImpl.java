package fr.fabien.webcrawler.apec.internal;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.fabien.webcrawler.apec.internal.input.Critere;
import fr.fabien.webcrawler.apec.internal.output.AdresseOffre;
import fr.fabien.webcrawler.apec.internal.output.ApecOffer;
import fr.fabien.webcrawler.common.Constants;
import fr.fabien.webcrawler.common.model.ApecOfferVo;

@Service
public class ApecOfferServiceImpl implements ApecOfferService {

	private Logger logger = LoggerFactory.getLogger(ApecOfferServiceImpl.class);

	/**
	 * Endpoint de recherche d'offres d'emploi apec
	 */
	private static String searchOfferUrl = "https://cadres.apec.fr/cms/webservices/rechercheOffre/ids";

	/**
	 * Endpoint d'obtention du détail d'une offre d'emploi
	 */
	private static String GET_OFFER_DETAILS_URL = "https://cadres.apec.fr/cms/webservices/offre/public?";

	/**
	 * URL d'une offre d'emploi sur le site internet
	 */
	private static String WEB_OFFER_URL = "https://cadres.apec.fr/home/mes-offres/recherche-des-offres-demploi/liste-des-offres-demploi/detail-de-loffre-demploi.html?numIdOffre=";

	@SuppressWarnings("unchecked")
	public List<ApecOfferVo> getOffers(String keyword) {
		List<ApecOfferVo> lOffers = new ArrayList<>();
		List<JSONObject> lTotalResultLists = new ArrayList<>();

		try {
			ObjectMapper lMapper = new ObjectMapper();
			JSONParser lJSONParser = new JSONParser();
			String lCritereJsonData = readJsonApecFile(lJSONParser, keyword, lMapper);

			HttpClient lHttpClient = HttpClient.newBuilder().version(Version.HTTP_2).build();

			String lHttpBody = callApecSite(lCritereJsonData, lHttpClient);
			JSONObject lJsonObject = (JSONObject) lJSONParser.parse(lHttpBody);
			List<JSONObject> lResultList = (List<JSONObject>) lJsonObject.get("resultats");
			lTotalResultLists.addAll(lResultList);

			Long lstrResultNumber = (Long) lJsonObject.get("totalCount");
			double lResultPageNumber = lstrResultNumber / 20.0;

			logger.trace("nombre resultats {}", lstrResultNumber);
			logger.trace("nombre pages {}", lResultPageNumber);

			DoubleStream.iterate(1, n -> n + 1).limit((long) lResultPageNumber).boxed()
					.map(page -> fetchApecResultsByPage(lMapper, lJSONParser, lCritereJsonData, lHttpClient, page))
					.collect(Collectors.toList()).forEach(lTotalResultLists::addAll);

			logger.trace("nombre résultats {}", lTotalResultLists.size());

			lOffers = lTotalResultLists.parallelStream()
					.map(lIdOffer -> collectDetailOffer(lMapper, lHttpClient, lIdOffer)).collect(Collectors.toList());

		} catch (IOException | ParseException | InterruptedException lException) {
			Thread.currentThread().interrupt();

			logger.error("erreur", lException);
			return lOffers;
		}
		return lOffers;
	}

	@SuppressWarnings("unchecked")
	private List<JSONObject> fetchApecResultsByPage(ObjectMapper lMapper, JSONParser lJSONParser,
			String lCritereJsonData, HttpClient lHttpClient, double n) {
		List<JSONObject> lTotalResultLists = new ArrayList<>();

		String lHttpBody;
		JSONObject lJsonObject;
		List<JSONObject> lAcpecResultList;
		try {
			lHttpBody = callApecSite(updateCritere(lMapper, lCritereJsonData, n), lHttpClient);
			lJsonObject = (JSONObject) lJSONParser.parse(lHttpBody);
			lAcpecResultList = ((List<JSONObject>) lJsonObject.get("resultats"));
			if (lAcpecResultList != null) {
				lTotalResultLists.addAll(lAcpecResultList);
			}
		} catch (IOException | InterruptedException | ParseException e) {

			Thread.currentThread().interrupt();
		}

		return lTotalResultLists;
	}

	private ApecOfferVo collectDetailOffer(ObjectMapper pMapper, HttpClient pHttpClient, JSONObject pJsonObject) {
		String lIdOffer = ((String) pJsonObject.get("@uriOffre")).substring(6);

		HttpRequest request = httpCall("GET", GET_OFFER_DETAILS_URL + lIdOffer, null);
		ApecOffer lApecOffer = null;
		try {
			HttpResponse<String> response = pHttpClient.send(request, BodyHandlers.ofString());
			lApecOffer = pMapper.readValue(response.body(), ApecOffer.class);
			return mapping(lApecOffer);

		} catch (IOException | InterruptedException e) {
			logger.error("erreur", e);
			Thread.currentThread().interrupt();
			return null;
		}

	}

	private ApecOfferVo mapping(ApecOffer lApecOffer) {
		ApecOfferVo lOfferVo = new ApecOfferVo();

		lOfferVo.setNumeroOffreExterne("APEC_" + lApecOffer.getNumeroOffre());

		lOfferVo.setTitre(lApecOffer.getIntitule());
		lOfferVo.setDatePublication(lApecOffer.getDatePublication());
		lOfferVo.setEntreprise(lApecOffer.getEnseigne());
		lOfferVo.setNumeroOffre(lApecOffer.getNumeroOffre());
		lOfferVo.setSalaire(lApecOffer.getSalaireTexte());
		lOfferVo.setUrl(WEB_OFFER_URL + lApecOffer.getNumeroOffre());

		lOfferVo.setTexteEntreprise(lApecOffer.getTexteHtmlEntreprise());
		lOfferVo.setTexteHtml(lApecOffer.getTexteHtml());
		lOfferVo.setTexteProfil(lApecOffer.getTexteHtmlProfil());
		if (null != lApecOffer.getLogoEtablissement()) {
			lOfferVo.setUrlLogo("https://cadres.apec.fr/files/live/mounts/images" + lApecOffer.getLogoEtablissement());
		}

		if (null != lApecOffer.getLieux()) {
			lOfferVo.setAdresse(
					lApecOffer.getLieux().get(0).getLibelleLieu() + " " + lApecOffer.getLieux().get(0).getIdNomLieu());
		}

		if (null != lApecOffer.getAdresseOffre()) {
			AdresseOffre lAdresseOffre = lApecOffer.getAdresseOffre();

			lOfferVo.setAdresseSiege(lAdresseOffre.getAdresseNumeroEtVoie() + " " + lAdresseOffre.getAdresseVille());
		}
		return lOfferVo;
	}

	private String callApecSite(String lJsonData, HttpClient httpClient) throws IOException, InterruptedException {
		HttpRequest request = httpCall("POST", searchOfferUrl, lJsonData);
		HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
		return response.body();
	}

	private HttpRequest httpCall(String lVerb, String lUrl, String lJsonData) {
		HttpRequest lHttpRequest;
		Builder lBuilder = HttpRequest.newBuilder().uri(URI.create(lUrl)).header("User-Agent", Constants.USER_AGENT)
				.header("Content-Type", "application/json");
		if ("POST".equals(lVerb)) {
			lHttpRequest = lBuilder.POST(BodyPublishers.ofString(lJsonData)).build();
		} else {
			lHttpRequest = lBuilder.GET().build();
		}

		return lHttpRequest;
	}

	private String updateCritere(ObjectMapper lMapper, String lCritereJsonData, double page) throws IOException {
		Critere lCritere = lMapper.readValue(lCritereJsonData, Critere.class);
		int index = Integer.parseInt(lCritere.getPagination().getStartIndex());
		lCritere.getPagination().setStartIndex(Integer.toString((int) (index + 20 * page)));
		lCritereJsonData = lMapper.writeValueAsString(lCritere);
		return lCritereJsonData;
	}

	private String readJsonApecFile(JSONParser parser, String keyword, ObjectMapper lMapper)
			throws IOException, ParseException {
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("src/main/resources/flux_apec.json"));
 
		if (StringUtils.isNotEmpty(keyword)) {
			Critere lCritere = lMapper.readValue(jsonObject.toJSONString(), Critere.class);
			lCritere.setMotsCles(keyword);
			return lMapper.writeValueAsString(lCritere);
		}

		return jsonObject.toJSONString();
	}

}