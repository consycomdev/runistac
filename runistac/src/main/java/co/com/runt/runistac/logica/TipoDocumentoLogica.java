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
public class TipoDocumentoLogica {

    @EJB
    private TipoDocumentoDAO persistencia;

    /**
     * @generated
     */
    public List<TipoDocumentoDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * @generated
     */
    public TipoDocumentoDTO obtener(Long id) {
        return convertirEntidad(persistencia.obtener(id));
    }

    /**
     * @generated
     */
    public TipoDocumentoDTO guardar(TipoDocumentoDTO dto) {
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
    public void actualizar(TipoDocumentoDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public TipoDocumento convertirDTO(TipoDocumentoDTO dto) {
        if (dto == null) {
            return null;
        }
        TipoDocumento entidad = new TipoDocumento();
        entidad.setId(dto.getId());
        entidad.setAbreviatura(dto.getAbreviatura());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    /**
     * @generated
     */
    public List<TipoDocumento> convertirDTO(List<TipoDocumentoDTO> dtos) {
        List<TipoDocumento> entidades = new ArrayList<TipoDocumento>();
        for (TipoDocumentoDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public TipoDocumentoDTO convertirEntidad(TipoDocumento entidad) {
        TipoDocumentoDTO dto = new TipoDocumentoDTO();
        dto.setId(entidad.getId());
        dto.setAbreviatura(entidad.getAbreviatura());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    /**
     * @generated
     */
    public List<TipoDocumentoDTO> convertirEntidad(List<TipoDocumento> entidades) {
        List<TipoDocumentoDTO> dtos = new ArrayList<TipoDocumentoDTO>();
        for (TipoDocumento entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

}
