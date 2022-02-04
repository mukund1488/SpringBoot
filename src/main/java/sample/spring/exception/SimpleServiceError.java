package sample.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleServiceError {
    private String errorMessage;
}
