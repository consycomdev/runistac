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
public class ParametroTextoLogica {

    @EJB
    private ParametroTextoDAO persistencia;

    /**
     * retorna una lista con los ParametroTexto que se encuentran en la base de
     * datos
     *
     * @return retorna una lista de ParametroTextoDTO
     * @generated
     */
    public List<ParametroTextoDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * @param id identificador del elemento ParametroTexto
     * @return ParametroTexto del id dado
     * @generated
     */
    public ParametroTextoDTO obtener(String clave) {
        return convertirEntidad(persistencia.obtener(clave));
    }

    /**
     * almacena la informacion de ParametroTexto
     *
     * @param dto ParametroTexto a guardar
     * @return ParametroTexto con los cambios realizados por el proceso de
     * guardar
     * @generated
     */
    public ParametroTextoDTO guardar(ParametroTextoDTO dto) {
        return convertirEntidad(persistencia.guardar(convertirDTO(dto)));
    }

    /**
     * Elimina el registro ParametroTexto con el identificador dado
     *
     * @param id identificador del ParametroTexto
     * @generated
     */
    public void borrar(String clave) {
        persistencia.borrar(clave);
    }

    /**
     * actualiza la informacion de ParametroTexto
     *
     * @param dto ParametroTexto a guardar
     * @return ParametroTexto con los cambios realizados por el proceso de
     * guardar
     * @generated
     */
    public void actualizar(ParametroTextoDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public ParametroTexto convertirDTO(ParametroTextoDTO dto) {
        if (dto == null) {
            return null;
        }
        ParametroTexto entidad = new ParametroTexto();
        entidad.setClave(dto.getClave());
        entidad.setValor(dto.getValor());

        return entidad;
    }

    /**
     * @generated
     */
    public List<ParametroTexto> convertirDTO(List<ParametroTextoDTO> dtos) {
        List<ParametroTexto> entidades = new ArrayList<ParametroTexto>();
        for (ParametroTextoDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public ParametroTextoDTO convertirEntidad(ParametroTexto entidad) {
        ParametroTextoDTO dto = new ParametroTextoDTO();
        dto.setClave(entidad.getClave());
        dto.setValor(entidad.getValor());

        return dto;
    }

    /**
     * @generated
     */
    public List<ParametroTextoDTO> convertirEntidad(List<ParametroTexto> entidades) {
        List<ParametroTextoDTO> dtos = new ArrayList<ParametroTextoDTO>();
        for (ParametroTexto entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

}
