package sample.spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import sample.spring.exception.PersonalDetailsException;
import sample.spring.model.PersonalDetails;
import sample.spring.model.RequestStatus;
import sample.spring.model.modify.ModifyPersonalDetailsRequest;
import sample.spring.model.modify.ModifyPersonalDetailsResponse;
import sample.spring.model.retrieve.RetrievePersonalDetailsResponse;
import sample.spring.repository.PersonalDetailsRepository;
import sample.spring.service.PersonalDetailsService;
import sample.spring.util.PersonalDetailsConstant;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PersonalDetailsServiceImpl implements PersonalDetailsService {


    @Autowired
    private PersonalDetailsRepository personalDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RetrievePersonalDetailsResponse retrieveAllPersonalDetails() throws PersonalDetailsException {
        List<sample.spring.entity.PersonalDetails> personalDetailsList = personalDetailsRepository.findAll();
        List<PersonalDetails> personalDetails = personalDetailsList.stream().map(personalDetailsEntity -> modelMapper.map(personalDetailsEntity, PersonalDetails.class)).collect(Collectors.toList());
        return RetrievePersonalDetailsResponse.builder().requestStatus(RequestStatus.SUCCESS).personalDetailsList(personalDetails).build();
    }

    @Override
    public RetrievePersonalDetailsResponse retrievePersonalDetailsById(Long id) throws PersonalDetailsException {
        sample.spring.entity.PersonalDetails personalDetails = personalDetailsRepository.findById(id).orElseThrow(() -> new PersonalDetailsException("No data found for input id", HttpStatus.NOT_FOUND));
        return RetrievePersonalDetailsResponse.builder().requestStatus(RequestStatus.SUCCESS).personalDetailsList(List.of(modelMapper.map(personalDetails, PersonalDetails.class))).build();
    }

    @Override
    public ModifyPersonalDetailsResponse deletePersonalDetailsById(Long id) {
        personalDetailsRepository.deleteById(id);
        return ModifyPersonalDetailsResponse.builder().requestStatus(RequestStatus.SUCCESS).build();
    }

    @Override
    public ModifyPersonalDetailsResponse createPersonalDetails(ModifyPersonalDetailsRequest modifyPersonalDetailsRequest) {
        sample.spring.entity.PersonalDetails personalDetails = modelMapper.map(modifyPersonalDetailsRequest, sample.spring.entity.PersonalDetails.class);
        sample.spring.entity.PersonalDetails savedEntity = personalDetailsRepository.save(personalDetails);
        return ModifyPersonalDetailsResponse.builder().requestStatus(RequestStatus.SUCCESS).message(PersonalDetailsConstant.updatedEntitySavedMessage + savedEntity.getId()).build();

    }
}
