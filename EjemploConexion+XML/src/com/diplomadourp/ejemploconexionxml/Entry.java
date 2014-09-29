package com.diplomadourp.ejemploconexionxml;

// This class represents a single entry (post) in the XML feed.
// It includes the data members "title," "link," and "summary."
public class Entry {
	private String title;
    private String link;
    private String summary;
    private String tags;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
}
