package tec.diseno.communities.model;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbstractAdministrativeLevelServices {

	@Autowired
	AbstractAdministrativeLevelDao administrativeLevelDao;
	@Autowired
	ContributionDao contributionDao;
	@Autowired
	NewsDao newsDao;
	
	public ArrayList<AbstractAdministrativeLevel> BuildBody() throws SQLException{
		return administrativeLevelDao.BuildBody();
	}
	
	public ArrayList<String> getAddress(int address) {
		return administrativeLevelDao.getAddress(address);
	}

	public int setAddress(int city, String address) {
		return administrativeLevelDao.setAddress(city, address);
	}

	public int addAddress(int city, String address) {
		return administrativeLevelDao.addAddress(city, address);
	}

	public int delAddress(int address) {
		return administrativeLevelDao.delAddress(address);
	}

	public int setMember(int current, int member) {
		return administrativeLevelDao.setMember(current, member);
	}

	public int addMember(Member member) throws SQLException {
		return administrativeLevelDao.addMember(member);
	}

	public int delMember(int current, int member) {
		return administrativeLevelDao.delMember(current, member);
	}

	public ArrayList<Member> getLeader(int current) {
		return administrativeLevelDao.getLeader(current);
	}

	public int addLeader(int current, int member) {
		return administrativeLevelDao.addLeader(current, member);
	}

	public int delLeader(int current, int member) {
		return administrativeLevelDao.delLeader(current, member);
	}

	public ArrayList<String> getTelephone(int telephone) {
		return administrativeLevelDao.getTelephone(telephone);
	}

	public int addTelephone(int telephone) {
		return administrativeLevelDao.addTelephone(telephone);
	}

	public int delTelephone(int telephone) {
		return administrativeLevelDao.delTelephone(telephone);
	}

	public ArrayList<String> getEmail(int email) {
		return administrativeLevelDao.getEmail(email);
	}

	public int addEmail(int email) {
		return administrativeLevelDao.addEmail(email);
	}

	public int delEmail(int email) {
		return administrativeLevelDao.delEmail(email);
	}

	public AbstractAdministrativeLevel getAdministrativeLevel(EnumAdministrativeLevel type, int id) {
		return administrativeLevelDao.getAdministrativeLevel(type, id);
	}

	public ArrayList<Member> getMember(AbstractAdministrativeLevel current) {
		return administrativeLevelDao.getMember(current);
	}

	public ArrayList<AbstractAdministrativeLevel> getChildren(AbstractAdministrativeLevel current) {
		return administrativeLevelDao.getChildren(current);
	}

	public int addChildren(int current, AbstractAdministrativeLevel children) throws SQLException {
		return administrativeLevelDao.addChildren(current, children);
	}

	public int delChildren(int current, int children) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int setAdministrativeLevel(AbstractAdministrativeLevel current) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int addAdministrativeLevel(AbstractAdministrativeLevel current) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delAdministrativeLevel(AbstractAdministrativeLevel current) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int setBranchChildren(int current, AbstractAdministrativeLevel children) {
		// TODO Auto-generated method stub
		return administrativeLevelDao.setBranchChildren(current, children);
	}

	public int setGroupChildren(int current, AbstractAdministrativeLevel children) {
		// TODO Auto-generated method stub
		return administrativeLevelDao.setGroupChildren(current, children);
	}

	public int setCoordinationChildren(AbstractAdministrativeLevel children) {
		// TODO Auto-generated method stub
		return administrativeLevelDao.setCoordinationChildren(children);
	}

	public int setZoneChildren(int current, AbstractAdministrativeLevel children) {
		// TODO Auto-generated method stub
		return administrativeLevelDao.setZoneChildren(current, children);
	}

	public Member getUser(int id) {
		// TODO Auto-generated method stub
		return administrativeLevelDao.getUser(id);
	}

	public int setBranchMember(int current, int user, int branch, boolean state) {
		// TODO Auto-generated method stub
		return administrativeLevelDao.setBranchMember(current, user, branch, state);
	}

	public int setGroupMember(int current, int user, int branch, boolean state) {
		// TODO Auto-generated method stub
		return administrativeLevelDao.setGroupMember(current, user, branch, state);
	}

	public int setZoneMember(int current, int user, int branch, boolean state) {
		// TODO Auto-generated method stub
		return administrativeLevelDao.setZoneMember(current, user, branch, state);
	}

	public int setBranchLeader(int current, int user, int branch, boolean state) {
		return administrativeLevelDao.setBranchLeader(current, user, branch, state);
	}

	public int setGroupLeader(int current, int user, int branch, boolean temporal, boolean state) {
		return administrativeLevelDao.setGroupLeader(current, user, branch, temporal, state);
	}

	public int setZoneLeader(int current, int user, int branch, boolean state) {
		return administrativeLevelDao.setZoneLeader(current, user, branch, state);
	}

	public int addBranchMember(Member member) {
		return administrativeLevelDao.addBranchMember(member);
	}

	public int addGroupMember(Member member) {
		return administrativeLevelDao.addGroupMember(member);
	}

	public int addZoneMember(Member member) {
		return administrativeLevelDao.addZoneMember(member);
	}

	public int addBranchLeader(Member member) {
		return administrativeLevelDao.addBranchLeader(member);
	}

	public int addGroupLeader(Member member) {
		return administrativeLevelDao.addGroupLeader(member);
	}

	public int addZoneLeader(Member member) {
		return administrativeLevelDao.addZoneLeader(member);
	}

	public void addContribution(Contribution contribution) {
		contributionDao.addContribution(contribution);
	}

	public ByteArrayInputStream getContributions(String type) {
		return contributionDao.getContributions(type);
	}
        
        public List<ContributionReport> getContributionReport(){
            return contributionDao.getContributionReport();
        }

	public List<String> addNews(News news) {
		return newsDao.addNews(news);
	}

	public List<News> getNewsByUser(int id) {
		return newsDao.getNewsByUser(id);
	}
        
        
        public Account checkAccount(String username){
            return administrativeLevelDao.checkAccount(username);
        }
        
        
        public String login(String username, String password){
            return administrativeLevelDao.login(username, password);
        }
}
