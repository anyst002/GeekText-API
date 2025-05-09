package geektext;

// import libraries
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Column(insertable=false, updatable=false)
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
	@Column(name = "avg_rating") 
	private double rating;
	
	@ManyToOne
	@JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
	@JsonIgnore
	private Author author;

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
		return author.getAuthorId();
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

    	public void setTitle(String newTitle) {
		title = newTitle;
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
}
