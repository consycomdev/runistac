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
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@6074eb6e (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@1c0cb43b (key: uuid,
 * value: _W9mCgK5nEDSvsK-foXulxg) uuid-_W9mCgK5nEDSvsK-foXulxg
 *
 * @generated
 */
@Stateless
@Path("/CertificadoCancelacionMatricula")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CertificadoCancelacionMatriculaServicio {

    @EJB
    private CertificadoCancelacionMatriculaLogica logica;

    /**
     * retorna una lista con los CertificadoCancelacionMatricula que se
     * encuentran en la base de datos
     *
     * @return retorna una lista de CertificadoCancelacionMatriculaDTO
     * @generated
     */
    @GET
    public List<CertificadoCancelacionMatriculaDTO> obtenerTodosCertificadoCancelacionMatriculas() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento CertificadoCancelacionMatricula
     * @return CertificadoCancelacionMatricula del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public CertificadoCancelacionMatriculaDTO obtenerCertificadoCancelacionMatricula(@PathParam("id") Long id) {
        return logica.obtener(id);
    }
    
    /**
     * obtiene la cantidad de certificados en estado nuevo
     * @return 
     */
    @GET
    @Path("/disponible")
    public Integer obtenerCertificadoCancelacionMatricula() {
        return logica.obtenerDisponibles();
    }

    /**
     * almacena la informacion de CertificadoCancelacionMatricula
     *
     * @param dto CertificadoCancelacionMatricula a guardar
     * @return CertificadoCancelacionMatricula con los cambios realizados por el
     * proceso de guardar
     * @generated
     */
    @POST
    public CertificadoCancelacionMatriculaDTO guardarCertificadoCancelacionMatricula(CertificadoCancelacionMatriculaDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }
    
    /**
     * consulta la lista de certificados por los parametros dados
     * @param dto parametros a consultar
     * @return 
     */
    @POST
    @Path("/consulta")
    public List<CertificadoCancelacionMatriculaDTO> consultar(ConsultaCCMDTO dto){
        return logica.consultar(dto);
    }

    /**
     * elimina el registro CertificadoCancelacionMatricula con el identificador
     * dado
     *
     * @param id identificador del CertificadoCancelacionMatricula
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarCertificadoCancelacionMatricula(@PathParam("id") Long id) {
        logica.borrar(id);
    }
    
    @GET
    @Path("/asignacion/{id}")
    public List<CertificadoCancelacionMatriculaDTO> obtenerPorAsignacion(@PathParam("id") Long idAsignacion){
        return logica.obtenerPorAsignacion(idAsignacion);
    }
    
    @GET
    @Path("/generarCCM")
    public String generarCertificados(){
        logica.generarCCM();
        return "ok";
    }
    
    @GET
    @Path("/consultaEstado")
    public List<ConsultaEstadoCCMDTO> consultarCCMPorEstado() {
        return logica.consultarCCMPorEstado();
    }


}
