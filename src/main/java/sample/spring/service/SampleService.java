package sample.spring.service;

import sample.spring.dto.PersonalDetailsDto;
import sample.spring.exception.SimpleServiceException;

public interface SampleService {
    PersonalDetailsDto execute(String id) throws SimpleServiceException;
}
