package geektext;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="wishlist")
public class Wishlist {
	@Id
	@GeneratedValue
	private Integer id;
	
	// this is how to deal with foreign keys.    
	private @ManyToOne
	@JoinColumn(name = "user_id") 
		User user;
	
	//private @OneToMany() BookEntity book;
	private String wishlistName;
	public Wishlist(String name, User u) {
		user = u;
		wishlistName = name;
	};
	public Wishlist() {
	}
	
	
	@Transient
	@ElementCollection
	public Set<BookEntity> books;
	public Integer getUserId() {
		return user.getId();
	}
	public Integer getId() {
		return id;
	}
	public String getWishlistName() {
		return wishlistName;
	}
	public String toString() {
		return "Wishlist{wishlist_id=" + id + ", wishlist_name=" + wishlistName + ", user_id=" + user.getId() + "}";
	}

}
