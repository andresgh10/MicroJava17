package cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.NonNull;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MessageDTO(
        @NonNull
        String status,
        @NonNull
        String message) implements Serializable {
}