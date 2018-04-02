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
public class ConsecutivoSolicitudLogica {

    @EJB
    private ConsecutivoSolicitudDAO persistencia;

    /**
     * @generated
     */
    public List<ConsecutivoSolicitudDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * @generated
     */
    public ConsecutivoSolicitudDTO obtener(String id) {
        return convertirEntidad(persistencia.obtener(id));
    }

    /**
     * @generated
     */
    public ConsecutivoSolicitudDTO guardar(ConsecutivoSolicitudDTO dto) {
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
    public void actualizar(ConsecutivoSolicitudDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public ConsecutivoSolicitud convertirDTO(ConsecutivoSolicitudDTO dto) {
        if (dto == null) {
            return null;
        }
        ConsecutivoSolicitud entidad = new ConsecutivoSolicitud();
        entidad.setSecuencia(dto.getSecuencia());
        entidad.setValor(dto.getValor());

        return entidad;
    }

    /**
     * @generated
     */
    public List<ConsecutivoSolicitud> convertirDTO(List<ConsecutivoSolicitudDTO> dtos) {
        List<ConsecutivoSolicitud> entidades = new ArrayList<ConsecutivoSolicitud>();
        for (ConsecutivoSolicitudDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public ConsecutivoSolicitudDTO convertirEntidad(ConsecutivoSolicitud entidad) {
        ConsecutivoSolicitudDTO dto = new ConsecutivoSolicitudDTO();
        dto.setSecuencia(entidad.getSecuencia());
        dto.setValor(entidad.getValor());

        return dto;
    }

    /**
     * @generated
     */
    public List<ConsecutivoSolicitudDTO> convertirEntidad(List<ConsecutivoSolicitud> entidades) {
        List<ConsecutivoSolicitudDTO> dtos = new ArrayList<ConsecutivoSolicitudDTO>();
        for (ConsecutivoSolicitud entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

}
