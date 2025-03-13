package geektext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private BookRepository bookRepository;

    // Add Item to Cart
    public void addItemToCart(Integer userId, Long isbn, int quantity) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);

        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart(userId);
            shoppingCartRepository.save(shoppingCart);
        }

        if (shoppingCart.getItems() == null) {
            shoppingCart.setItems(new ArrayList<>()); // Fix for NullPointerException
        }

        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Book not found with ISBN: " + isbn));

        CartItem cartItem = new CartItem(book, quantity);
        cartItem.setShoppingCart(shoppingCart);

        shoppingCart.getItems().add(cartItem);
        shoppingCartRepository.save(shoppingCart);
    }

    // View Cart Items
    public List<CartItem> getCartItems(Integer userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        if (shoppingCart == null || shoppingCart.getItems() == null) {
            return new ArrayList<>();
        }
        return shoppingCart.getItems();
    }

    // Remove Item from Cart
    public void removeItemFromCart(Integer userId, Long isbn) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);

        if (shoppingCart != null && shoppingCart.getItems() != null) {
            shoppingCart.getItems().removeIf(cartItem -> cartItem.getBook().getIsbn() == isbn);
            shoppingCartRepository.save(shoppingCart);
        }
    }

    // Calculate Subtotal
    public double calculateSubtotal(Integer userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        if (shoppingCart == null || shoppingCart.getItems() == null) {
            return 0.0;
        }

        double subtotal = 0.0;
        for (CartItem cartItem : shoppingCart.getItems()) {
            subtotal += cartItem.getQuantity() * cartItem.getBook().getPrice();
        }
        return subtotal;
    }
}
