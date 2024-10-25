package cl.bch.cloud.ms.concepto.jdbc.entities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RandomGeneratedTest {

    static int min;
    static int max;
    static int random;
    static String message;
    static String status;
    RandomGenerated randomGenerated;

    @BeforeEach
    public void setup() {
        min = 0;
        max = 10;
        random = 5;
        message = "message";
        status = "status";

        randomGenerated = new RandomGenerated(min, max, random, message, status);

    }

    @Test
    public void shouldGetMin() throws Exception {
        assertThat(randomGenerated.getMin()).isEqualTo(min);
    }

    @Test
    public void shouldGetMax() throws Exception {
        assertThat(randomGenerated.getMax()).isEqualTo(max);
    }

    @Test
    public void shouldGetRandom() throws Exception {
        assertThat(randomGenerated.getRandom()).isEqualTo(random);
    }

    @Test
    public void shouldGetMessage() throws Exception {
        assertThat(randomGenerated.getMessage()).isEqualTo(message);
    }

    @Test
    public void shouldGetStatus() throws Exception {
        assertThat(randomGenerated.getStatus()).isEqualTo(status);
    }

    @Test
    public void shouldSetMin() throws Exception {
        randomGenerated.setMin(randomGenerated.getMin() + 1);
        assertThat(randomGenerated.getMin()).isEqualTo(min + 1);
    }

    @Test
    public void shouldSetMax() throws Exception {
        randomGenerated.setMax(randomGenerated.getMax() + 1);
        assertThat(randomGenerated.getMax()).isEqualTo(max + 1);
    }

    @Test
    public void shouldSetRandom() throws Exception {
        randomGenerated.setRandom(randomGenerated.getRandom() + 1);
        assertThat(randomGenerated.getRandom()).isEqualTo(random + 1);
    }

    @Test
    public void shouldSetMessage() throws Exception {
        randomGenerated.setMessage("new" + randomGenerated.getMessage());
        assertThat(randomGenerated.getMessage()).isEqualTo("new" + message);
    }

    @Test
    public void shouldSetStatus() throws Exception {
        randomGenerated.setStatus("new" + randomGenerated.getStatus());
        assertThat(randomGenerated.getStatus()).isEqualTo("new" + status);
    }

    @Test
    public void shouldBeEqualsTwoEntities() throws Exception {
        RandomGenerated firstEntity = new RandomGenerated(1, 10, 5, "message", "status");
        RandomGenerated secondEntity = new RandomGenerated(1, 10, 5, "message", "status");
        assertThat(firstEntity.hashCode() == secondEntity.hashCode());
        assertThat(firstEntity.equals(secondEntity) && secondEntity.equals(firstEntity));
    }

    @Test
    public void shouldBeEqualToSelf() throws Exception {
        RandomGenerated firstEntity = new RandomGenerated(1, 10, 5, "message", "status");
        assertThat(firstEntity.equals(firstEntity));
    }

    @Test
    public void shouldNotBeEquals() throws Exception {
        RandomGenerated firstEntity = new RandomGenerated(1, 10, 5, "message", "status");
        RandomGenerated secondEntity = null;
        assertFalse(firstEntity.equals(secondEntity));
    }

    @Test
    public void shouldNotBeNullToString() throws Exception {
        RandomGenerated firstEntity = new RandomGenerated(1, 10, 5, "message", "status");
        assertFalse(firstEntity.toString().equals(null));
    }

}