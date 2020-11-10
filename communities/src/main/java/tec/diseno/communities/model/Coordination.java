package tec.diseno.communities.model;

import java.util.ArrayList;

public class Coordination extends AdministrativeLevel implements InterfaceCoordination {
	private String legalID;
	private String country;
	private String state;
	private String city;
	private String address;
	private ArrayList<String> telephoneCollection;
	private ArrayList<String> emailCollection;
	
	public Coordination() {
		
	}
	
	public String getLegalID() {
		return legalID;
	}
	public void setLegalID(String legalID) {
		this.legalID = legalID;
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
