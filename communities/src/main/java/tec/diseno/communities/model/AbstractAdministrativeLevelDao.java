package tec.diseno.communities.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class AbstractAdministrativeLevelDao extends JdbcDaoSupport  implements InterfaceAdministrativeLevelBuilder {
	private String sql;
	
	@Autowired 
	DataSource dataSource;
	
	public ArrayList<AbstractAdministrativeLevel> BuildBody() {
		ArrayList<AbstractAdministrativeLevel> coordinationCollection =
				getChildren(EnumAdministrativeLevel.ROOT, 0);

		for(int c = 0; c < coordinationCollection.size(); c++) {
			Coordination coordination = (Coordination)coordinationCollection.get(c);
			ArrayList<AbstractAdministrativeLevel> zoneCollection =
					getChildren(EnumAdministrativeLevel.COORDINATION, coordination.getId());
			coordination.setChildrenCollection(zoneCollection);
			
			for(int z = 0; z < zoneCollection.size(); z++) {
				AdministrativeLevel zone = (AdministrativeLevel)zoneCollection.get(z);
				ArrayList<AbstractAdministrativeLevel> branchCollection =
						getChildren(EnumAdministrativeLevel.ZONE, zone.getId());
				zone.setChildrenCollection(branchCollection);
				
				for(int b = 0; b < branchCollection.size(); b++) {
					AdministrativeLevel branch = (AdministrativeLevel)branchCollection.get(b);
					ArrayList<AbstractAdministrativeLevel> groupCollection =
							getChildren(EnumAdministrativeLevel.BRANCH, branch.getId());
					branch.setChildrenCollection(groupCollection);
				}
			}
		}
		return coordinationCollection;
	}
	
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
	public ArrayList<AbstractAdministrativeLevel> getChildren(EnumAdministrativeLevel type, int parent) {
		List<AbstractAdministrativeLevel> result = new ArrayList<AbstractAdministrativeLevel>();
		switch(type) {
		case ROOT:
			sql = "SELECT * FROM sp_get_coordination_full(?)";
			result =  getJdbcTemplate().query(sql, new Object[] {parent},
				new RowMapper<AbstractAdministrativeLevel>() {
			        public AbstractAdministrativeLevel mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	AdministrativeLevelBuilder builder = new AdministrativeLevelBuilder();
			        	builder.setType(EnumAdministrativeLevel.COORDINATION);
			        	builder.setId(rs.getInt("Id"));
			        	builder.setLegalId(rs.getString("legalId"));
			        	builder.setName(rs.getString("name"));
			        	builder.setWebsite(rs.getString("website"));
			        	builder.setCountry(rs.getString("country"));
			        	builder.setState(rs.getString("state"));
			        	builder.setCity(rs.getString("city"));
			        	builder.setAddress(rs.getString("address"));
			        	builder.setEnable(rs.getBoolean("enable"));
			            return builder.getProduct();
		        }
		    });
			break;
		case COORDINATION:
			sql = "SELECT * FROM sp_get_zone_full(?, ?)";
			result =  getJdbcTemplate().query(sql, new Object[] {0, parent},
				new RowMapper<AbstractAdministrativeLevel>() {
			        public AbstractAdministrativeLevel mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	AdministrativeLevelBuilder builder = new AdministrativeLevelBuilder();
			        	builder.setType(EnumAdministrativeLevel.ZONE);
			        	builder.setId(rs.getInt("Id"));
			        	builder.setName(rs.getString("name"));
			        	builder.setEnable(rs.getBoolean("enable"));
			            return builder.getProduct();
		        }
		    });
			break;
		case ZONE:
			sql = "SELECT * FROM sp_get_branch_full(?, ?)";
			result =  getJdbcTemplate().query(sql, new Object[] {0, parent},
				new RowMapper<AbstractAdministrativeLevel>() {
			        public AbstractAdministrativeLevel mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	AdministrativeLevelBuilder builder = new AdministrativeLevelBuilder();
			        	builder.setType(EnumAdministrativeLevel.BRANCH);
			        	builder.setId(rs.getInt("Id"));
			        	builder.setName(rs.getString("name"));
			        	builder.setEnable(rs.getBoolean("enable"));
			            return builder.getProduct();
		        }
		    });
			break;
		case BRANCH:
			sql = "SELECT * FROM sp_get_group_full(?, ?)";
			result =  getJdbcTemplate().query(sql, new Object[] {0, parent},
				new RowMapper<AbstractAdministrativeLevel>() {
			        public AbstractAdministrativeLevel mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	AdministrativeLevelBuilder builder = new AdministrativeLevelBuilder();
			        	builder.setType(EnumAdministrativeLevel.GROUP);
			        	builder.setId(rs.getInt("Id"));
			        	builder.setName(rs.getString("name"));
			        	builder.setNumber(rs.getInt("number"));
			        	builder.setEnable(rs.getBoolean("enable"));
			            return builder.getProduct();
		        }
		    });
			break;
		default:
			break;
		}
		return new ArrayList<AbstractAdministrativeLevel>(result);
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
		List<AdministrativeLevelBuilder> result = new ArrayList<AdministrativeLevelBuilder>();
		switch(type) {
		case COORDINATION:
			sql = "SELECT * FROM sp_get_coordination(?)";
			result =  getJdbcTemplate().query(sql, new Object[] {id},
				new RowMapper<AdministrativeLevelBuilder>() {
			        public AdministrativeLevelBuilder mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	AdministrativeLevelBuilder builder = new AdministrativeLevelBuilder();
			        	builder.setType(EnumAdministrativeLevel.COORDINATION);
			        	builder.setId(rs.getInt("Id"));
			        	builder.setLegalId(rs.getString("legalId"));
			        	builder.setName(rs.getString("name"));
			        	builder.setWebsite(rs.getString("website"));
			        	builder.setCountry(rs.getString("country"));
			        	builder.setState(rs.getString("state"));
			        	builder.setCity(rs.getString("city"));
			        	builder.setAddress(rs.getString("address"));
			        	builder.setEnable(rs.getBoolean("enable"));
			            return builder;
		        }
		    });
			break;
		case ZONE:
			sql = "SELECT * FROM sp_get_zone(?)";
			result =  getJdbcTemplate().query(sql, new Object[] {id},
				new RowMapper<AdministrativeLevelBuilder>() {
			        public AdministrativeLevelBuilder mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	AdministrativeLevelBuilder builder = new AdministrativeLevelBuilder();
			        	builder.setType(EnumAdministrativeLevel.ZONE);
			        	builder.setId(rs.getInt("Id"));
			        	builder.setName(rs.getString("name"));
			        	builder.setEnable(rs.getBoolean("enable"));
			            return builder;
		        }
		    });
			break;
		case BRANCH:
			sql = "SELECT * FROM sp_get_branch(?)";
			result =  getJdbcTemplate().query(sql, new Object[] {id},
				new RowMapper<AdministrativeLevelBuilder>() {
			        public AdministrativeLevelBuilder mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	AdministrativeLevelBuilder builder = new AdministrativeLevelBuilder();
			        	builder.setType(EnumAdministrativeLevel.ZONE);
			        	builder.setId(rs.getInt("Id"));
			        	builder.setName(rs.getString("name"));
			        	builder.setEnable(rs.getBoolean("enable"));
			            return builder;
		        }
		    });
			break;
		case GROUP:
			sql = "SELECT * FROM sp_get_group(?)";
			result =  getJdbcTemplate().query(sql, new Object[] {id},
				new RowMapper<AdministrativeLevelBuilder>() {
			        public AdministrativeLevelBuilder mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	AdministrativeLevelBuilder builder = new AdministrativeLevelBuilder();
			        	builder.setType(EnumAdministrativeLevel.GROUP);
			        	builder.setId(rs.getInt("Id"));
			        	builder.setName(rs.getString("name"));
			        	builder.setEnable(rs.getBoolean("enable"));
			            return builder;
		        }
		    });
			break;
		default:
			break;
		}
		return result.get(0).getProduct();
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
