package spring.searchscraper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.searchscraper.domain.SearchTask;
import spring.searchscraper.producer.SearchTaskProducer;
import spring.searchscraper.service.api.SearchTaskService;

@Service
public class SearchResultsProcessor {
	private static Logger logger = LoggerFactory.getLogger(SearchResultsProcessor.class);

	@Autowired
	private SearchTaskService searchTaskService;

	@Autowired
	private SearchTaskProducer searchTaskProducer;

	// @Scheduled(initialDelay=2000,fixedDelayString = "#{new
	// Double((T(java.lang.Math).random() + 1) * 1000).intValue()}")
	public void process() throws Exception {

		List<SearchTask> searchTasks = searchTaskService.findActiveSearchTasks();
		logger.info("searchtasks returned {}", searchTasks);

		for (SearchTask searchTask : searchTasks) {
			searchTaskProducer.sendMessageToSearchTaskQueue(searchTask);
		}
	}

}
