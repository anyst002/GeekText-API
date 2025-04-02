package geektext;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AuthorServicer {
    @Autowired
    private AuthorRepository authorRepository;

    public void createAuthor(Author author) {
        authorRepository.save(author);
    }
}
