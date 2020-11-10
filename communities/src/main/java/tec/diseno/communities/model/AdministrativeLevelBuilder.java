package tec.diseno.communities.model;

import java.util.ArrayList;

public class AdministrativeLevelBuilder {
	private AbstractAdministrativeLevel node;
	
	public AdministrativeLevelBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	public void setMembemCollection(ArrayList<Member> memberCollection){
		node.setMemberCollection(memberCollection);
	}
}
