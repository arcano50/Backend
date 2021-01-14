package tec.diseno.communities.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public List<News> geNews(@RequestParam(name="id", required=true) int id) {
	    return services.getNewsByUser(id);
	}
	
	@GetMapping("/download")
	public ResponseEntity<InputStreamResource> getContribution(){
		String filename = "tutorials.csv";
	    InputStreamResource file = new InputStreamResource(services.getContributions());

		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
		        .contentType(MediaType.parseMediaType("application/csv"))
		        .body(file);
	}
}