package geektext;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "author")

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  
    private Integer id;

    private String first_name;
    private String last_name;
    
    @Column(columnDefinition = "TEXT")
    private String biography;

    private int publisher_id;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

    // Getters and Setters
    public Integer getAuthorId() {
        return id;
        
    }

    public void setAuthorId(int id) {
    	this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getPublisherId() {
        return publisher_id;
    }

    public void setPublisherId(int publisher_id) {
        this.publisher_id = publisher_id;
        
    }
    
    public List<Book> getBooks(){
    	return books;
    }
    
    public void setBooks(List<Book> books) {
    	this.books = books;
    }

	
}
