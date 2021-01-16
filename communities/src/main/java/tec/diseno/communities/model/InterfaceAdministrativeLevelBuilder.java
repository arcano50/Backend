package tec.diseno.communities.model;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface InterfaceAdministrativeLevelBuilder {
	
	public ArrayList<String> getAddress(int address);
	public int setAddress(int id, String address);
	public int addAddress(int city, String address);
	public int delAddress(int address);
	
	public Member getUser(int id);
	
	public ArrayList<Member> getMember(AbstractAdministrativeLevel current);
	public int addBranchMember(int current, int member);
	public int addGroupMember(int current, int member);
	public int addZoneMember(int current, int member);
	public int addBranchLeader(int current, int member);
	public int addGroupLeader(int current, int member);
	public int addZoneLeader(int current, int member);
	public int setMember(int current, int member);
	public int setBranchLeader(int current, int user, int branch, boolean state);
	public int setGroupLeader(int current, int user, int branch, boolean temporal, boolean state);
	public int setZoneLeader(int current, int user, int branch, boolean state);
	public int setBranchMember(int current, int user, int branch, boolean state);
	public int setGroupMember(int current, int user, int branch, boolean state);
	public int setZoneMember(int current, int user, int branch, boolean state);
	public int addMember(Member member) throws SQLException;
	public int delMember(int current, int member);
	
	public ArrayList<Member> getLeader(int current);
	public int addLeader(int current, int member);
	public int delLeader(int current, int member);
	
	public ArrayList<AbstractAdministrativeLevel> getChildren(AbstractAdministrativeLevel current);
	public int addChildren(int current, AbstractAdministrativeLevel children) throws SQLException;
	public int delChildren(int current, int children);
	public int setBranchChildren(int current, AbstractAdministrativeLevel children);
	public int setGroupChildren(int current, AbstractAdministrativeLevel children);
	public int setCoordinationChildren(AbstractAdministrativeLevel children);
	public int setZoneChildren(int current, AbstractAdministrativeLevel children);
	
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