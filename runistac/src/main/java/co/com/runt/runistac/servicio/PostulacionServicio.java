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
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

/**
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@499aa508 (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@47c8d5f8 (key: uuid,
 * value: _HIh5MK5lEDSvsK-foXulxg) uuid-_HIh5MK5lEDSvsK-foXulxg
 *
 * @generated
 */
@Stateless
@Path("/Postulacion")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostulacionServicio {

    @EJB
    private PostulacionLogica logica;
    
    @Context
    private HttpServletRequest request;
    
    @EJB
    private ReporteLogica reporteLogica;
    
    /**
     * retorna una lista con los Postulacion que se encuentran en la base de
     * datos
     *
     * @return retorna una lista de PostulacionDTO
     * @generated
     */
    @GET
    public List<PostulacionDTO> obtenerTodosPostulacions() {
        logica.setInfoUsuario(obtenerUsuario(request));
        return logica.obtenerTodos();
    }
    
    @POST
    @Path("/filtro")
    public List<PostulacionDTO> obtenerPostulacionesPorFiltro(ConsultaPostulacionDTO dto) {
        logica.setInfoUsuario(obtenerUsuario(request));
        return logica.obtenerPorFiltro(dto);
    }
    
    @POST
    @Path("/filtroExcel")
    public String obtenerPostulacionesPorFiltroExcel(ConsultaPostulacionDTO dto) {
        request.getSession().setAttribute("reporteExcelPostulacion", reporteLogica.consultarReporte(dto));
        return "ok";
    }
    
    @GET
    @Path("/descargar/postulacion.xlsx")
    @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public byte[] descargarPostulacionesPorFiltroExcel() {
        return (byte[]) request.getSession().getAttribute("reporteExcelPostulacion");
    }

    /**
     * retorna una lista con los Postulacion que se encuentran en la base de
     * datos en estado aprobado
     *
     * @return retorna una lista de PostulacionDTO
     * @generated
     */
    @GET
    @Path("/consulta/aprobados")
    public List<PostulacionDTO> obtenerTodosAprobados() {
        logica.setInfoUsuario(obtenerUsuario(request));
        return logica.obtenerAprobados();
    }

    /**
     * retorna una lista con los Postulacion que se encuentran en la base de
     * datos en estado registrado
     *
     * @return retorna una lista de PostulacionDTO
     * @generated
     */
    @GET
    @Path("/consulta/registrados")
    public List<PostulacionDTO> obtenerTodosRegistrados() {
        logica.setInfoUsuario(obtenerUsuario(request));
        return logica.obtenerRegistrados();
    }

    /**
     * retorna una lista con los Postulacion que se encuentran en la base de
     * datos en estado REGISTRO_POLIZA
     *
     * @return retorna una lista de PostulacionDTO
     * @generated
     */
    @GET
    @Path("/consulta/pagados")
    public List<PostulacionDTO> obtenerTodosPagados() {
        logica.setInfoUsuario(obtenerUsuario(request));
        return logica.obtenerRegistroPoliza();
    }
    
    /**
     * retorna una lista con los Postulacion que se encuentran en la base de
     * datos en estado SOLICITUD_SINIESTRO
     *
     * @return retorna una lista de PostulacionDTO
     * @generated
     */
    @GET
    @Path("/consulta/solicitudSiniestro")
    public List<PostulacionDTO> obtenerTodosSolicitudSiniestro() {
        logica.setInfoUsuario(obtenerUsuario(request));
        return logica.obtenerSolicitudSiniestro();
    }
    
    /**
     * retorna una lista con los Postulacion que se encuentran en la base de
     * datos en estado SOLICITUD_SINIESTRO
     *
     * @return retorna una lista de PostulacionDTO
     * @generated
     */
    @GET
    @Path("/consulta/solicitudCancelacion")
    public List<PostulacionDTO> obtenerTodosSolicitudCancelacion() {
        logica.setInfoUsuario(obtenerUsuario(request));
        return logica.obtenerSolicitudCancelacion();
    }

    /**
     * @param id identificador del elemento Postulacion
     * @return Postulacion del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public PostulacionDTO obtenerPostulacion(@PathParam("id") Long id) {
        logica.setInfoUsuario(obtenerUsuario(request));
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de Postulacion
     *
     * @param dto Postulacion a guardar
     * @return Postulacion con los cambios realizados por el proceso de guardar
     * @generated
     */
    @POST
    public PostulacionDTO guardarPostulacion(PostulacionDTO dto) {
        logica.setInfoUsuario(obtenerUsuario(request));
        return logica.guardar(dto);
    }

    /**
     * elimina el registro Postulacion con el identificador dado
     *
     * @param id identificador del Postulacion
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarPostulacion(@PathParam("id") Long id) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.borrar(id);
    }

    /**
     * @deprecated 
     * realiza la aprobacion de la potulacion.
     * Se elimina y la aprobacion se hace directamente en el pago de la solicitud
     * @param postulacion
     * @return 
     */
    @POST
    @Path("aprobar")
    public String aprobarPostulacion(FirmaDTO postulacion) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.aprobar(postulacion.getFirma());
        return "OK";
    }

    /**
     * autoriza el uso de la poliza en la postulacion 
     * @param postulacion postulacion en formato de firma pkcs5
     * @return 
     */
    @POST
    @Path("/confirmarPago")
    public String autorizarPostulacion(FirmaDTO postulacion) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.autorizar(postulacion.getFirma());
        return "OK";
    }
    
    /**
     * autoriza la solicitud de siniestro (MT)
     * @param postulacion postulacion en formato de firma pkcs5
     * @return 
     */
    @POST
    @Path("/autorizarSolicitudSiniestro")
    public String autorizarSolicitudSiniestro(FirmaDTO postulacion) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.autorizarSolicitudSiniestro(postulacion.getFirma());
        return "OK";
    }
    
    /**
     * autoriza la solicitud de Cancelacion de Matricula (MT)
     * @param postulacion postulacion en formato de firma pkcs5
     * @return 
     */
    @POST
    @Path("/autorizarCancelacionMatricula")
    public String autorizarCancelacionMatricula(FirmaDTO postulacion) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.autorizarCancelacionMatricula(postulacion.getFirma());
        return "OK";
    }

    /**
     * rechazar la postulacion 
     * @param firma postulacion en formato de firma pkcs5
     * @return 
     */
    @POST
    @Path("/rechazar")
    public String rechazarPostulacion(FirmaDTO firma) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.rechazar(firma.getFirma());
        return "OK";
    }

    /**
     * devuelve la solicitud al ciudadano
     * @param idPostulacion
     * @return 
     */
    @POST
    @Path("/devolver")
    public String devolverCiudadano(PostulacionDTO postulacion) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.devolverCiudadano(postulacion);
        return "OK";
    }
    
    @POST
    @Path("/rechazarCiudadano")
    public String rechazarCiudadano(PostulacionDTO postulacion) {
        logica.setInfoUsuario(obtenerUsuario(request));
        return rechazarSolicitudSiniestro(postulacion);
    }
    

    /**
     * aprueba el pago de la solicitud
     * @param id
     * @return 
     */
    @GET
    @Path("/aprobarPago/{id}")
    public String aprobarPago(@PathParam("id") Long id) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.aprobarPoliza(id);
        return "OK";
    }
    
    /**
     * realiza la solicitud de siniestro
     * @param id identificador de la solicitud
     * @return 
     */
    @POST
    @Path("/solicitudSiniestro")
    public String solicitudSiniestro(PostulacionDTO solicitudSiniestro) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.solicitudSiniestroPoliza(solicitudSiniestro);
        return "OK";
    }

    /**
     * devuelve la solicitud al ciudadano
     * @param idPostulacion
     * @return 
     */
    @POST
    @Path("/rechazarSolicitudSiniestro")
    public String rechazarSolicitudSiniestro(PostulacionDTO postulacion) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.rechazarSolicitudSiniestro(postulacion);
        return "OK";
    }
    
    /**
     * devuelve la solicitud al ciudadano
     * @param idPostulacion
     * @return 
     */
    @POST
    @Path("/devolverSolicitudSiniestro")
    public String devolverSolicitudSiniestro(PostulacionDTO postulacion) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.devolverSolicitudSiniestro(postulacion);
        return "OK";
    }
    
    /**
     * devuelve la solicitud al ciudadano
     * @param idPostulacion
     * @return 
     */
    @POST
    @Path("/devolverSolicitudCancelacion")
    public String devolverSolicitudCancelacion(PostulacionDTO postulacion) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.devolverSolicitudCancelacion(postulacion);
        return "OK";
    }
    
    /**
     * obtiene el detalle de un CCM por el identificador
     * @param idCCM
     * @return 
     */
    @GET
    @Path("/ccm/{id}")
    public DetallePostulacionCCMDTO obtenerDetalleCCM(@PathParam("id") Long idCCM) {
        logica.setInfoUsuario(obtenerUsuario(request));
        return logica.obtenerDetalleCCM(idCCM);
    }

    /**
     * obtiene la lista de postulaciones de un usuario, por tipo de documento y numero de documento
     * @param tipoDocumento
     * @param numeroDocumento
     * @return 
     */
    @GET
    @Path("/persona/{tipoDocumento}/{numeroDocumento}")
    public List<PostulacionDTO> obtenerPostulaciones(@PathParam("tipoDocumento") String tipoDocumento, @PathParam("numeroDocumento") String numeroDocumento) {
        logica.setInfoUsuario(obtenerUsuario(request));
        return logica.obtenerPostulaciones(tipoDocumento, numeroDocumento);
    }

    /**
     * @deprecated 
     * registra el vin de un vehiculo a una postulacion
     * @param vin
     * @param idPostulacion
     * @return 
     */
    @GET
    @Path("/registrovin/{vin}/{idPostulacion}")
    public String registrarVin(@PathParam("vin") String vin,@PathParam("idPostulacion") Long idPostulacion) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.finalizarSolicitud(vin, idPostulacion);
        return "OK";
    }
    
    @GET
    @Path("/actualizarLiquidacion/{idPostulacion}/{idLiquidacion}")
    public String actualizarLiquidacion(@PathParam("idPostulacion") Long idPostulacion, @PathParam("idLiquidacion") String idLiquidacion) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.actualizarLiquidacion(idPostulacion, idLiquidacion);
        return "OK";
    }
    
    @GET
    @Path("/Desistimiento/{idPostulacion}")
    public String desistirPostulacion(@PathParam("idPostulacion") Long idPostulacion) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.desistir(idPostulacion);
        return "OK";
    }
    
    @POST
    @Path("/solicitarCancelacion/{idPostulacion}")
    public String solicitarCancelarPostulacion(@PathParam("idPostulacion") Long idPostulacion, PostulacionDTO observaciones) {
        logica.setInfoUsuario(obtenerUsuario(request));
        logica.solicitarCancelacion(idPostulacion, observaciones.getObservacionesRechazo());
        return "OK";
    }
    
    @GET
    @Path("/PostulacionesAprobadas/{tipoDocumento}/{numeroDocumento}")
    public List<PostulacionDTO> obtenerPostulacionesAprobadas(@PathParam("tipoDocumento") String tipoDocumento, @PathParam("numeroDocumento") String numeroDocumento) {
        return logica.obtenerPostulacionesAprobadas(tipoDocumento, numeroDocumento);
    }
}
