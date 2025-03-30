
package geektext;

// import libraries
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

}
=======
package geektext;

// import libraries
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
	private double rating;

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
	public double getRating() {
		return rating;
	}

}

