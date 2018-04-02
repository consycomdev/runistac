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
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@2659af10 (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@49f4571b (key: uuid,
 * value: _It3GAK5qEDSvsK-foXulxg) uuid-_It3GAK5qEDSvsK-foXulxg
 *
 * @generated
 */
@Stateless
@Path("/TipoDocumento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoDocumentoServicio {

    @EJB
    private TipoDocumentoLogica logica;

    /**
     * retorna una lista con los TipoDocumento que se encuentran en la base de
     * datos
     *
     * @return retorna una lista de TipoDocumentoDTO
     * @generated
     */
    @GET
    public List<TipoDocumentoDTO> obtenerTodosTipoDocumentos() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento TipoDocumento
     * @return TipoDocumento del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public TipoDocumentoDTO obtenerTipoDocumento(@PathParam("id") Long id) {
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de TipoDocumento
     *
     * @param dto TipoDocumento a guardar
     * @return TipoDocumento con los cambios realizados por el proceso de
     * guardar
     * @generated
     */
    @POST
    public TipoDocumentoDTO guardarTipoDocumento(TipoDocumentoDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro TipoDocumento con el identificador dado
     *
     * @param id identificador del TipoDocumento
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarTipoDocumento(@PathParam("id") Long id) {
        logica.borrar(id);
    }

}
