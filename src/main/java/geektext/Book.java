package geektext;

// import libraries
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "book")
public class Book {
	//book class variables
	//primary key
	@Id
	private long isbn;
	@Column(name = "publisher_id") 
	private int publisher_id;
	private String title;
	private String book_description;
	private double price;
	@Column(name = "genre") 
	private String genre;
	private int year_published;
	private int copies_sold;
	private double rating;
	
	 @ManyToOne
	 @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
	 private Author author;

	// Getters and Setters
	public long getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	

	public void setPrice(double newPrice) {
		price = newPrice;
	}

	public double getPrice() {
		return price;
	}

	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	}

	public int getPublisher_id() { 
		return publisher_id;
	}

	public String getBook_description() {
		return book_description;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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


=======
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

// Book Entity
@Entity
@Table(name = "book")
public class Book {
	//book class variables
	
	//primary key
	@Id
	@GeneratedValue
	private long isbn;
	private int author_id;
	@Column(name = "publisher_id") 
	private int publisher_id;
	private String title;
	private String book_description;
	private double price;
	@Column(name = "genre") 
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


	public double getAvg_rating() {
		return rating;
	}

	public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


	

}
