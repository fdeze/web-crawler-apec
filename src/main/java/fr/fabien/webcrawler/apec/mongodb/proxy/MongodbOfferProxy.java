package fr.fabien.webcrawler.apec.mongodb.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import fr.fabien.contracts.OfferVo;


@FeignClient(name = "web-crawler-mongodb", url = "localhost:10000")
public interface MongodbOfferProxy {

	@PostMapping(value = "/insert", produces = { "application/json" })
	String insertOffer(OfferVo offer);

}