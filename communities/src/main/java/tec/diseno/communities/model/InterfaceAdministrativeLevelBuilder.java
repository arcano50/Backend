package tec.diseno.communities.model;

import java.util.ArrayList;

public interface InterfaceAdministrativeLevelBuilder {
	
	public ArrayList<String> getAddress(int address);
	public int setAddress(int city, String address);
	public int addAddress(int city, String address);
	public int delAddress(int address);
	
	public ArrayList<Member> getMember(int current);
	public int setMember(int current, int member);
	public int addMember(int current, int member);
	public int delMember(int current, int member);
	
	public ArrayList<Member> getLeader(int current);
	public int addLeader(int current, int member);
	public int delLeader(int current, int member);
	
	public ArrayList<AbstractAdministrativeLevel> getChildren(int parent, int current);
	public int addChildren(int current, int children);
	public int delChildren(int current, int children);
	
	public ArrayList<String> getTelephone(int telephone);
	public int addTelephone(int telephone);
	public int delTelephone(int telephone);
	
	public ArrayList<String> getEmail(int email);
	public int addEmail(int email);
	public int delEmail(int email);

	public AbstractAdministrativeLevel getAdministrativeLevel(EnumAdministrativeLevel type, int id);
	public int setAdministrativeLevel(AdministrativeLevel current);
	public int addAdministrativeLevel(AdministrativeLevel current);
	public int delAdministrativeLevel(EnumAdministrativeLevel type, int id);
}