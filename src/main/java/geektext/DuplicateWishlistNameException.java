package geektext;

public class DuplicateWishlistNameException extends RuntimeException{
	public DuplicateWishlistNameException(String name, Integer userId) {
		super("There is an existing wishlist with name " + name + " for user with id " + userId);
	}
}
