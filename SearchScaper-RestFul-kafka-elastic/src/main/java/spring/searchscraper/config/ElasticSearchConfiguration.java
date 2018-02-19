package spring.searchscraper.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfiguration {

	@Value(value = "${elastic.search.host}")
	private String elasticSearchHost;

	@Value(value = "${elastic.search.port}")
	private String elasticSearchPort;

	@Bean(destroyMethod = "close")
	public RestHighLevelClient buildRestHighLevelClient() {
		return new RestHighLevelClient(
				RestClient.builder(new HttpHost(elasticSearchHost, Integer.valueOf(elasticSearchPort), "http")));
	}

}
