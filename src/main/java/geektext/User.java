package geektext;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "user", uniqueConstraints = 
@UniqueConstraint(columnNames = {"id", "username"}))
public class User {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Nonnull @Id Integer id;
	private @Nonnull String username;
	private @Nonnull String userPassword;
	private String fullName;
	private String emailAddress;
	private String homeAddress;

	//======================
	//=== Getter methods ===
	//======================
	
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
	
	//======================
	//=== Setter methods ===
	//======================
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	// need this @OneToMany annotation because of foreign key & entity relationship stuff.
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore // omits wishlists from json output.
	private Set<Wishlist> wishlists = new HashSet<>();     
	public Set<Wishlist> getWishlists() {
		return wishlists;
	}
}
