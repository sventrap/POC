package spring.searchscraper.service.api;

import java.util.List;

import spring.searchscraper.domain.SearchTask;

public interface SearchTaskService {
	void save(SearchTask searchTask) throws Exception;

	void deleteIndex() throws Exception;

	void createIndex() throws Exception;

	List<SearchTask> findByTaskName(String taskName) throws Exception;

	List<SearchTask> findActiveSearchTasks() throws Exception;
}
