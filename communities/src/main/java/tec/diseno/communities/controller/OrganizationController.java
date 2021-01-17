package tec.diseno.communities.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tec.diseno.communities.model.AbstractAdministrativeLevel;
import tec.diseno.communities.model.AbstractAdministrativeLevelServices;
import tec.diseno.communities.model.AdministrativeLevel;
import tec.diseno.communities.model.AdministrativeLevelBuilder;
import tec.diseno.communities.model.Contribution;
import tec.diseno.communities.model.Coordination;
import tec.diseno.communities.model.EnumAdministrativeLevel;
import tec.diseno.communities.model.Group;
import tec.diseno.communities.model.Member;
import tec.diseno.communities.model.News;

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
			return services.addChildren(children.getParent(), children.getProduct());
		}
		catch (SQLException e) {
			return e;
		}
	}
	
	@RequestMapping(value="/addUser", produces = "application/json", method = RequestMethod.POST)
	public int addMember(@RequestBody Member member) {
		int value = -1;
		try {
			value = services.addMember(member);
		}
		catch (SQLException e) {
			System.out.print(e);
		}
		return value;
	}
	
	@RequestMapping(value="/getMembers", produces = "application/json", method = RequestMethod.POST)
	public ArrayList<Member> getMembers(@RequestBody AbstractAdministrativeLevel current) throws SQLException {
		ArrayList<Member> members = new ArrayList<Member>();
		members = services.getMember(current);
		return members;
	}
	
	@RequestMapping(value="/getChildren", produces = "application/json", method = RequestMethod.POST)
	public ArrayList<AbstractAdministrativeLevel> getChildren(@RequestBody AbstractAdministrativeLevel children) throws SQLException {
		ArrayList<AbstractAdministrativeLevel> administrativeLevels = new ArrayList<AbstractAdministrativeLevel>();
		administrativeLevels = services.getChildren(children);
		return administrativeLevels;
	}
	
	@RequestMapping(value="/getAdministrativeLevel", produces = "application/json", method = RequestMethod.POST)
	public AbstractAdministrativeLevel getAdministrativeLevel(@RequestBody AbstractAdministrativeLevel children) throws SQLException {
		AbstractAdministrativeLevel administrativeLevel;
		administrativeLevel = services.getAdministrativeLevel(children.getType(), children.getId());
		return administrativeLevel;
	}
	
	@RequestMapping(value="/setChildren", produces = "application/json", method = RequestMethod.POST)
	public int setChildren(@RequestBody AdministrativeLevelBuilder children) {
		int value = -1;
		EnumAdministrativeLevel type = children.getType();
		switch(type) {
		case COORDINATION:
			value = services.setCoordinationChildren(children.getProduct());
			break;
		case ZONE:
			value = services.setZoneChildren(children.getParent(), children.getProduct());
			break;
		case BRANCH:
			value = services.setBranchChildren(children.getParent(), children.getProduct());
			break;
		case GROUP:
			value = services.setGroupChildren(children.getParent(), children.getProduct());
			break;
		default:
			break;
		}
		return value;
	}
	
	@RequestMapping(value="/setMember", produces = "application/json", method = RequestMethod.POST)
	public int setMember(@RequestBody AdministrativeLevelBuilder children) {
		int value = -1;
		EnumAdministrativeLevel type = children.getType();
		switch(type) {
		case ZONE:
			value = services.setZoneMember(children.getParent(), children.getMemberCollection().get(0).getId(), children.getProduct().getId(), children.isEnable());
			break;
		case BRANCH:
			value = services.setBranchMember(children.getParent(), children.getMemberCollection().get(0).getId(), children.getProduct().getId(), children.isEnable());
			break;
		case GROUP:
			value = services.setGroupMember(children.getParent(), children.getMemberCollection().get(0).getId(), children.getProduct().getId(), children.isEnable());
			break;
		default:
			break;
		}
		return value;
	}
	
	@RequestMapping(value="/setLeader", produces = "application/json", method = RequestMethod.POST)
	public int setLeader(@RequestBody AdministrativeLevelBuilder children) {
		int value = -1;
		EnumAdministrativeLevel type = children.getType();
		switch(type) {
		case ZONE:
			value = services.setZoneLeader(children.getParent(), children.getLeaderCollection().get(0).getId(), children.getProduct().getId(), children.isEnable());
			break;
		case BRANCH:
			value = services.setBranchLeader(children.getParent(), children.getLeaderCollection().get(0).getId(), children.getProduct().getId(), children.isEnable());
			break;
		case GROUP:
			value = services.setGroupLeader(children.getParent(), children.getLeaderCollection().get(0).getId(), children.getProduct().getId(), children.getMemberCollection().get(0).isEnable(), children.isEnable());
			break;
		default:
			break;
		}
		return value;
	}
	
	@RequestMapping(value="/addLeader", produces = "application/json", method = RequestMethod.POST)
	public int addLeader(@RequestBody AdministrativeLevelBuilder children) {
		int value = -1;
		EnumAdministrativeLevel type = children.getType();
		switch(type) {
		case ZONE:
			value = services.addZoneLeader(children.getLeaderCollection().get(0));
			break;
		case BRANCH:
			value = services.addBranchLeader(children.getLeaderCollection().get(0));
			break;
		case GROUP:
			value = services.addGroupLeader(children.getLeaderCollection().get(0));
			break;
		default:
			break;
		}
		return value;
	}
	
	@RequestMapping(value="/addMember", produces = "application/json", method = RequestMethod.POST)
	public int addMember(@RequestBody AdministrativeLevelBuilder children) {
		int value = -1;
		EnumAdministrativeLevel type = children.getType();
		switch(type) {
		case ZONE:
			value = services.addZoneMember(children.getLeaderCollection().get(0));
			break;
		case BRANCH:
			value = services.addBranchMember(children.getLeaderCollection().get(0));
			break;
		case GROUP:
			value = services.addGroupMember(children.getLeaderCollection().get(0));
			break;
		default:
			break;
		}
		return value;
	}
	
	@RequestMapping(value="/getUser", produces = "application/json", method = RequestMethod.POST)
	public Member getUser(@RequestBody Member member) throws SQLException {
		Member outMember = new Member();
		outMember = services.getUser(member.getId());
		return outMember;
	}
	
	@RequestMapping(value = "/addContribution", method = RequestMethod.POST)
	public ResponseEntity<String> addContribution(@RequestBody Contribution contribution) {
		services.addContribution(contribution);
		return new ResponseEntity<>("Contribution was created", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/addNews", method = RequestMethod.POST)
	public ResponseEntity<String> addNews(@RequestBody News news) {
		services.addNews(news);
		return new ResponseEntity<>("Contribution was created", HttpStatus.CREATED);
	}
	
	@GetMapping("/getNews")
	@ResponseBody
	public List<News> getNews(@RequestParam(name="id", required=true) int id) {
	    return services.getNewsByUser(id);
	}
	
	@GetMapping("/download")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getContribution(@RequestHeader(value="Authorization") String AuthorizationHeader, @RequestParam(name="type", required=true) String type) {
		System.out.println("AuthorizationHeader: " + AuthorizationHeader);
		String filename;
		if(type == "JSON") {
			filename = "tutorials.json";
		} else {
			filename = "tutorials.csv";
		}
	    InputStreamResource file = new InputStreamResource(services.getContributions(type));
	    
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
		        .contentType(MediaType.parseMediaType("application/csv"))
		        .body(file);
	}
}