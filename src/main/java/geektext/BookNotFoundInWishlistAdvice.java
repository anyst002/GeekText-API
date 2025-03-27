package geektext;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class BookNotFoundInWishlistAdvice {

  @ExceptionHandler(BookNotFoundInWishlistException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String BookNotFoundHandler(BookNotFoundInWishlistException ex) {
    return ex.getMessage();
  }
}