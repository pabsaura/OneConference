package Models;

import java.util.Date;

public abstract class User {
	
	private final String id;
	private String name;
	private String surname;
	private String city;
	private String country;
	private String mail;
	private String password;
	
	
	
	public User(String id, String name, String surname, String city,
			String country, String mail, String password) {
		super();
		this.id=id;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.country = country;
		this.mail = mail;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getID(){
		return id;
	}
	
	
}
