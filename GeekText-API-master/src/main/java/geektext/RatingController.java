package geektext;

import geektext.Rating;
import geektext.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<?> addRating(@RequestBody Rating rating) {
        try {
            Rating savedRating = ratingService.addRating(rating);
            return ResponseEntity.ok(savedRating);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{bookId}/average")
    public ResponseEntity<?> getAverageRating(@PathVariable Long bookId) {
        double average = ratingService.getAverageRating(bookId);
        return ResponseEntity.ok(Map.of("bookId", bookId, "averageRating", average));
    }
}
