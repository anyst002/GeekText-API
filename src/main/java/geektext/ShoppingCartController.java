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

    @GetMapping("/view")
    public ResponseEntity<List<CartItem>> viewCart(@RequestParam Integer userId) {
        List<CartItem> items = shoppingCartService.viewCart(userId);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeItem(@RequestParam Integer userId, @RequestParam Long isbn) {
        try {
            shoppingCartService.removeItemFromCart(userId, isbn);
            return ResponseEntity.ok("Item removed from cart");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/subtotal")
    public ResponseEntity<String> getSubtotal(@RequestParam Integer userId) {
        double subtotal = shoppingCartService.calculateSubtotal(userId);
        String formatted = String.format("$%.2f", subtotal);
        return ResponseEntity.ok(formatted);
    }
}
