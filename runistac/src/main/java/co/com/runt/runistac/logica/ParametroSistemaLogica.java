package co.com.runt.runistac.logica;

import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.persistencia.*;
import co.com.runt.runistac.persistencia.entity.*;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @generated
 */
@Stateless
public class ParametroSistemaLogica {

    @EJB
    private ParametroSistemaDAO persistencia;

    /**
     * @generated
     */
    public List<ParametroSistemaDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * @generated
     */
    public ParametroSistemaDTO obtener(String id) {
        return convertirEntidad(persistencia.obtener(id));
    }

    /**
     * @generated
     */
    public ParametroSistemaDTO guardar(ParametroSistemaDTO dto) {
        return convertirEntidad(persistencia.guardar(convertirDTO(dto)));
    }

    /**
     * @generated
     */
    public void borrar(String id) {
        persistencia.borrar(id);
    }

    /**
     * @generated
     */
    public void actualizar(ParametroSistemaDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public ParametroSistema convertirDTO(ParametroSistemaDTO dto) {
        if (dto == null) {
            return null;
        }
        ParametroSistema entidad = new ParametroSistema();
        entidad.setClave(dto.getClave());
        entidad.setValor(dto.getValor());

        return entidad;
    }

    /**
     * @generated
     */
    public List<ParametroSistema> convertirDTO(List<ParametroSistemaDTO> dtos) {
        List<ParametroSistema> entidades = new ArrayList<ParametroSistema>();
        for (ParametroSistemaDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public ParametroSistemaDTO convertirEntidad(ParametroSistema entidad) {
        ParametroSistemaDTO dto = new ParametroSistemaDTO();
        dto.setClave(entidad.getClave());
        dto.setValor(entidad.getValor());

        return dto;
    }

    /**
     * @generated
     */
    public List<ParametroSistemaDTO> convertirEntidad(List<ParametroSistema> entidades) {
        List<ParametroSistemaDTO> dtos = new ArrayList<ParametroSistemaDTO>();
        for (ParametroSistema entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

}
