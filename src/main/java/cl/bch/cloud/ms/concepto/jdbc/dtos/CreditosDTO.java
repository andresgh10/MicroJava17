package cl.bch.cloud.ms.concepto.jdbc.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreditosDTO {
    private String llave;
    private String oficina;
    private Integer to;
}
