package geektext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Use 'publisher_id' as the field name in the query method
	@Query("SELECT b FROM Book b WHERE b.publisher_id = :publisher_id")
    List<Book> findByPublisher_id(int publisher_id);
}
