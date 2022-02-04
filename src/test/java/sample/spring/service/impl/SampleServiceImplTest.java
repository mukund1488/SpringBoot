package sample.spring.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sample.spring.dto.PersonalDetailsDto;
import sample.spring.model.PersonalDetails;
import sample.spring.repository.PersonalDetailsRepository;
import sample.spring.service.SampleService;
import sample.spring.service.configuration.SampleServiceTestConfiguration;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(SampleServiceTestConfiguration.class)
public class SampleServiceImplTest {

    @MockBean
    private PersonalDetailsRepository personalDetailsRepository;
    @Autowired
    private SampleService sampleService;
    public ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Test
    public void shouldReturnPersonalDetailsWhenFoundInDb() {
        PersonalDetails personalDetails = objectMapper.readValue(new ClassPathResource("PersonalDetails.json").getFile(), PersonalDetails.class);
        when(personalDetailsRepository.findById("2a")).thenReturn(Optional.of(personalDetails));
        PersonalDetailsDto expectedDto = sampleService.execute("2a");
        assertThat(expectedDto).usingRecursiveComparison().ignoringActualNullFields().isEqualTo(personalDetails);
    }
}
