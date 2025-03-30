package geektext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query(value = "SELECT * FROM cart_item WHERE shopping_cart_id = :cartId AND book_isbn = :isbn LIMIT 1", nativeQuery = true)
    CartItem findItem(@Param("cartId") Long cartId, @Param("isbn") Long isbn);
}
