package geektext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    // Add Item to Cart
    @PostMapping("/add")
    public ResponseEntity<String> addItemToCart(@RequestParam Integer userId, @RequestParam Long isbn, @RequestParam int quantity) {
        try {
            shoppingCartService.addItemToCart(userId, isbn, quantity);
            return ResponseEntity.ok("Item added to cart");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // View Cart
    @GetMapping("/view")
    public ResponseEntity<List<CartItem>> viewCart(@RequestParam Integer userId) {
        try {
            List<CartItem> cartItems = shoppingCartService.getCartItems(userId);
            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    // Remove Item from Cart
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeItemFromCart(@RequestParam Integer userId, @RequestParam Long isbn) {
        try {
            shoppingCartService.removeItemFromCart(userId, isbn);
            return ResponseEntity.ok("Item removed from cart");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // Get Subtotal
    @GetMapping("/subtotal")
    public ResponseEntity<Double> getSubtotal(@RequestParam Integer userId) {
        try {
            double subtotal = shoppingCartService.calculateSubtotal(userId);
            return ResponseEntity.ok(subtotal);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
