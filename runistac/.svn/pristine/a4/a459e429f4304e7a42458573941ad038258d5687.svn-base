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
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@5906cbb (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@4cd26bd7 (key: uuid,
 * value: _pCkQQLmeEDSSG6VucD1K-g) uuid-_pCkQQLmeEDSSG6VucD1K-g
 *
 * @generated
 */
@Stateless
@Path("/PagosMT")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagosMTServicio {

    @EJB
    private PagosMTLogica logica;

    /**
     * retorna una lista con los PagosMT que se encuentran en la base de datos
     *
     * @return retorna una lista de PagosMTDTO
     * @generated
     */
    @GET
    public List<PagosMTDTO> obtenerTodosPagosMTs() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento PagosMT
     * @return PagosMT del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public PagosMTDTO obtenerPagosMT(@PathParam("id") Long id) {
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de PagosMT
     *
     * @param dto PagosMT a guardar
     * @return PagosMT con los cambios realizados por el proceso de guardar
     * @generated
     */
    @POST
    public PagosMTDTO guardarPagosMT(PagosMTDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro PagosMT con el identificador dado
     *
     * @param id identificador del PagosMT
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarPagosMT(@PathParam("id") Long id) {
        logica.borrar(id);
    }

    @GET
    @Path("/postulacion/{id}")
    public List<PagosMTDTO> obtenerPagosPorPostulacion(@PathParam("id") Long idPostulacion) {
        return logica.obtenerPorPostulacion(idPostulacion);
    }
}
