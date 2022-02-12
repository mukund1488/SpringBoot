package sample.spring.model.retrieve;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import sample.spring.model.PersonalDetails;
import sample.spring.model.ResponseDetails;

import java.util.List;

@SuperBuilder
@Data
public class RetrievePersonalDetailsResponse extends ResponseDetails {
    private List<PersonalDetails> personalDetailsList;
}
