package security;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import application.User;

@Entity
public class EmailVerificationToken {
	private final static int EXPIRATION = 60 * 24;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String tokenValue;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	
    private Date expiryDate;
    
    public EmailVerificationToken() {
    	
    }
    
    public EmailVerificationToken(User user, String value) {
    	this.user = user;
    	this.tokenValue = value;
    	expiryDate = calculateExpiryDate();
    }
    
    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, EXPIRATION);
        return new Date(cal.getTime().getTime());
    }
    
	public String getToken() {
		return tokenValue;
	}

	public User getUser() {
		return user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setToken(String token) {
		this.tokenValue = token;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}
