package cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.repositories;

import cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.dtos.PostDTO;
import cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.restclients.JsonPlaceHolderClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class JsonPlaceHolderRepositoryTest {

    private JsonPlaceHolderRepository jsonPlaceHolderRepository;

    @Mock
    private JsonPlaceHolderClient jsonPlaceHolderClient;

    @BeforeEach
    public void setup() {
        jsonPlaceHolderRepository = new JsonPlaceHolderRepository(jsonPlaceHolderClient);
    }

    @Test
    public void shouldGetNotNullStatusMessageOk()  {
        Mockito.when(jsonPlaceHolderClient.getAll()).thenReturn(new ArrayList<>());
        List<PostDTO> repositoryResponse = jsonPlaceHolderRepository.getAllPosts();
        Assertions.assertThat(repositoryResponse).isEmpty();
    }

}