package geektext;

class DuplicateBookInWishlistException extends RuntimeException {

  public DuplicateBookInWishlistException(Long id, Integer wId, String wName) {
    super("Book with isbn " + id + " is already in wishlist with id " + wId + " and name " + wName);
  }

}