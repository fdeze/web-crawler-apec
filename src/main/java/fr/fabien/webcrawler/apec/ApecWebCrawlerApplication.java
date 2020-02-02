package fr.fabien.webcrawler.apec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients("fr.fabien.webcrawler")
@EnableSwagger2
public class ApecWebCrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApecWebCrawlerApplication.class, args);
	}

}
