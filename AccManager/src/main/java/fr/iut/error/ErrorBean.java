package fr.iut.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionBean {
    @ExceptionHandler(value = Error.class)
    public ResponseEntity<Object> exception(Error error) {
        return ResponseEntity.status(error.getStatus()).body(error.getMessage());
    }
}
