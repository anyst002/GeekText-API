package geektext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
  
    @Autowired 
    private ShoppingCartRepository shoppingCartRepository;

    // Retrieve the subtotal of a user's shopping cart
    @GetMapping("/{userId}/subtotal")
    public double getSubtotal(@PathVariable Integer userId) {
        return shoppingCartService.calculateSubtotal(userId);
    }

    // Add a book to the user's shopping cart
    @PostMapping("/add")
    public void addBookToCart(@RequestParam Integer userId, @RequestParam Long isbn) {
        shoppingCartService.addBookToCart(userId, isbn);
    }

    // View all the books in the user's shopping cart
    @GetMapping("/{userId}")
    public List<Book> getBooksInCart(@PathVariable Integer userId) {
        return shoppingCartRepository.findByUserId(userId)
                .map(ShoppingCart::getBooks)
                .orElse(Collections.emptyList()); // Return an empty list if the cart does not exist
    }

    // Remove a book from the user's shopping cart
    @DeleteMapping("/remove")
    public void removeBookFromCart(@RequestParam Integer userId, @RequestParam Long isbn) {
        shoppingCartService.removeBookFromCart(userId, isbn);
    }
}
