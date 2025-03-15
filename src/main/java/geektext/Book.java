package geektext;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

// import libraries
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
	//book class variables
	//primary key
	@Id
	@GeneratedValue
	private long isbn;
	private int author_id;
	@Column(name = "publisher_id") //might be able to remove later
	private int publisher_id;
	private String title;
	private String book_description;
	private double price;
	@Column(name = "genre") //might be able to remove later
	private String genre;
	private int year_published;
	private int copies_sold;
	private double avg_rating;
	private 
	@OneToMany(mappedBy = "id.book", fetch = FetchType.LAZY)
		Set<Lists> L;
	// Getters and Setters
	// Isbn getter
	public long getIsbn() {
		return isbn;
	}

	//Title getter
	public String getTitle() {
		return title;
	}

	// price setter
	public void setPrice(double newPrice) {
		price = newPrice;
	}

	// Price getter
	public double getPrice() {
		return price;
	}

	//Author_id getter
	public int getAuthor_id() {
		return author_id;
	}

	//Publisher_id setter
	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	}

	// Publisher_id getter
	public int getPublisher_id() { 
		return publisher_id;
	}

	// book_description getter
	public String getBook_description() {
		return book_description;
	}

	// Genre setter
	public void setGenre(String genre) {
		this.genre = genre;
	}

	// Genre getter
	public String getGenre() {
		return genre;
	}

	// Year_published getter
	public int getYear_published() {
		return year_published;
	}

	// Copies_sold getter
	public int getCopies_sold() {
		return copies_sold;
	}

	// Avg_rating getter
	public double getAvg_rating() {
		return avg_rating;
	}
	@Override
	public boolean equals(Object o) {
	       // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Book)) {
            return false;
        }
        Book b = (Book) o;
        if(b.isbn != this.isbn) {
        	return false;
        }
        return true; // isbn is a unique key.
        
	}
	
	@JsonIgnore
	public Set<Lists> getLists() {
		return L;
	}

}