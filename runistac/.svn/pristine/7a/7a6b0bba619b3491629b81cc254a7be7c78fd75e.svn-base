package co.com.runt.runistac.logica;

import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.persistencia.*;
import co.com.runt.runistac.persistencia.entity.*;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @generated
 */
@Stateless
public class RegistroEstadosLogica {

    @EJB
    private RegistroEstadosDAO persistencia;

    private final SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * retorna una lista con los RegistroEstados que se encuentran en la base de
     * datos
     *
     * @return retorna una lista de RegistroEstadosDTO
     * @generated
     */
    public List<RegistroEstadosDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * @param id identificador del elemento RegistroEstados
     * @return RegistroEstados del id dado
     * @generated
     */
    public RegistroEstadosDTO obtener(Long id) {
        return convertirEntidad(persistencia.obtener(id));
    }

    /**
     * almacena la informacion de RegistroEstados
     *
     * @param dto RegistroEstados a guardar
     * @return RegistroEstados con los cambios realizados por el proceso de
     * guardar
     * @generated
     */
    public RegistroEstadosDTO guardar(RegistroEstadosDTO dto) {
        return convertirEntidad(persistencia.guardar(convertirDTO(dto)));
    }

    /**
     * Elimina el registro RegistroEstados con el identificador dado
     *
     * @param id identificador del RegistroEstados
     * @generated
     */
    public void borrar(Long id) {
        persistencia.borrar(id);
    }

    /**
     * actualiza la informacion de RegistroEstados
     *
     * @param dto RegistroEstados a guardar
     * @return RegistroEstados con los cambios realizados por el proceso de
     * guardar
     * @generated
     */
    public void actualizar(RegistroEstadosDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public RegistroEstados convertirDTO(RegistroEstadosDTO dto) {
        if (dto == null) {
            return null;
        }
        RegistroEstados entidad = new RegistroEstados();
        entidad.setId(dto.getId());
        entidad.setIdPostulacion(dto.getIdPostulacion());
        entidad.setEstado(dto.getEstado());
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
    public List<RegistroEstados> convertirDTO(List<RegistroEstadosDTO> dtos) {
        List<RegistroEstados> entidades = new ArrayList<RegistroEstados>();
        for (RegistroEstadosDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public RegistroEstadosDTO convertirEntidad(RegistroEstados entidad) {
        RegistroEstadosDTO dto = new RegistroEstadosDTO();
        dto.setId(entidad.getId());
        dto.setIdPostulacion(entidad.getIdPostulacion());
        dto.setEstado(entidad.getEstado());
        if (entidad.getFecha() != null) {
            dto.setFecha(fecha.format(entidad.getFecha()));
        }

        return dto;
    }

    /**
     * @generated
     */
    public List<RegistroEstadosDTO> convertirEntidad(List<RegistroEstados> entidades) {
        List<RegistroEstadosDTO> dtos = new ArrayList<RegistroEstadosDTO>();
        for (RegistroEstados entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

    public List<RegistroEstadosDTO> obtenerPorPostulacion(Long id) {
        return convertirEntidad(persistencia.obtenerPorPostulacion(id));
    }

}
