package sample.spring.controller;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sample.spring.service.PersonalDetailsService;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PersonalDetailsControllerTest {

    @MockBean
    private PersonalDetailsService personalDetailsService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldReturn200StatusWhenEndpointIsCalled() {
        try {
            mockMvc.perform(get("/getById?id=1"))
                    .andExpect(status().is2xxSuccessful());
            //verify(personalDetailsService, times(1)).retrievePersonalDetailsById(1L);
        } catch (Exception ex) {
            fail();
        }
    }


    @Test
    void shouldThrowBadRequestWhenMandatoryParametersAreMissingForModifyRequest() {
        try {
            String modifyPersonalDetailsRequest = "{\"firstName\":\"Mukund\",\"lastName\":\"Motwani\"}";
            mockMvc.perform(MockMvcRequestBuilders.post("/create").accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON).content(modifyPersonalDetailsRequest))
                    .andExpect(status().isBadRequest()).andExpect(jsonPath("$.requestStatus", Is.is("BAD_REQUEST")));
        } catch (Exception ex) {
            fail();
        }
    }


}
