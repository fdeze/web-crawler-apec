package fr.fabien.webcrawler.apec;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.fabien.contracts.apec.ApecOfferVo;
import fr.fabien.webcrawler.apec.internal.ApecOfferService;
import fr.fabien.webcrawler.apec.mongodb.proxy.MongodbOfferProxy;
import io.swagger.annotations.Api;

@EnableDiscoveryClient
@RestController
@Api("APEC search offer API")
public class OfferController implements HealthIndicator {

	private final static Logger LOGGER = LoggerFactory.getLogger(OfferController.class);

	@Autowired
	private ApecOfferService apecProxy;

	@Autowired
	private MongodbOfferProxy mongodbProxy;

	
	
	
	@GetMapping(path = "/getOffers/apec/", produces = { "application/json" })
	public List<ApecOfferVo> getOffers() {
		return getOffersWithKeyword("java");
	}

	@GetMapping(path = "/getOffers/apec/{keyword}", produces = { "application/json" })
	public List<ApecOfferVo> getOffersWithKeyword(@PathVariable final String keyword) {
		return getOffersWithLocationAndKeyword("69", keyword);
	}

	@GetMapping(path = "/getOffers/apec/{location}/{keyword}", produces = { "application/json" })
	public List<ApecOfferVo> getOffersWithLocationAndKeyword(@PathVariable final String location,
			@PathVariable final String keyword) {
		
		LOGGER.info("Call - apec-microservice - getOffersWithLocationAndKeyword - location : {} -  keyword : {}",
				location, keyword);
		
		List<ApecOfferVo> lOfferList = apecProxy.getOffers(location, keyword);
		
		LOGGER.info("Call - apec-microservice - getOffersWithLocationAndKeyword - result number : {}",
				lOfferList.size());

		/*
		 * try { lOfferList.parallelStream().forEach(offer -> insertOffer(offer)); }
		 * catch (Exception e) { logger.error("getOffersWithKeyword - erreur", e); }
		 */

		return lOfferList;
	}

	/*
	 * private void insertOffer(ApecOfferVo apecOfferVo) { OfferVo offer; offer =
	 * new OfferVo(); offer.setDatePublication(apecOfferVo.getDatePublication());
	 * offer.setNumeroOffre(apecOfferVo.getNumeroOffre());
	 * offer.setNumeroOffreExterne(apecOfferVo.getNumeroOffreExterne());
	 * offer.setTitre(apecOfferVo.getTitre()); offer.setUrl(apecOfferVo.getUrl());
	 * offer.setDescriptionOffre(apecOfferVo.getDescriptionOffre());
	 * offer.setEntreprise(apecOfferVo.getEntreprise());
	 * logger.info("insertOffer {}", mongodbProxy.insertOffer(offer)); }
	 */

	@Override
	public Health health() {
		return Health.up().build();
	}

}
