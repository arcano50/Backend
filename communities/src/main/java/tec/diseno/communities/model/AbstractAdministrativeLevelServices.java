package tec.diseno.communities.model;

import java.sql.SQLException;
import java.util.ArrayList;

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
}
