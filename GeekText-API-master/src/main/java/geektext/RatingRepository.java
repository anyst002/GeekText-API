package geektext;

import geektext.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByBookId(Long bookId);

    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.bookId = :bookId")
    Optional<Double> findAverageRatingByBookId(@Param("bookId") Long bookId);
}
