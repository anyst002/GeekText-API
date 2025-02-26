package geektext;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Wishlist {
	@Id
	@GeneratedValue
	private Integer id;
	
	// this is how to deal with foreign keys.
	private @ManyToOne
	@JoinColumn(name = "user_id") 
	User user;
	
	private String wishlistName;
	public Wishlist(String name, User u) {
		user = u;
		wishlistName = name;
	}
	// default constructor needed bc i got an error for not having it.
	public Wishlist() {
		
	}

	
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
