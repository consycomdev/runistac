package co.com.runt.runistac.servicio;

import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.logica.*;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@5e422c7f (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@1e34a402 (key: uuid,
 * value: _cM-gMN81EDSQloy9RWqr_g) uuid-_cM-gMN81EDSQloy9RWqr_g
 *
 * @generated
 */
@Stateless
@Path("/ParametroTexto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParametroTextoServicio {

    @EJB
    private ParametroTextoLogica logica;

    /**
     * retorna una lista con los ParametroTexto que se encuentran en la base de
     * datos
     *
     * @return retorna una lista de ParametroTextoDTO
     * @generated
     */
    @GET
    public List<ParametroTextoDTO> obtenerTodosParametroTextos() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento ParametroTexto
     * @return ParametroTexto del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public ParametroTextoDTO obtenerParametroTexto(@PathParam("id") String clave) {
        return logica.obtener(clave);
    }

    /**
     * almacena la informacion de ParametroTexto
     *
     * @param dto ParametroTexto a guardar
     * @return ParametroTexto con los cambios realizados por el proceso de
     * guardar
     * @generated
     */
    @POST
    public ParametroTextoDTO guardarParametroTexto(ParametroTextoDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro ParametroTexto con el identificador dado
     *
     * @param id identificador del ParametroTexto
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarParametroTexto(@PathParam("id") String clave) {
        logica.borrar(clave);
    }

}
