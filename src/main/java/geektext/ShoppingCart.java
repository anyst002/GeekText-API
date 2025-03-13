package geektext;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // This allows the items to be serialized without creating a loop
    private List<CartItem> items;

    @Column(name = "user_id")
    private Integer userId;

    public ShoppingCart() {}

    public ShoppingCart(Integer userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

