package cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.services;

import cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.dtos.MessageDTO;

public interface RandomService {

    /**
     * Generates a random number between zero and a given max number
     * @param max {@link int} An int with the max value for the random number
     * @return {@link MessageDTO} A message with a text with the random number
     *         generated or an error text if the given number is lower than zero
     */
    MessageDTO random(int max);

}