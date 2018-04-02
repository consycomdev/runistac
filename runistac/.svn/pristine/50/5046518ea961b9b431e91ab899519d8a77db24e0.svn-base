package co.com.runt.runistac.logica;

import co.com.runt.common.excepciones.ApplicationException;
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
public class TipoAnexoLogica {

    @EJB
    private TipoAnexoDAO persistencia;
    
    @EJB
    private AnexoDAO anexoDAO;

    /**
     * @generated
     */
    public List<TipoAnexoDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }
    
    public List<TipoAnexoDTO> obtenerSolicitud() {
        return convertirEntidad(persistencia.obtenerAnexosTipo(TipoProceso.SOLICITUD));
    }
    
    public List<TipoAnexoDTO> obtenerRegistroPoliza() {
        return convertirEntidad(persistencia.obtenerAnexosTipo(TipoProceso.REGISTRO_POLIZA));
    }
    
    public List<TipoAnexoDTO> obtenerRegistroPolizaTipo(TipoProceso tipo) {
        return convertirEntidad(persistencia.obtenerAnexosTipo(tipo));
    }

    /**
     * @generated
     */
    public TipoAnexoDTO obtener(Long id) {
        return convertirEntidad(persistencia.obtener(id));
    }

    /**
     * @generated
     */
    public TipoAnexoDTO guardar(TipoAnexoDTO dto) {
        return convertirEntidad(persistencia.guardar(convertirDTO(dto)));
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        if(!anexoDAO.obtenerPorTipoAnexo(id).isEmpty()){
            throw new ApplicationException("No se puede modificar un elemento que ya fue utilizado");
        }
        persistencia.borrar(id);
    }

    /**
     * @generated
     */
    public void actualizar(TipoAnexoDTO dto) {
        //no permite actualizar si tiene un registro
        if(!anexoDAO.obtenerPorTipoAnexo(dto.getId()).isEmpty()){
            throw new ApplicationException("No se puede modificar un elemento que ya fue utilizado");
        }
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public TipoAnexo convertirDTO(TipoAnexoDTO dto) {
        if (dto == null) {
            return null;
        }
        TipoAnexo entidad = new TipoAnexo();
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setTipoProceso(dto.getTipoProceso());

        return entidad;
    }

    /**
     * @generated
     */
    public List<TipoAnexo> convertirDTO(List<TipoAnexoDTO> dtos) {
        List<TipoAnexo> entidades = new ArrayList<TipoAnexo>();
        for (TipoAnexoDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public TipoAnexoDTO convertirEntidad(TipoAnexo entidad) {
        TipoAnexoDTO dto = new TipoAnexoDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setTipoProceso(entidad.getTipoProceso());
        return dto;
    }

    /**
     * @generated
     */
    public List<TipoAnexoDTO> convertirEntidad(List<TipoAnexo> entidades) {
        List<TipoAnexoDTO> dtos = new ArrayList<TipoAnexoDTO>();
        for (TipoAnexo entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

}
