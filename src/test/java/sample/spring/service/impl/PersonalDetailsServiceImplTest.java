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
import sample.spring.entity.PersonalDetails;
import sample.spring.model.retrieve.RetrievePersonalDetailsResponse;
import sample.spring.repository.PersonalDetailsRepository;
import sample.spring.service.PersonalDetailsService;
import sample.spring.service.configuration.PersonalDetailsTestConfiguration;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(PersonalDetailsTestConfiguration.class)
public class PersonalDetailsServiceImplTest {

    @MockBean
    private PersonalDetailsRepository personalDetailsRepository;
    @Autowired
    private PersonalDetailsService personalDetailsService;
    public ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Test
    public void shouldReturnPersonalDetailsWhenFoundInDb() {
        PersonalDetails personalDetails = objectMapper.readValue(new ClassPathResource("PersonalDetails.json").getFile(), PersonalDetails.class);
        when(personalDetailsRepository.findById(2L)).thenReturn(Optional.of(personalDetails));
        RetrievePersonalDetailsResponse expectedResponse = personalDetailsService.retrievePersonalDetailsById(2L);
        assertThat(expectedResponse.getPersonalDetailsList()).usingRecursiveComparison().ignoringActualNullFields().isEqualTo(personalDetails);
    }
}
