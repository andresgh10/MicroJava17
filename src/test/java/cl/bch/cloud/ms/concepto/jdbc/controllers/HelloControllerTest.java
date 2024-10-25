package cl.bch.cloud.ms.concepto.jdbc.controllers;

import cl.bch.cloud.ms.concepto.jdbc.exceptions.TooManyRequestException;
import cl.bch.cloud.ms.concepto.jdbc.services.HelloService;
import cl.bch.cloud.ms.concepto.jdbc.dtos.MessageDTO;
import cl.bch.cloud.resterror.advice.BchResponseEntityExceptionHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
@Import({BchResponseEntityExceptionHandler.class})
public class HelloControllerTest {

    @Value("${properties.helloService.statusMessageOk}")
    private String statusMessageOk;

    @Value("${properties.helloService.greetingMessage}")
    private String greetingMessage;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloService helloServiceMock;

    @Test
    public void shouldGetOkResponseWithHelloMessage() throws Exception {
        Mockito.when(helloServiceMock.greetings()).thenReturn(new MessageDTO(statusMessageOk, greetingMessage));

        mockMvc.perform(
                        get("/domain/subdomain/greetings"))
                .andDo(
                        print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.message", is("Greetings from Spring Boot!")));
    }

    @Test
    public void shouldGetOkResponseWithHelloMessageNoGreetings() throws Exception {
        Mockito.when(helloServiceMock.noGreetings()).thenThrow(new TooManyRequestException(new RuntimeException()));

        mockMvc.perform(
                        get("/domain/subdomain/noGreetings"))
                .andDo(
                        print())
                .andExpect(status().isTooManyRequests())
                .andExpect(jsonPath("$.internalCode", is(3003)))
                .andExpect(jsonPath("$.detail", is("Too many request")));
    }

}