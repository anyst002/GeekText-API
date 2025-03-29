package geektext;

import geektext.Rating;
import geektext.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Transactional
    public Rating addRating(Rating rating) {
        if (rating.getRating() < 1 || rating.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        return ratingRepository.save(rating);
    }

    public double getAverageRating(Long bookId) {
        return ratingRepository.findAverageRatingByBookId(bookId).orElse(0.0);
    }
}
