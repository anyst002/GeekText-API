package geektext;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Book book;

    private int quantity;

    @ManyToOne
    @JsonBackReference // This prevents circular reference
    private ShoppingCart shoppingCart;

    public CartItem() {}

    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
