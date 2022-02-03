package sample.spring.controller.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sample.spring.service.SampleService;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
public class SimpleControllerTest {

    @MockBean
    private SampleService sampleService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturn200StatusWhenEndpointIsCalled() {
        try {
            mockMvc.perform(get("/exec"))
                    .andExpect(status().is2xxSuccessful());
        } catch (Exception ex) {
            fail();
        }

    }


}
