package geektext;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class CartEntryExistsWLAdvice {

  @ExceptionHandler(CartEntryExistsWLException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  String cartEntryExistsHandler(CartEntryExistsWLException ex) {
    return ex.getMessage();
  }
}