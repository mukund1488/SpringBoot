package sample.spring.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sample.spring.entity.PersonalDetails;
import sample.spring.exception.NoDataFoundException;
import sample.spring.model.modify.ModifyPersonalDetailsResponse;
import sample.spring.model.retrieve.RetrievePersonalDetailResponse;
import sample.spring.model.retrieve.RetrievePersonalDetailsListResponse;
import sample.spring.repository.PersonalDetailsRepository;
import sample.spring.service.PersonalDetailsService;
import sample.spring.service.configuration.PersonalDetailsTestConfiguration;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static sample.spring.model.RequestStatus.SUCCESS;

@ExtendWith(SpringExtension.class)
@Import(PersonalDetailsTestConfiguration.class)
public class PersonalDetailsServiceImplTest {

    @MockBean
    private PersonalDetailsRepository personalDetailsRepository;
    @Autowired
    private PersonalDetailsService personalDetailsService;
    @Autowired
    private ModelMapper modelMapper;

    public ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Test
    void shouldReturnPersonalDetailsWhenFoundInDbForGivenId() {
        PersonalDetails personalDetails = objectMapper.readValue(new ClassPathResource("PersonalDetails.json").getFile(), PersonalDetails.class);
        when(personalDetailsRepository.findById(2L)).thenReturn(Optional.of(personalDetails));
        RetrievePersonalDetailResponse expectedResponse = personalDetailsService.retrievePersonalDetailsById(2L);
        assertThat(expectedResponse.getPersonalDetails()).usingRecursiveComparison().isEqualTo(personalDetails);
    }

    @SneakyThrows
    @Test
    void shouldThrowExceptionWhenPersonalDetailsNotFoundInDb() {
        NoDataFoundException exception = assertThrows(NoDataFoundException.class, () -> personalDetailsService.retrievePersonalDetailsById(2L));
        assertThat(exception.getMessage()).isEqualTo("No data provided for input id");
    }

    @SneakyThrows
    @Test
    void shouldReturnAllPersonalDetailsFoundInDbWhenRetrieveAllCalled() {
        PersonalDetails personalDetails = objectMapper.readValue(new ClassPathResource("PersonalDetails.json").getFile(), PersonalDetails.class);
        when(personalDetailsRepository.findAll()).thenReturn(List.of(personalDetails));
        RetrievePersonalDetailsListResponse expectedResponse = personalDetailsService.retrieveAllPersonalDetails();
        assertThat(expectedResponse.getPersonalDetailsList()).containsExactlyElementsOf(List.of(modelMapper.map(personalDetails, sample.spring.model.PersonalDetails.class)));
    }

    @SneakyThrows
    @Test
    void shouldReturnNoDataFoundExceptionWhenDataNotFoundInDbForRetrieveAll() {
        when(personalDetailsRepository.findAll()).thenReturn(Collections.emptyList());
        NoDataFoundException noDataFoundException = assertThrows(NoDataFoundException.class, () ->
                personalDetailsService.retrieveAllPersonalDetails());
        assertThat(noDataFoundException.getMessage()).isEqualTo("Table is empty");
    }

    @SneakyThrows
    @Test
    void shouldReturnSucessMessageWhenInputIdIsDeletedSuccessfully() {
        ModifyPersonalDetailsResponse modifyPersonalDetailsResponse = personalDetailsService.deletePersonalDetailsById(1L);
        assertThat(modifyPersonalDetailsResponse.getRequestStatus()).isEqualTo(SUCCESS);

    }
}
