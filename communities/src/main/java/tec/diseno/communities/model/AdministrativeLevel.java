package tec.diseno.communities.model;

import java.util.ArrayList;

public class AdministrativeLevel extends AbstractAdministrativeLevel{
	private ArrayList<AbstractAdministrativeLevel> childrenCollection;
	
	public AdministrativeLevel() {

	}
	
	public AdministrativeLevel(int _id, EnumAdministrativeLevel _type, String _name,  ArrayList<Member> _memberCollection,
			 ArrayList<Member> _leaderCollection, ArrayList<AbstractAdministrativeLevel> _childrenCollection,
			 boolean _loaded, boolean _enable) {
		super(_id, _type, _name, _memberCollection, _leaderCollection,_loaded, _enable);
		childrenCollection = _childrenCollection;
	}

	public ArrayList<AbstractAdministrativeLevel> getChildrenCollection() {
		return childrenCollection;
	}

	public void setChildrenCollection(ArrayList<AbstractAdministrativeLevel> childrenCollection) {
		this.childrenCollection = childrenCollection;
	}
}
