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
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@78fa3801 (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@737307b3 (key: uuid,
 * value: _HoLs0K5pEDSvsK-foXulxg) uuid-_HoLs0K5pEDSvsK-foXulxg
 *
 * @generated
 */
@Stateless
@Path("/PersonaNatural")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonaNaturalServicio {

    @EJB
    private PersonaNaturalLogica logica;

    /**
     * retorna una lista con los PersonaNatural que se encuentran en la base de
     * datos
     *
     * @return retorna una lista de PersonaNaturalDTO
     * @generated
     */
    @GET
    public List<PersonaNaturalDTO> obtenerTodosPersonaNaturals() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento PersonaNatural
     * @return PersonaNatural del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public PersonaNaturalDTO obtenerPersonaNatural(@PathParam("id") Long id) {
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de PersonaNatural
     *
     * @param dto PersonaNatural a guardar
     * @return PersonaNatural con los cambios realizados por el proceso de
     * guardar
     * @generated
     */
    @POST
    public PersonaNaturalDTO guardarPersonaNatural(PersonaNaturalDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro PersonaNatural con el identificador dado
     *
     * @param id identificador del PersonaNatural
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarPersonaNatural(@PathParam("id") Long id) {
        logica.borrar(id);
    }

}
