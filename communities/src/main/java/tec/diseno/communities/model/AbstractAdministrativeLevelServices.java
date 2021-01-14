package tec.diseno.communities.model;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbstractAdministrativeLevelServices implements InterfaceAdministrativeLevelBuilder {

	@Autowired
	AbstractAdministrativeLevelDao dao;
	
	public ArrayList<AbstractAdministrativeLevel> BuildBody() throws SQLException{
		return dao.BuildBody();
	}
	
	@Override
	public ArrayList<String> getAddress(int address) {
		return dao.getAddress(address);
	}

	@Override
	public int setAddress(int city, String address) {
		return dao.setAddress(city, address);
	}

	@Override
	public int addAddress(int city, String address) {
		return dao.addAddress(city, address);
	}

	@Override
	public int delAddress(int address) {
		return dao.delAddress(address);
	}

	@Override
	public int setMember(int current, int member) {
		return dao.setMember(current, member);
	}

	@Override
	public int addMember(Member member) throws SQLException {
		return dao.addMember(member);
	}

	@Override
	public int delMember(int current, int member) {
		return dao.delMember(current, member);
	}

	@Override
	public ArrayList<Member> getLeader(int current) {
		return dao.getLeader(current);
	}

	@Override
	public int addLeader(int current, int member) {
		return dao.addLeader(current, member);
	}

	@Override
	public int delLeader(int current, int member) {
		return dao.delLeader(current, member);
	}

	@Override
	public ArrayList<String> getTelephone(int telephone) {
		return dao.getTelephone(telephone);
	}

	@Override
	public int addTelephone(int telephone) {
		return dao.addTelephone(telephone);
	}

	@Override
	public int delTelephone(int telephone) {
		return dao.delTelephone(telephone);
	}

	@Override
	public ArrayList<String> getEmail(int email) {
		return dao.getEmail(email);
	}

	@Override
	public int addEmail(int email) {
		return dao.addEmail(email);
	}

	@Override
	public int delEmail(int email) {
		return dao.delEmail(email);
	}

	@Override
	public AbstractAdministrativeLevel getAdministrativeLevel(EnumAdministrativeLevel type, int id) {
		return dao.getAdministrativeLevel(type, id);
	}

	@Override
	public ArrayList<Member> getMember(AbstractAdministrativeLevel current) {
		return dao.getMember(current);
	}

	@Override
	public ArrayList<AbstractAdministrativeLevel> getChildren(AbstractAdministrativeLevel current) {
		return dao.getChildren(current);
	}

	@Override
	public int addChildren(int current, AbstractAdministrativeLevel children) throws SQLException {
		return dao.addChildren(current, children);
	}

	@Override
	public int delChildren(int current, int children) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setAdministrativeLevel(AbstractAdministrativeLevel current) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addAdministrativeLevel(AbstractAdministrativeLevel current) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delAdministrativeLevel(AbstractAdministrativeLevel current) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setBranchChildren(int current, AbstractAdministrativeLevel children) {
		// TODO Auto-generated method stub
		return dao.setBranchChildren(current, children);
	}

	@Override
	public int setGroupChildren(int current, AbstractAdministrativeLevel children) {
		// TODO Auto-generated method stub
		return dao.setGroupChildren(current, children);
	}

	@Override
	public int setCoordinationChildren(AbstractAdministrativeLevel children) {
		// TODO Auto-generated method stub
		return dao.setCoordinationChildren(children);
	}

	@Override
	public int setZoneChildren(int current, AbstractAdministrativeLevel children) {
		// TODO Auto-generated method stub
		return dao.setZoneChildren(current, children);
	}

	@Override
	public Member getUser(int id) {
		// TODO Auto-generated method stub
		return dao.getUser(id);
	}

	@Override
	public int setBranchMember(int current, int user, int branch, boolean state) {
		// TODO Auto-generated method stub
		return dao.setBranchMember(current, user, branch, state);
	}

	@Override
	public int setGroupMember(int current, int user, int branch, boolean state) {
		// TODO Auto-generated method stub
		return dao.setGroupMember(current, user, branch, state);
	}

	@Override
	public int setZoneMember(int current, int user, int branch, boolean state) {
		// TODO Auto-generated method stub
		return dao.setZoneMember(current, user, branch, state);
	}

	@Override
	public int setBranchLeader(int current, int user, int branch, boolean state) {
		// TODO Auto-generated method stub
		return dao.setBranchLeader(current, user, branch, state);
	}

	@Override
	public int setGroupLeader(int current, int user, int branch, boolean temporal, boolean state) {
		// TODO Auto-generated method stub
		return dao.setGroupLeader(current, user, branch, temporal, state);
	}

	@Override
	public int setZoneLeader(int current, int user, int branch, boolean state) {
		// TODO Auto-generated method stub
		return dao.setZoneLeader(current, user, branch, state);
	}

	@Override
	public int addBranchMember(int current, int member) {
		// TODO Auto-generated method stub
		return dao.addBranchMember(current, member);
	}

	@Override
	public int addGroupMember(int current, int member) {
		// TODO Auto-generated method stub
		return dao.addGroupMember(current, member);
	}

	@Override
	public int addZoneMember(int current, int member) {
		// TODO Auto-generated method stub
		return dao.addZoneMember(current, member);
	}

	@Override
	public int addBranchLeader(int current, int member) {
		// TODO Auto-generated method stub
		return dao.addBranchLeader(current, member);
	}

	@Override
	public int addGroupLeader(int current, int member) {
		// TODO Auto-generated method stub
		return dao.addGroupLeader(current, member);
	}

	@Override
	public int addZoneLeader(int current, int member) {
		// TODO Auto-generated method stub
		return dao.addZoneLeader(current, member);
	}

	@Override
	public void addContribution(Contribution contribution) {
		dao.addContribution(contribution);
	}

	@Override
	public ByteArrayInputStream getContributions() {
		return dao.getContributions();
	}

	@Override
	public List<String> addNews(News news) {
		return dao.addNews(news);
	}

	@Override
	public List<News> getNewsByUser(int id) {
		return dao.getNewsByUser(id);
	}
}
