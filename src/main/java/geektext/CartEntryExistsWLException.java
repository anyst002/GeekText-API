package geektext;

public class CartEntryExistsWLException extends RuntimeException{
	public CartEntryExistsWLException(Long isbn, User u, Integer wId) {
		super("Cannot add book to shopping cart removing from wishlist with id " + wId + ": "
				+ "Shopping cart for user with name " + u.getFullName() + " and id " + u.getId() + 
				" already contains book with isbn " + isbn);
	}
}
