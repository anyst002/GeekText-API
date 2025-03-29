package geektext;

public class BookNotFoundInWishlistException extends RuntimeException{
	public BookNotFoundInWishlistException(Long isbn, Integer wishlistId, String wishlistName) {
		super("Could not find book with isbn " + isbn + " in wishlist with id " + wishlistId + " and name " + wishlistName);
	}

}
