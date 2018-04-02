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
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@54d9047a (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@5b0ce1c3 (key: uuid,
 * value: _f63MIOcVEDS_DJILSw87Yg) uuid-_f63MIOcVEDS_DJILSw87Yg
 *
 * @generated
 */
@Stateless
@Path("/ValorPoliza")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ValorPolizaServicio {

    @EJB
    private ValorPolizaLogica logica;

    /**
     * retorna una lista con los ValorPoliza que se encuentran en la base de
     * datos
     *
     * @return retorna una lista de ValorPolizaDTO
     * @generated
     */
    @GET
    public List<ValorPolizaDTO> obtenerTodosValorPolizas() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento ValorPoliza
     * @return ValorPoliza del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public ValorPolizaDTO obtenerValorPoliza(@PathParam("id") Long id) {
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de ValorPoliza
     *
     * @param dto ValorPoliza a guardar
     * @return ValorPoliza con los cambios realizados por el proceso de guardar
     * @generated
     */
    @POST
    public ValorPolizaDTO guardarValorPoliza(ValorPolizaDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro ValorPoliza con el identificador dado
     *
     * @param id identificador del ValorPoliza
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarValorPoliza(@PathParam("id") Long id) {
        logica.borrar(id);
    }

}
