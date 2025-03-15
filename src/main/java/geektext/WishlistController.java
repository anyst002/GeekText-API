package geektext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController // if its not restcontroller stuff doesnt work
@RequestMapping(path="/wishlist") // This means URIs start with /wishlist (after Application path)
public class WishlistController {
	@Autowired
	private final WishlistRepository wishlistRepository;

	private final WishlistModelAssembler assembler;
	private final ListsService listsService;
	
    @PersistenceContext // import for entity manager
    private EntityManager entityManager; // need entityManager to get userRepository so i can get a user by userId

    WishlistController(WishlistRepository wRepository, WishlistModelAssembler assembler, ListsService l) {
        this.wishlistRepository = wRepository;
        this.assembler = assembler;
        this.listsService = l;
    }
    private void initializeBooks(Wishlist w) {
    	if(w.books != null) {
    		return;
    	}
    	Set<Book> books = listsService.getBooksByWishlistId(w.getId());
    	w.books = books;
    }
    
   	@PostMapping
	@ResponseBody
	ResponseEntity<?> createWishlist(@RequestParam("wishlistName") String wishlistName, @RequestParam("userId") Integer userId) {
		int max_wishlist_num = 3; // can only have 3 wishlists at most
		RepositoryFactorySupport factory = new JpaRepositoryFactory(entityManager); // jpa repository factory needs entity manager passed in.
		UserRepository uRepository = factory.getRepository(UserRepository.class); // then can get user repository class from jpaRepositoryFactory.
		User u = uRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)); // gets user or throws error.
		Set<Wishlist> wishlists = u.getWishlists();
		// initialization function that initializes set<books> if it hasnt been initialized yet.
		for(Wishlist w: wishlists) {
	 		initializeBooks(w);
		}
		// duplicate name checker.
		for(Wishlist w : wishlists) {
			System.out.println("WISHLIST NAME: " + w.getWishlistName());
			if(w.getWishlistName().equals(wishlistName)) {
				throw new DuplicateWishlistNameException(wishlistName, userId);
			}
		}
		// max # of wishlist checker.
		if(u.getWishlists().size() >= max_wishlist_num) {
			throw new TooManyWishlistsException(userId); // exception for when theres too much wishlist ( x >= 3)
		}

		// can create and save wishlist to db if theres x < 3 wishlists when this function is ran
		Wishlist w = new Wishlist(wishlistName, u);
		EntityModel<Wishlist> wModel = assembler.toModel(wishlistRepository.save(w));

		return ResponseEntity //
		    .created(wModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
		    .body(wModel);
		  // this return gives the links n stuff
		//return wishlistRepository.save(w); // nonrest way that does not return the links and stuff
	}
	@PostMapping("/{wishlistId}")
	@ResponseBody
	Set<EntityModel<Book>> addBookToWishlist(@PathVariable Integer wishlistId, @RequestParam("bookId") Long bookId) {
		Wishlist w = wishlistRepository.findById(wishlistId).orElseThrow(() -> new WishlistNotFoundException(wishlistId));
		listsService.checkBookExists(bookId);
		initializeBooks(w); // i need to call this function in all of the mappings.
		Set<Long> isbns = listsService.getBookIsbnByWishlistId(wishlistId);
		if(isbns.contains(bookId)) {
			throw new DuplicateBookInWishlistException(bookId, wishlistId, w.getWishlistName());
		}
		listsService.addBookToWishlist(w, bookId);
		Set<EntityModel<Book>> m = listsService.getBookModelsByWishlistId(wishlistId);
		// if code reaches here no exceptions are thrown.

		return m;
	}
	@GetMapping("/{wishlistId}")
	@ResponseBody
	Set<EntityModel<Book>> getBooksFromWishlist(@PathVariable Integer wishlistId) {
		Wishlist w = wishlistRepository.findById(wishlistId).orElseThrow(() -> new WishlistNotFoundException(wishlistId));
		initializeBooks(w);
		return listsService.getBookModelsByWishlistId(wishlistId);
	}
	@DeleteMapping("/{wishlistId}")
	@ResponseBody
	ResponseEntity<?> removeBookFromWishlist(@PathVariable Integer wishlistId, @RequestParam("bookId") Long bookId) {
		Wishlist w = wishlistRepository.findById(wishlistId).orElseThrow(() -> new WishlistNotFoundException(wishlistId));
		initializeBooks(w);
		listsService.checkBookInWishlist(w, bookId); // also checks if the book exists in general.
		listsService.deleteEntry(w, bookId);
		return ResponseEntity.noContent().build();
	}
	EntityModel<Wishlist> one(Integer id) {

		Wishlist w = wishlistRepository.findById(id) //
				.orElseThrow(() -> new WishlistNotFoundException(id)); 
		initializeBooks(w);

		System.out.println(w); // just a debug print
		EntityModel<Wishlist> eModel = assembler.toModel(w); // the model i'm returning.
		System.out.println(eModel); // just a debug print.
		return eModel;
	}
	// this get is for entity model.
	CollectionModel<EntityModel<Wishlist>> all() {

		  List<EntityModel<Wishlist>> wishlists = wishlistRepository.findAll().stream()
		      .map(wishlist -> EntityModel.of(wishlist,
		          linkTo(methodOn(WishlistController.class).one(wishlist.getId())).withSelfRel(),
		          linkTo(methodOn(WishlistController.class).all()).withRel("wishlist")))
		      .collect(Collectors.toList());
		  for(EntityModel<Wishlist> w : wishlists) {
			  initializeBooks(w.getContent());
		  }
		  System.out.println(wishlists);
		  return CollectionModel.of(wishlists, linkTo(methodOn(WishlistController.class).all()).withSelfRel());
	}


}