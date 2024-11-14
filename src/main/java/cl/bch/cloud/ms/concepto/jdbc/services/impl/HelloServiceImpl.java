package cl.bch.cloud.ms.concepto.jdbc.services.impl;

import cl.bch.cloud.ms.concepto.jdbc.dtos.CreditosDTO;
import cl.bch.cloud.ms.concepto.jdbc.dtos.CreditosListDTO;
import cl.bch.cloud.ms.concepto.jdbc.entities.CreditosEntity;
import cl.bch.cloud.ms.concepto.jdbc.dtos.MessageDTO;
import cl.bch.cloud.ms.concepto.jdbc.exceptions.TooManyRequestException;

import cl.bch.cloud.ms.concepto.jdbc.repositories.CreditosRepository;
import cl.bch.cloud.ms.concepto.jdbc.repositories.JsonPlaceHolderRepository;
import cl.bch.cloud.ms.concepto.jdbc.services.HelloService;
import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Observed
@Service("HelloService")
@RequiredArgsConstructor
public class HelloServiceImpl implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Value("${properties.helloService.statusMessageOk}")
    private String statusMessageOk;

    @Value("${properties.helloService.greetingMessage}")
    private String greetingMessage;

    private final JsonPlaceHolderRepository jsonPlaceHolderRepository;

    /**
     * Returns a simple greeting text wrapped in a MessageDTO
     * @return {@link MessageDTO} A simple greeting message
     */
    @Override
    public CreditosListDTO greetings() {

        String sql = "SELECT ALT_ACC_NO, PRODUCT_CODE, ACCOUNT_NUMBER, BRANCH_CODE FROM CLTB_ACCOUNT_APPS_MASTER "+
                "WHERE CUSTOMER_ID=5621645";
        System.out.println("Query: "+sql);
         List<CreditosEntity> creditoBD = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(CreditosEntity.class));
        System.out.println(creditoBD);
        System.out.println("Cantidad Creditos: "+creditoBD.size());
        /*return new MessageDTO(statusMessageOk, greetingMessage+", TO: "+credito.get(0).getProductCode()+
                " Llave:"+credito.get(0).getAltAccNo());*/
        List<CreditosDTO> creditosListaFinal = new ArrayList<>();

        for (var i=0; i<creditoBD.size(); i++ ){
            /*Variable debe ir dentro del for ya que se debe iniciar una cada vez que que se itera
              de caso contrario se mapearea en response el ultimo credito de la BD*/
            CreditosDTO credito = new CreditosDTO();
            credito.setLlave(creditoBD.get(i).getAltAccNo());
            credito.setOficina(creditoBD.get(i).getBranchCode());
            credito.setTo(creditoBD.get(i).getProductCode());
            creditosListaFinal.add(i,credito);
        }
        return new CreditosListDTO(statusMessageOk,greetingMessage,creditosListaFinal);
    }

    /**
     * This method will always throw a BchException after fetching post from 
     * an external REST API using feign in order to simulate an error
     * @return {@link MessageDTO} A greeting message that will never be returned
     */
    @Override
    public MessageDTO noGreetings() {
        try {
            jsonPlaceHolderRepository.getAllPosts();
            logger.info("noGreetings - Esta excepcion es solo ilustrativa para mostrar el @ControllerAdvice");
            throw new TooManyRequestException("Too many requests", new RuntimeException());
        } finally {
            /* Esta excepción es sólo ilustrativa para mostrar el @ControllerAdvice */
            logger.info("finnaly noGreeting");
        }
    }

}