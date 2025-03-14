package geektext;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/lists")
public class ListsController {
	private ListsRepository repository; 
	ListsController(ListsRepository r) {
		this.repository = r;
	}

	@GetMapping("/all")
	List<Lists> all() {
		return repository.findAll();
	}
}
