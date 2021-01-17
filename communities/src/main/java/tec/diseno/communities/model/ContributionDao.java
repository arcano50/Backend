package tec.diseno.communities.model;

import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class ContributionDao extends JdbcDaoSupport implements ContributionBuilder {
	private String sql;
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Override
	public void addContribution(Contribution contribution) {
		List<Integer> result = new ArrayList<Integer>();
		sql = "SELECT * FROM sp_add_contribution(?, ?, ?, ?)";
		result =  getJdbcTemplate().query(sql, new Object[] { contribution.getMemeber().getId(), contribution.getType(), contribution.getSubject(), contribution.getContent() },
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getInt("sp_add_contribution");
					}
				}
		);
	}

	@Override
	public ByteArrayInputStream getContributions(String type) {
		// TODO Auto-generated method stub
		List<Contribution> result = new ArrayList<Contribution>();
		sql = "SELECT * FROM sp_get_contribution()";
		result =  getJdbcTemplate().query(sql, new Object[] { },
				new RowMapper<Contribution>() {
					public Contribution mapRow(ResultSet rs, int rowNum) throws SQLException{
						Contribution contribution = new Contribution();
						Member member = new Member();
						member.setId(rs.getInt("idUser"));
						member.setName(rs.getString("user"));
						contribution.setMember(member);
						contribution.setType(rs.getString("type"));
						contribution.setDate(rs.getDate("date"));
						contribution.setSubject(rs.getString("subject"));
						contribution.setContent(rs.getString("content"));
						return contribution;
					}
				}
		);
		
		DocumentStrategy documentStrategy;
		if(type == "JSON") {
			documentStrategy = new DocumentXML();
		} else {
			documentStrategy = new DocumentCSV();
		}
		
		ByteArrayInputStream in = documentStrategy.generateDocument(result);
		
	    return in;
	}
	
        public List<ContributionReport> getContributionReport(){
            List<ContributionReport> result = new ArrayList<>();
            sql = "SELECT * FROM sp_get_contribution_report()";
            result =  getJdbcTemplate().query(sql, new Object[] { },
				new RowMapper<ContributionReport>() {
					public ContributionReport mapRow(ResultSet rs, int rowNum) throws SQLException{
						ContributionReport contribution = new ContributionReport();
                                                contribution.setId(rs.getInt("type"));
                                                contribution.setName(rs.getString("name"));
                                                contribution.setValue(rs.getInt("amount"));
						return contribution;
					}
				}
		);
            return result;
        }
}
