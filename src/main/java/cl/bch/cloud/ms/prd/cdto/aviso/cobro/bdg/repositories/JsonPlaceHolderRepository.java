package cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.repositories;

import cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.dtos.PostDTO;
import cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.restclients.JsonPlaceHolderClient;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * An interface that consumes an external REST API using Feign
 */
@Observed
@Repository("jsonPlaceHolderRepository")
@RequiredArgsConstructor
public class JsonPlaceHolderRepository {

    private final JsonPlaceHolderClient jsonPlaceHolderClient;

    /**
     * Fetches a list of posts from an external REST API
     * @return {@link List<PostDTO>}
     */
    public List<PostDTO> getAllPosts() {
        return jsonPlaceHolderClient.getAll();
    }

}
