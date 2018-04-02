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
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@39828d39 (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@2a05b6ae (key: uuid,
 * value: _a-j9kK5nEDSvsK-foXulxg) uuid-_a-j9kK5nEDSvsK-foXulxg
 *
 * @generated
 */
@Stateless
@Path("/ParametroSistema")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParametroSistemaServicio {

    @EJB
    private ParametroSistemaLogica logica;

    /**
     * retorna una lista con los ParametroSistema que se encuentran en la base
     * de datos
     *
     * @return retorna una lista de ParametroSistemaDTO
     * @generated
     */
    @GET
    public List<ParametroSistemaDTO> obtenerTodosParametroSistemas() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento ParametroSistema
     * @return ParametroSistema del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public ParametroSistemaDTO obtenerParametroSistema(@PathParam("id") String id) {
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de ParametroSistema
     *
     * @param dto ParametroSistema a guardar
     * @return ParametroSistema con los cambios realizados por el proceso de
     * guardar
     * @generated
     */
    @POST
    public ParametroSistemaDTO guardarParametroSistema(ParametroSistemaDTO dto) {
        if (dto.getClave() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro ParametroSistema con el identificador dado
     *
     * @param id identificador del ParametroSistema
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarParametroSistema(@PathParam("id") String id) {
        logica.borrar(id);
    }

}
