package Models;

import java.util.ArrayList;
import java.util.Date;

public class Chair extends User{
	
	private ArrayList<Conference> conferenceList;
	

	public Chair(String id, String name, String surname, String city,
			String country, String mail, String password) {
		super(id,name, surname, city, country, mail, password);
			this.conferenceList = new ArrayList<Conference>();
			
	}
	
	

	public ArrayList<Conference> getConferenceList() {
		return conferenceList;
	}

	public void setConferenceList(ArrayList<Conference> conferenceList) {
		this.conferenceList = conferenceList;
	}

	
	

}
