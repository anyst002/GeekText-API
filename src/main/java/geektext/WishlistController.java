package geektext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Controller // This means that this class is a Controller
@RequestMapping(path="/wishlist") // This means URIs start with /wishlist (after Application path)
public class WishlistController {
	@Autowired // This means to get the bean called wishlistRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private WishlistRepository wishlistRepository;
	
    @PersistenceContext // import for entity manager
    private EntityManager entityManager; // need entityManager to get userRepository so i can get a user by userId

	@PostMapping
	@ResponseBody
	Wishlist createWishlist(@RequestParam("wishlistName") String wishlistName, @RequestParam("userId") Integer userId) {
		int max_wishlist_num = 3; // can only have 3 wishlists at most
		RepositoryFactorySupport factory = new JpaRepositoryFactory(entityManager); // jpa repository factory needs entity manager passed in.
		UserRepository repository = factory.getRepository(UserRepository.class); // then can get user repository class from jpaRepositoryFactory.
		User u = repository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)); // gets user or throws error.
		if(u.getWishlists().size() >= max_wishlist_num) {
			throw new TooManyWishlistsException(userId); // exception for when theres too much wishlist ( x >= 3)
		}
		// can create and save wishlist to db if theres x < 3 wishlists when this function is ran
		Wishlist w = new Wishlist(wishlistName, u);
		return wishlistRepository.save(w);
	}

}