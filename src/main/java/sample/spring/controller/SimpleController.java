package sample.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sample.spring.model.PersonalDetails;
import sample.spring.service.SampleService;

@RestController
public class SimpleController {

    @Autowired
    private SampleService sampleService;

    @RequestMapping(method = RequestMethod.GET, path = "/exec")
    public PersonalDetails execute(@RequestParam String id) {
        return sampleService.execute(id);
    }


}
