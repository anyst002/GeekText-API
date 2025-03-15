package geektext;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	public ListsId() {
		
	}
	public ListsId(Wishlist w, Book b, User u) {
		wishlist = w;
		book = b;
		user = u;
	}
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(!(o instanceof ListsId)) {
			return false;
		}
		ListsId i = (ListsId) o;
		if(!i.getBook().equals(this.getBook())) {
			return false;
		}
		if(!i.getUser().equals(this.getUser())) {
			return false;
		}
		if(!i.getWishlist().equals(this.getWishlist())) {
			return false;
		}
		return true;
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
	public Lists(ListsId id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "List{" + getUser() + ", " + getBook() + ", " + getWishlist() + "}";
	}
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		if(!(o instanceof Lists)) {
			return false;
		}
		Lists L = (Lists)o;
		ListsId oId = new ListsId(L.getWishlist(), L.getBook(), L.getUser());
		if(!oId.equals(id)) {
			return false;
		}
		return true;
	}

}
