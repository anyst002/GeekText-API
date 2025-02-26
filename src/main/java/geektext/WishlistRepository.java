package geektext;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface WishlistRepository extends CrudRepository<Wishlist, Integer> {
//	public static final String FIND_USERNAMES = "SELECT id, username FROM user";
//
//	@Query(value = FIND_USERNAMES, nativeQuery = true)
//	public List<Object[]> findUsernames();
//  queries a subset but doesn't format it properly, probably shouldn't use this
}