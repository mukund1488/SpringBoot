package sample.spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import sample.spring.dto.PersonalDetailsDto;
import sample.spring.exception.SimpleServiceException;
import sample.spring.model.PersonalDetails;
import sample.spring.repository.PersonalDetailsRepository;
import sample.spring.service.SampleService;

@Slf4j
@Component
public class SampleServiceImpl implements SampleService {


    @Autowired
    private PersonalDetailsRepository personalDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonalDetailsDto execute(String id) throws SimpleServiceException {
        PersonalDetails personalDetails = personalDetailsRepository.findById(id).orElseThrow(() -> new SimpleServiceException("No data found for input id", HttpStatus.NOT_FOUND));
        return modelMapper.map(personalDetails, PersonalDetailsDto.class);
    }
}
