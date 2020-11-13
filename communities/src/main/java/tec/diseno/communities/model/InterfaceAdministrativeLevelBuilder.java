package tec.diseno.communities.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InterfaceAdministrativeLevelBuilder {
	
	public ArrayList<String> getAddress(int address);
	public int setAddress(int id, String address);
	public int addAddress(int city, String address);
	public int delAddress(int address);
	
	public ArrayList<Member> getMember(AbstractAdministrativeLevel current);
	public int setMember(int current, int member);
	public int addMember(Member member) throws SQLException;
	public int delMember(int current, int member);
	
	public ArrayList<Member> getLeader(int current);
	public int addLeader(int current, int member);
	public int delLeader(int current, int member);
	
	public ArrayList<AbstractAdministrativeLevel> getChildren(AbstractAdministrativeLevel current);
	public int addChildren(int current, AbstractAdministrativeLevel children) throws SQLException;
	public int delChildren(int current, int children);
	
	public ArrayList<String> getTelephone(int telephone);
	public int addTelephone(int telephone);
	public int delTelephone(int telephone);
	
	public ArrayList<String> getEmail(int email);
	public int addEmail(int email);
	public int delEmail(int email);

	public AbstractAdministrativeLevel getAdministrativeLevel(EnumAdministrativeLevel type, int id);
	public int setAdministrativeLevel(AbstractAdministrativeLevel current);
	public int addAdministrativeLevel(AbstractAdministrativeLevel current);
	public int delAdministrativeLevel(AbstractAdministrativeLevel current);
}