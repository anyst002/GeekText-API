package geektext;

public class UserNotFoundException extends RuntimeException {
	UserNotFoundException(String username) {
		super("Could not find user " + username);
	}

	UserNotFoundException(Integer id) {
		super("Could not find user with id " + id);
	}

}