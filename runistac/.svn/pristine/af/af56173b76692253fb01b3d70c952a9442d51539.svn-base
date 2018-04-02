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
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@307e1191 (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@63d06cc7 (key: uuid,
 * value: _f5q58d9tEDSoEPq95V4_AQ) uuid-_f5q58d9tEDSoEPq95V4_AQ
 *
 * @generated
 */
@Stateless
@Path("/RegistroEstados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistroEstadosServicio {

    @EJB
    private RegistroEstadosLogica logica;

    /**
     * retorna una lista con los RegistroEstados que se encuentran en la base de
     * datos
     *
     * @return retorna una lista de RegistroEstadosDTO
     * @generated
     */
    @GET
    public List<RegistroEstadosDTO> obtenerTodosRegistroEstadoss() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento RegistroEstados
     * @return RegistroEstados del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public RegistroEstadosDTO obtenerRegistroEstados(@PathParam("id") Long id) {
        return logica.obtener(id);
    }
    
    @GET
    @Path("/postulacion/{id}")
    public List<RegistroEstadosDTO> obtenerPorPostulacion(@PathParam("id") Long id) {
        return logica.obtenerPorPostulacion(id);
    }

    /**
     * almacena la informacion de RegistroEstados
     *
     * @param dto RegistroEstados a guardar
     * @return RegistroEstados con los cambios realizados por el proceso de
     * guardar
     * @generated
     */
    @POST
    public RegistroEstadosDTO guardarRegistroEstados(RegistroEstadosDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro RegistroEstados con el identificador dado
     *
     * @param id identificador del RegistroEstados
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarRegistroEstados(@PathParam("id") Long id) {
        logica.borrar(id);
    }

}
