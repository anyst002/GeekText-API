package geektext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServicer {

    @Autowired
    private AuthorRepository authorRepository;

    public void createAuthor(Author author) {
        authorRepository.save(author);
    }
}
