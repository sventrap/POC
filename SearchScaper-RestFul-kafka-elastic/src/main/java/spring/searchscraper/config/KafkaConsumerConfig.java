package spring.searchscraper.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import spring.searchscraper.domain.SearchResultRequest;
import spring.searchscraper.domain.SearchTask;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, SearchTask> searchTaskKafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, SearchTask> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(getSearchTaskConsumerFactory());
		return factory;
	}

	public ConsumerFactory<String, SearchTask> getSearchTaskConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "searchTask");
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
				new JsonDeserializer<>(SearchTask.class));

	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, SearchResultRequest> searchResultKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, SearchResultRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(getSearchResultConsumerFactory());
		return factory;
	}

	public ConsumerFactory<String, SearchResultRequest> getSearchResultConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "searchResult");
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
				new JsonDeserializer<>(SearchResultRequest.class));

	}

}
