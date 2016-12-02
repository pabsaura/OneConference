package Models;

import java.util.ArrayList;
import java.util.Date;

public class Author extends User{
	private ArrayList<Paper> papers;
	
	public Author(String id, String name, String surname, String city,
			String country, String mail, String password) {
		super(id, name, surname, city, country, mail, password);
		this.papers= new ArrayList<Paper>();
	
		
		
	}
	public ArrayList<Paper> getPapers(){
		return this.papers;
	}
	
	

}
