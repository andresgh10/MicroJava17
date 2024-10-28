package cl.bch.cloud.ms.concepto.jdbc.dtos;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FlowAccountResponseDTO{

    private String altAccNo;

    private Integer productCode;

    private String accountNumber;

    private String branchCode;

}