package sample.spring.integration.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sample.spring.repository.PersonalDetailsRepository;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PersonalDetailsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PersonalDetailsRepository personalDetailsRepository;

    @Autowired
    private DataSource dataSource;

    @BeforeEach()
    void setUpStaticData() throws SQLException {
        dataSource.getConnection().prepareStatement("INSERT INTO PERSONAL_DETAILS(FIRST_NAME,LAST_NAME,ADDRESS,CURRENT_LOCATION,MOBILE,EMAIL) values('Mukund','Motwani','C-104 Kool Homes Rising Landscape','Pune',7798837255,'mukund4motwani@gmail.com')").execute();

    }

    @AfterEach()
    void tearDownUpStaticData() throws SQLException {
        dataSource.getConnection().prepareStatement("TRUNCATE TABLE PERSONAL_DETAILS").execute();

    }


    @SneakyThrows
    @Test
    void assertBehaviorForGetByIdSuccessScenario() {
        String expectedResponse = new String(Files.readAllBytes(Paths.get("src/test/resources/PersonalDetailsResponse.json")));
        mockMvc.perform(MockMvcRequestBuilders.get("/getById").queryParam("id", String.valueOf(1L)))
                .andExpect(status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.content().json(expectedResponse));
    }

    @SneakyThrows
    @Test
    void assertBehaviorForDeleteByIdSuccessScenario() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete").queryParam("id", String.valueOf(1L)))
                .andExpect(status().is2xxSuccessful());
        assertThat(personalDetailsRepository.findById(1L)).isEmpty();

    }

    @SneakyThrows
    @Test
    void assertBehaviorForGetAllSuccessScenario() {
        mockMvc.perform(MockMvcRequestBuilders.get("/getAll"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.personalDetailsList", Matchers.hasSize(1)));
    }


}
