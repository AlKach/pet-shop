package by.kachanov.shop.web.rest.util;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionTranslationAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(new ExceptionMessage(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<?> handleObjectNotFoundException(ObjectNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionMessage(ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return new ResponseEntity<>(new ExceptionMessage(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static class ExceptionMessage {
        private Exception ex;

        public ExceptionMessage(Exception ex) {
            this.ex = ex;
        }

        public String getException() {
            return ex.getClass().getName();
        }

        public String getMessage() {
            return ex.getMessage();
        }
    }

}
