package sample.spring.model.retrieve;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import sample.spring.model.PersonalDetails;
import sample.spring.model.ResponseDetails;

@Data
@SuperBuilder
public class RetrievePersonalDetailResponse extends ResponseDetails {
    private PersonalDetails personalDetails;
}
