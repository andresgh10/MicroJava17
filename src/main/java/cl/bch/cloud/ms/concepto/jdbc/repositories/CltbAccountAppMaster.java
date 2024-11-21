package cl.bch.cloud.ms.concepto.jdbc.repositories;

import cl.bch.cloud.ms.concepto.jdbc.entities.CreditosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CltbAccountAppMaster extends JpaRepository<CreditosEntity, String>{
    @Query(
            value = "SELECT ALT_ACC_NO, PRODUCT_CODE, ACCOUNT_NUMBER, BRANCH_CODE FROM CLTB_ACCOUNT_APPS_MASTER" +
                    " WHERE CUSTOMER_ID=5621645", nativeQuery = true)
    List<CreditosEntity> getAcountInformation();

}
