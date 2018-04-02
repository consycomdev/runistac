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
public class CausalesRechazoLogica {

    @EJB
    private CausalesRechazoDAO persistencia;

    /**
     * @generated
     */
    public List<CausalesRechazoDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * @generated
     */
    public CausalesRechazoDTO obtener(Long id) {
        return convertirEntidad(persistencia.obtener(id));
    }

    /**
     * @generated
     */
    public CausalesRechazoDTO guardar(CausalesRechazoDTO dto) {
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
    public void actualizar(CausalesRechazoDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public CausalesRechazo convertirDTO(CausalesRechazoDTO dto) {
        if (dto == null) {
            return null;
        }
        CausalesRechazo entidad = new CausalesRechazo();
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    /**
     * @generated
     */
    public List<CausalesRechazo> convertirDTO(List<CausalesRechazoDTO> dtos) {
        List<CausalesRechazo> entidades = new ArrayList<CausalesRechazo>();
        for (CausalesRechazoDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public CausalesRechazoDTO convertirEntidad(CausalesRechazo entidad) {
        CausalesRechazoDTO dto = new CausalesRechazoDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    /**
     * @generated
     */
    public List<CausalesRechazoDTO> convertirEntidad(List<CausalesRechazo> entidades) {
        List<CausalesRechazoDTO> dtos = new ArrayList<CausalesRechazoDTO>();
        for (CausalesRechazo entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }
    
    public List<CausalesRechazoDTO> obtenerPorTipo(TipoCausalRechazo tipoCausalRechazo){
        return convertirEntidad(persistencia.obtenerPorTipo(tipoCausalRechazo));
    }

}
