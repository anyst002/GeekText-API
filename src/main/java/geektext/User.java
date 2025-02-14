package geektext;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
	@Id
	@GeneratedValue
	private Integer id;

	private String username;
	private String userPassword;
	private String fullName;
	private String emailAddress;
	private String homeAddress;

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public String getHomeAddress() {
		return homeAddress;
	}
}