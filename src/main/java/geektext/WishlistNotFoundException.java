package geektext;

public class WishlistNotFoundException extends RuntimeException {

	  public WishlistNotFoundException(Integer id) {
	    super("Could not find wishlist with id " + id);
	  }
}
