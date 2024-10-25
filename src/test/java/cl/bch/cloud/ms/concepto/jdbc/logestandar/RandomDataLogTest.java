package cl.bch.cloud.ms.concepto.jdbc.logestandar;

import cl.bch.cloud.logging.enums.Action;
import cl.bch.cloud.logging.exceptions.LogEstandarException;
import cl.bch.cloud.ms.concepto.jdbc.dtos.MessageDTO;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomDataLogTest {
    @Test
    public void resultadoTest(){
        var datos = new HashMap<Action, Object>();
        var dataLog = new RandomDataLog();
        var responseNOK = new MessageDTO("NOK", "");
        var responseOK = new MessageDTO("OK", "");

        assertThrows(LogEstandarException.class, () -> {
            dataLog.resultado(datos);
        });

        datos.put(Action.RESPONSE, responseOK);
        assertEquals("A", dataLog.resultado(datos));
        datos.clear();

        datos.put(Action.RESPONSE, responseNOK);
        assertEquals("R", dataLog.resultado(datos));
    }

    @Test
    public void generarDataVariableTest(){
        var datos = new HashMap<Action, Object>();
        var arguments = new Object[1];
        arguments[0] = 50;
        var dataLog = new RandomDataLog();
        var responseOK = new MessageDTO("OK", "Mensaje de prueba");

        datos.put(Action.RESPONSE, responseOK);
        datos.put(Action.DATA, arguments);

        var logLine = dataLog.generarDataVariable(datos);
        assertNotNull(logLine);
        assertTrue(logLine.contains("Mensaje de prueba"));
        assertEquals(156, logLine.length());
        assertEquals("000050Mensaje de prueba".concat(" ".repeat(133)), logLine);
    }
}
