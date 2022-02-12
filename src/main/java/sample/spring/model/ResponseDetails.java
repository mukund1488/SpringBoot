package sample.spring.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import sample.spring.exception.PersonalDetailsError;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseDetails {
    private RequestStatus requestStatus;
    private PersonalDetailsError personalDetailsError;
}
