package geektext;

//import libraries
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

// BookRepository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	//Find by Publisher for discounting
    // Use 'publisher_id' as the field name in the query method, might be able to remove later, added to help with some query issues
	@Query("SELECT b FROM Book b WHERE b.publisher_id = :publisher_id")
    List<Book> findByPublisher_id(int publisher_id);
	
	// Find by Genre for genre sorting
	List<Book> findByGenre(String genre);
	// Find by Genre for genre sorting
	List<Book> findByIsbn(long isbn);
	// Find by Author
	@Query("SELECT b FROM Book b WHERE b.author_id = :author_id")
	List<Book> findByAuthor_id(int author_id);

	// Find top 10 books based on copies sold in descending order
	//  query to get the top 10 books sorted by copies_sold in descending order limit 10
    @Query("SELECT b FROM Book b ORDER BY b.copies_sold DESC LIMIT 10")
    List<Book> findTop10Books();
    }

