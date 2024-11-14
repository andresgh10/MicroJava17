package cl.bch.cloud.ms.concepto.jdbc.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
/*
Import usado para un standar en la respuesta json. Por ejemplo listadoCredito -> listado_Creditos
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
 */
public class CreditosListDTO {
    private String status;
    private String message;
    private List<CreditosDTO> listadoCreditos;
}
