package geektext;

//import libraries
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

// BookRepository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	// Find by Publisher for discounting
	@Query("SELECT b FROM Book b WHERE b.publisher_id = :publisher_id")
    List<Book> findByPublisher_id(int publisher_id);
	
	// Find by Genre for genre sorting
	List<Book> findByGenre(String genre);
	
	// Find by rating to return books equal to and greater than given rating in descending order
	 @Query("SELECT b FROM Book b WHERE b.rating >= :rating ORDER BY b.rating DESC")   
	 List<Book> findByRatingGreaterThanEqual(double rating);
	
	
	
	// Find top 10 books based on copies sold in descending order
    @Query("SELECT b FROM Book b ORDER BY b.copies_sold DESC LIMIT 10")
    List<Book> findTop10Books();
    
    }

