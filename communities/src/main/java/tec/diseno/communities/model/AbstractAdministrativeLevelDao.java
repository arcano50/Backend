package tec.diseno.communities.model;

import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import tec.diseno.communities.helper.CSVHelper;

@Repository
public class AbstractAdministrativeLevelDao extends JdbcDaoSupport  implements InterfaceAdministrativeLevelBuilder {
	private String sql;
	
	@Autowired 
	DataSource dataSource;
	
	public ArrayList<AbstractAdministrativeLevel> BuildBody() throws SQLException {
		ArrayList<AbstractAdministrativeLevel> coordinationCollection =
				getChildren(null);

		for(int c = 0; c < coordinationCollection.size(); c++) {
			Coordination coordination = (Coordination)coordinationCollection.get(c);
			ArrayList<AbstractAdministrativeLevel> zoneCollection =
					getChildren(coordination);
			coordination.setChildrenCollection(zoneCollection);
			
			for(int z = 0; z < zoneCollection.size(); z++) {
				AdministrativeLevel zone = (AdministrativeLevel)zoneCollection.get(z);
				ArrayList<AbstractAdministrativeLevel> branchCollection =
						getChildren(zone);
				zone.setChildrenCollection(branchCollection);
				
				for(int b = 0; b < branchCollection.size(); b++) {
					AdministrativeLevel branch = (AdministrativeLevel)branchCollection.get(b);
					ArrayList<AbstractAdministrativeLevel> groupCollection =
							getChildren(branch);
					for(int g = 0; g < groupCollection.size(); g++) {
						Group group = (Group)groupCollection.get(g);
						group.setMemberCollection(getMember(group));
					}
					
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
	
	public Member MemberRowMapper(ResultSet rs) throws SQLException {
		Member member = new Member();
		member.setId(rs.getInt("Id"));
		member.setCardId(rs.getString("cardId"));
		member.setName(rs.getString("name"));
		member.setLastname(rs.getString("lastname"));
		member.setCountry(rs.getString("country"));
		member.setState(rs.getString("state"));
		member.setCity(rs.getString("city"));
		member.setAddress(rs.getString("address"));
		return member;
	}

	@Override
	public ArrayList<Member> getMember(AbstractAdministrativeLevel current) {
		List<Member> result = null;
		
		EnumAdministrativeLevel type = current.getType();
		int parent = current.getId();
		switch(type) {
		case COORDINATION:
			sql = "SELECT * FROM sp_get_user_by_coordination_full(?)";
			result =  getJdbcTemplate().query(sql, new Object[] { parent },
				new RowMapper<Member>() {
			        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			            return MemberRowMapper(rs);
		        }
		    });
			break;
		case ZONE:
			sql = "SELECT * FROM sp_get_user_by_zone_full(?, ?)";
			result =  getJdbcTemplate().query(sql, new Object[] {0, parent},
				new RowMapper<Member>() {
			        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	 return MemberRowMapper(rs);
		        }
		    });
			break;
		case BRANCH:
			sql = "SELECT * FROM sp_get_user_by_branch_full(?, ?)";
			result =  getJdbcTemplate().query(sql, new Object[] {0, parent},
				new RowMapper<Member>() {
			        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	 return MemberRowMapper(rs);
		        }
		    });
			break;
		case GROUP:
			sql = "SELECT * FROM sp_get_user_by_group_full(?, ?)";
			result =  getJdbcTemplate().query(sql, new Object[] {0, parent},
				new RowMapper<Member>() {
			        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	 return MemberRowMapper(rs);
		        }
		    });
			break;
		default:
			break;
		}
		return new ArrayList<Member>(result);
	}

	@Override
	public int setMember(int current, int member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addMember(Member member) {
		List<Integer> id = new ArrayList<Integer>();
		if ( member.getId() == 0) {
			sql = "SELECT * FROM sp_add_user(?, ?, ?)";
			id = getJdbcTemplate().query(sql, new Object[] {member.getCardId(), member.getName(),
					member.getLastname()}, new RowMapper<Integer>() {
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
							// member.setId(rs.getInt("result"));
							return 0;
						}
			});
			member.setId(id.get(0));
		}
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
	public ArrayList<AbstractAdministrativeLevel> getChildren(AbstractAdministrativeLevel current) {
		List<AbstractAdministrativeLevel> result = null;
		EnumAdministrativeLevel type = current == null ? EnumAdministrativeLevel.ROOT : current.getType();
		int parent = current == null ? 0 : current.getId();
		switch(type) {
		case ROOT:
			sql = "SELECT * FROM sp_get_coordination_full(?)";
			result =  getJdbcTemplate().query(sql, new Object[] { parent },
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
	public int addChildren(int parent, AbstractAdministrativeLevel child) throws SQLException {
		List<Integer> result = new ArrayList<Integer>();
		EnumAdministrativeLevel type = child.getType();
		switch(type) {
		case COORDINATION:
			Coordination coordination = (Coordination)child;
			sql = "SELECT * FROM sp_add_coordination(?, ?, ?, ?, ?)";
			result =  getJdbcTemplate().query(sql, new Object[] { coordination.getLegalId(), coordination.getName(), coordination.getWebsite(), "", true },
					new RowMapper<Integer>() {
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
							return rs.getInt("sp_add_coordination");
						}
					}
			);
			break;
		case ZONE:
			sql = "SELECT * FROM sp_add_zone(?, ?, ?)";
			result =  getJdbcTemplate().query(sql, new Object[] { parent, child.getName(), true },
					new RowMapper<Integer>() {
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
							return rs.getInt("sp_add_zone");
						}
					}
			);
			break;
		case BRANCH:
			sql = "SELECT * FROM sp_add_branch(?, ?, ?)";
			result =  getJdbcTemplate().query(sql, new Object[] { parent, child.getName(), true },
					new RowMapper<Integer>() {
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
							return rs.getInt("sp_add_branch");
						}
					}
			);
			break;
		case GROUP:
			Group group = (Group)child;
			sql = "SELECT * FROM sp_add_group(?, ?, ?, ?)";
			result =  getJdbcTemplate().query(sql, new Object[] { parent, group.getName(), group.getNumber(), true },
					new RowMapper<Integer>() {
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
							return rs.getInt("sp_add_group");
						}
					}
			);
			break;
		default:
			break;
		}
		return result.get(0);
	}
	
	@Override
	public int setBranchChildren(int current, AbstractAdministrativeLevel children) {
		List<Integer> result = new ArrayList<Integer>();
		sql = "SELECT * FROM sp_set_branch(?, ?, ?, ?)";
		result =  getJdbcTemplate().query(sql, new Object[] { children.getId(), current, children.getName(), children.isEnable() },
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt(0);
					}
				}
		);
		return result.get(0);
	}

	@Override
	public int setGroupChildren(int current, AbstractAdministrativeLevel children) {
		List<Integer> result = new ArrayList<Integer>();
		Group group = (Group)children;
		sql = "SELECT * FROM sp_set_group(?, ?, ?, ?, ?)";
		result =  getJdbcTemplate().query(sql, new Object[] { group.getId(), current, group.getName(), group.getNumber(), group.isEnable() },
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt(0);
					}
				}
		);
		return result.get(0);
	}

	@Override
	public int setZoneChildren(int current, AbstractAdministrativeLevel children) {
		List<Integer> result = new ArrayList<Integer>();
		sql = "SELECT * FROM sp_add_zone(?, ?, ?)";
		result =  getJdbcTemplate().query(sql, new Object[] { children.getId(), current, children.getName(), children.isEnable() },
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt(0);
					}
				}
		);
		return result.get(0);
	}
	
	@Override
	public int setCoordinationChildren(AbstractAdministrativeLevel children) {
		List<Integer> result = new ArrayList<Integer>();
		Coordination coordination = (Coordination)children;
		sql = "SELECT * FROM sp_set_coordination(?, ?, ?, ?, ?, ?)";
		result =  getJdbcTemplate().query(sql, new Object[] { coordination.getId(), coordination.getLegalId(), coordination.getName(), coordination.getWebsite(), 0, coordination.isEnable() },
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt(0);
					}
				}
		);
		return result.get(0);
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
	public int delAdministrativeLevel(AbstractAdministrativeLevel type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Member getUser(int id) {
		List<Member> result = null;
		sql = "SELECT * FROM sp_get_user_full(?)";
		result =  getJdbcTemplate().query(sql, new Object[] { id },
			new RowMapper<Member>() {
		        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	Member member = new Member();
		        	member.setId(rs.getInt("Id"));
		        	member.setName(rs.getString("name"));
		        	member.setLastname(rs.getString("lastname"));
		        	member.setCountry(rs.getString("country"));
		        	member.setState(rs.getString("state"));
		        	member.setCity(rs.getString("city"));
		        	member.setAddress(rs.getString("address"));
		            return MemberRowMapper(rs);
	        }
	    });
		return result.get(0);
	}

	@Override
	public int setBranchMember(int current, int user, int branch, boolean state) {
		List<Integer> result = new ArrayList<Integer>();
		sql = "SELECT * FROM sp_set_branch_member(?, ?, ?, ?)";
		result =  getJdbcTemplate().query(sql, new Object[] { current, user, branch, state },
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt("sp_set_branch_member");
					}
				}
		);
		return result.get(0);
	}

	@Override
	public int setGroupMember(int current, int user, int branch, boolean state) {
		List<Integer> result = new ArrayList<Integer>();
		sql = "SELECT * FROM sp_set_group_member(?, ?, ?, ?)";
		result =  getJdbcTemplate().query(sql, new Object[] { current, user, branch, state },
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt(0);
					}
				}
		);
		return result.get(0);
	}

	@Override
	public int setZoneMember(int current, int user, int branch, boolean state) {
		List<Integer> result = new ArrayList<Integer>();
		sql = "SELECT * FROM sp_set_zone_member(?, ?, ?, ?)";
		result =  getJdbcTemplate().query(sql, new Object[] { current, user, branch, state },
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt(0);
					}
				}
		);
		return result.get(0);
	}

	@Override
	public int setBranchLeader(int current, int user, int branch, boolean state) {
		List<Integer> result = new ArrayList<Integer>();
		sql = "SELECT * FROM sp_set_branch_leader(?, ?, ?, ?)";
		result =  getJdbcTemplate().query(sql, new Object[] { current, user, branch, state },
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt(0);
					}
				}
		);
		return result.get(0);
	}

	@Override
	public int setGroupLeader(int current, int user, int branch, boolean temporal, boolean state) {
		List<Integer> result = new ArrayList<Integer>();
		sql = "SELECT * FROM sp_set_group_leader(?, ?, ?, ?)";
		result =  getJdbcTemplate().query(sql, new Object[] { current, user, branch, temporal, state },
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt(0);
					}
				}
		);
		return result.get(0);
	}

	@Override
	public int setZoneLeader(int current, int user, int branch, boolean state) {
		List<Integer> result = new ArrayList<Integer>();
		sql = "SELECT * FROM sp_set_zone_leader(?, ?, ?, ?)";
		result =  getJdbcTemplate().query(sql, new Object[] { current, user, branch, state },
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt(0);
					}
				}
		);
		return result.get(0);
	}

	@Override
	public int addBranchMember(Member member) {
		List<Integer> id = new ArrayList<Integer>();
		sql = "SELECT * FROM sp_add_branch_member(?, ?, ?)";
		id = getJdbcTemplate().query(sql, new Object[] {member.getId(), member.getParent(), true}, new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt("sp_add_branch_member");
					}
		});
		return id.get(0);
	}

	@Override
	public int addGroupMember(Member member) {
		List<Integer> id = new ArrayList<Integer>();
		sql = "SELECT * FROM sp_add_group_member(?, ?)";
		id = getJdbcTemplate().query(sql, new Object[] {member.getId(), member.getParent()}, new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt("sp_add_group_member");
					}
		});
		return id.get(0);
	}

	@Override
	public int addZoneMember(Member member) {
		List<Integer> id = new ArrayList<Integer>();
		sql = "SELECT * FROM sp_add_zone_member(?, ?, ?)";
		id = getJdbcTemplate().query(sql, new Object[] {member.getId(), member.getParent(), true}, new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt("sp_add_zone_member");
					}
		});
		return id.get(0);
	}

	@Override
	public int addBranchLeader(Member member) {
		List<Integer> id = new ArrayList<Integer>();
		sql = "SELECT * FROM sp_add_branch_leader(?, ?, ?)";
		id = getJdbcTemplate().query(sql, new Object[] {member.getId(), member.getParent(), true}, new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt("sp_add_zone_member");
					}
		});
		return id.get(0);
	}

	@Override
	public int addGroupLeader(Member member) {
		List<Integer> id = new ArrayList<Integer>();
		sql = "SELECT * FROM sp_add_branch_leader(?, ?, ?, ?)";
		id = getJdbcTemplate().query(sql, new Object[] {member.getId(), member.getParent(), member.isEnable(), true}, new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt("sp_add_zone_member");
					}
		});
		return id.get(0);
	}

	@Override
	public int addZoneLeader(Member member) {
		List<Integer> id = new ArrayList<Integer>();
		sql = "SELECT * FROM sp_add_zone_leader(?, ?, ?)";
		id = getJdbcTemplate().query(sql, new Object[] {member.getId(), member.getParent(), true}, new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt("sp_add_zone_leader");
					}
		});
		return id.get(0);
	}

}
