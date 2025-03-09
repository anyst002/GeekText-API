package geektext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServicer {

    @Autowired
    private BookRepository bookRepository;

    public void applyDiscount(double discountPercent, int publisherId) {
        // Use the new method to find books by publisher_id
        List<Book> books = bookRepository.findByPublisher_id(publisherId);

        // Loop through each book, apply the discount, and save
        for (Book book : books) {
            double newPrice = book.getPrice() * (1 - discountPercent / 100);
            book.setPrice(newPrice);
            bookRepository.save(book);
        }
    }
}
