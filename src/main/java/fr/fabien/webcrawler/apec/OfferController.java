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

import fr.fabien.webcrawler.apec.internal.ApecOfferService;
import fr.fabien.webcrawler.common.model.ApecOfferVo;

@EnableDiscoveryClient
@RestController
public class OfferController implements HealthIndicator {

	private Logger logger = LoggerFactory.getLogger(OfferController.class);

	@Autowired
	private ApecOfferService apecProxy;

	@GetMapping(path = "/getOffers/apec/{keyword}", produces = { "application/json" })
	public List<ApecOfferVo> getOffers(@PathVariable String keyword) {
		logger.info("Reception requête vers apec-microservice - getOffers - mot clé : {}", keyword);

		return apecProxy.getOffers(keyword);

	}

	@Override
	public Health health() {
		return Health.up().build();
	}

}
