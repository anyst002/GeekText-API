package geektext;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class WishlistModelAssembler implements RepresentationModelAssembler<Wishlist, EntityModel<Wishlist>> {

	@Override
	public EntityModel<Wishlist> toModel(Wishlist wishlist) {
		return EntityModel.of(wishlist, //
				linkTo(methodOn(WishlistController.class).one(wishlist.getId())).withSelfRel(),
				linkTo(methodOn(WishlistController.class).all()).withRel("wishlist"));
	}
	@Override
	public String toString() {
		return "Wishlist Entity model assembler.";
	}
}