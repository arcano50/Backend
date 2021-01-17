package tec.diseno.communities.model;

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
public class NewsDao extends JdbcDaoSupport implements NewsBuilder{
	
	private String sql;
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	@Override
	public List<String> addNews(News news) {
		List<String> result = new ArrayList<String>();
		sql = "SELECT * FROM sp_add_news(?, ?, ?, ?, ?)";
		result =  getJdbcTemplate().query(sql, new Object[] { news.getMember().getId(), news.getTo(), news.getDate(), news.getSubject(), news.getMessage() },
				new RowMapper<String>() {
					public String mapRow(ResultSet rs, int rowNum) throws SQLException{
						String email = rs.getString("email");
						return email;
					}
				}
		);
		
		EventManager events = new EventManager("emails");
		for (int i = 0; i < result.size(); i++) {
			events.subscribe("emails", new EmailAlertListener(result.get(i)));
		}
		
		events.notify("emails");
		
		return result;
	}

	@Override
	public List<News> getNewsByUser(int id) {
		List<News> result = new ArrayList<News>();
		sql = "SELECT * FROM sp_get_news(?)";
		result =  getJdbcTemplate().query(sql, new Object[] { id },
				new RowMapper<News>() {
					public News mapRow(ResultSet rs, int rowNum) throws SQLException{
						News news = new News();
						Member member = new Member();
						member.setId(rs.getInt("IdUser"));
						member.setName(rs.getString("name"));
						news.setMember(member);
						news.setDate(rs.getDate("date"));
						news.setId(rs.getInt("Id"));
						news.setMessage(rs.getString("content"));
						news.setTo(rs.getString("to"));
						news.setSubject(rs.getString("subject"));
						return news;
					}
				}
		);
		return result;
	}
}
