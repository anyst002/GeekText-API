package geektext;

class TooManyWishlistsException extends RuntimeException {
  public TooManyWishlistsException(Integer userId) {
    super("Cannot create wishlist: user with id=" + userId + " has maximum number of wishlists (3).");
  }
}