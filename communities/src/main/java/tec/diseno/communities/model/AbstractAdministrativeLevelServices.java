package tec.diseno.communities.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbstractAdministrativeLevelServices implements InterfaceAdministrativeLevelBuilder {

	@Autowired
	AbstractAdministrativeLevelDao dao;
	
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
	public ArrayList<Member> getMember(int current) {
		return dao.getMember(current);
	}

	@Override
	public int setMember(int current, int member) {
		return dao.setMember(current, member);
	}

	@Override
	public int addMember(int current, int member) {
		return dao.addMember(current, member);
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
	public ArrayList<AbstractAdministrativeLevel> getChildren(int parent, int current) {
		return dao.getChildren(parent, current);
	}

	@Override
	public int addChildren(int current, int children) {
		return dao.addChildren(current, children);
	}

	@Override
	public int delChildren(int current, int children) {
		return dao.delChildren(current, children);
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
	public int setAdministrativeLevel(AdministrativeLevel current) {
		return dao.setAdministrativeLevel(current);
	}

	@Override
	public int addAdministrativeLevel(AdministrativeLevel current) {
		return dao.addAdministrativeLevel(current);
	}

	@Override
	public int delAdministrativeLevel(EnumAdministrativeLevel type, int id) {
		return dao.delAdministrativeLevel(type, id);
	}

}
