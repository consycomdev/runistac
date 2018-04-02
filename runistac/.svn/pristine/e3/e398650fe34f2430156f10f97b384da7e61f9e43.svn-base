package co.com.runt.runistac.logica;

import co.com.runt.clienteserviciosfirma.ClienteServiciosFirma;
import co.com.runt.clienteserviciosfirma.dto.ValidacionFirmaDTO;
import co.com.runt.clienteserviciosfirma.dto.ValidacionFirmaInDTO;
import co.com.runt.common.excepciones.ApplicationException;
import co.com.runt.common.seguridad.InfoUsuario;
import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.persistencia.*;
import co.com.runt.runistac.persistencia.entity.*;
import co.com.runt.runistac.utils.FechaUtils;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Decoder;

/**
 * @generated
 */
@Stateless
public class PostulacionLogica {

    private InfoUsuario infoUsuario;
    
    @EJB
    private PostulacionDAO persistencia;

    @EJB
    private PersonaLogica personaLogica;

    @EJB
    private AnexoLogica anexoLogica;

    @EJB
    private AsignacionCCMDAO asignacionCCMDAO;

    @EJB
    private CertificadoCancelacionMatriculaDAO certificadoCancelacionMatriculaDAO;

    @EJB
    private ConsecutivoSolicitudLogica consecutivoSolicitudLogica;

    @EJB
    private ParametroSistemaLogica parametroSistemaLogica;

    @EJB
    private CertificadoCancelacionMatriculaLogica certificadoCancelacionMatriculaLogica;

    @EJB
    private PersonaNaturalLogica personaNaturalLogica;

    @EJB
    private AsignacionCCMLogica asignacionCCMLogica;

    @EJB
    private PersonaDAO personaDAO;

    private static final SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

    @EJB
    private TipoAnexoLogica tipoAnexoLogica;

    @EJB
    private AutomotorLogica automotorLogica;

    @EJB
    private RegistroVINDAO registroVINDAO;

    @EJB
    private AseguradoraDAO aseguradoraDAO;

    @EJB
    private PolizaCaucionDAO polizaCaucionDAO;

    @EJB
    private FirmaDAO auditoriaDAO;

    @EJB
    private EnvioCorreoLogica envioCorreoLogica;

    @EJB
    private EntidadFinancieraDAO bancoDAO;

    @EJB
    private ParametroTextoLogica parametroTextoLogica;

    /**
     * @generated
     */
    public List<PostulacionDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * @generated
     */
    public List<PostulacionDTO> obtenerRegistrados() {
        return convertirEntidad(persistencia.obtenerPorEstado(EstadoPostulacion.EN_TRAMITE));
    }

    public List<PostulacionDTO> obtenerRegistroPoliza() {
        return convertirEntidad(persistencia.obtenerPorEstado(EstadoPostulacion.REGISTRO_POLIZA));
    }

    /**
     * obtiene la lista de postulaciones aprobadas
     *
     * @return
     */
    public List<PostulacionDTO> obtenerAprobados() {
        return convertirEntidad(persistencia.obtenerPorEstado(EstadoPostulacion.APROBADO));
    }

    /**
     * @generated
     */
    public PostulacionDTO obtener(Long id) {
        return convertirEntidad(persistencia.obtener(id));
    }

    /**
     * @generated
     */
    public PostulacionDTO guardar(PostulacionDTO dto) {
        //if(true){throw new ApplicationException("Ocurrio un error inesperado, por favor contacte al administrador del sistema");}
        //validar campos
        validarCampos(dto);
        ConsultaVINDTO data = automotorLogica.consultarVin(dto.getVin());
        if (data.getEstado() == null || data.getEstado().compareTo("REGISTRADO") != 0) {
            throw new ApplicationException("El VIN digitado no se encontr&oacute; o no se encuentra en estado REGISTRADO");
        }
        //validar que existan suficientes certificados
        Integer disponibles = certificadoCancelacionMatriculaLogica.obtenerDisponibles();

        dto.setNumeroCCM(Integer.parseInt(parametroSistemaLogica.obtener("NUMERO_CCM_SOLICITUD").getValor()));
        dto.setEstado(EstadoPostulacion.REGISTRADO);

        if (dto.getNumeroCCM() > disponibles) {
            throw new ApplicationException("El n\u00FAmero de certificados solicitados supera los disponibles");
        }

        ConsecutivoSolicitudDTO consecutivo = consecutivoSolicitudLogica.obtener("FORMULARIO_POSTULACION");
        Long solicitud = consecutivo.getValor();
        dto.setSolicitud(solicitud + 1);
        consecutivo.setValor(solicitud + 1);
        consecutivoSolicitudLogica.actualizar(consecutivo);

        PersonaDTO persona = personaLogica.obtenerTipoYNumeroDocumento(dto.getTipoDocumento(), dto.getNumeroDocumento());
        dto.setPersona(persona);

        //validar que no tenga 4 solicitudes hace 30 dias
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        //Las entidades financieras autorizadas y que realicen operaciones de leasing o renting, podrán solicitar certificados de cancelación de matrícula, para matricular un máximo de 10 vehículos (cantidad paramétrica) por mes
        if (persona.getTipoDocumento().getId().compareTo("N") == 0 && !bancoDAO.obtenerBancoPorNit(persona.getNumeroDocumento()).isEmpty()) {
            Integer numeroPostulacionesMesF = Integer.parseInt(parametroSistemaLogica.obtener("NUMERO_POSTULACIONES_ENTIDAD_FINANCIERA").getValor());
            if (persistencia.obtenerPostulacionesAprobadasPorFecha(persona.getTipoDocumento().getId(), persona.getNumeroDocumento(), c.getTime(), new Date()).size() >= numeroPostulacionesMesF) {
                throw new ApplicationException("Supera el numero de solicitudes permitidas cada 30 días");
            }
            if(dto.getLeasing()==null){
                dto.setLeasing(Boolean.FALSE);
            }
            if(dto.getLeasing()){
                if(dto.getLocatarios().isEmpty()){
                    throw new ApplicationException("Debe seleccionar al menos un locatario");
                }
                Integer numeroPostulacionesMes = Integer.parseInt(parametroSistemaLogica.obtener("NUMERO_POSTULACIONES_MES").getValor());
                for(PersonaDTO p:dto.getLocatarios()){
                    PersonaDTO locatario=personaLogica.obtener(p.getId());
                    if (persistencia.obtenerPostulacionesAprobadasPorFecha(locatario.getTipoDocumento().getId(), locatario.getNumeroDocumento(), c.getTime(), new Date()).size() >= numeroPostulacionesMes) {
                        throw new ApplicationException("El locatario "+locatario.getNombre()+" Supera el número de solicitudes permitidas cada 30 días");
                    }
                }
            }
            
        } else {
            Integer numeroPostulacionesMes = Integer.parseInt(parametroSistemaLogica.obtener("NUMERO_POSTULACIONES_MES").getValor());
            if (persistencia.obtenerPostulacionesAprobadasPorFecha(persona.getTipoDocumento().getId(), persona.getNumeroDocumento(), c.getTime(), new Date()).size() >= numeroPostulacionesMes) {
                throw new ApplicationException("Supera el número de solicitudes permitidas en el mes");
            }
        }

        //validar que no tenga solicitudes en tramite
        if (!persistencia.obtenerPostulaciones(persona.getTipoDocumento().getId(), persona.getNumeroDocumento(), EstadoPostulacion.REGISTRADO).isEmpty()
                || !persistencia.obtenerPostulaciones(persona.getTipoDocumento().getId(), persona.getNumeroDocumento(), EstadoPostulacion.EN_TRAMITE).isEmpty()) {
            throw new ApplicationException("Usted ya tiene una solicitud en curso, hasta tanto no sea atendida no puede volver a radicar otra solicitud.");
        }

        //validar que el vin no haya sido utilizado
        if (!persistencia.obtenerPostulacionesActivaPorVin(dto.getVin()).isEmpty()) {
            throw new ApplicationException("El VIN digitado ya fue utilizado en otra solicitud");
        }
        String directorio = parametroSistemaLogica.obtener(Constantes.ParametroSistema.DIRECTORIO_BASE).getValor();
        for (AnexoDTO anexo : dto.getAnexo()) {
            try {
                TipoAnexoDTO anexo1 = tipoAnexoLogica.obtener(anexo.getId());
                anexo.setTipoAnexo(new TipoAnexoDTO(anexo.getId()));
                anexo.setId(null);
                String ubicacion = "" + (solicitud + 1) + "/" + anexo1.getNombre() + ".pdf";
                new File(directorio + "/" + (solicitud + 1) + "/").mkdirs();
                FileOutputStream fos = new FileOutputStream(directorio + "/" + ubicacion);
                String b64 = new String(new BASE64Decoder().decodeBuffer(anexo.getArchivo()));

                fos.write(new BASE64Decoder().decodeBuffer(b64.substring(b64.indexOf(",") + 1)));
                fos.close();
                anexo.setUbicacion(ubicacion);

            } catch (IOException ex) {
                Logger.getLogger(PostulacionLogica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Postulacion postulacion = persistencia.guardar(convertirDTO(dto));

        AsignacionCCM asignacion = new AsignacionCCM();
        asignacion.setFecha(new Date());
        asignacion = asignacionCCMDAO.guardar(asignacion);
        postulacion.setAsignacionCCM(asignacion);

        //obtener la lista de CCM
        List<CertificadoCancelacionMatricula> certificados = certificadoCancelacionMatriculaDAO.obtenerPorCantidad(dto.getNumeroCCM());
        if (certificados.size() == dto.getNumeroCCM()) {
            asignacion.setCcm(certificados);
            for (CertificadoCancelacionMatricula certificado : certificados) {
                certificado.setAsignacionCCM(asignacion);
                certificado.setEstado(EstadoCCM.RESERVADO);
            }
        } else {
            //no hay suficientes
            throw new ApplicationException("No hay suficientes certificados de cancelación de matricula disponibles");
        }

        //enviar correo
        String titulo = "Creaci&oacute;n de la Solicitud del Tr&aacute;mite RUNISTAC";
        String textoEnvioCorreo = parametroTextoLogica.obtener("CORREO_SOLICITUD").getValor();
        textoEnvioCorreo = textoEnvioCorreo.replaceAll("\\$fecha", FechaUtils.obtenerFechaTexto(new Date()));
        textoEnvioCorreo = textoEnvioCorreo.replaceAll("\\$solicitud", dto.getSolicitud().toString());
        textoEnvioCorreo = textoEnvioCorreo.replaceAll("\\$vin", dto.getVin());
        textoEnvioCorreo = textoEnvioCorreo.replaceAll("\\$numeroCCM", dto.getNumeroCCM().toString());
        if (data.getFthCarroceria() == null) {
            textoEnvioCorreo = textoEnvioCorreo.replaceAll("\\$adicionales", "El vehiculo no cuenta con FTH de carroceria, la cual deberá ser suministrada a la autoridad competente");
        } else {
            textoEnvioCorreo = textoEnvioCorreo.replaceAll("\\$adicionales", "");
        }

        String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", textoEnvioCorreo);
        envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(dto.getTipoDocumento(), dto.getNumeroDocumento()),
                mensaje);

        PostulacionDTO postulacionDTO = convertirEntidad(postulacion);
        return postulacionDTO;
    }

    public String obtenerMensajeCorreoSolicitud(String titulo, Date fecha, Long solicitud) {
        try {
            String mensaje = IOUtils.toString(PostulacionLogica.class.getResourceAsStream("CorreoCreacionSolicitud.txt"), "UTF-8");
            mensaje = mensaje.replaceAll("\\{\\{TITULO\\}\\}", titulo);
            Calendar c = Calendar.getInstance();
            c.setTime(fecha);
            mensaje = mensaje.replaceAll("\\{\\{DIA\\}\\}", "" + c.get(Calendar.DAY_OF_MONTH));
            mensaje = mensaje.replaceAll("\\{\\{MES\\}\\}", obtenerMes(c.get(Calendar.DAY_OF_MONTH)));
            mensaje = mensaje.replaceAll("\\{\\{ANIO\\}\\}", "" + c.get(Calendar.DAY_OF_MONTH));
            mensaje = mensaje.replaceAll("\\{\\{SOLICITUD\\}\\}", "" + solicitud);
            return mensaje;
        } catch (IOException ex) {
            Logger.getLogger(PostulacionLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String obtenerMes(int mes) {
        switch (mes) {
            case 0:
                return "Enero";
            case 1:
                return "Febrero";
            case 2:
                return "Marzo";
            case 3:
                return "Abril";
            case 4:
                return "Mayo";
            case 5:
                return "Junio";
            case 6:
                return "Julio";
            case 7:
                return "Agosto";
            case 8:
                return "Septiembre";
            case 9:
                return "Octubre";
            case 10:
                return "Noviembre";
            case 11:
                return "Diciembre";
            default:
                return "";
        }
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        persistencia.borrar(id);
    }

    /**
     * @generated
     */
    public void actualizar(PostulacionDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public Postulacion convertirDTO(PostulacionDTO dto) {
        if (dto == null) {
            return null;
        }
        Postulacion entidad = new Postulacion();
        entidad.setId(dto.getId());
        if (dto.getFecha() != null) {
            try {
                entidad.setFecha(fecha.parse(dto.getFecha()));
            } catch (ParseException ex) {
                throw new RuntimeException("Error al convertir la fecha " + dto.getFecha());
            }
        }
        entidad.setNumeroCCM(dto.getNumeroCCM());
        entidad.setSolicitud(dto.getSolicitud());
        entidad.setEstado(dto.getEstado());

        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }

        if (dto.getAnexo() != null && !dto.getAnexo().isEmpty()) {
            entidad.setAnexo(anexoLogica.convertirDTO(dto.getAnexo()));
        }

        entidad.setObservacionesRechazo(dto.getObservacionesRechazo());
        if (dto.getCausalesRechazo() != null) {
            entidad.setCausalesRechazo(new CausalesRechazo());
            entidad.getCausalesRechazo().setId(dto.getCausalesRechazo().getId());
        }

        entidad.setLiquidacion(dto.getIdLiquidacion());
        entidad.setVin(dto.getVin());
        
        entidad.setLeasing(dto.getLeasing());
        if(dto.getLocatarios()!=null && !dto.getLocatarios().isEmpty()){
            entidad.setLocatarios(new ArrayList<Persona>());
            for(PersonaDTO p:dto.getLocatarios()){
                entidad.getLocatarios().add(personaLogica.convertirDTO(p));
            }
        }
        
        entidad.setFechaModificacion(new Date());
        entidad.setUsuarioModificacion(infoUsuario.getUsuario());
        entidad.setIpModificacion(infoUsuario.getIp());

        return entidad;
    }

    /**
     * @generated
     */
    public List<Postulacion> convertirDTO(List<PostulacionDTO> dtos) {
        List<Postulacion> entidades = new ArrayList<Postulacion>();
        for (PostulacionDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public PostulacionDTO convertirEntidad(Postulacion entidad) {
        PostulacionDTO dto = new PostulacionDTO();
        dto.setId(entidad.getId());
        if (entidad.getFecha() != null) {
            dto.setFecha(fecha.format(entidad.getFecha()));
        }
        dto.setNumeroCCM(entidad.getNumeroCCM());
        dto.setSolicitud(entidad.getSolicitud());
        dto.setEstado(entidad.getEstado());

        if (entidad.getPersona() != null) {
            dto.setPersona(personaLogica.convertirEntidad(entidad.getPersona()));
            PersonaNaturalDTO persona = personaNaturalLogica.obtener(entidad.getPersona().getId());
            dto.setNombreSolicitante((persona.getPrimerNombre() == null ? "" : persona.getPrimerNombre()) + " "
                    + (persona.getSegundoNombre() == null ? "" : persona.getSegundoNombre()) + " "
                    + (persona.getPrimerApellido() == null ? "" : persona.getPrimerApellido()) + " "
                    + (persona.getSegundoApellido() == null ? "" : persona.getSegundoApellido()));
        }

        if (entidad.getAnexo() != null && !entidad.getAnexo().isEmpty()) {
            dto.setAnexo(anexoLogica.convertirEntidad(entidad.getAnexo()));
        }

        if (entidad.getCausalesRechazo() != null) {
            dto.setCausalesRechazo(new CausalesRechazoDTO(entidad.getCausalesRechazo().getId()));
            dto.getCausalesRechazo().setNombre(entidad.getCausalesRechazo().getNombre());
        }
        dto.setObservacionesRechazo(entidad.getObservacionesRechazo());

        if (entidad.getAsignacionCCM() != null) {
            dto.setAsignacionCCM(asignacionCCMLogica.convertirEntidad(entidad.getAsignacionCCM()));
        }

        dto.setIdLiquidacion(entidad.getLiquidacion());
        if (entidad.getFechaPago() != null) {
            dto.setFechaPago(fecha.format(entidad.getFechaPago()));
            dto.setTiempoAtencion(String.valueOf(diferenciaHorasDias(entidad.getFechaPago(), new Date())));
        }
        dto.setVin(entidad.getVin());
        dto.setLeasing(entidad.getLeasing());
        if(entidad.getLocatarios()!=null && !entidad.getLocatarios().isEmpty()){
            dto.setLocatarios(new ArrayList<PersonaDTO>());
            for(Persona p:entidad.getLocatarios()){
                dto.getLocatarios().add(personaLogica.convertirEntidad(p));
            }
        }
        return dto;
    }

    public static long diferenciaHorasDias(Date fechaInicial, Date fechaFinal) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(fechaInicial);
        c2.setTime(fechaFinal);

        return diferenciaHorasDias(c1, c2);
    }

    public static long diferenciaHorasDias(Calendar fechaInicial, Calendar fechaFinal) {
        //Milisegundos al día
        long diferenciaHoras = 0;
        //Restamos a la fecha final la fecha inicial y lo dividimos entre el numero de milisegundos al dia
        diferenciaHoras = (fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / 60 / 60 / 1000;
        return diferenciaHoras;
    }

    /**
     * @generated
     */
    public List<PostulacionDTO> convertirEntidad(List<Postulacion> entidades) {
        List<PostulacionDTO> dtos = new ArrayList<PostulacionDTO>();
        for (Postulacion entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

    public void aprobar(String datosFirmados) {
        aprobar(infoUsuario.getUsuario(), datosFirmados);
    }

    public void aprobar(String usuario, String datosFirmados) {
        //validar firma
        ClienteServiciosFirma clienteFirma = new ClienteServiciosFirma(parametroSistemaLogica.obtener("URL_SERVICIO_FIRMA").getValor());
        ValidacionFirmaInDTO entrada = new ValidacionFirmaInDTO();
        entrada.setCedulaUsuario(usuario);
        entrada.setDatosFirmados(datosFirmados);
        ValidacionFirmaDTO respuesta = clienteFirma.validarFirma(entrada);
        if (respuesta.getCodigo().compareTo("OK") == 0) {
            //convertir json
            Gson gson = new Gson();
            PostulacionDTO postulacion = gson.fromJson(new String(respuesta.getDatos()), PostulacionDTO.class);
            //guardar auditoria
            Firma auditoria = new Firma();
            auditoria.setFecha(new Date());
            auditoria.setFirma(datosFirmados);
            auditoria.setIdTabla(postulacion.getId());
            auditoria.setOperacion(OperacionesAuditoria.ACTUALIZACION);
            auditoria.setTabla(Postulacion.class.getName());
            auditoria.setUsuario(usuario);
            auditoriaDAO.guardarAuditoria(auditoria);
            //aprobar
            aprobar(postulacion);
        } else {
            throw new ApplicationException("Error al validar la firma de la aprobaci&oacute;n");
        }

    }

    public void aprobar(PostulacionDTO idPostulacion) {
        Postulacion postulacion = persistencia.obtener(idPostulacion.getId());
        postulacion.setEstado(EstadoPostulacion.APROBADO);
        postulacion.setNumeroCCMAsignados(idPostulacion.getNumeroCCMAsignados());
        postulacion.setTipoTransportador(new TipoTransportador());
        postulacion.getTipoTransportador().setId(idPostulacion.getTipoTransportador().getId());
        persistencia.actualizar(postulacion);
        //obtener la lista de CCM
        List<CertificadoCancelacionMatricula> certificados = certificadoCancelacionMatriculaDAO.obtenerPorCantidad(postulacion.getNumeroCCM());
        if (certificados.size() == postulacion.getNumeroCCM()) {
            for (CertificadoCancelacionMatricula certificado : certificados) {
                certificado.setEstado(EstadoCCM.ASIGNADO);
            }
        } else {
            //no hay suficientes
            throw new ApplicationException("No hay suficientes certificados de cancelación de matricula disponibles");
        }

        //enviar correo
        String titulo = "Aprobaci&oacute;n de la Solicitud del Tr&aacute;mite RUNISTAC";
        String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", "La solicitud n&uacute;mero " + postulacion.getSolicitud() + " del tr&aacute;mite RUNISTAC ha sido APROBADA<br/>");
        envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(postulacion.getPersona().getTipoDocumento().getId(), postulacion.getPersona().getNumeroDocumento()), mensaje);

    }

    public void rechazar(String datosFirmados) {
        rechazar(infoUsuario.getUsuario(), datosFirmados);
    }

    public void rechazar(String usuario, String datosFirmados) {
        //validar firma
        ClienteServiciosFirma clienteFirma = new ClienteServiciosFirma(parametroSistemaLogica.obtener("URL_SERVICIO_FIRMA").getValor());
        ValidacionFirmaInDTO entrada = new ValidacionFirmaInDTO();
        entrada.setCedulaUsuario(usuario);
        entrada.setDatosFirmados(datosFirmados);
        ValidacionFirmaDTO respuesta = clienteFirma.validarFirma(entrada);
        if (respuesta.getCodigo().compareTo("OK") == 0) {
            //convertir json
            Gson gson = new Gson();
            PostulacionDTO postulacion = gson.fromJson(new String(respuesta.getDatos()), PostulacionDTO.class);
            //guardar auditoria
            Firma auditoria = new Firma();
            auditoria.setFecha(new Date());
            auditoria.setFirma(datosFirmados);
            auditoria.setIdTabla(postulacion.getId());
            auditoria.setOperacion(OperacionesAuditoria.ACTUALIZACION);
            auditoria.setTabla(Postulacion.class.getName());
            auditoria.setUsuario(usuario);
            auditoriaDAO.guardarAuditoria(auditoria);
            //aprobar
            rechazar(postulacion);
        } else {
            throw new ApplicationException("Error al validar la firma de la aprobaci&oacute;n");
        }

    }

    public void rechazar(PostulacionDTO idPostulacion) {
        Postulacion postulacion = persistencia.obtener(idPostulacion.getId());
        postulacion.setObservacionesRechazo(idPostulacion.getObservacionesRechazo());
        postulacion.setCausalesRechazo(new CausalesRechazo());
        postulacion.getCausalesRechazo().setId(idPostulacion.getCausalesRechazo().getId());
        postulacion.setEstado(EstadoPostulacion.RECHAZADO);
        persistencia.actualizar(postulacion);

        //enviar correo
        String titulo = "Rechazo de la Solicitud del Tr&aacute;mite RUNISTAC";
        String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", "La solicitud n&uacute;mero " + postulacion.getSolicitud() + " del tr&aacute;mite RUNISTAC ha sido RECHAZADA<br/><br/>" + postulacion.getObservacionesRechazo());
        envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(postulacion.getPersona().getTipoDocumento().getId(), postulacion.getPersona().getNumeroDocumento()), mensaje);

    }
    
    public void desistir(Long idPostulacion){
        //consultar id
        Postulacion postulacion = persistencia.obtener(idPostulacion);
        postulacion.setObservacionesRechazo("Desistimiento por solicitud del usuario");
        postulacion.setEstado(EstadoPostulacion.RECHAZADO);
        persistencia.actualizar(postulacion);

        //enviar correo
        String titulo = "Rechazo de la Solicitud del Tr&aacute;mite RUNISTAC";
        String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", "La solicitud n&uacute;mero " + postulacion.getSolicitud() + " del tr&aacute;mite RUNISTAC ha sido RECHAZADA<br/><br/>" + postulacion.getObservacionesRechazo());
        envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(postulacion.getPersona().getTipoDocumento().getId(), postulacion.getPersona().getNumeroDocumento()), mensaje);
        
        liberarCCM();
    }

    public void autorizar(String datosFirmados) {
        autorizar(infoUsuario.getUsuario(), datosFirmados);
    }

    public void autorizar(String usuario, String datosFirmados) {
        //validar firma
        ClienteServiciosFirma clienteFirma = new ClienteServiciosFirma(parametroSistemaLogica.obtener("URL_SERVICIO_FIRMA").getValor());
        ValidacionFirmaInDTO entrada = new ValidacionFirmaInDTO();
        entrada.setCedulaUsuario(usuario);
        entrada.setDatosFirmados(datosFirmados);
        ValidacionFirmaDTO respuesta = clienteFirma.validarFirma(entrada);
        if (respuesta.getCodigo().compareTo("OK") == 0) {
            //convertir json
            Gson gson = new Gson();
            PostulacionDTO postulacion = gson.fromJson(new String(respuesta.getDatos()), PostulacionDTO.class);
            //guardar auditoria
            Firma auditoria = new Firma();
            auditoria.setFecha(new Date());
            auditoria.setFirma(datosFirmados);
            auditoria.setIdTabla(postulacion.getId());
            auditoria.setOperacion(OperacionesAuditoria.ACTUALIZACION);
            auditoria.setTabla(Postulacion.class.getName());
            auditoria.setUsuario(usuario);
            auditoriaDAO.guardarAuditoria(auditoria);
            //aprobar
            autorizar(postulacion.getId());
        } else {
            throw new ApplicationException("Error al validar la firma de la aprobaci&oacute;n");
        }
    }

    public void autorizar(Long idPostulacion) {
        Postulacion postulacion = persistencia.obtener(idPostulacion);
        if (postulacion.getEstado() != EstadoPostulacion.REGISTRO_POLIZA) {
            throw new ApplicationException("No se pueden editar los pagos en un estado diferente a Aprobado");
        }
        postulacion.setEstado(EstadoPostulacion.AUTORIZADO);
        persistencia.actualizar(postulacion);

        String titulo = "Autorizaci&oacute;n de la Solicitud del Tr&aacute;mite RUNISTAC";
        String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", "La solicitud n&uacute;mero " + postulacion.getSolicitud() + " del tr&aacute;mite RUNISTAC ha sido AUTORIZADA<br/>");
        envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(postulacion.getPersona().getTipoDocumento().getId(), postulacion.getPersona().getNumeroDocumento()), mensaje);
    }

    public void autorizarSolicitudSiniestro(String datosFirmados) {
        autorizarSolicitudSiniestro(infoUsuario.getUsuario(), datosFirmados);
    }

    public void autorizarSolicitudSiniestro(String usuario, String datosFirmados) {
        //validar firma
        ClienteServiciosFirma clienteFirma = new ClienteServiciosFirma(parametroSistemaLogica.obtener("URL_SERVICIO_FIRMA").getValor());
        ValidacionFirmaInDTO entrada = new ValidacionFirmaInDTO();
        entrada.setCedulaUsuario(usuario);
        entrada.setDatosFirmados(datosFirmados);
        ValidacionFirmaDTO respuesta = clienteFirma.validarFirma(entrada);
        if (respuesta.getCodigo().compareTo("OK") == 0) {
            //convertir json
            Gson gson = new Gson();
            PostulacionDTO postulacion = gson.fromJson(new String(respuesta.getDatos()), PostulacionDTO.class);
            //guardar auditoria
            Firma auditoria = new Firma();
            auditoria.setFecha(new Date());
            auditoria.setFirma(datosFirmados);
            auditoria.setIdTabla(postulacion.getId());
            auditoria.setOperacion(OperacionesAuditoria.ACTUALIZACION);
            auditoria.setTabla(Postulacion.class.getName());
            auditoria.setUsuario(usuario);
            auditoriaDAO.guardarAuditoria(auditoria);
            //validar FTH
            if (postulacion.getFthCarroceria() != null) {
                Postulacion postulacion1 = persistencia.obtener(postulacion.getId());
                String info = automotorLogica.validarFTHCarroceria(postulacion.getId(), postulacion.getFthCarroceria());
                //asociar fth carroceria
                automotorLogica.registrarFTH(postulacion1.getVin(), postulacion.getFthCarroceria());
            }
            //aprobar
            autorizarSolicitudSiniestro(postulacion.getId());
        } else {
            throw new ApplicationException("Error al validar la firma de la aprobaci&oacute;n");
        }
    }
    
    public void autorizarSolicitudSiniestro(Long idPostulacion) {
        Postulacion postulacion = persistencia.obtener(idPostulacion);
        if (postulacion.getEstado() != EstadoPostulacion.SOLICITUD_SINIESTRO) {
            throw new ApplicationException("No se pueden editar los pagos en un estado diferente a P&oacute;liza Solicitud Siniestro");
        }
        //validar que el vin no haya sido utilizado
        //consultar vin
        ConsultaVINDTO automotor = automotorLogica.consultarVin(postulacion.getVin());
        if (automotor == null) {
            throw new ApplicationException("No se encontro un vehiculo asociado");
        }

        postulacion.setEstado(EstadoPostulacion.UTILIZADO);

        //generar autorizacion de matricula inicial
        finalizarSolicitud(postulacion.getVin(), postulacion.getId());

        persistencia.actualizar(postulacion);
        String titulo = "Autorizaci&oacute;n Sinistro de P&oacute;liza de Cauci&oacute;n de la Solicitud del Tr&aacute;mite RUNISTAC";
        String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", "La solicitud del siniestro de la p&oacute;liza de caución para el tr&aacute;mite n&uacute;mero " + postulacion.getSolicitud() + " RUNISTAC ha sido AUTORIZADA<br/>");
        envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(postulacion.getPersona().getTipoDocumento().getId(), postulacion.getPersona().getNumeroDocumento()), mensaje);
        
        ParametroTextoDTO textoSiniestro=parametroTextoLogica.obtener("CORREO_SINIESTRO");
        
        String texto=textoSiniestro.getValor();
        texto=texto.replaceAll("\\$\\{solicitud\\}", String.valueOf(postulacion.getSolicitud()));
        texto=texto.replaceAll("\\$\\{tipoDocumento\\}", postulacion.getPersona().getTipoDocumento().getNombre());
        texto=texto.replaceAll("\\$\\{numeroDocumento\\}", postulacion.getPersona().getNumeroDocumento());
        texto=texto.replaceAll("\\$\\{vin\\}", postulacion.getVin());
        texto=texto.replaceAll("\\$\\{numero\\}", postulacion.getTituloValor().getNumero());
        
        EntidadFinanciera aseguradora = polizaCaucionDAO.obtenerPorPostulacion(postulacion.getId()).getEntidadFinanciera();
        Logger.getLogger(PostulacionLogica.class.getName()).log(Level.INFO, "entidad financiera: {0}", aseguradora.getNombre());
        Logger.getLogger(PostulacionLogica.class.getName()).log(Level.INFO, "entidad financiera: {0}", aseguradora.getCorreo());
        mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, aseguradora.getNombre(), texto);
        envioCorreoLogica.enviarCorreo(aseguradora.getCorreo(), mensaje);
    }
    
    public void autorizarCancelacionMatricula(String datosFirmados) {
        autorizarCancelacionMatricula(infoUsuario.getUsuario(), datosFirmados);
    }

    public void autorizarCancelacionMatricula(String usuario, String datosFirmados) {
        //validar firma
        ClienteServiciosFirma clienteFirma = new ClienteServiciosFirma(parametroSistemaLogica.obtener("URL_SERVICIO_FIRMA").getValor());
        ValidacionFirmaInDTO entrada = new ValidacionFirmaInDTO();
        entrada.setCedulaUsuario(usuario);
        entrada.setDatosFirmados(datosFirmados);
        ValidacionFirmaDTO respuesta = clienteFirma.validarFirma(entrada);
        if (respuesta.getCodigo().compareTo("OK") == 0) {
            //convertir json
            Gson gson = new Gson();
            PostulacionDTO postulacion = gson.fromJson(new String(respuesta.getDatos()), PostulacionDTO.class);
            //guardar auditoria
            Firma auditoria = new Firma();
            auditoria.setFecha(new Date());
            auditoria.setFirma(datosFirmados);
            auditoria.setIdTabla(postulacion.getId());
            auditoria.setOperacion(OperacionesAuditoria.ACTUALIZACION);
            auditoria.setTabla(Postulacion.class.getName());
            auditoria.setUsuario(usuario);
            auditoriaDAO.guardarAuditoria(auditoria);
            
            //aprobar
            autorizarCancelacionMatricula(postulacion.getId());
        } else {
            throw new ApplicationException("Error al validar la firma de la aprobaci&oacute;n");
        }
    }

    public void autorizarCancelacionMatricula(Long idPostulacion) {
        Postulacion postulacion = persistencia.obtener(idPostulacion);
        if (postulacion.getEstado() != EstadoPostulacion.SOLICITUD_CANCELACION) {
            throw new ApplicationException("No se puede Cancelar una solicitud un estado diferente a 'SOLICITUD CANCELACION'");
        }
        
        //anular autorizacion de matricula inicial
        persistencia.anularAutorizacion(postulacion.getVin(), postulacion.getSolicitud(), infoUsuario);

        postulacion.setEstado(EstadoPostulacion.RECHAZADO);
        persistencia.actualizar(postulacion);
        
        String titulo = "Cancelaci&oacute;n de la Autorizaci&oacute;n de  RUNISTAC";
        String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", "La solicitud n&uacute;mero " + postulacion.getSolicitud() + " del tr&aacute;mite RUNISTAC ha sido CANCELADA de acuerdo a la solicitud del usuario<br/><br/><br/> ");
        envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(postulacion.getPersona().getTipoDocumento().getId(), postulacion.getPersona().getNumeroDocumento()), mensaje);
        
        liberarCCM();
        
    }

    public void devolverCiudadano(PostulacionDTO postulacionDTO) {
        Postulacion postulacion = persistencia.obtener(postulacionDTO.getId());
        postulacion.setEstado(EstadoPostulacion.APROBADO);
        if (postulacionDTO.getCausalesRechazo() != null) {
            postulacion.setCausalesRechazo(new CausalesRechazo(postulacionDTO.getCausalesRechazo().getId()));
        }
        postulacion.setObservacionesRechazo(postulacionDTO.getObservacionesRechazo());
        persistencia.actualizar(postulacion);

        //enviar correo
        String titulo = "Devoluci&oacute;n de la Solicitud del Tr&aacute;mite RUNISTAC";
        String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", "La solicitud n&uacute;mero " + postulacion.getSolicitud() + " del tr&aacute;mite RUNISTAC ha sido Devuelta con la siguiente observaci&oacute;n:<br/><br/>" + (postulacion.getCausalesRechazo() == null ? "" : postulacion.getCausalesRechazo().getNombre() + " <br/> ") + postulacion.getObservacionesRechazo());
        envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(postulacion.getPersona().getTipoDocumento().getId(), postulacion.getPersona().getNumeroDocumento()), mensaje);

    }

    public void aprobarPoliza(Long id) {
        Postulacion postulacion = persistencia.obtener(id);
        if (postulacion.getEstado() != EstadoPostulacion.APROBADO) {
            throw new ApplicationException("No se pueden editar los pagos en un estado diferente a Aprobado");
        }
        postulacion.setEstado(EstadoPostulacion.POLIZA_APROBADA);
        persistencia.actualizar(postulacion);
        //colocar los ccm en estado pagado
        certificadoCancelacionMatriculaLogica.actualizarCCMPorAsignacion(postulacion.getAsignacionCCM().getId(), EstadoCCM.PAGADO);
    }

    public void solicitudSiniestroPoliza(PostulacionDTO solicitud) {
        Postulacion postulacion = persistencia.obtener(solicitud.getId());
        //validar que el usuario sea el mismo
        Persona p = postulacion.getPersona();
        String usuario = p.getTipoDocumento().getId();
        usuario = usuario + "-" + p.getNumeroDocumento();
        /*if (usuario.compareTo(infoUsuario.getUsuario()) != 0) {
            throw new ApplicationException("El usuario no es válido para hacer esta acción.");
        }*///TODO: hay que revisar con carlos
        if (postulacion.getEstado() != EstadoPostulacion.AUTORIZADO) {
            throw new ApplicationException("No se puede solicitar el siniestro de la poliza en un estado diferente a Autorizado");
        }
        //guardar anexos
        String directorio = parametroSistemaLogica.obtener(Constantes.ParametroSistema.DIRECTORIO_BASE).getValor();
        for (AnexoDTO anexo : solicitud.getAnexo()) {
            boolean nuevo = true;
            Anexo borrar=null;
            for (Anexo anexo1 : postulacion.getAnexo()) {
                if (Objects.equals(anexo.getId(), anexo1.getTipoAnexo().getId())) {
                    try {
                        anexo.setTipoAnexo(new TipoAnexoDTO(anexo.getId()));
                        if(anexo.getArchivo()!=null){
                            anexo.setUbicacion(guardarArchivo(directorio, solicitud.getId(), anexo1.getTipoAnexo().getNombre(), anexo.getArchivo()));
                        }else{
                            borrar=anexo1;
                        }
                        nuevo = false;
                    } catch (IOException ex) {
                        Logger.getLogger(PostulacionLogica.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (nuevo && anexo.getArchivo()!=null) {
                try {
                    TipoAnexoDTO anexo1 = tipoAnexoLogica.obtener(anexo.getId());
                    anexo.setTipoAnexo(new TipoAnexoDTO(anexo.getId()));
                    anexo.setId(null);
                    anexo.setUbicacion(guardarArchivo(directorio, solicitud.getId(), anexo1.getNombre(), anexo.getArchivo()));
                    postulacion.getAnexo().add(anexoLogica.convertirDTO(anexo));
                } catch (IOException ex) {
                    Logger.getLogger(PostulacionLogica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(borrar!=null){
                postulacion.getAnexo().remove(borrar);
                anexoLogica.borrar(borrar.getId());
            }
        }
        postulacion.setEstado(EstadoPostulacion.SOLICITUD_SINIESTRO);
        persistencia.actualizar(postulacion);
    }

    private String guardarArchivo(String directorio, Long idSolicitud, String nombreAnexo, String data) throws IOException {
        String ubicacion = "" + idSolicitud + "/" + nombreAnexo + ".pdf";
        new File(directorio + "/" + idSolicitud + "/").mkdirs();
        FileOutputStream fos = new FileOutputStream(directorio + "/" + ubicacion);
        String b64 = new String(new BASE64Decoder().decodeBuffer(data));

        fos.write(new BASE64Decoder().decodeBuffer(b64.substring(b64.indexOf(",") + 1)));
        fos.close();
        return ubicacion;
    }

    public DetallePostulacionCCMDTO obtenerDetalleCCM(Long idCCM) {
        Object[] info = persistencia.obtenerPostulacionPorCCM(idCCM);
        DetallePostulacionCCMDTO detalle = new DetallePostulacionCCMDTO();
        detalle.setSolicitud((String) info[0]);
        detalle.setNombreSolicitante((String) info[1]);
        detalle.setFecha((String) info[2]);
        detalle.setValorPagado((String) info[3]);
        detalle.setDocumento((String) info[4]);
        return detalle;
    }

    public List<PostulacionDTO> obtenerPostulaciones(String tipoDocumento, String numeroDocumento) {
        return convertirEntidad(persistencia.obtenerPostulaciones(tipoDocumento, numeroDocumento));
    }

    public int validarPago() {
        //obtiene la lista de postulaciones en estado registrado y que ya fueron pagados
        persistencia.validarPago();
        List<Postulacion> postulacionesEnTramite = persistencia.obtenerPorEstado(EstadoPostulacion.EN_TRAMITE);

        int conteo = 0;
        for (Postulacion postulacion : postulacionesEnTramite) {
            //cambiar estado
            postulacion.setEstado(EstadoPostulacion.APROBADO);
            persistencia.actualizar(postulacion);

            //enviar correo
            String titulo = "Aprobaci&oacute;n de la Solicitud del Tr&aacute;mite RUNISTAC";
            String textoCorreo = parametroTextoLogica.obtener("CORREO_PAGOS").getValor();
            textoCorreo = textoCorreo.replaceAll("\\$solicitud", postulacion.getSolicitud().toString());
            textoCorreo = textoCorreo.replaceAll("\\$vin", postulacion.getVin());
            textoCorreo = textoCorreo.replaceAll("\\$dias", parametroSistemaLogica.obtener("DIAS_PARA_CARGUE_POLIZA").getValor());

            String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", textoCorreo);
            envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(postulacion.getPersona().getTipoDocumento().getId(), postulacion.getPersona().getNumeroDocumento()), mensaje);

            conteo++;
        }
        return conteo;

    }

    public int validarPagosNoRealizados() {
        Integer horas = Integer.parseInt(parametroSistemaLogica.obtener("HORAS_RECHAZO_CUPL").getValor());
        persistencia.validarPagoNoRealizados(horas);
        List<Postulacion> postulacionesSinPago = persistencia.obtenerPorEstado(EstadoPostulacion.NO_PAGO);
        int conteo = 0;
        for (Postulacion postulacion : postulacionesSinPago) {
            //enviar correo
            String titulo = "Rechazo de la Solicitud del Tr&aacute;mite RUNISTAC";
            String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", "La solicitud n&uacute;mero " + postulacion.getSolicitud() + " del tr&aacute;mite <b>Solicitud Certificado de Cancelaci&oacute;n de Matr&iacute;cula</b> para el veh&iacute;culo identificado con VIN <b>" + postulacion.getVin() + "</b> ha sido <b>RECHAZADA</b><br/><br/>Motivo de rechazo: <br/><ul><li>" + postulacion.getObservacionesRechazo() + "</li></ul><br/>");
            envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(postulacion.getPersona().getTipoDocumento().getId(), postulacion.getPersona().getNumeroDocumento()), mensaje);
            //cambiar estado
            postulacion.setEstado(EstadoPostulacion.RECHAZADO);

            List<CertificadoCancelacionMatricula> ccms = certificadoCancelacionMatriculaDAO.obtenerPorAsignacion(postulacion.getAsignacionCCM().getId());
            for (CertificadoCancelacionMatricula ccm : ccms) {
                ccm.setEstado(EstadoCCM.NUEVO);
                ccm.setAsignacionCCM(null);
            }

            conteo++;
        }
        return conteo;
    }

    public void finalizarSolicitud(String vin, Long idSolicitud) {
        //consultar vin
        ConsultaVINDTO automotor = automotorLogica.consultarVin(vin);
        if (automotor == null) {
            throw new ApplicationException("No se encontro un vehiculo asociado");
        }
        //registrar el vin en las tablas
        RegistroVIN registro = new RegistroVIN();
        registro.setAutomotor(new Automotor());
        registro.getAutomotor().setId(Long.parseLong(automotor.getId()));
        registro.setPostulacion(new Postulacion());
        registro.getPostulacion().setId(idSolicitud);
        registro.setVin(vin);
        registroVINDAO.guardar(registro);
        //registrar la autorizacion
        //cambiar el estado de los ccm
        //cambiar estado de la solicitud si ya no tiene suficientes ccm
        persistencia.guardarAutorizacion(vin, idSolicitud);
    }

    private void validarCampos(PostulacionDTO dto) {
        //validar que el vin existe
        if (dto.getVin() == null) {
            throw new ApplicationException("Debe digitar el vin de la solicitud");
        }
        //validar que el vin cumpla con los requerimientos
        //la logica de automotor ya arroja un error al consultar la informacion de los vehiculos que no cumplen con las condiciones
        automotorLogica.consultarVin(dto.getVin());
    }

    /**
     * anula todas las solicitudes que no cargaron poliza
     */
    public int rechazarPolizasNoCargadas() {
        //consultar polizas en estado aprobado con mas de un dia de aprobacion
        List<Postulacion> postulaciones = persistencia.obtenerPorEstado(EstadoPostulacion.APROBADO);
        //obtener parametro de dias
        int dias = Integer.parseInt(parametroSistemaLogica.obtener("DIAS_PARA_CARGUE_POLIZA").getValor());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -dias);
        int conteo = 0;
        for (Postulacion p : postulaciones) {
            if (c.getTime().after(p.getFechaPago())) {
                //se debe anular
                p.setEstado(EstadoPostulacion.RECHAZADO);
                p.setObservacionesRechazo("El cargue de la poliza no fue realizado en el tiempo establecido para este");
                //liberar ccm
                List<CertificadoCancelacionMatricula> ccms = certificadoCancelacionMatriculaDAO.obtenerPorAsignacion(p.getAsignacionCCM().getId());
                for (CertificadoCancelacionMatricula ccm : ccms) {
                    ccm.setEstado(EstadoCCM.NUEVO);
                    ccm.setAsignacionCCM(null);
                }
                //enviar correo
                String titulo = "Rechazo de la Solicitud del Tr&aacute;mite RUNISTAC";
                String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", "La solicitud n&uacute;mero " + p.getSolicitud() + " del tr&aacute;mite <b>Solicitud Certificado de Cancelaci&oacute;n de Matr&iacute;cula</b> para el veh&iacute;culo identificado con VIN <b>" + p.getVin() + "</b> ha sido <b>RECHAZADA</b><br/><br/>Motivo de rechazo: <br/><ul><li>" + p.getObservacionesRechazo() + "</li></ul><br/>");
                envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(p.getPersona().getTipoDocumento().getId(), p.getPersona().getNumeroDocumento()), mensaje);
                conteo++;
            }
        }

        //persistencia.rechazarPolizasNoCargadas();
        return conteo;
    }

    public List<PostulacionDTO> obtenerSolicitudSiniestro() {
        return convertirEntidad(persistencia.obtenerPorEstado(EstadoPostulacion.SOLICITUD_SINIESTRO));
    }
    
    public List<PostulacionDTO> obtenerSolicitudCancelacion() {
        return convertirEntidad(persistencia.obtenerPorEstado(EstadoPostulacion.SOLICITUD_CANCELACION));
    }

    public int validarInformacionVehiculo() {
        //consultar vehiculos en estado autorizado
        List<Postulacion> postulaciones = persistencia.obtenerPorEstado(EstadoPostulacion.AUTORIZADO);

        int dias = Integer.parseInt(parametroSistemaLogica.obtener("DIAS_PARA_CARGUE_POLIZA").getValor());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -dias);
        int conteo = 0;
        for (Postulacion p : postulaciones) {
            RegistroVIN registro = registroVINDAO.obtenerPorSolicitud(p.getId());
            if (registro != null && c.getTime().before(registro.getFechaCreacion())) {
                //anular solicitud
                p.setEstado(EstadoPostulacion.RECHAZADO);
                p.setObservacionesRechazo("La matricula inicial no fue realizado en el tiempo establecido para este");
                conteo++;
            }
        }
        return conteo;
    }

    public void devolverSolicitudSiniestro(PostulacionDTO postulacionDTO) {
        Postulacion postulacion = persistencia.obtener(postulacionDTO.getId());
        postulacion.setEstado(EstadoPostulacion.AUTORIZADO);
        if (postulacionDTO.getCausalesRechazo() != null) {
            postulacion.setCausalesRechazo(new CausalesRechazo(postulacionDTO.getCausalesRechazo().getId()));
        }
        postulacion.setObservacionesRechazo(postulacionDTO.getObservacionesRechazo());
        persistencia.actualizar(postulacion);

        //enviar correo
        String titulo = "Devoluci&oacute;n de la Solicitud del Tr&aacute;mite RUNISTAC";
        String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", "La solicitud de siniestro de p&oacute;liza de cauci&oacute;n n&uacute;mero " + postulacion.getSolicitud() + " del tr&aacute;mite RUNISTAC ha sido DEVUELTA con la siguiente observaci&oacute;n: <br/><br/>" + (postulacion.getCausalesRechazo() == null ? "" : postulacion.getCausalesRechazo().getNombre() + " <br/> ") + postulacion.getObservacionesRechazo());
        envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(postulacion.getPersona().getTipoDocumento().getId(), postulacion.getPersona().getNumeroDocumento()), mensaje);
    }
    
    public void devolverSolicitudCancelacion(PostulacionDTO postulacionDTO) {
        Postulacion postulacion = persistencia.obtener(postulacionDTO.getId());
        postulacion.setEstado(EstadoPostulacion.UTILIZADO);
        postulacion.setObservacionesRechazo(postulacionDTO.getObservacionesRechazo());
        persistencia.actualizar(postulacion);

        //enviar correo
        String titulo = "Devoluci&oacute;n de la Solicitud del Tr&aacute;mite RUNISTAC";
        String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", "La solicitud de cancelaci&oacute;n de autorizaci&oacute;n de matr&iacute;cula de la postulaci&oacute;n n&uacute;mero " + postulacion.getSolicitud() + " del tr&aacute;mite RUNISTAC ha sido DEVUELTA con la siguiente observaci&oacute;n: <br/><br/>" + (postulacion.getCausalesRechazo() == null ? "" : postulacion.getCausalesRechazo().getNombre() + " <br/> ") + postulacion.getObservacionesRechazo());
        envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(postulacion.getPersona().getTipoDocumento().getId(), postulacion.getPersona().getNumeroDocumento()), mensaje);
    }

    public void rechazarSolicitudSiniestro(PostulacionDTO postulacionDTO) {
        Postulacion postulacion = persistencia.obtener(postulacionDTO.getId());
        postulacion.setEstado(EstadoPostulacion.RECHAZADO);
        if (postulacionDTO.getCausalesRechazo() != null) {
            postulacion.setCausalesRechazo(new CausalesRechazo(postulacionDTO.getCausalesRechazo().getId()));
        }
        postulacion.setObservacionesRechazo(postulacionDTO.getObservacionesRechazo());
        persistencia.actualizar(postulacion);

        //liberar ccm
        List<CertificadoCancelacionMatricula> ccms = certificadoCancelacionMatriculaDAO.obtenerPorAsignacion(postulacion.getAsignacionCCM().getId());
        for (CertificadoCancelacionMatricula ccm : ccms) {
            ccm.setEstado(EstadoCCM.NUEVO);
            ccm.setAsignacionCCM(null);
        }

        //enviar correo
        String titulo = "Devoluci&oacute;n de la Solicitud del Tr&aacute;mite RUNISTAC";
        String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", "La solicitud de siniestro de p&oacute;liza de cauci&oacute;n n&uacute;mero " + postulacion.getSolicitud() + " del tr&aacute;mite RUNISTAC ha sido Rechazada<br/>");
        envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(postulacion.getPersona().getTipoDocumento().getId(), postulacion.getPersona().getNumeroDocumento()), mensaje);
    }

    public int finalizarTodasSolicitudes() {
        List<Postulacion> lista = persistencia.obtenerPorEstado(EstadoPostulacion.AUTORIZADO);

        for (Postulacion postulacion : lista) {
            finalizarSolicitud(postulacion.getVin(), postulacion.getId());
            postulacion.setEstado(EstadoPostulacion.UTILIZADO);
            persistencia.actualizar(postulacion);
            String titulo = "Autorizaci&oacute;n de la Solicitud del Tr&aacute;mite RUNISTAC";
            String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", "La solicitud n&uacute;mero " + postulacion.getSolicitud() + " del tr&aacute;mite RUNISTAC ha sido AUTORIZADA<br/>");
            envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(postulacion.getPersona().getTipoDocumento().getId(), postulacion.getPersona().getNumeroDocumento()), mensaje);
        }

        return lista.size();
    }

    public int liberarCCM() {
        //consultar polizas en estado aprobado con mas de un dia de aprobacion
        List<Postulacion> postulaciones = persistencia.obtenerPorEstado(EstadoPostulacion.RECHAZADO);
        int conteo = 0;
        for (Postulacion p : postulaciones) {
            //liberar ccm
            List<CertificadoCancelacionMatricula> ccms = certificadoCancelacionMatriculaDAO.obtenerPorAsignacion(p.getAsignacionCCM().getId());
            for (CertificadoCancelacionMatricula ccm : ccms) {
                ccm.setEstado(EstadoCCM.NUEVO);
                ccm.setAsignacionCCM(null);
                conteo++;
            }
        }

        return conteo;
    }

    public void actualizarLiquidacion(Long idPostulacion, String idLiquidacion) {
        persistencia.actualizarLiquidacion(idPostulacion,idLiquidacion);
    }

    public InfoUsuario getInfoUsuario() {
        return infoUsuario;
    }

    public void setInfoUsuario(InfoUsuario infoUsuario) {
        this.infoUsuario = infoUsuario;
    }

    public void solicitarCancelacion(Long idPostulacion, String observaciones) {
        //consultar id
        Postulacion postulacion = persistencia.obtener(idPostulacion);
        //validar cancelacion
        persistencia.validarAnularAutorizacion(postulacion.getVin(), postulacion.getSolicitud(), infoUsuario);
        //cancelacion
        postulacion.setEstado(EstadoPostulacion.SOLICITUD_CANCELACION);
        postulacion.setObservacionesRechazo(observaciones);
        //enviar correo
        String titulo = "Rechazo de la Solicitud del Tr&aacute;mite RUNISTAC";
        String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", "La solicitud n&uacute;mero " + postulacion.getSolicitud() + " del tr&aacute;mite RUNISTAC ha sido RECHAZADA<br/><br/>" + postulacion.getObservacionesRechazo());
        envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(postulacion.getPersona().getTipoDocumento().getId(), postulacion.getPersona().getNumeroDocumento()), mensaje);
        
    }

    public List<PostulacionDTO> obtenerPorFiltro(ConsultaPostulacionDTO dto) {
        return convertirEntidad(persistencia.obtenerPorFiltro(dto));
    }
    
    public List<PostulacionDTO> obtenerPostulacionesAprobadas(String tipoDocumento, String numeroDocumento){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        return convertirEntidad(persistencia.obtenerPostulacionesAprobadasPorFecha(tipoDocumento, numeroDocumento, c.getTime(), new Date()));
    }
    
}
