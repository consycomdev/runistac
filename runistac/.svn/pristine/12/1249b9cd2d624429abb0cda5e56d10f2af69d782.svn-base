package co.com.runt.runistac.servicio;

import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.logica.*;
import co.com.runt.runistac.persistencia.entity.TipoEntidadFinanciera;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import static co.com.runt.runistac.utils.InfoUsuarioUtil.obtenerUsuario;
import javax.servlet.http.HttpServletRequest;

/**
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@5484303e (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@327b61cb (key: uuid,
 * value: _oeEmoLmfEDSSG6VucD1K-g) uuid-_oeEmoLmfEDSSG6VucD1K-g
 *
 * @generated
 */
@Stateless
@Path("/EntidadFinanciera")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntidadFinancieraServicio {

    @EJB
    private EntidadFinancieraLogica logica;
    
    @Context
    private HttpServletRequest request;

    /**
     * retorna una lista con los Banco que se encuentran en la base de datos
     *
     * @return retorna una lista de BancoDTO
     * @generated
     */
    @GET
    public List<EntidadFinancieraDTO> obtenerTodosEntidadFinanciera() {
        return logica.obtenerTodos();
    }
    
    @GET
    @Path("Banco")
    public List<EntidadFinancieraDTO> obtenerTodosBancos() {
        return logica.obtenerPorTipo(TipoEntidadFinanciera.BANCO);
    }
    
    @GET
    @Path("Aseguradora")
    public List<EntidadFinancieraDTO> obtenerTodosAseguradora() {
        return logica.obtenerPorTipo(TipoEntidadFinanciera.ASEGURADORA);
    }
    
    @GET
    @Path("esLeasing")
    public EntidadFinancieraDTO obtenerEsLeasing() {
        return logica.obtenerEsLeasing(obtenerUsuario(request).getUsuario().replaceAll("NIT\\-", "").replaceAll("N\\-", ""));
    }

    /**
     * @param id identificador del elemento Banco
     * @return Banco del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public EntidadFinancieraDTO obtenerBanco(@PathParam("id") Long id) {
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de Banco
     *
     * @param dto Banco a guardar
     * @return Banco con los cambios realizados por el proceso de guardar
     * @generated
     */
    @POST
    public EntidadFinancieraDTO guardarBanco(EntidadFinancieraDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro Banco con el identificador dado
     *
     * @param id identificador del Banco
     * @generated
     */
    @GET
    @Path("/d/{id}")
    public void borrarBanco(@PathParam("id") Long id) {
        logica.borrar(id);
    }

}
