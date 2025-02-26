package geektext;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity // This tells Hibernate to make a table out of this class
public class User {
	@Id
	@GeneratedValue
	private Integer id;


	// need this @OneToMany annotation because of foreign key & entity relationship stuff.
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore // omits wishlists from json output.
	private Set<Wishlist> wishlists = new HashSet<>();     
	
	private String username;
	private String userPassword;
	private String fullName;
	private String emailAddress;
	private String homeAddress;

	public Set<Wishlist> getWishlists() {
		return wishlists;
	}
	
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