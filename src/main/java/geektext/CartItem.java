package geektext;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "book_isbn", referencedColumnName = "isbn", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore 
    private ShoppingCart shoppingCart;

    public CartItem() {}

    public CartItem(ShoppingCart shoppingCart, Book book, int quantity) {
        this.shoppingCart = shoppingCart;
        this.book = book;
        this.quantity = quantity;
    }

    public Long getId() { return id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public ShoppingCart getShoppingCart() { return shoppingCart; }
    public void setShoppingCart(ShoppingCart shoppingCart) { this.shoppingCart = shoppingCart; }
}
