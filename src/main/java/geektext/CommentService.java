package geektext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        if (comment.getCommentText() == null || comment.getCommentText().trim().isEmpty()) {
            throw new IllegalArgumentException("Comment cannot be empty.");
        }
        comment.setCommentText(comment.getCommentText().trim());
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByBook(Long bookId) {
        return commentRepository.findByBookId(bookId);
    }
}

 