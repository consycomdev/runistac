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
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@4d61e412 (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@5b8c85b4 (key: uuid,
 * value: _pCkQQLmeEDSSG6VucD1K-g) uuid-_pCkQQLmeEDSSG6VucD1K-g
 *
 * @generated
 */
@Stateless
@Path("/PolizaCaucion")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PolizaCaucionServicio {

    @EJB
    private PolizaCaucionLogica logica;

    /**
     * retorna una lista con los PolizaCaucion que se encuentran en la base de
     * datos
     *
     * @return retorna una lista de PolizaCaucionDTO
     * @generated
     */
    @GET
    public List<PolizaCaucionDTO> obtenerTodosPolizaCaucions() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento PolizaCaucion
     * @return PolizaCaucion del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public PolizaCaucionDTO obtenerPolizaCaucion(@PathParam("id") Long id) {
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de PolizaCaucion
     *
     * @param dto PolizaCaucion a guardar
     * @return PolizaCaucion con los cambios realizados por el proceso de
     * guardar
     * @generated
     */
    @POST
    public PolizaCaucionDTO guardarPolizaCaucion(PolizaCaucionDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro PolizaCaucion con el identificador dado
     *
     * @param id identificador del PolizaCaucion
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarPolizaCaucion(@PathParam("id") Long id) {
        logica.borrar(id);
    }
    
    @GET
    @Path("/postulacion/{id}")
    public PolizaCaucionDTO obtenerPorPostulacion(@PathParam("id") Long idPostulacion) {
        return logica.obtenerPorPostulacion(idPostulacion);
    }
}
