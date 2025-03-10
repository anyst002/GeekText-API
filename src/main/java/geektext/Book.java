package geektext;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Comment> comments;

	public double getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public double setPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

    // Getters and Setters
}