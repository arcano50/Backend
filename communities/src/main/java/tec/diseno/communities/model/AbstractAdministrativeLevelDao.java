package tec.diseno.communities.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class AbstractAdministrativeLevelDao extends JdbcDaoSupport  implements InterfaceAdministrativeLevelBuilder {
	private String sql;
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Override
	public ArrayList<String> getAddress(int address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int setAddress(int city, String address) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addAddress(int city, String address) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delAddress(int address) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Member> getMember(int current) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int setMember(int current, int member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addMember(int current, int member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delMember(int current, int member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Member> getLeader(int current) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addLeader(int current, int member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delLeader(int current, int member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<AbstractAdministrativeLevel> getChildren(int parent, int current) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addChildren(int current, int children) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delChildren(int current, int children) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<String> getTelephone(int telephone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addTelephone(int telephone) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delTelephone(int telephone) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<String> getEmail(int email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addEmail(int email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delEmail(int email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AbstractAdministrativeLevel getAdministrativeLevel(EnumAdministrativeLevel type, int id) {
		switch(type) {
		case COORDINATION:
			sql = "SELECT sp_get_coordination(?)";
			List<Map<String, Object>> _result =  getJdbcTemplate().queryForList(sql,
					new Object[] {id});
			System.out.print(_result);
			Map<String, Object> result = _result.get(0);
			Coordination coordination = new Coordination();
			coordination.setId((int) result.get("Id"));
			coordination.setLegalID((String) result.get("legalId"));
			coordination.setName((String) result.get("name"));
			coordination.setWebsite((String) result.get("website"));
			coordination.setEnable((boolean) result.get("enable"));
			return coordination;
		case ZONE:
			break;
		case BRANCH:
			break;
		case GROUP:
			break;
		default:
			break;
		}
		return null;
	}

	@Override
	public int setAdministrativeLevel(AdministrativeLevel current) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addAdministrativeLevel(AdministrativeLevel current) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delAdministrativeLevel(EnumAdministrativeLevel type, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
