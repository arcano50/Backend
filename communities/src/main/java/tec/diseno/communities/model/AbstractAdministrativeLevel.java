package tec.diseno.communities.model;

import java.util.ArrayList;

public abstract class AbstractAdministrativeLevel {
	private int id;
	private EnumAdministrativeLevel type;
	private String name;
	private ArrayList<Member> memberCollection;
	private ArrayList<Member> leaderCollection;
	private boolean loaded;
	private boolean enable;
	
	public AbstractAdministrativeLevel() {
		
	}
	
	public AbstractAdministrativeLevel(int _id, EnumAdministrativeLevel _type, String _name,  ArrayList<Member> _memberCollection,
			 ArrayList<Member> _leaderCollection, boolean _loaded, boolean _enable){
		id = _id;
		type = _type;
		name = _name;
		memberCollection = _memberCollection;
		leaderCollection = _leaderCollection;
		loaded = _loaded;
		enable = _enable;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
