package tec.diseno.communities.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tec.diseno.communities.model.AbstractAdministrativeLevel;
import tec.diseno.communities.model.AbstractAdministrativeLevelServices;
import tec.diseno.communities.model.AdministrativeLevel;
import tec.diseno.communities.model.AdministrativeLevelBuilder;
import tec.diseno.communities.model.Coordination;
import tec.diseno.communities.model.EnumAdministrativeLevel;
import tec.diseno.communities.model.Group;
import tec.diseno.communities.model.Member;

@RestController
public class OrganizationController {
	
	@Autowired
	AbstractAdministrativeLevelServices services;
	
	ArrayList<AbstractAdministrativeLevel> root = null;
	
	ArrayList<Coordination> localCoordinationCollection = new ArrayList<Coordination>();
	ArrayList<AdministrativeLevel> localZoneCollection = new ArrayList<AdministrativeLevel>();
	ArrayList<AdministrativeLevel> localBranchCollection = new ArrayList<AdministrativeLevel>();
	ArrayList<Group> localGroupCollection = new ArrayList<Group>();
	ArrayList<Member> localMemberCollection = new ArrayList<Member>();

	@RequestMapping(value = "/getData", produces = "application/json", method = RequestMethod.GET)
	public Object getData() {
		
		if (root != null)
			return root;
		try {
			root = services.BuildBody();
		}
		catch (SQLException e) {
			return e;
		}
		return root;
	}
	
	@RequestMapping(value="/addChildren", produces = "application/json", method = RequestMethod.POST)
	public Object addChildren(@RequestBody AdministrativeLevelBuilder children) {
		EnumAdministrativeLevel type = children.getType();
		try {
			services.addChildren(children.getParent(), children.getProduct());
		}
		catch (SQLException e) {
			return e;
		}
		switch(type) {
		case COORDINATION:
			break;
		case ZONE:
			break;
		case BRANCH:
			break;
		case GROUP:
			break;
		default:
			break;
		}
		return 0;
	}
	
	@RequestMapping(value="/addMember", produces = "application/json", method = RequestMethod.POST)
	public Member addMember(@RequestBody Member member) {
		try {
			services.addMember(member);
		}
		catch (SQLException e) {
			System.out.print(e);
		}
		return member;
	}
}