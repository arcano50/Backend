package tec.diseno.communities.model;

import java.util.Date;

public class Contribution {
	private Member member;
	private String subject;
	private String content;
	private String type;
	private Date date;
	
	public Contribution(Member member, String subject, String content, String type, Date date) {
		super();
		this.member = member;
		this.subject = subject;
		this.content = content;
		this.type = type;
		this.date = date;
	}
	
	public Contribution() {
		
	}

	public Member getMemeber() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
