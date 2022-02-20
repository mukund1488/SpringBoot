package sample.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoDataFoundException extends Exception {
    private String message;
}
