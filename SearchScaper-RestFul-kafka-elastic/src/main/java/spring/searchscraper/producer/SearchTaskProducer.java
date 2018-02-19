package spring.searchscraper.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import spring.searchscraper.domain.SearchTask;

@Service
public class SearchTaskProducer {

	private final Logger logger = LoggerFactory.getLogger(SearchTaskProducer.class);

	@Autowired
	private KafkaTemplate<String, SearchTask> kafkaSearchTaskTemplate;

	@Value(value = "${searchTaskQueue.topic.name}")
	private String topicName;

	public void sendMessageToSearchTaskQueue(SearchTask searchTask) {

		logger.info("Adding {} to {} ", searchTask, topicName);
		kafkaSearchTaskTemplate.send(topicName, searchTask);
	}
}
