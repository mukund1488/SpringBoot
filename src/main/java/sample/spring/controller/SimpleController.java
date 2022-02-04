package sample.spring.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sample.spring.dto.PersonalDetailsDto;
import sample.spring.service.SampleService;

@RestController
public class SimpleController {

    @Autowired
    private SampleService sampleService;

    @SneakyThrows
    @RequestMapping(method = RequestMethod.GET, path = "/exec")
    public PersonalDetailsDto execute(@RequestParam String id) {
        return sampleService.execute(id);
    }


}
