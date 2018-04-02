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
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@418affbf (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@c9c7637 (key: uuid,
 * value: _6x598K5oEDSvsK-foXulxg) uuid-_6x598K5oEDSvsK-foXulxg
 *
 * @generated
 */
@Stateless
@Path("/Automotor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutomotorServicio {

    @EJB
    private AutomotorLogica logica;

    /**
     * retorna una lista con los Automotor que se encuentran en la base de datos
     *
     * @return retorna una lista de AutomotorDTO
     * @generated
     */
    @GET
    public List<AutomotorDTO> obtenerTodosAutomotors() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento Automotor
     * @return Automotor del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public AutomotorDTO obtenerAutomotor(@PathParam("id") Long id) {
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de Automotor
     *
     * @param dto Automotor a guardar
     * @return Automotor con los cambios realizados por el proceso de guardar
     * @generated
     */
    @POST
    public AutomotorDTO guardarAutomotor(AutomotorDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro Automotor con el identificador dado
     *
     * @param id identificador del Automotor
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarAutomotor(@PathParam("id") Long id) {
        logica.borrar(id);
    }
    
    @GET
    @Path("/propietario/{id}")
    public List<ConsultaVehiculosDTO> consultarInformacionPropiedades(@PathParam("id") Long idPersona){
        return logica.consultarInformacionPropiedades(idPersona);
    }
    
    @GET
    @Path("/vin/{vin}")
    public ConsultaVINDTO consultarVehiculoVIN(@PathParam("vin") String vin){
        return logica.consultarVin(vin);
    }
    
    @GET
    @Path("/validarFTHCarroceria/{id}/{fth}")
    public String validarFTHCarroceria(@PathParam("id") Long id, @PathParam("fth") String fth){
        return logica.validarFTHCarroceria(id,fth);
    }

}
