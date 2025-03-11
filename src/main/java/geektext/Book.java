package geektext;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue
    private long isbn;
    private int author_id;
    
    @Column(name = "publisher_id")
    private int publisher_id;

    private String title;
    private String book_description;
    private double price;
    private String genre;
    private int year_published;
    private int copies_sold;
    private double avg_rating;

    // Getters and Setters
    public long getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }
    
    public int getPublisher_id() {  // Getter for publisher_id
        return publisher_id;
    }

    public String getBook_description() {
        return book_description;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear_published() {
        return year_published;
    }

    public int getCopies_sold() {
        return copies_sold;
    }

    public double getAvg_rating() {
        return avg_rating;
    }

    public void setPrice(double newPrice) {
        price = newPrice;
    }
}
