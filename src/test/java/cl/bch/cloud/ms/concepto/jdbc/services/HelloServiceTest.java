package cl.bch.cloud.ms.concepto.jdbc.services;

import cl.bch.cloud.ms.concepto.jdbc.dtos.CreditosListDTO;
import cl.bch.cloud.ms.concepto.jdbc.exceptions.TooManyRequestException;
import cl.bch.cloud.ms.concepto.jdbc.services.impl.HelloServiceImpl;
import cl.bch.cloud.ms.concepto.jdbc.dtos.MessageDTO;
import cl.bch.cloud.ms.concepto.jdbc.repositories.JsonPlaceHolderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class HelloServiceTest {

    private HelloService helloService;

    @Mock
    private JsonPlaceHolderRepository jsonPlaceHolderRepository;

    @BeforeEach
    void setUp() {
        helloService = new HelloServiceImpl(jsonPlaceHolderRepository);
    }

    @Test
    public void shouldGetHelloMessage()  {
        ReflectionTestUtils.setField(helloService, "statusMessageOk", "OK");
        ReflectionTestUtils.setField(helloService, "greetingMessage", "Greetings from Spring Boot!");
        CreditosListDTO serviceResponse = helloService.greetings();

        assertThat(serviceResponse.getMessage()).isEqualTo("Greetings from Spring Boot!");
    }

    @Test
    public void noGreetingShouldGetError()  {
        assertThatThrownBy(() -> helloService.noGreetings())
                .isInstanceOf(TooManyRequestException.class)
                .hasMessageContaining("Too many requests");
    }


}