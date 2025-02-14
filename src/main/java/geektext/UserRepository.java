package geektext;

//import java.util.List;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
//	public static final String FIND_USERNAMES = "SELECT id, username FROM user";
//
//	@Query(value = FIND_USERNAMES, nativeQuery = true)
//	public List<Object[]> findUsernames();
//  queries a subset but doesn't format it properly, probably shouldn't use this
}