package geektext;

// import libraries
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/book") 
	

public class BookController {

    @Autowired
    private BookServicer bookServicer;
    
    
    @PostMapping
	//method for create book
    public ResponseEntity<Book> createBook(@RequestBody Book book){
    	Book savedBook = bookServicer.createBook(book);
    	return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

//Patch mapping for discount based on publisher    
    @PatchMapping("/discount")
    //Method to apply discount given requestParams
    public ResponseEntity<String> applyDiscount(
            @RequestParam("discountPercent") double discountPercent,
            @RequestParam("publisher_id") int publisher_id) {

        // Validate the parameters
        if (discountPercent <= 0 || discountPercent > 100) {
            return ResponseEntity.badRequest().body("Invalid discount percentage. The value should be between 1 and 100.");
        }

        System.out.println("Applying discount: " + discountPercent + "% to publisher_id: " + publisher_id);  // Can remove later, used for testing

        try {
            // Call the service to apply the discount based on publisher
            bookServicer.applyDiscount(discountPercent, publisher_id);
            return ResponseEntity.noContent().build();  // Return HTTP 204 (No Content) to show success
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error has occurred while applying the discount.");
        }
    }
   
    // GetMapping for finding list of books by genre
 @GetMapping("/genre")
 
 //Method to return entity list of books given a requestParam genre
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam("genre") String genre) {
        // Log the genre that is being passed in the request, for testing, can remove for final
        System.out.println("Received genre parameter: " + genre);

        List<Book> books = bookServicer.getBooksByGenre(genre);
        
        // To check if no books are in genre
        if (books.isEmpty()) {
            // If no books are found, log result and return 204 No Content
            System.out.println("No books found for genre: " + genre);  // Can remove later, used for testing
            return ResponseEntity.noContent().build();
        }

        // If books are found, log result and return the list
        System.out.println("Books found: " + books); // Can remove later, used for testing
        return ResponseEntity.ok(books); // Return 200 OK & the list of books
    }
 
//GetMapping to retrieve the top 10 best-selling books
 @GetMapping("/topSellers")
 public List<Book> getTopSellers() {
     return bookServicer.getTop10BestSellers();
 }
 
 @GetMapping("/isbn")

//Method to return entity list of books given a requestParam genre
  public ResponseEntity<List<Book>> getBooksByIsbn(@RequestParam("isbn") long isbn) {
      // Log the genre that is being passed in the request, for testing, can remove for final
      System.out.println("Received isbn parameter: " + isbn);

      List<Book> books = bookServicer.getBooksByIsbn(isbn);
      
      // To check if no books are in isbn
      if (books.isEmpty()) {
          // If no books are found, log result and return 204 No Content
          System.out.println("No books found for isbn: " + isbn);  // Can remove later, used for testing
          return ResponseEntity.noContent().build();
      }

      // If books are found, log result and return the list
      System.out.println("Books found: " + books); // Can remove later, used for testing
      return ResponseEntity.ok(books); // Return 200 OK & the list of books
  } 
 @GetMapping("/author")
 public ResponseEntity<List<Book>> getBooksByAuthor_id(@RequestParam("author_id") int author_id) {
     // Log the genre that is being passed in the request, for testing, can remove for final
     System.out.println("Received author parameter: " + author_id );

     List<Book> books = bookServicer.getBooksByAuthorid(author_id);
     
     // To check if no books are from author
     if (books.isEmpty()) {
         // If no books are found, log result and return 204 No Content
         System.out.println("No books found for author: " + author_id);  // Can remove later, used for testing
         return ResponseEntity.noContent().build();
     }

     // If books are found, log result and return the list
     System.out.println("Books found: " + books); // Can remove later, used for testing
     return ResponseEntity.ok(books); // Return 200 OK & the list of books
 } 
 

 
 
 }
