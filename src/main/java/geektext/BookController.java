package geektext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/book")
public class BookController {

    @Autowired
    private BookServicer bookServicer;

    @PatchMapping("/discount")
    public ResponseEntity<String> applyDiscount(
            @RequestParam("discountPercent") double discountPercent,
            @RequestParam("publisherId") int publisherId) {

        // Validate the parameters
        if (discountPercent <= 0 || discountPercent > 100) {
            return ResponseEntity.badRequest().body("Invalid discount percentage. It should be between 1 and 100.");
        }

        System.out.println("Applying discount: " + discountPercent + "% to publisherId: " + publisherId);

        try {
            // Call the service to apply the discount
            bookServicer.applyDiscount(discountPercent, publisherId);
            return ResponseEntity.noContent().build();  // Return HTTP 204 (No Content) for success
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while applying the discount.");
        }
    }}
