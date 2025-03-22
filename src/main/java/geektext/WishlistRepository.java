package geektext;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// i just remember changing something to jpa repository & it worked again
interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

	@Query(nativeQuery=true, value="SELECT book_id FROM cart WHERE user_id = :input")
	public Set<Long> findBookIdInCart(@Param("input") Integer uId);
}