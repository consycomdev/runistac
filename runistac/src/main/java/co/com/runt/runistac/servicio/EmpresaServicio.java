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
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@489d1e8e (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@434121e0 (key: uuid,
 * value: _7Wbx4K5pEDSvsK-foXulxg) uuid-_7Wbx4K5pEDSvsK-foXulxg
 *
 * @generated
 */
@Stateless
@Path("/Empresa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpresaServicio {

    @EJB
    private EmpresaLogica logica;

    /**
     * retorna una lista con los Empresa que se encuentran en la base de datos
     *
     * @return retorna una lista de EmpresaDTO
     * @generated
     */
    @GET
    public List<EmpresaDTO> obtenerTodosEmpresas() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento Empresa
     * @return Empresa del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public EmpresaDTO obtenerEmpresa(@PathParam("id") Long id) {
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de Empresa
     *
     * @param dto Empresa a guardar
     * @return Empresa con los cambios realizados por el proceso de guardar
     * @generated
     */
    @POST
    public EmpresaDTO guardarEmpresa(EmpresaDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro Empresa con el identificador dado
     *
     * @param id identificador del Empresa
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarEmpresa(@PathParam("id") Long id) {
        logica.borrar(id);
    }

}
