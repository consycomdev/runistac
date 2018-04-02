package co.com.runt.runistac.logica;

import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.persistencia.*;
import co.com.runt.runistac.persistencia.entity.*;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @generated
 */
@Stateless
public class AsignacionCCMLogica {

    @EJB
    private AsignacionCCMDAO persistencia;

    private final SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * @generated
     */
    public List<AsignacionCCMDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * @generated
     */
    public AsignacionCCMDTO obtener(Long id) {
        return convertirEntidad(persistencia.obtener(id));
    }

    /**
     * @generated
     */
    public AsignacionCCMDTO guardar(AsignacionCCMDTO dto) {
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
    public void actualizar(AsignacionCCMDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public AsignacionCCM convertirDTO(AsignacionCCMDTO dto) {
        if (dto == null) {
            return null;
        }
        AsignacionCCM entidad = new AsignacionCCM();
        entidad.setId(dto.getId());
        if (dto.getFecha() != null) {
            try {
                entidad.setFecha(fecha.parse(dto.getFecha()));
            } catch (ParseException ex) {
                throw new RuntimeException("Error al convertir la fecha Fecha " + dto.getFecha());
            }
        }

        return entidad;
    }

    /**
     * @generated
     */
    public List<AsignacionCCM> convertirDTO(List<AsignacionCCMDTO> dtos) {
        List<AsignacionCCM> entidades = new ArrayList<AsignacionCCM>();
        for (AsignacionCCMDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public AsignacionCCMDTO convertirEntidad(AsignacionCCM entidad) {
        AsignacionCCMDTO dto = new AsignacionCCMDTO();
        dto.setId(entidad.getId());
        if (entidad.getFecha() != null) {
            dto.setFecha(fecha.format(entidad.getFecha()));
        }

        return dto;
    }

    /**
     * @generated
     */
    public List<AsignacionCCMDTO> convertirEntidad(List<AsignacionCCM> entidades) {
        List<AsignacionCCMDTO> dtos = new ArrayList<AsignacionCCMDTO>();
        for (AsignacionCCM entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

}
