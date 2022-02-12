package sample.spring.service;

import sample.spring.exception.PersonalDetailsException;
import sample.spring.model.modify.ModifyPersonalDetailsRequest;
import sample.spring.model.modify.ModifyPersonalDetailsResponse;
import sample.spring.model.retrieve.RetrievePersonalDetailsResponse;

public interface PersonalDetailsService {

    RetrievePersonalDetailsResponse retrieveAllPersonalDetails() throws PersonalDetailsException;

    RetrievePersonalDetailsResponse retrievePersonalDetailsById(Long id) throws PersonalDetailsException;

    ModifyPersonalDetailsResponse deletePersonalDetailsById(Long id);

    ModifyPersonalDetailsResponse createPersonalDetails(ModifyPersonalDetailsRequest modifyPersonalDetailsRequest);

}



