package sample.spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.spring.model.PersonalDetails;
import sample.spring.repository.PersonalDetailsRepository;
import sample.spring.service.SampleService;

@Slf4j
@Component
public class SampleServiceImpl implements SampleService {


    @Autowired
    private PersonalDetailsRepository personalDetailsRepository;

    @Override
    public PersonalDetails execute(String id) {
        return personalDetailsRepository.findById(id).orElse(null);
    }
}
