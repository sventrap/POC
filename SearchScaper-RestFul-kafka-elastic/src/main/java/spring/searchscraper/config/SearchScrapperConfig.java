package spring.searchscraper.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
// @EnableScheduling
@ComponentScan(basePackages = { "spring.searchscraper", "spring.searchscraper.consumer",
		"spring.searchscraper.producer", "spring.searchscraper.scrape.service.api",
		"spring.searchscraper.scrape.service.impl", "spring.searchscraper.service.api",
		"spring.searchscraper.service.impl" })
@Import(value = { ElasticSearchConfiguration.class, KafkaConfiguration.class })
public class SearchScrapperConfig {

}
