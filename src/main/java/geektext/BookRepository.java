package geektext;

//import libraries
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

// BookRepository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	// Find by Publisher for discounting
	@Query("SELECT b FROM Book b WHERE b.publisher_id = :publisher_id")
    public List<Book> findByPublisher_id(@Param("publisher_id") int publisher_id);
	
	// Find by Genre for genre sorting
	@Query("SELECT b FROM Book b WHERE b.genre = :genre")
	public List<Book> findByGenre(@Param("genre") String genre);
	
	// Find by Author
	@Query("SELECT b FROM Book b WHERE b.author_id = :author_id")
	public List<Book> findByAuthor_id(@Param("author_id") int author_id);
	
	// Find by rating to return books equal to and greater than given rating in descending order
	@Query("SELECT b FROM Book b WHERE b.rating >= :rating ORDER BY b.rating DESC")   
	public List<Book> findByRatingGreaterThanEqual(@Param("rating") double rating);
	
	// Find top 10 books based on copies sold in descending order
    @Query("SELECT b FROM Book b ORDER BY b.copies_sold DESC LIMIT 10")
    public List<Book> findTop10Books();
    
}