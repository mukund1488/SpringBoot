package sample.spring.service;

import sample.spring.exception.NoDataFoundException;
import sample.spring.model.modify.ModifyPersonalDetailsRequest;
import sample.spring.model.modify.ModifyPersonalDetailsResponse;
import sample.spring.model.retrieve.RetrievePersonalDetailResponse;
import sample.spring.model.retrieve.RetrievePersonalDetailsListResponse;

public interface PersonalDetailsService {

    RetrievePersonalDetailsListResponse retrieveAllPersonalDetails() throws NoDataFoundException;

    RetrievePersonalDetailResponse retrievePersonalDetailsById(Long id) throws NoDataFoundException;

    ModifyPersonalDetailsResponse deletePersonalDetailsById(Long id);

    ModifyPersonalDetailsResponse createPersonalDetails(ModifyPersonalDetailsRequest modifyPersonalDetailsRequest);

}



