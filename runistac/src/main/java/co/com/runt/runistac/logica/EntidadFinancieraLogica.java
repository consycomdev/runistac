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

/**
 * @generated
 */
@Stateless
public class EntidadFinancieraLogica {

    @EJB
    private EntidadFinancieraDAO persistencia;

    /**
     * @generated
     */
    public List<EntidadFinancieraDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }
    
    public List<EntidadFinancieraDTO> obtenerPorTipo(TipoEntidadFinanciera tipo){
        return convertirEntidad(persistencia.obtenerPorTipo(tipo));
    }

    /**
     * @generated
     */
    public EntidadFinancieraDTO obtener(Long id) {
        return convertirEntidad(persistencia.obtener(id));
    }

    /**
     * @generated
     */
    public EntidadFinancieraDTO guardar(EntidadFinancieraDTO dto) {
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
    public void actualizar(EntidadFinancieraDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public EntidadFinanciera convertirDTO(EntidadFinancieraDTO dto) {
        if (dto == null) {
            return null;
        }
        EntidadFinanciera entidad = new EntidadFinanciera();
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setNit(dto.getNit());
        entidad.setCorreo(dto.getCorreo());
        entidad.setGarantiaBancaria(dto.getGarantiaBancaria());
        entidad.setSolicitudCCM(dto.getSolicitudCCM());
        entidad.setTipoEntidadFinanciera(dto.getTipo());
        
        return entidad;
    }

    /**
     * @generated
     */
    public List<EntidadFinanciera> convertirDTO(List<EntidadFinancieraDTO> dtos) {
        List<EntidadFinanciera> entidades = new ArrayList<EntidadFinanciera>();
        for (EntidadFinancieraDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public EntidadFinancieraDTO convertirEntidad(EntidadFinanciera entidad) {
        EntidadFinancieraDTO dto = new EntidadFinancieraDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setCorreo(entidad.getCorreo());
        dto.setGarantiaBancaria(entidad.getGarantiaBancaria());
        dto.setNit(entidad.getNit());
        dto.setSolicitudCCM(entidad.getSolicitudCCM());
        dto.setTipo(entidad.getTipoEntidadFinanciera());
        
        return dto;
    }

    /**
     * @generated
     */
    public List<EntidadFinancieraDTO> convertirEntidad(List<EntidadFinanciera> entidades) {
        List<EntidadFinancieraDTO> dtos = new ArrayList<EntidadFinancieraDTO>();
        for (EntidadFinanciera entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }
    
    public EntidadFinancieraDTO obtenerEsLeasing(String nit){
        List<EntidadFinanciera> entidad=persistencia.obtenerBancoPorNit(nit);
        if(!entidad.isEmpty()){
            if(entidad.get(0).getSolicitudCCM()){
                return convertirEntidad(entidad.get(0));
            }
        }
        return null;
    }

}
