package sample.spring.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sample.spring.exception.SimpleServiceError;
import sample.spring.exception.SimpleServiceException;

@RestControllerAdvice
public class SimpleServiceAdvice {

    @ExceptionHandler(SimpleServiceException.class)
    public ResponseEntity<SimpleServiceError> handleSimpleServiceException(SimpleServiceException exception) {
        return new ResponseEntity<>(new SimpleServiceError(exception.getMessage()), exception.getHttpStatus());


    }
}
