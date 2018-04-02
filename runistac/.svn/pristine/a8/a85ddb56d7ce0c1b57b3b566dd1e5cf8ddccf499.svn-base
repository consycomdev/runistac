package co.com.runt.runistac.servicio;

import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.logica.*;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import static co.com.runt.runistac.utils.InfoUsuarioUtil.obtenerUsuario;
import javax.servlet.http.HttpServletRequest;

/**
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@4b51d2e7 (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@68ed6252 (key: uuid,
 * value: _UYd94K5oEDSvsK-foXulxg) uuid-_UYd94K5oEDSvsK-foXulxg
 *
 * @generated
 */
@Stateless
@Path("/ConsecutivoSolicitud")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsecutivoSolicitudServicio {

    @EJB
    private ConsecutivoSolicitudLogica logica;

    /**
     * retorna una lista con los ConsecutivoSolicitud que se encuentran en la
     * base de datos
     *
     * @return retorna una lista de ConsecutivoSolicitudDTO
     * @generated
     */
    @GET
    public List<ConsecutivoSolicitudDTO> obtenerTodosConsecutivoSolicituds() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento ConsecutivoSolicitud
     * @return ConsecutivoSolicitud del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public ConsecutivoSolicitudDTO obtenerConsecutivoSolicitud(@PathParam("id") String id) {
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de ConsecutivoSolicitud
     *
     * @param dto ConsecutivoSolicitud a guardar
     * @return ConsecutivoSolicitud con los cambios realizados por el proceso de
     * guardar
     * @generated
     */
    @POST
    public ConsecutivoSolicitudDTO guardarConsecutivoSolicitud(ConsecutivoSolicitudDTO dto) {
        if (dto.getSecuencia()!= null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro ConsecutivoSolicitud con el identificador dado
     *
     * @param id identificador del ConsecutivoSolicitud
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarConsecutivoSolicitud(@PathParam("id") String id) {
        logica.borrar(id);
    }

}
