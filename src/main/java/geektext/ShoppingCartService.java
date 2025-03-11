package geektext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    // Calculate the subtotal of all books in the shopping cart
    public double calculateSubtotal(Integer userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Shopping cart not found for user"));

        return cart.getBooks().stream()
                .mapToDouble(Book::getPrice)
                .sum();
    }

    // Add a book to the user's shopping cart
    public void addBookToCart(Integer userId, long isbn) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        // Retrieve or create the user's shopping cart
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    ShoppingCart newCart = new ShoppingCart();
                    newCart.setUser(user);
                    newCart.setBooks(new ArrayList<>()); // Ensure books list is initialized
                    return shoppingCartRepository.save(newCart);
                });

        // Ensure books list is not null before adding
        if (cart.getBooks() == null) {
            cart.setBooks(new ArrayList<>());
        }

        cart.getBooks().add(book);
        shoppingCartRepository.save(cart);
    }
