package geektext;

//import libraries
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

// BookRepository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Use 'publisher_id' as the field name in the query method, might be able to remove later, added to help with some issues
	@Query("SELECT b FROM Book b WHERE b.publisher_id = :publisher_id")
    List<Book> findByPublisher_id(int publisher_id);
	List<Book> findByGenre(String genre);
}
