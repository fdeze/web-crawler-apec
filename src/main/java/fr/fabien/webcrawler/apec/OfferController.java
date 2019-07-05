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

import fr.fabien.contracts.OfferVo;
import fr.fabien.contracts.apec.ApecOfferVo;
import fr.fabien.webcrawler.apec.internal.ApecOfferService;
import fr.fabien.webcrawler.apec.mongodb.proxy.MongodbOfferProxy;
import io.swagger.annotations.Api;

@EnableDiscoveryClient
@RestController
@Api("")
public class OfferController implements HealthIndicator {

	private Logger logger = LoggerFactory.getLogger(OfferController.class);

	@Autowired
	private ApecOfferService apecProxy;

	@Autowired
	private MongodbOfferProxy mongodbProxy;

	@GetMapping(path = "/getOffers/apec/", produces = { "application/json" })
	public List<ApecOfferVo> getOffers() {
		return getOffersWithKeyword("java");
	}
	
	@GetMapping(path = "/getOffers/apec/{keyword}", produces = { "application/json" })
	public List<ApecOfferVo> getOffersWithKeyword(@PathVariable String keyword) {
		logger.info("Reception requête vers apec-microservice - getOffersWithKeyword - mot clé : {}", keyword);
		List<ApecOfferVo> lOfferList = apecProxy.getOffers(keyword);
		logger.info("Reception requête vers apec-microservice - getOffersWithKeyword - nombre résultats : {}", lOfferList.size());

		try {
			OfferVo offer;
			for (ApecOfferVo apecOfferVo : lOfferList) {
				offer = new OfferVo();
				offer.setDatePublication(apecOfferVo.getDatePublication());
				offer.setNumeroOffreExterne(apecOfferVo.getNumeroOffreExterne());
				offer.setTitre(apecOfferVo.getTitre());
				offer.setUrl(apecOfferVo.getUrl());
				logger.info("insertOffer:" + mongodbProxy.insertOffer(offer));
			}

		} catch (Exception e) {
			logger.error("erreur", e);
		}

		return lOfferList;
	}

	@Override
	public Health health() {
		return Health.up().build();
	}

}
