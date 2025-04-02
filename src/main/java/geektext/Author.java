package geektext;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name = "author")

public class Author {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private String first_name;
	private String last_name;
	private int author_id;
	private String biography;
	private int publisher_id;
	
	
	public String getFirstName() {
		return first_name;
	}
	

	public String getLastName() {
		return last_name;
	}
	
	public int getAuthor_id() {
		return author_id;
	}
	
	
	public String getBiography(){
		return biography;
	}
	
	public int publisher_id(){
		return publisher_id;
	}
	
	public void setFirstName(String newFirstName) {
		first_name = newFirstName;
	}
	
	public void setLastName(String newLastName) {
		first_name = newLastName;
	}
	
	public void setAuthorId(int newAuthor_id) {
		author_id = newAuthor_id;
	}
	
	public void setBiography(String newBiography) {
		biography = newBiography;
	}
	
	public void setPublisher_id(int newPublisher_id) {
		publisher_id = newPublisher_id;
	}
	
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Book> books;

}//end class
