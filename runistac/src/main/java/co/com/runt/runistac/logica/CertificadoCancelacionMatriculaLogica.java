package co.com.runt.runistac.logica;

import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.persistencia.*;
import co.com.runt.runistac.persistencia.entity.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Schedule;

/**
 * @generated
 */
@Stateless
public class CertificadoCancelacionMatriculaLogica {

    @EJB
    private CertificadoCancelacionMatriculaDAO persistencia;
    
    private final SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * @generated
     */
    public List<CertificadoCancelacionMatriculaDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * @generated
     */
    public CertificadoCancelacionMatriculaDTO obtener(Long id) {
        return convertirEntidad(persistencia.obtener(id));
    }
    
    /**
     * obtiene la cantidad de certificados disponibles
     * @param id
     * @return 
     */
    public Integer obtenerDisponibles() {
        return persistencia.obtenerDisponibles();
    }

    /**
     * @generated
     */
    public CertificadoCancelacionMatriculaDTO guardar(CertificadoCancelacionMatriculaDTO dto) {
        return convertirEntidad(persistencia.guardar(convertirDTO(dto)));
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
    public void actualizar(CertificadoCancelacionMatriculaDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public CertificadoCancelacionMatricula convertirDTO(CertificadoCancelacionMatriculaDTO dto) {
        if (dto == null) {
            return null;
        }
        CertificadoCancelacionMatricula entidad = new CertificadoCancelacionMatricula();
        entidad.setId(dto.getId());
        if (dto.getFecha() != null) {
            try {
                entidad.setFecha(fecha.parse(dto.getFecha()));
            } catch (ParseException ex) {
                throw new RuntimeException("Error al convertir la fecha Fecha " + dto.getFecha());
            }
        }
        entidad.setMotivo(dto.getMotivo());
        entidad.setEstado(dto.getEstado());
        entidad.setNumeroCertificado(dto.getNumeroCertificado());

        if (dto.getAutomotor() != null) {
            entidad.setAutomotor(new Automotor());
            entidad.getAutomotor().setId(dto.getAutomotor().getId());
        }

        return entidad;
    }

    /**
     * @generated
     */
    public List<CertificadoCancelacionMatricula> convertirDTO(List<CertificadoCancelacionMatriculaDTO> dtos) {
        List<CertificadoCancelacionMatricula> entidades = new ArrayList<CertificadoCancelacionMatricula>();
        for (CertificadoCancelacionMatriculaDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public CertificadoCancelacionMatriculaDTO convertirEntidad(CertificadoCancelacionMatricula entidad) {
        CertificadoCancelacionMatriculaDTO dto = new CertificadoCancelacionMatriculaDTO();
        dto.setId(entidad.getId());
        if (entidad.getFecha() != null) {
            dto.setFecha(fecha.format(entidad.getFecha()));
        }
        dto.setMotivo(entidad.getMotivo());
        dto.setEstado(entidad.getEstado());
        dto.setNumeroCertificado(entidad.getNumeroCertificado());

        if (entidad.getAutomotor() != null) {
            dto.setAutomotor(
                    new AutomotorDTO(
                            entidad.getAutomotor().getId()));
            dto.getAutomotor().setPlaca(entidad.getAutomotor().getPlaca());
        }

        return dto;
    }

    /**
     * @generated
     */
    public List<CertificadoCancelacionMatriculaDTO> convertirEntidad(List<CertificadoCancelacionMatricula> entidades) {
        List<CertificadoCancelacionMatriculaDTO> dtos = new ArrayList<CertificadoCancelacionMatriculaDTO>();
        for (CertificadoCancelacionMatricula entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

    /**
     * consulta la lista de certificados por los parametros dados
     * @param dto parametros a consultar
     * @return 
     */
    public List<CertificadoCancelacionMatriculaDTO> consultar(ConsultaCCMDTO dto) {
        return convertirEntidad(persistencia.consultar(dto));
    }

    public int generarCCM(){
        return persistencia.generarCCM();
    }
    
    public void actualizarCCMPorAsignacion(Long idAsignacion, EstadoCCM estado){
        List<CertificadoCancelacionMatricula> lista = persistencia.obtenerPorAsignacion(idAsignacion);
        for (CertificadoCancelacionMatricula ccm : lista) {
            ccm.setEstado(estado);
        }
    }
    
    public List<CertificadoCancelacionMatriculaDTO> obtenerPorAsignacion(Long idAsignacion){
        return convertirEntidad(persistencia.obtenerPorAsignacion(idAsignacion));
    }
    
    public List<ConsultaEstadoCCMDTO> consultarCCMPorEstado() {
        List<ConsultaEstadoCCMDTO> consulta=new ArrayList<>();
        boolean asignado=false;
        boolean utilizado=false;
        boolean nuevo=false;
        for(Object[] ob:persistencia.consultarCCMPorEstado()){
            ConsultaEstadoCCMDTO c=new ConsultaEstadoCCMDTO();
            c.setFecha((String)ob[0]);
            c.setEstado((String)ob[1]);
            c.setCantidad(((BigDecimal)ob[2]).intValue());
            consulta.add(c);
            if(c.getEstado().compareTo("NUEVO")==0){
                nuevo=true;
            }
            if(c.getEstado().compareTo("USADO")==0){
                asignado=true;
            }
            
            if(c.getEstado().compareTo("EN CERTIFICADO CUMPLIMIENTO")==0){
                utilizado=true;
            }
             
        }
        if(!nuevo){
            ConsultaEstadoCCMDTO c=new ConsultaEstadoCCMDTO();
            c.setEstado("NUEVO");
            c.setCantidad(0);
            c.setFecha(fecha.format(new Date()));
            consulta.add(c);
        }
        if(!asignado){
            ConsultaEstadoCCMDTO c=new ConsultaEstadoCCMDTO();
            c.setEstado("USADO");
            c.setCantidad(0);
            c.setFecha(fecha.format(new Date()));
            consulta.add(c);
        }
        
        if(!utilizado){
            ConsultaEstadoCCMDTO c=new ConsultaEstadoCCMDTO();
            c.setEstado("EN CERTIFICADO CUMPLIMIENTO");
            c.setCantidad(0);
            c.setFecha(fecha.format(new Date()));
            consulta.add(c);
        }
        return consulta;
    }
    
    
}
