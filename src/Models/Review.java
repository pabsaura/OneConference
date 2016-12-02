package Models;

import java.io.File;
import java.util.Date;

public class Review {
	private final String id;
	private int grade;
	private Date deadLine;
	private String comment;
	private Reviewer reviewer;
	private Paper paper;
	
	public Review(String id, int grade, Date deadLine, String comment, Reviewer reviewer,
			Paper paper) {
		super();
		this.id=id;
		this.grade = grade;
		this.deadLine = deadLine;
		this.comment = comment;
		this.reviewer = reviewer;
		this.paper = paper;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Reviewer getReviewer() {
		return reviewer;
	}

	public void setReviewer(Reviewer reviewer) {
		this.reviewer = reviewer;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public String getID() {
		return id;
	}
	
	
	
	

}
