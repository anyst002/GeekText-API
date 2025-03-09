package geektext;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// i just remember changing something to jpa repository n it worked again
interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

	
	/* todo
	 * 	// this is the query i tested and it worked in mysql workbench
	// SELECT * FROM geektext.book WHERE isbn IN(SELECT book_id FROM geektext.lists WHERE wishlist_id = [insert a wishlist id]);
	@Query(nativeQuery=true, value="SELECT * FROM book WHERE isbn IN(SELECT book_id FROM lists WHERE wishlist_id = :wId)")

	public Set<BookEntity> getBooksByWishlistId(@Param("wId") Integer wishlistId);
	
	@Query(nativeQuery = true, value = "SELECT wishlist_name FROM wishlist WHERE id = :wId")
	public Set<Wishlist> getWishlistsById(@Param("wId") Integer wishlistId);
	*/

}