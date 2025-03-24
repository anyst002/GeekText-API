package geektext;

import geektext.Rating;
import geektext.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> getRatingsByBook(Long bookId) {
        return ratingRepository.findByBookId(bookId);
    }

    public double getAverageRating(Long bookId) {
        List<Rating> ratings = ratingRepository.findByBookId(bookId);
        OptionalDouble average = ratings.stream().mapToInt(Rating::getRating).average();
        return average.orElse(0.0);
    }
}
