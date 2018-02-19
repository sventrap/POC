package spring.searchscraper.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import spring.searchscraper.domain.ScrapeResult;
import spring.searchscraper.domain.SearchResultRequest;
import spring.searchscraper.domain.SearchTask;
import spring.searchscraper.producer.SearchResultProducer;
import spring.searchscraper.scrape.service.api.ScapperService;

@Service
public class SearchTaskConsumer {

	private final Logger logger = LoggerFactory.getLogger(SearchTaskConsumer.class);

	@Autowired
	private ScapperService scrapperService;

	@Autowired
	private SearchResultProducer searchResultProducer;

	@KafkaListener(topics = "${searchTaskQueue.topic.name}", group = "searchTask", containerFactory = "searchTaskKafkaListenerContainerFactory")
	public void listenToSearchTaskQueue(SearchTask searchTask) throws Exception {

		logger.info("Consumed message {}", searchTask);
		scrapeTasks(searchTask);

	}

	private void scrapeTasks(SearchTask searchTask) throws Exception {
		String keywords = searchTask.getKeyWords();
		if (null != keywords) {
			List<String> keywordList = new ArrayList<String>(Arrays.asList(keywords.split(",")));

			for (String keyword : keywordList) {
				logger.info("Start Scrape for keyword {} ", keyword);

				List<ScrapeResult> scrapeResults = scrapperService.scrape(keyword);
				logger.info("scrape results {} ", scrapeResults);

				processSearchScrapeResult(searchTask, scrapeResults);

			}
		}
	}

	private void processSearchScrapeResult(SearchTask searchTask, List<ScrapeResult> results) throws Exception {
		SearchResultRequest req;
		for (ScrapeResult scrapeResult : results) {
			req = new SearchResultRequest();
			req.setActive(searchTask.isActive());
			req.setKeyWords(searchTask.getKeyWords());
			req.setTaskName(searchTask.getTaskName());
			req.setTitle(scrapeResult.getTitle());
			req.setUrl(scrapeResult.getUrl());
			searchResultProducer.sendMessageToSearchTaskQueue(req);
		}
	}

}
