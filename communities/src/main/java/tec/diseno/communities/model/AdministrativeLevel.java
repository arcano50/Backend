package tec.diseno.communities.model;

import java.util.ArrayList;

public class AdministrativeLevel extends AbstractAdministrativeLevel{
	private ArrayList<AbstractAdministrativeLevel> childrenCollection;
	
	public AdministrativeLevel() {
		
	}

	public ArrayList<AbstractAdministrativeLevel> getChildrenCollection() {
		return childrenCollection;
	}

	public void setChildrenCollection(ArrayList<AbstractAdministrativeLevel> childrenCollection) {
		this.childrenCollection = childrenCollection;
	}
}
