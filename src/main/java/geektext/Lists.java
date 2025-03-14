package geektext;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

// have to do this for 
@Embeddable
class ListsId {
	private 
		@ManyToOne
	    @JoinColumn(name = "wishlist_id")
			Wishlist wishlist; 
	private
		@ManyToOne
		@JoinColumn(name = "book_id")
			Book book;
	private
		@ManyToOne
	    @JoinColumn(name = "user_id")
			User user;
	public User getUser() {
		return user;
	}
	public Book getBook() {
		return book;
	}
	public Wishlist getWishlist() {
		return wishlist;
	}
}

@Entity
@Table(name="lists")
public class Lists {	
	@EmbeddedId
	private ListsId id;
    @Transient
    public User getUser() {
        return id.getUser();
    }

    @Transient
    public Book getBook() {
        return id.getBook();
    }

    @Transient
    public Wishlist getWishlist() {
        return id.getWishlist();
    }    

	public Lists() {
		
	}


}
