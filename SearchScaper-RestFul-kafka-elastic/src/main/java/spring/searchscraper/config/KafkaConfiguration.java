package spring.searchscraper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { spring.searchscraper.config.KafkaProducerConfig.class,
		spring.searchscraper.config.KafkaConsumerConfig.class })
public class KafkaConfiguration {

}
