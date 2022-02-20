package sample.spring.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sample.spring.model.modify.ModifyPersonalDetailsRequest;
import sample.spring.model.modify.ModifyPersonalDetailsResponse;
import sample.spring.model.retrieve.RetrievePersonalDetailResponse;
import sample.spring.model.retrieve.RetrievePersonalDetailsListResponse;
import sample.spring.service.PersonalDetailsService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
public class PersonalDetailsController {

    @Autowired
    private PersonalDetailsService personalDetailsService;

    @SneakyThrows
    @RequestMapping(method = RequestMethod.GET, path = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public RetrievePersonalDetailResponse retrievePersonalDetailsById(@NotNull @RequestParam Long id) {
        return personalDetailsService.retrievePersonalDetailsById(id);
    }

    @SneakyThrows
    @RequestMapping(method = RequestMethod.GET, path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public RetrievePersonalDetailsListResponse retrieveAllPersonalDetails() {
        return personalDetailsService.retrieveAllPersonalDetails();
    }


    @SneakyThrows
    @RequestMapping(method = RequestMethod.DELETE, path = "/delete")
    public ModifyPersonalDetailsResponse deletePersonalDetailsById(@RequestParam Long id) {
        return personalDetailsService.deletePersonalDetailsById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ModifyPersonalDetailsResponse modifyPersonalDetails(@RequestBody @Valid ModifyPersonalDetailsRequest modifyPersonalDetailsRequest) {
        return personalDetailsService.createPersonalDetails(modifyPersonalDetailsRequest);
    }


}
