package sample.spring.service.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import sample.spring.service.PersonalDetailsService;
import sample.spring.service.impl.PersonalDetailsServiceImpl;

@TestConfiguration
public class PersonalDetailsTestConfiguration {
    @Bean
    public PersonalDetailsService personalDetailsService() {
        return new PersonalDetailsServiceImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
