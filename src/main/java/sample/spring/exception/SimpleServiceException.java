package sample.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class SimpleServiceException extends Exception {
    private String message;
    private HttpStatus httpStatus;
}
