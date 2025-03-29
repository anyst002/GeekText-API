package geektext;

import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class DuplicateUsernameAdvice {
	@ExceptionHandler(DuplicateUsernameException.class)
	ResponseEntity<?> duplicateUsernameHandler(DuplicateUsernameException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
			.body(Problem.create()
				.withTitle("Forbidden")
				.withDetail(ex.getMessage()));
	}
}

@RestControllerAdvice
class EmptyParamAdvice {
	@ExceptionHandler(EmptyParamException.class)
	ResponseEntity<?> emptyParamHandler(EmptyParamException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(Problem.create()
				.withTitle("Bad Request")
				.withDetail(ex.getMessage()));
	}
}

@RestControllerAdvice
class UserNotFoundAdvice {
	@ExceptionHandler(UserNotFoundException.class)
	ResponseEntity<?> userNotFoundHandler(UserNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(Problem.create()
				.withTitle("Not Found")
				.withDetail(ex.getMessage()));
	}
}