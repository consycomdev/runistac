/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.runt.common.excepciones;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author daniel
 */
public class ApplicationException extends WebApplicationException {

    private Logger log=Logger.getLogger(ApplicationException.class.getName());
    
    public ApplicationException(String mensaje) {
        super(Response.status(511)
                .type("application/json")
                .entity("{" + ((char) 0x22) + "mensaje" + ((char) 0x22) + ":" + ((char) 0x22) + procesarTexto(mensaje) + ((char) 0x22) + "}")
                .build());
        log.log(Level.SEVERE, mensaje);
    }

    public ApplicationException(String mensaje, Throwable e) {
        super(Response.status(511)
                .type("application/json")
                .entity("{" + ((char) 0x22) + "mensaje" + ((char) 0x22) + ":" + ((char) 0x22) + procesarTexto(mensaje + obtenerUltimaExcepcion(e).getMessage()) + ((char) 0x22) + "}")
                .build()
        );
        e.printStackTrace();
    }

    /**
     * convierte los saltos de linea y tabuladores para que se interprete en el
     * javascript
     */
    private static String procesarTexto(String texto) {
        return texto.replaceAll("\n", "\\\\n")
                .replaceAll("\t", "\\\\t");
    }

    /**
     * Metodo recursivo que obtiene la excepcion original
     */
    private static Throwable obtenerUltimaExcepcion(Throwable e) {
        if (e.getCause() != null) {
            return obtenerUltimaExcepcion(e.getCause());
        } else {
            return e;
        }
    }

}
