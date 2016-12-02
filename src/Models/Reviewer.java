package Models;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Reviewer extends User {
	
	private String topic;
	private String cv;
	private int SalaryPerReview;
	private ArrayList<Review> reviews;
	private ArrayList<Paper> pendingPapers;
	
	
	public Reviewer(String id, String topic, String cv, int salaryPerReview,String name
			,String surname,String city,String mail, String country,String password) {
		super(id, name,surname,city,country,mail,password);
		
		this.topic = topic;
		this.cv = cv;
		this.SalaryPerReview = salaryPerReview;
		
		this.reviews= new ArrayList<Review>();
		this.pendingPapers= new ArrayList<Paper>();
		
		
	}
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getCv() {
		return cv;
	}
	public void setCv(String cv) {
		this.cv = cv;
	}
	public int getSalaryPerReview() {
		return SalaryPerReview;
	}
	public void setSalaryPerReview(int salaryPerReview) {
		SalaryPerReview = salaryPerReview;
	}
	public ArrayList<Review> getReviews(){
		return this.reviews;
	}
	
	public ArrayList<Paper> getPendingPapers(){
		return this.pendingPapers;
	}

	public void setPendingPapers(ArrayList<Paper> pendingPaperList) {
		this.pendingPapers=pendingPaperList;
		
	}
	

}
