package geektext;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class WishlistNotFoundAdvice {

  @ExceptionHandler(WishlistNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String wishlistNotFoundHandler(WishlistNotFoundException ex) {
    return ex.getMessage();
  }
}