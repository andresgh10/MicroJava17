package cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.services;

import cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.dtos.MessageDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RandomServiceTest {

    @Autowired
    private RandomService randomService;

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
    public void shouldGetRandomNumberMessage() throws Exception {
        int max = min + 1;
        MessageDTO serviceResponse = randomService.random(max);
        Assertions.assertThat(serviceResponse.status()).isEqualTo(statusMessageOk);
        Assertions.assertThat(serviceResponse.message()).contains(String.format(okMessage, min, max, ""));
    }

    @Test
    public void shouldGetErrorMessage() throws Exception {
        int max = min - 1;
        MessageDTO serviceResponse = randomService.random(max);
        Assertions.assertThat(serviceResponse.status()).isEqualTo(statusMessageNok);
        Assertions.assertThat(serviceResponse.message()).contains(String.format(errorMessage, min));
    }

}