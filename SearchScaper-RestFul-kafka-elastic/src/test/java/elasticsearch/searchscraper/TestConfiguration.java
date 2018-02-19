package elasticsearch.searchscraper;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import spring.searchscraper.config.SearchScrapperConfig;

@Configuration
@Import(SearchScrapperConfig.class)
public class TestConfiguration {

}
