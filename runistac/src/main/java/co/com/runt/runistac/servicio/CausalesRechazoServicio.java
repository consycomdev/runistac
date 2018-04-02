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
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@14149a96 (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@54b0f826 (key: uuid,
 * value: _14OHYLjKEDSWntwNo5onzQ) uuid-_14OHYLjKEDSWntwNo5onzQ
 *
 * @generated
 */
@Stateless
@Path("/CausalesRechazo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CausalesRechazoServicio {

    @EJB
    private CausalesRechazoLogica logica;

    /**
     * retorna una lista con los CausalesRechazo que se encuentran en la base de
     * datos
     *
     * @return retorna una lista de CausalesRechazoDTO
     * @generated
     */
    @GET
    public List<CausalesRechazoDTO> obtenerTodosCausalesRechazos() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento CausalesRechazo
     * @return CausalesRechazo del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public CausalesRechazoDTO obtenerCausalesRechazo(@PathParam("id") Long id) {
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de CausalesRechazo
     *
     * @param dto CausalesRechazo a guardar
     * @return CausalesRechazo con los cambios realizados por el proceso de
     * guardar
     * @generated
     */
    @POST
    public CausalesRechazoDTO guardarCausalesRechazo(CausalesRechazoDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro CausalesRechazo con el identificador dado
     *
     * @param id identificador del CausalesRechazo
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarCausalesRechazo(@PathParam("id") Long id) {
        logica.borrar(id);
    }
    
    @GET
    @Path("/rechazo")
    public List<CausalesRechazoDTO> obtenerCausalRechazo(){
        return logica.obtenerPorTipo(TipoCausalRechazo.RECHAZO);
    }
    
    @GET
    @Path("/devolucion")
    public List<CausalesRechazoDTO> obtenerCausalDevolucion(){
        return logica.obtenerPorTipo(TipoCausalRechazo.DEVOLUCION);
    }

}
