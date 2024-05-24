package cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.services;

import cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.dtos.MessageDTO;

public interface HelloService {

    /**
     * Returns a simple greeting text wrapped in a MessageDTO
     * @return {@link MessageDTO} A simple greeting message
     */
    MessageDTO greetings();

    /**
     * This method will always throw a BchException in order to simulate an error
     * @return {@link MessageDTO} A greeting message that will never be returned
     */
    MessageDTO noGreetings();

}