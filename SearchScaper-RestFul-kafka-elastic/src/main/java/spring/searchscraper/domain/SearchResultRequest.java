package spring.searchscraper.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SearchResultRequest {

	private String taskName;
	private String keyWords;
	private boolean active;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	private String url;
	private String title;

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName
	 *            the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the keyWords
	 */
	public String getKeyWords() {
		return keyWords;
	}

	/**
	 * @param keyWords
	 *            the keyWords to set
	 */
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String toString() {
		return new StringBuilder(getTaskName()).append(getKeyWords()).toString();
	}
}
