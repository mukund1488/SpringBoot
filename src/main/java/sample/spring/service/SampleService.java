package sample.spring.service;

import sample.spring.dto.PersonalDetailsDto;
import sample.spring.model.PersonalDetails;

public interface SampleService {
    PersonalDetailsDto execute(String id);
}
