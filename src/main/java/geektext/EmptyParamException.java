package geektext;

public class EmptyParamException extends RuntimeException {
	EmptyParamException() {
		super("Required parameters must not be empty");
	}
}