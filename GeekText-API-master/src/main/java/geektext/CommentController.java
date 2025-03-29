package geektext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        try {
            Comment savedComment = commentService.addComment(comment);
            return ResponseEntity.status(201).body(savedComment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to add comment"));
        }
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<List<Comment>> getCommentsByBook(@PathVariable Long bookId) {
        List<Comment> comments = commentService.getCommentsByBook(bookId);
        return ResponseEntity.ok(comments);
    }
}
