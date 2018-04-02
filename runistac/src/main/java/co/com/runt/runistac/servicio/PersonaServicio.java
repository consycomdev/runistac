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
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@74af7b09 (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@2cb6c7b8 (key: uuid,
 * value: _JehjYK5pEDSvsK-foXulxg) uuid-_JehjYK5pEDSvsK-foXulxg
 *
 * @generated
 */
@Stateless
@Path("/Persona")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonaServicio {

    @EJB
    private PersonaLogica logica;

    /**
     * retorna una lista con los Persona que se encuentran en la base de datos
     *
     * @return retorna una lista de PersonaDTO
     * @generated
     */
    @GET
    public List<PersonaDTO> obtenerTodosPersonas() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento Persona
     * @return Persona del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public PersonaDTO obtenerPersona(@PathParam("id") Long id) {
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de Persona
     *
     * @param dto Persona a guardar
     * @return Persona con los cambios realizados por el proceso de guardar
     * @generated
     */
    @POST
    public PersonaDTO guardarPersona(PersonaDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro Persona con el identificador dado
     *
     * @param id identificador del Persona
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarPersona(@PathParam("id") Long id) {
        logica.borrar(id);
    }

    @GET
    @Path("/{tipoDocumento}/{numeroDocumento}")
    public PersonaDTO obtenerTipoYNumeroDocumento(@PathParam("tipoDocumento") String tipoDocumento,@PathParam("numeroDocumento") String numeroDocumento) {
        return logica.obtenerTipoYNumeroDocumento(tipoDocumento, numeroDocumento);
    }
    
}
