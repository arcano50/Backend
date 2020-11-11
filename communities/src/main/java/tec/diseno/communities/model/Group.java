package tec.diseno.communities.model;

import java.util.ArrayList;

public class Group extends AbstractAdministrativeLevel{
	private int number;
	private boolean established;
	
	public Group() {
		
	}
	
	public Group(int _id, EnumAdministrativeLevel _type, String _name,  ArrayList<Member> _memberCollection,
			ArrayList<Member> _leaderCollection, boolean _loaded, boolean _enable, int _number, boolean _established) {
		super(_id, _type, _name, _memberCollection, _leaderCollection,_loaded, _enable);
		number = _number;
		established = _established;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public boolean isEstablished() {
		return established;
	}
	public void setEstablished(boolean established) {
		this.established = established;
	}
}
