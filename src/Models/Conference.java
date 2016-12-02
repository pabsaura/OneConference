package Models;

import java.util.ArrayList;
import java.util.Date;

public class Conference {
	
	private final String id;
	private final String name;
	private Date startDate;
	private int price;
	private String city;
	private Date paperDeadlines;
	private Date endDate;
	private int capacity;
	private ArrayList<Paper> paperList;
	private ArrayList<Reviewer> reviewerList;
	private ArrayList<Author> authorlist;
	private Chair chair;
	
	public Conference( String id, String name, Date startDate, int price, String city,
			Date paperDeadlines, Date endDate, int capacity,
			Chair chair) {
		
		super();
		this.name=name;
		this.startDate = startDate;
		this.price = price;
		this.city = city;
		this.paperDeadlines = paperDeadlines;
		this.endDate = endDate;
		this.capacity = capacity;
		this.paperList = new ArrayList<Paper>();
		this.reviewerList = new ArrayList<Reviewer>();
		this.authorlist = new ArrayList<Author>();
		this.chair = chair;
		this.id=id;
		
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getPaperDeadlines() {
		return paperDeadlines;
	}
	public void setPaperDeadlines(Date paperDeadlines) {
		this.paperDeadlines = paperDeadlines;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public ArrayList<Paper> getPaperList() {
		return paperList;
	}
	public void setPaperList(ArrayList<Paper> paperList) {
		this.paperList = paperList;
	}
	public ArrayList<Reviewer> getReviewerList() {
		return reviewerList;
	}
	public void setReviewerList(ArrayList<Reviewer> reviewerList) {
		this.reviewerList = reviewerList;
	}
	public ArrayList<Author> getAuthorlist() {
		return authorlist;
	}
	public void setAuthorlist(ArrayList<Author> authorlist) {
		this.authorlist = authorlist;
	}
	public Chair getChair() {
		return chair;
	}
	public void setChair(Chair chair) {
		this.chair = chair;
	}
	public String getID(){
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	
	
	
	
	

}
