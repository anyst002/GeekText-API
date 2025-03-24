package geektext;

import geektext.Comment;
import geektext.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("/{bookId}")
    public List<Comment> getCommentsByBook(@PathVariable Long bookId) {
        return commentService.getCommentsByBook(bookId);
    }
}
