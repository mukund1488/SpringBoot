package sample.spring.service.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import sample.spring.service.SampleService;
import sample.spring.service.impl.SampleServiceImpl;

@TestConfiguration
public class SampleServiceTestConfiguration {
    @Bean
    public SampleService sampleService() {
        return new SampleServiceImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
