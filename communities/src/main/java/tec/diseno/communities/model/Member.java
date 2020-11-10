package tec.diseno.communities.model;

import java.util.ArrayList;

public class Member {
	private int id;
	private int cardId;
	private String name;
	private String lastName;
	private String country;
	private String state;
	private String city;
	private String address;
	private ArrayList<String> telephoneCollection;
	private ArrayList<String> emailCollection;

	public Member() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<String> getTelephoneCollection() {
		return telephoneCollection;
	}

	public void setTelephoneCollection(ArrayList<String> telephoneCollection) {
		this.telephoneCollection = telephoneCollection;
	}

	public ArrayList<String> getEmailCollection() {
		return emailCollection;
	}

	public void setEmailCollection(ArrayList<String> emailCollection) {
		this.emailCollection = emailCollection;
	}
}
