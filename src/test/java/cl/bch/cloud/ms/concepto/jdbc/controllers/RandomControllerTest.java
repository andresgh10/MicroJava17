package cl.bch.cloud.ms.concepto.jdbc.controllers;

import cl.bch.cloud.ms.concepto.jdbc.services.RandomService;
import cl.bch.cloud.ms.concepto.jdbc.dtos.MessageDTO;
import cl.bch.cloud.resterror.advice.BchResponseEntityExceptionHandler;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(RandomController.class)
@Import({BchResponseEntityExceptionHandler.class})
public class RandomControllerTest {

    @Value("${properties.randomService.minRandomValue}")
    private int min;

    @Value("${properties.randomService.statusMessageOk}")
    private String statusMessageOk;

    @Value("${properties.randomService.okMessage}")
    private String okMessage;

    @Value("${properties.randomService.statusMessageNok}")
    private String statusMessageNok;

    @Value("${properties.randomService.errorMessage}")
    private String errorMessage;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RandomService randomServiceMock;
    
    @Test
    public void shouldReadMinRandomValueEqualOrGreaterThanZeroFromProperties() throws Exception {
        Assertions.assertThat(min).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void shouldReadNotNullStatusMessageOkFromProperties() throws Exception {
        Assertions.assertThat(statusMessageOk).isNotEmpty();
    }

    @Test
    public void shouldReadNotNullOkMessageFromProperties() throws Exception {
        Assertions.assertThat(okMessage).isNotEmpty();
    }

    @Test
    public void shouldReadNotNullStatusMessageNokFromProperties() throws Exception {
        Assertions.assertThat(statusMessageNok).isNotEmpty();
    }

    @Test
    public void shouldReadNotNullErrorMessageFromProperties() throws Exception {
        Assertions.assertThat(errorMessage).isNotEmpty();
    }

    @Test
    public void shouldGetOkResponseWithRandomNumberMessage() throws Exception {
        int max = min + 1;
        Mockito.when(randomServiceMock.random(max))
                .thenReturn(new MessageDTO(statusMessageOk, String.format(okMessage, min, max, max)));

        var result = mvc.perform(
                MockMvcRequestBuilders.get("/domain/subdomain/random/" + max)
                        .accept(MediaType.APPLICATION_JSON));

        result.andDo(print())
                .andExpect(
                        MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status", is(statusMessageOk)))
                .andExpect(jsonPath("$.message", Matchers.containsString(String.format(okMessage, min, max, ""))));
    }

    @Test
    public void shouldGetOkResponseWithErrorMessage() throws Exception {
        int max = min - 1;
        Mockito.when(randomServiceMock.random(max))
                .thenReturn(new MessageDTO(statusMessageNok, String.format(errorMessage, min)));

        var result = mvc.perform(
                MockMvcRequestBuilders.get("/domain/subdomain/random/" + max)
                        .accept(MediaType.APPLICATION_JSON));
        result.andDo(print())
                .andExpect(
                        MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status", is(statusMessageNok)))
                .andExpect(jsonPath("$.message", Matchers.containsString(String.format(errorMessage, min))));
    }

}