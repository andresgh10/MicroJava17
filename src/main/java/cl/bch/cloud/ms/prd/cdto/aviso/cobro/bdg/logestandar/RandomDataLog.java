package cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.logestandar;

import cl.bch.cloud.logging.domains.DataLogEstandar;
import cl.bch.cloud.logging.enums.Action;
import cl.bch.cloud.logging.enums.Alineacion;
import cl.bch.cloud.logging.enums.Relleno;
import cl.bch.cloud.logging.utils.LogEstandarUtils;
import cl.bch.cloud.logging.utils.LogUtils;
import cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.dtos.MessageDTO;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

/**
 * Clase para generar Log Estándar en base a la implementación con lib-ms-log-estandar
 * Se pueden implementar métodos de DataLogEstandar para setear datos de Data Fija y se DEBE implementar
 * generarDataVariable para poder generar la Data Variable del log específico
 *
 * La clase LogEstándarUtils provee métodos para recuperar del mapa de data la información de:
 * - argumentos del controller
 * - excepción lanzada (si es que existe)
 * - objeto de respuesta
 */
@NoArgsConstructor
public class RandomDataLog implements DataLogEstandar {

    private final static String NOK = "NOK";

    @Override
    public String resultado(Map<Action, Object> data) {
        var response = LogEstandarUtils.getResponse(data, MessageDTO.class);
        if(Objects.nonNull(response) && NOK.equals(response.status())) {
            return "R";
        }
        return "A";
    }

    @Override
    public String generarDataVariable(Map<Action, Object> data) {
        var maxRandom = LogEstandarUtils.getArgument(data, Integer.class);
        var response = LogEstandarUtils.getResponse(data, MessageDTO.class);
        var strbuilder = new StringBuilder();
        strbuilder.append(LogUtils.formatMensaje(String.valueOf(maxRandom), 6, Relleno.ZERO, Alineacion.DERECHA))
                .append(LogUtils.formatMensaje(response.message(), 150, Relleno.ESPACIO, Alineacion.IZQUIERDA));
        return strbuilder.toString();
    }
}
