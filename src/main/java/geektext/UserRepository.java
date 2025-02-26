package geektext;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
	//Finds user id via username
	//TODO sql injection, nonexistent user
	@Query(nativeQuery=true, value="SELECT id FROM user WHERE username = :input")
	public Integer findUserId(@Param("input") String username);
}