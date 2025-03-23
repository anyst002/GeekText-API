package geektext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookRepository bookRepository;

    public void addItemToCart(Integer userId, Long bookIsbn, int quantity) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart(userId);
            shoppingCartRepository.save(shoppingCart);
        }

        Book book = bookRepository.findById(bookIsbn)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        CartItem existingCartItem = cartItemRepository.findItem(shoppingCart.getId(), bookIsbn);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem newCartItem = new CartItem(shoppingCart, book, quantity);
            cartItemRepository.save(newCartItem);
        }
    }

    public List<CartItem> viewCart(Integer userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        return shoppingCart != null ? shoppingCart.getItems() : List.of();
    }

    public void removeItemFromCart(Integer userId, Long bookIsbn) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        if (shoppingCart == null) throw new RuntimeException("Shopping cart not found");

        CartItem cartItem = cartItemRepository.findItem(shoppingCart.getId(), bookIsbn);
        if (cartItem != null) {
            cartItemRepository.deleteById(cartItem.getId());
            System.out.println("Cart item deleted.");
        } else {
            throw new RuntimeException("Cart item not found for book ISBN: " + bookIsbn);
        }
    }

    public double calculateSubtotal(Integer userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        if (shoppingCart == null || shoppingCart.getItems() == null) return 0.0;

        double subtotal = shoppingCart.getItems().stream()
                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                .sum();

        return subtotal;
    }
}
