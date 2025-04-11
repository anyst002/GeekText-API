package geektext;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class BookModelAssembler implements RepresentationModelAssembler<Book, EntityModel<Book>> {

	@Override
	public EntityModel<Book> toModel(Book b) {
		return EntityModel.of(b, //
		linkTo(methodOn(BookController.class).getBookModelByIsbn(b.getIsbn())).withSelfRel());
	}
}