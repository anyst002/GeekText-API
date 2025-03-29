package geektext;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String commentText;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Constructors
    public Comment() {}

    public Comment(Long bookId, Long userId, String commentText) {
        this.bookId = bookId;
        this.userId = userId;
        this.commentText = commentText;
    }

    // Auto-set the timestamp before persisting to DB
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public Long getBookId() { return bookId; }
    public Long getUserId() { return userId; }
    public String getCommentText() { return commentText; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // ✅ Add this setter to allow modification
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
