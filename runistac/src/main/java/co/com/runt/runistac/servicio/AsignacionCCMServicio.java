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
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@6e87fd90 (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@221831a1 (key: uuid,
 * value: _h6h_QK5pEDSvsK-foXulxg) uuid-_h6h_QK5pEDSvsK-foXulxg
 *
 * @generated
 */
@Stateless
@Path("/AsignacionCCM")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AsignacionCCMServicio {

    @EJB
    private AsignacionCCMLogica logica;

    /**
     * retorna una lista con los AsignacionCCM que se encuentran en la base de
     * datos
     *
     * @return retorna una lista de AsignacionCCMDTO
     * @generated
     */
    @GET
    public List<AsignacionCCMDTO> obtenerTodosAsignacionCCMs() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento AsignacionCCM
     * @return AsignacionCCM del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public AsignacionCCMDTO obtenerAsignacionCCM(@PathParam("id") Long id) {
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de AsignacionCCM
     *
     * @param dto AsignacionCCM a guardar
     * @return AsignacionCCM con los cambios realizados por el proceso de
     * guardar
     * @generated
     */
    @POST
    public AsignacionCCMDTO guardarAsignacionCCM(AsignacionCCMDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro AsignacionCCM con el identificador dado
     *
     * @param id identificador del AsignacionCCM
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarAsignacionCCM(@PathParam("id") Long id) {
        logica.borrar(id);
    }

}
