package geektext;

//import libraries
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

// Indicates class is a service
@Service

//Book servicer class
public class BookServicer {

    @Autowired
    private BookRepository bookRepository; // Create bookRepository
    
    // added this function to work with my listsservice file
    public Book getBookByIsbn(Long isbn) {
    	return bookRepository.findById(isbn).orElseThrow( () -> new BookNotFoundException(isbn));
    }

    // Retrieve books by genre
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }
    public BookRepository getBookRepository() {
    	return bookRepository;
    }

    // AppyDiscount Method
    public void applyDiscount(double discountPercent, int publisher_id) {
        //method to find books by publisher_id
        List<Book> books = bookRepository.findByPublisher_id(publisher_id);

        // Loop through each book, apply the discount, then save
        for (Book book : books) {
            double newPrice = book.getPrice() * (1 - discountPercent / 100);
            book.setPrice(newPrice);
            bookRepository.save(book);
        }
    }
    
    // Retrieve top 10 best-selling books
    public List<Book> getTop10BestSellers() {
        return bookRepository.findTop10Books();
    }

}





