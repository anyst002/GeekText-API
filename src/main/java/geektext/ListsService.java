package geektext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ListsService {
	private ListsRepository repository;
	private WishlistRepository wRepository;
	private BookRepository bRepository;
	private BookModelAssembler bAssembler;
	/*@PersistenceContext
	private EntityManager e;*/
	public ListsService(ListsRepository r, BookModelAssembler b, WishlistRepository w, BookRepository bR) {
		repository = r;
		bAssembler = b;
		wRepository = w;
		bRepository = bR;
	}
	ListsRepository getRepository() {
		return repository;
	}
	List<Lists> getAll() {
		return repository.findAll();
	}
	Lists getListByIds(Long bookId, Integer userId, Integer wishlistId) {
		List<Lists> all = repository.findAll();
		for(Lists L : all) {
			if(L.getBook().getIsbn() != bookId) {
				continue;
			}
			if(L.getUser().getId() != userId) {
				continue;
			}
			if(L.getWishlist().getId() != wishlistId) {
				continue;
			}
			return L;
		}
		return null;
		
	}
	Set<Book> getBooksByWishlistId(Integer wishlistId) {
		// make sure wishlist exists.
		wRepository.findById(wishlistId).orElseThrow(() -> new WishlistNotFoundException(wishlistId));
		List<Lists> all = repository.findAll();
		
		Set<Book> b = new HashSet<Book>();
		for(Lists L: all) {
			if(L.getWishlist().getId() != wishlistId) {
				continue;
			}
			b.add(L.getBook());
		}
		
		return b;
	}
	Set<Long> getBookIsbnByWishlistId(Integer wishlistId) {
		// make sure wishlist exists.
		wRepository.findById(wishlistId).orElseThrow(() -> new WishlistNotFoundException(wishlistId));
		List<Lists> all = repository.findAll();
		Set<Long> i = new HashSet<Long>();
		for(Lists L: all) {
			if(L.getWishlist().getId() != wishlistId) {
				continue;
			}
			i.add(L.getBook().getIsbn());
		}
		
		return i;
	}
	void checkBookInWishlist(Wishlist w, Long bookId) {
		Book b = bRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
		if(!w.books.contains(b)) {
			throw new BookNotFoundInWishlistException(bookId, w.getId(), w.getWishlistName());
		}
	}
	void checkBookExists(Long bookId) {
		bRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
	}
	
	Set<EntityModel<Book>> getBookModelsByWishlistId(Integer wishlistId) {
		Set<EntityModel<Book>> m = new HashSet<EntityModel<Book>>();
		for(Book b : getBooksByWishlistId(wishlistId)) {
			m.add(bAssembler.toModel(b));
		}
		return m;
	}
	
	void deleteEntry(Wishlist w, Long isbn) {
		Book b = bRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
		ListsId id = new ListsId(w, b, w.getUser());
		System.out.println("finding wishlist by composite id {" + w + ", " + b + ", " + w.getUser() + "}");
		Lists L = repository.findById(id).orElseThrow(() -> new BookNotFoundException(isbn));
		w.books.remove(b);
		repository.delete(L);
	}
	
	void addBookToWishlist(Wishlist w, Long isbn) {
		Book b = bRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
		w.books.add(b);
		ListsId id = new ListsId(w, b, w.getUser());
		Lists L = new Lists(id);
		repository.save(L);
	}
	
}
