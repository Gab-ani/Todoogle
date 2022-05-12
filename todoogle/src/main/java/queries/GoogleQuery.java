package queries;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Entity;

import application.User;

@javax.persistence.Entity
@Table(name="future_queries")
public class GoogleQuery {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String query;
	private String date;
	private String time;
	
	@ManyToOne
	private User user;
	
	public GoogleQuery() {
		
	}
	
	public GoogleQuery(String query, String date, String time) {
		this.query = query;
		this.date = date;
		this.time = time;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
