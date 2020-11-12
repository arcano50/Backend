package tec.diseno.communities.model;

import java.util.ArrayList;

public class AdministrativeLevelBuilder {
	private AbstractAdministrativeLevel product;
	
	private int id;
	private String legalId;
	private int number;
	private EnumAdministrativeLevel type;
	private String name;
	private String website;
	private String country;
	private String state;
	private String city;
	private String address;
	private ArrayList<Member> memberCollection;
	private ArrayList<Member> leaderCollection;
	private ArrayList<AbstractAdministrativeLevel> childrenCollection;
	private ArrayList<String> telephoneCollection;
	private ArrayList<String> emailCollection;
	private boolean established;
	private boolean loaded;
	private boolean enable;
	
	public AdministrativeLevelBuilder() {
		
	}
	
	public AbstractAdministrativeLevel getProduct() {
		AbstractAdministrativeLevel product = null;
		switch (type) {
		case COORDINATION:
			product = new Coordination(id, type, name, memberCollection, leaderCollection, childrenCollection,
					 loaded, enable, legalId, website, country, state, city, address, telephoneCollection, emailCollection);
			break;
		case ZONE:
		case BRANCH:
			product = new AdministrativeLevel(id, type, name, memberCollection, leaderCollection, childrenCollection,
					 loaded, enable);
			break;
		case GROUP:
			product = new Group(id, type, name, memberCollection, leaderCollection, loaded, enable, number, established);
			break;
		default:
			break;
		}
		return product;
	}
	
	public void setProduct(AbstractAdministrativeLevel product) {
		this.product = product;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLegalId() {
		return legalId;
	}
	public void setLegalId(String legalID) {
		this.legalId = legalID;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public EnumAdministrativeLevel getType() {
		return type;
	}
	public void setType(EnumAdministrativeLevel type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public ArrayList<Member> getMemberCollection() {
		return memberCollection;
	}
	public void setMemberCollection(ArrayList<Member> memberCollection) {
		this.memberCollection = memberCollection;
	}
	public ArrayList<Member> getLeaderCollection() {
		return leaderCollection;
	}
	public void setLeaderCollection(ArrayList<Member> leaderCollection) {
		this.leaderCollection = leaderCollection;
	}
	public ArrayList<AbstractAdministrativeLevel> getChildrenCollection() {
		return childrenCollection;
	}
	public void setChildrenCollection(ArrayList<AbstractAdministrativeLevel> childrenCollection) {
		this.childrenCollection = childrenCollection;
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
	public boolean isEstablished() {
		return established;
	}
	public void setEstablished(boolean established) {
		this.established = established;
	}
	public boolean isLoaded() {
		return loaded;
	}
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
