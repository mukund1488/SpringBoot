package sample.spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.spring.exception.NoDataFoundException;
import sample.spring.model.PersonalDetails;
import sample.spring.model.RequestStatus;
import sample.spring.model.modify.ModifyPersonalDetailsRequest;
import sample.spring.model.modify.ModifyPersonalDetailsResponse;
import sample.spring.model.retrieve.RetrievePersonalDetailResponse;
import sample.spring.model.retrieve.RetrievePersonalDetailsListResponse;
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
    public RetrievePersonalDetailsListResponse retrieveAllPersonalDetails() throws NoDataFoundException {
        List<sample.spring.entity.PersonalDetails> personalDetailsList = personalDetailsRepository.findAll();
        if (personalDetailsList.isEmpty()) {
            throw new NoDataFoundException("Table is empty");
        }
        List<PersonalDetails> personalDetails = personalDetailsList.stream().map(personalDetailsEntity -> modelMapper.map(personalDetailsEntity, PersonalDetails.class)).collect(Collectors.toList());
        return RetrievePersonalDetailsListResponse.builder().requestStatus(RequestStatus.SUCCESS).personalDetailsList(personalDetails).build();
    }

    @Override
    public RetrievePersonalDetailResponse retrievePersonalDetailsById(Long id) throws NoDataFoundException {
        sample.spring.entity.PersonalDetails personalDetails = personalDetailsRepository.findById(id).orElseThrow(() -> new NoDataFoundException("No data provided for input id"));
        return RetrievePersonalDetailResponse.builder().requestStatus(RequestStatus.SUCCESS).personalDetails(modelMapper.map(personalDetails, PersonalDetails.class)).build();
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
