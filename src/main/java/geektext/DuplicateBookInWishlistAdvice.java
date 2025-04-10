package geektext;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class DuplicateBookInWishlistAdvice {

  @ExceptionHandler(DuplicateBookInWishlistException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  String duplicateWishlistNameHandler(DuplicateBookInWishlistException ex) {
    return ex.getMessage();
  }
}