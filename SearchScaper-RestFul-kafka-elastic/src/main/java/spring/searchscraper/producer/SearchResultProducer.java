package spring.searchscraper.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import spring.searchscraper.domain.SearchResultRequest;

@Service
public class SearchResultProducer {

	private final Logger logger = LoggerFactory.getLogger(SearchResultProducer.class);

	@Autowired
	private KafkaTemplate<String, SearchResultRequest> kafkaSearchResultTemplate;

	@Value(value = "${searchResultQueue.topic.name}")
	private String topicName;

	public void sendMessageToSearchTaskQueue(SearchResultRequest searchResultRequest) {
		logger.info("Adding {} to {} ", searchResultRequest, topicName);
		kafkaSearchResultTemplate.send(topicName, searchResultRequest);
	}
}
