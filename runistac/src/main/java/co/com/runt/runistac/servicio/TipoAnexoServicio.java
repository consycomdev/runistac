package co.com.runt.runistac.servicio;

import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.logica.*;
import co.com.runt.runistac.persistencia.entity.TipoProceso;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@1741d59f (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@632fc5a6 (key: uuid,
 * value: _ZBM5AK5nEDSvsK-foXulxg) uuid-_ZBM5AK5nEDSvsK-foXulxg
 *
 * @generated
 */
@Stateless
@Path("/TipoAnexo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoAnexoServicio {

    @EJB
    private TipoAnexoLogica logica;

    /**
     * retorna una lista con los TipoAnexo que se encuentran en la base de datos
     *
     * @return retorna una lista de TipoAnexoDTO
     * @generated
     */
    @GET
    public List<TipoAnexoDTO> obtenerSolicitud() {
        return logica.obtenerSolicitud();
    }
    
    @GET
    @Path("consulta")
    public List<TipoAnexoDTO> obtenerTodosSolicitud() {
        return logica.obtenerTodos();
    }
    
    @GET
    @Path("registroPoliza")
    public List<TipoAnexoDTO> obtenerTodosRegistroPoliza() {
        return logica.obtenerRegistroPoliza();
    }
    
    @GET
    @Path("/tipo/{tipo}")
    public List<TipoAnexoDTO> obtenerTodosRegistroPolizaTipo(@PathParam("tipo") TipoProceso tipo) {
        return logica.obtenerRegistroPolizaTipo(tipo);
    }

    /**
     * @param id identificador del elemento TipoAnexo
     * @return TipoAnexo del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public TipoAnexoDTO obtenerTipoAnexo(@PathParam("id") Long id) {
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de TipoAnexo
     *
     * @param dto TipoAnexo a guardar
     * @return TipoAnexo con los cambios realizados por el proceso de guardar
     * @generated
     */
    @POST
    public TipoAnexoDTO guardarTipoAnexo(TipoAnexoDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro TipoAnexo con el identificador dado
     *
     * @param id identificador del TipoAnexo
     * @generated
     */
    @GET
    @Path("/d/{id}")
    public void borrarTipoAnexo(@PathParam("id") Long id) {
        logica.borrar(id);
    }

}
