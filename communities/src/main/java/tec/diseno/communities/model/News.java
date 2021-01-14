package tec.diseno.communities.model;

import java.util.Date;

public class News {
	private int id;
	private Member member;
	private String subject;
	private String message;
	private String to;
	private Date date;
	
	public News(int id, Member member, String subject, String message, String to, Date date) {
		this.id = id;
		this.member = member;
		this.subject = subject;
		this.message = message;
		this.to = to;
		this.date = date;
	}

	public News() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Member getMember() {
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
