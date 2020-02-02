package fr.fabien.webcrawler.apec.internal;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import javax.ws.rs.HttpMethod;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.fabien.contracts.apec.ApecOfferVo;
import fr.fabien.webcrawler.apec.configuration.ApecConstants;
import fr.fabien.webcrawler.apec.internal.input.CritereVO;
import fr.fabien.webcrawler.apec.internal.output.AdresseOffreVO;
import fr.fabien.webcrawler.apec.internal.output.OffreVO;
import fr.fabien.webcrawler.common.Constants;

@Service
public class ApecOfferServiceImpl implements ApecOfferService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApecOfferServiceImpl.class);


	private final HttpClient httpClient = HttpClient.newBuilder()
	                                                .version(HttpClient.Version.HTTP_2)
	                                                .build();
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ApecOfferVo> getOffers(String location, String keyword) {
		List<ApecOfferVo> apecOfferVoList = new ArrayList<>();

		try {		
			final List<JSONObject> totalApecOfferList = new ArrayList<>();
			final JSONParser jsonParser = new JSONParser();
			
			//load APEC search request data default configuration
			final String apecSearchConfiguration = getApecSearchCriteria();
			//update configuration with input criteria
 			final String searchCriteria = updateApecSearchKeywordCriteria(apecSearchConfiguration, location, keyword);
			
			//call APEC search API
			final String httpCallBody = callApecSearchApi(searchCriteria);
			final JSONObject lJsonObject = (JSONObject) jsonParser.parse(httpCallBody);
			//get current offers found
			final List<JSONObject> apecOfferList = (List<JSONObject>) lJsonObject.get("resultats");
			totalApecOfferList.addAll(apecOfferList);

			//get offer total count
			final Long offerNumber = (Long) lJsonObject.get("totalCount");
			//get result page number
			final double offerPageNumber = offerNumber / ApecConstants.OFFER_PER_PAGE;

			LOGGER.trace("apec offers found {}", offerNumber);
			LOGGER.trace("page number to iterate {}", offerPageNumber);

			//for each result page to iterate
			DoubleStream.iterate(1, n -> n + 1)
			            .limit((long) offerPageNumber)
			            .boxed()
			            //iterate over result pages
					    .map(page -> fetchApecResultsByPage(searchCriteria, page))
					    .collect(Collectors.toList())
					    .forEach(totalApecOfferList::addAll);

			//map List<JSONObject> offers result list to List<ApecOfferVo> apecOfferVoList
			apecOfferVoList = totalApecOfferList.parallelStream()
					                   .map(lIdOffer -> collectDetailOffer(lIdOffer))
					                   .collect(Collectors.toList());

		} catch (IOException | ParseException | InterruptedException exception) {
			LOGGER.error("error during fetching offers", exception);
			Thread.currentThread().interrupt();
		}
		return apecOfferVoList;
	}

	/**
	 * call APEC search API 
	 * @param searchCriteria
	 * @param pageNumber
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<JSONObject> fetchApecResultsByPage(final String searchCriteria, final double pageNumber) {
	    List<JSONObject> apecOfferList = new ArrayList<>();
		 
		final JSONParser jsonParser = new JSONParser();
		try {
			final String httpCallBody = callApecSearchApi(updateApecSearchPagingCriteria(searchCriteria, pageNumber));
			final JSONObject  jsonObject = (JSONObject) jsonParser.parse(httpCallBody);
			apecOfferList = ((List<JSONObject>) jsonObject.get("resultats"));
			 
		} catch (IOException | InterruptedException | ParseException e) {
			Thread.currentThread().interrupt();
		}

		return apecOfferList;
	}

	private ApecOfferVo collectDetailOffer(final JSONObject pJsonObject) {
		ApecOfferVo apecOfferVo = null;
		final String idOffer = ((String) pJsonObject.get("numeroOffre"));

		final HttpRequest request = httpCall(HttpMethod.GET,ApecConstants.GET_OFFER_DETAILS_URL + idOffer, null);
 		try {
 			final HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
			
 			final ObjectMapper objectMapper = new ObjectMapper();
			final OffreVO lApecOffer = objectMapper.readValue(response.body(), OffreVO.class);
			apecOfferVo = mapping(lApecOffer);

		} catch (IOException | InterruptedException e) {
			LOGGER.error("error during fetching offer detail - offer id:{}", idOffer, e);
			Thread.currentThread().interrupt();
		}
 		
 		return apecOfferVo;

	}



	/**
	 * call Apec Search Api
	 * @param searchCriteria search criteria
	 * @return the API result
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private String callApecSearchApi(final String searchCriteria) throws IOException, InterruptedException {
		final HttpRequest request = httpCall(HttpMethod.POST, ApecConstants.SEARCH_OFFER_URL, searchCriteria);
		final HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
		return response.body();
	}

	
	/**
	 * make a HTTP call
	 * @param httpVerb
	 * @param url the URL to call
	 * @param postData the Data to send
	 * @return a HttpRequest object
	 */
	private HttpRequest httpCall(final String httpVerb,final String url, final String postData) {
		final HttpRequest httpRequest;
		final Builder lBuilder = HttpRequest.newBuilder()
				                      .uri(URI.create(url))
				                      .header(Constants.HTTP_HEADER_USER_AGENT, Constants.USER_AGENT_VALUE)
				                      .header(Constants.HTTP_HEADER_CONTENT_TYPE, Constants.CONTENT_TYPE_VALUE);
		
		if (HttpMethod.POST.equals(httpVerb)) {
			httpRequest = lBuilder.POST(BodyPublishers.ofString(postData)).build();
		} else {
			httpRequest = lBuilder.GET().build();
		}

		return httpRequest;
	}


	/**
	 * load APEC search API input data
	 * @return InputStream
	 * @throws IOException 
	 * @throws ParseException 
	 */
	private String getApecSearchCriteria() throws ParseException, IOException {
		InputStream resource = null;
		final String errorMessage = "error during loading flux_apec.json file";
		
		//load flux_apec.json file
		try {
			resource = getClass().getResourceAsStream("/"+ ApecConstants.APEC_SEARCH_API_INPUT_DATA_FILE);
		} catch (Exception exception) {
			LOGGER.error(errorMessage, exception);
		}

		if (null == resource) {
			try {
				resource = getClass().getResourceAsStream(ApecConstants.APEC_SEARCH_API_INPUT_DATA_FILE);
			} catch (Exception exception) {
				LOGGER.error(errorMessage, exception);
			}
		}
		
		final JSONParser jsonParser = new JSONParser();
		final JSONObject jsonObject = (JSONObject) jsonParser.parse(IOUtils.toString(resource));
		final ObjectMapper objectMapper = new ObjectMapper();
		//map jsonObject to CritereVO object
		final CritereVO lCritere = objectMapper.readValue(jsonObject.toJSONString(), CritereVO.class);
		
		return objectMapper.writeValueAsString(lCritere);
		

	}

	/**
	 * update APEC search criteria
	 * @param apecSearchConfiguration
	 * @param location the location criteria
	 * @param keyword the keyword criteria
	 * @return the search criteria configuration
	 * @throws JsonProcessingException
	 */
	private String updateApecSearchKeywordCriteria(final String searchCriteria, final String location, final String keyword) throws JsonProcessingException {
		
		final ObjectMapper objectMapper = new ObjectMapper();
		//map jsonObject to CritereVO object
		final CritereVO lCritere = objectMapper.readValue(searchCriteria, CritereVO.class);

		//override location value
		if (StringUtils.isNotEmpty(location)) {
			String locationArray[] = { location };
			lCritere.setLieux(locationArray);
		}
		
		//override keyword value
		if (StringUtils.isNotEmpty(keyword)) {
			lCritere.setMotsCles(keyword);
		}
		
		//return configuration
		return objectMapper.writeValueAsString(lCritere);
	}
	
	
	private String updateApecSearchPagingCriteria( final String searchCriteria, final double page)
			throws IOException {
 
		final ObjectMapper objectMapper = new ObjectMapper();
		//map jsonObject to CritereVO object
		final CritereVO critereVO = objectMapper.readValue(searchCriteria, CritereVO.class);
		
		final int index = Integer.parseInt(critereVO.getPagination().getStartIndex());
		critereVO.getPagination().setStartIndex(Integer.toString((int) (index + 20 * page)));
		
		//return configuration
		return objectMapper.writeValueAsString(critereVO);
 	}

	
	/**
	 * map @OffreVO object to @ApecOfferVo object
	 * @param @OffreVO offreVO object to map
	 * @return @ApecOfferVo object
	 */
	private ApecOfferVo mapping(OffreVO offreVO) {
		final ApecOfferVo lOfferVo = new ApecOfferVo();

		lOfferVo.setNumeroOffreExterne(ApecConstants.ACPEC_OFFER_CODE + offreVO.getNumeroOffre());

		lOfferVo.setTitre(offreVO.getIntitule());
		lOfferVo.setDatePublication(offreVO.getDatePublication());
		lOfferVo.setEntreprise(offreVO.getEnseigne());
		lOfferVo.setNumeroOffre(offreVO.getNumeroOffre());
		lOfferVo.setSalaire(offreVO.getSalaireTexte());
		lOfferVo.setUrl(ApecConstants.PUBLIC_OFFER_URL + offreVO.getNumeroOffre());

		lOfferVo.setDescriptionEntreprise(offreVO.getTexteHtmlEntreprise());
		lOfferVo.setDescriptionOffre(offreVO.getTexteHtml());
		lOfferVo.setDescriptionProfil(offreVO.getTexteHtmlProfil());
		if (null != offreVO.getLogoEtablissement()) {
			lOfferVo.setUrlLogo(ApecConstants.APEC_IMAGE_URL + offreVO.getLogoEtablissement());
		}

		if (null != offreVO.getLieux()) {
			lOfferVo.setAdresse(
					offreVO.getLieux().get(0).getLibelleLieu() + " " + offreVO.getLieux().get(0).getIdNomLieu());
		}

		if (null != offreVO.getAdresseOffre()) {
			final AdresseOffreVO lAdresseOffre = offreVO.getAdresseOffre();
			lOfferVo.setAdresseSiege(lAdresseOffre.getAdresseNumeroEtVoie() + " " + lAdresseOffre.getAdresseVille());
		}
		return lOfferVo;
	}
	
}