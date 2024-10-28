package cl.bch.cloud.ms.concepto.jdbc.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity(name = "CLTB_ACCOUNT_APPS_MASTER")
@Table(schema = "CORE_FCUBS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditosEntity {

    @Id()
    @Column(
            name = "ALT_ACC_NO")
    private String altAccNo;

    @Column(name = "PRODUCT_CODE")
    private int productCode;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "BRANCH_CODE")
    private String branchCode;
}
