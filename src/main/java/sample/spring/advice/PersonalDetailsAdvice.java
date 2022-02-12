package sample.spring.advice;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sample.spring.exception.PersonalDetailsError;
import sample.spring.exception.PersonalDetailsException;
import sample.spring.model.RequestStatus;
import sample.spring.model.ResponseDetails;
import sample.spring.model.modify.ModifyPersonalDetailsResponse;

@RestControllerAdvice
public class PersonalDetailsAdvice {

    @ExceptionHandler(PersonalDetailsException.class)
    public ResponseEntity<ModifyPersonalDetailsResponse> handleSimpleServiceException(PersonalDetailsException exception) {
        return new ResponseEntity<>(ModifyPersonalDetailsResponse.builder().requestStatus(RequestStatus.FAILURE).personalDetailsError(new PersonalDetailsError(exception.getMessage())).build(), exception.getHttpStatus());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ModifyPersonalDetailsResponse> handleEmptyResultDataException(EmptyResultDataAccessException exception) {
        return new ResponseEntity<>(ModifyPersonalDetailsResponse.builder().requestStatus(RequestStatus.NO_DATA_FOUND).personalDetailsError(new PersonalDetailsError(exception.getMessage())).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDetails> handleInvalidRequestException(MethodArgumentNotValidException exception) {
        PersonalDetailsError personalDetailsError = new PersonalDetailsError(exception.getMessage());
        return new ResponseEntity<>(ResponseDetails.builder().requestStatus(RequestStatus.BAD_REQUEST).personalDetailsError(personalDetailsError).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDetails> handleInvalidRequestException(Exception exception) {
        PersonalDetailsError personalDetailsError = new PersonalDetailsError(exception.getMessage());
        return new ResponseEntity<>(ResponseDetails.builder().requestStatus(RequestStatus.FAILURE).personalDetailsError(personalDetailsError).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
