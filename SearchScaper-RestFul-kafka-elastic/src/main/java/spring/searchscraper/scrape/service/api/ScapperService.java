package spring.searchscraper.scrape.service.api;

import java.util.List;

import spring.searchscraper.domain.ScrapeResult;
import spring.searchscraper.domain.UrlContents;

public interface ScapperService {

	List<ScrapeResult> scrape(String searchWord) throws Exception;

	UrlContents getContents(String url) throws Exception;

}
