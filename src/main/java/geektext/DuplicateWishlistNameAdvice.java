package geektext;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class DuplicateWishlistNameAdvice {

  @ExceptionHandler(DuplicateWishlistNameException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  String duplicateWishlistNameHandler(DuplicateWishlistNameException ex) {
    return ex.getMessage();
  }
}