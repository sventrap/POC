package spring.searchscraper.consumer;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import spring.searchscraper.domain.SearchResult;
import spring.searchscraper.domain.SearchResultRequest;
import spring.searchscraper.domain.UrlContents;
import spring.searchscraper.scrape.service.api.ScapperService;
import spring.searchscraper.service.api.SearchResultService;

@Service
public class SearchResultConsumer {

	private final Logger logger = LoggerFactory.getLogger(SearchResultConsumer.class);

	@Autowired
	private ScapperService scrapperService;

	@Autowired
	SearchResultService searchResultsService;

	@KafkaListener(topics = "${searchResultQueue.topic.name}", group = "searchResult", containerFactory = "searchResultKafkaListenerContainerFactory")
	public void listen(SearchResultRequest request) throws Exception {
		try {
			SearchResult searchResult = new SearchResult();
			searchResult.setTaskName(request.getTaskName());
			searchResult.setTitle(request.getTitle());
			searchResult.setCreatedAt(Calendar.getInstance().getTime());

			String url = request.getUrl();
			if (null != url && url.indexOf("webcache.googleusercontent.com") != -1) {
				url = url.substring(url.indexOf("http", url.indexOf("http") + 1));
				request.setUrl(url);
			}

			UrlContents urlContent;
			urlContent = scrapperService.getContents(request.getUrl());
			searchResult.setHttpStatusCode(String.valueOf(urlContent.getHttpStatusCode()));
			searchResult.setContent(urlContent.getContent());
			searchResultsService.save(searchResult);
		} catch (Exception e) {
			logger.error("Exception in the thread processing scrape result {} {}", request.getTaskName(),
					request.getUrl());
		}
	}

}
