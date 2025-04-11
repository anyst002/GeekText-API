package geektext;

// import libraries
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Rest Controller
@RestController
@RequestMapping(path="/book") // This maps the book path

// BookController Class
public class BookController {

    @Autowired
    private BookServicer bookServicer;

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

         System.out.println("Applying discount: " + discountPercent + "% to publisher_id: " + publisher_id);  

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
        
	 // Log the genre that is being passed in the request
        System.out.println("Received genre parameter: " + genre);

        List<Book> books = bookServicer.getBooksByGenre(genre);
        
        // To check if no books are in genre
        if (books.isEmpty()) {
            // If no books are found, log result and return 204 No Content
            System.out.println("No books found for genre: " + genre);  
            return ResponseEntity.noContent().build();
        }

        // If books are found, log result and return the list
         System.out.println("Books found: " + books); 
        return ResponseEntity.ok(books); // Return 200 OK & the list of books
    }
 
 // GetMapping for finding list of books by rating
 @GetMapping("/rating")
 
 //Method to return entity list of books given a requestParam rating
    public ResponseEntity<List<Book>> getBooksByRating(@RequestParam("rating") double rating) {
	 
        // Log the rating that is being passed in the request, for testing, can remove for final
        System.out.println("Received rating parameter: " + rating);

        List<Book> books = bookServicer.getBooksByRating(rating);
        
        // To check if no books are >= rating
        if (books.isEmpty()) {
        	
            // If no books are found, log result and return 204 No Content
              System.out.println("No books found for rating: " + rating);
            return ResponseEntity.noContent().build();
        }

        // If books are found, log result and return the list
        System.out.println("Books found: " + books); 
        return ResponseEntity.ok(books); // Return 200 OK & the list of books
    }
 
//GetMapping to retrieve the top 10 best-selling books
 @GetMapping("/topSellers")
 public List<Book> getTopSellers() {
     return bookServicer.getTop10BestSellers();
 }


 }
