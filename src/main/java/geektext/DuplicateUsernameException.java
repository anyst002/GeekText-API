package geektext;

public class DuplicateUsernameException extends RuntimeException {
	DuplicateUsernameException(String username) {
		super("Username " + username + " is already taken");
	}
}