package sample.spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.spring.dto.PersonalDetailsDto;
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
    public PersonalDetailsDto execute(String id) {
        return modelMapper.map(personalDetailsRepository.findById(id).orElse(new PersonalDetails()), PersonalDetailsDto.class);
    }
}
