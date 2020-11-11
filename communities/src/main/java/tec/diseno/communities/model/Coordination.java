package tec.diseno.communities.model;

import java.util.ArrayList;

public class Coordination extends AdministrativeLevel {
	private String legalId;
	private String website;
	private String country;
	private String state;
	private String city;
	private String address;
	private ArrayList<String> telephoneCollection;
	private ArrayList<String> emailCollection;
	
	public Coordination() {
		
	}
	
	public Coordination(int _id, EnumAdministrativeLevel _type, String _name,  ArrayList<Member> _memberCollection,
	 ArrayList<Member> _leaderCollection, ArrayList<AbstractAdministrativeLevel> _childrenCollection,
	 boolean _loaded, boolean _enable, String _legalId, String _website, String _country, String _state,
	 String _city, String _address, ArrayList<String> _telephoneCollection, ArrayList<String> _emailCollection) {
		super(_id, _type, _name, _memberCollection, _leaderCollection, _childrenCollection, _loaded, _enable);
		legalId = _legalId;
		website = _website;
		country = _country;
		state = _state;
		city = _city;
		address = _address;
		telephoneCollection = _telephoneCollection;
		emailCollection = _emailCollection;
		
	}
	
	public Coordination(String _legalId, String _website, String _country, String _state, String _city,
			String _address, ArrayList<String> _telephoneCollection, ArrayList<String> _emailCollection) {
		legalId = _legalId;
		website = _website;
	}
	
	public String getLegalID() {
		return legalId;
	}
	public void setLegalID(String legalID) {
		this.legalId = legalID;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
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
