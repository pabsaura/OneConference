package Models;

import java.io.File;
import java.util.Date;

public class Paper {
	
	private final String id;
	private final Author author;
	private String name;
	private Date subDate;
	private Review review;
	private String topic;
	private String file;
	
	public Paper(String id, Author author, String name, Review review,
			String topic, String file) {
		super();
		this.id=id;
		this.author = author;
		this.name = name;
		this.subDate = null;
		this.review = review;
		this.topic = topic;
		this.file = file;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getSubDate() {
		return subDate;
	}

	public void setSubDate(Date subDate) {
		this.subDate = subDate;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Author getAuthor() {
		return author;
	}
	
	public String getID(){
		return id;
	}


	public boolean hasReview() {
		if(this.review==null) return false;
		if(this.review.toString().equals("null")) return false;
		return true;
	}
	
	
}
