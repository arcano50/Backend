package tec.diseno.communities.model;

import java.util.ArrayList;

public abstract class AbstractAdministrativeLevel {
	private int id;
	private String name;
	private ArrayList<Member> memberCollection;
	private ArrayList<Member> leaderCollection;
	private boolean loaded;
	private boolean enable;
	
	public AbstractAdministrativeLevel(){
		
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
