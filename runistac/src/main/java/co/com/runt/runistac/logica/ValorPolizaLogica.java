package co.com.runt.runistac.logica;

import co.com.runt.runistac.persistencia.entity.ValorPoliza;
import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.persistencia.*;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @generated
 */
@Stateless
public class ValorPolizaLogica {

    @EJB
    private ValorPolizaDAO persistencia;

    /**
     * retorna una lista con los ValorPoliza que se encuentran en la base de
     * datos
     *
     * @return retorna una lista de ValorPolizaDTO
     * @generated
     */
    public List<ValorPolizaDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * @param id identificador del elemento ValorPoliza
     * @return ValorPoliza del id dado
     * @generated
     */
    public ValorPolizaDTO obtener(Long id) {
        return convertirEntidad(persistencia.obtener(id));
    }

    /**
     * almacena la informacion de ValorPoliza
     *
     * @param dto ValorPoliza a guardar
     * @return ValorPoliza con los cambios realizados por el proceso de guardar
     * @generated
     */
    public ValorPolizaDTO guardar(ValorPolizaDTO dto) {
        return convertirEntidad(persistencia.guardar(convertirDTO(dto)));
    }

    /**
     * Elimina el registro ValorPoliza con el identificador dado
     *
     * @param id identificador del ValorPoliza
     * @generated
     */
    public void borrar(Long id) {
        persistencia.borrar(id);
    }

    /**
     * actualiza la informacion de ValorPoliza
     *
     * @param dto ValorPoliza a guardar
     * @return ValorPoliza con los cambios realizados por el proceso de guardar
     * @generated
     */
    public void actualizar(ValorPolizaDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public ValorPoliza convertirDTO(ValorPolizaDTO dto) {
        if (dto == null) {
            return null;
        }
        ValorPoliza entidad = new ValorPoliza();
        entidad.setId(dto.getId());
        entidad.setValor(dto.getValor());
        entidad.setEstado(dto.getEstado());

        return entidad;
    }

    /**
     * @generated
     */
    public List<ValorPoliza> convertirDTO(List<ValorPolizaDTO> dtos) {
        List<ValorPoliza> entidades = new ArrayList<ValorPoliza>();
        for (ValorPolizaDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public ValorPolizaDTO convertirEntidad(ValorPoliza entidad) {
        ValorPolizaDTO dto = new ValorPolizaDTO();
        dto.setId(entidad.getId());
        dto.setValor(entidad.getValor());
        dto.setEstado(entidad.getEstado());

        return dto;
    }

    /**
     * @generated
     */
    public List<ValorPolizaDTO> convertirEntidad(List<ValorPoliza> entidades) {
        List<ValorPolizaDTO> dtos = new ArrayList<ValorPolizaDTO>();
        for (ValorPoliza entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

}
