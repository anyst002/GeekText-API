package geektext;


//import libraries
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class BookServicer {

    @Autowired
    private BookRepository bookRepository; 

    
 // Retrieve books by genre
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
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
    
    // Retrieve books by genre
    public List<Book> getBooksByIsbn(long isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    
 // Retrieve books by genre
    public List<Book> getBooksByAuthorid(int author_id) {
        return bookRepository.findByAuthorid(author_id);
    }
    
   // create a new book
   public Book createBook(Book book){
	   return bookRepository.save(book);
	   
   }
   


}







