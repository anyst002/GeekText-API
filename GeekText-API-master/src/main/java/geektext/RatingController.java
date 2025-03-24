package geektext;

import geektext.Rating;
import geektext.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public Rating addRating(@RequestBody Rating rating) {
        return ratingService.addRating(rating);
    }

    @GetMapping("/{bookId}/average")
    public double getAverageRating(@PathVariable Long bookId) {
        return ratingService.getAverageRating(bookId);
    }
}
