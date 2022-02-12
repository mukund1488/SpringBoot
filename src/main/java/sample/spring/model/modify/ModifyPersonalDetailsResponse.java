package sample.spring.model.modify;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import sample.spring.model.ResponseDetails;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ModifyPersonalDetailsResponse extends ResponseDetails {
    private String message;
}
