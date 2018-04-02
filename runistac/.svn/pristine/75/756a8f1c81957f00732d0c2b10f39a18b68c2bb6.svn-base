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
public class EmpresaLogica {

    @EJB
    private EmpresaDAO persistencia;
    
    @EJB
    private PersonaLogica personaLogica;

    /**
     * @generated
     */
    public List<EmpresaDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * @generated
     */
    public EmpresaDTO obtener(Long id) {
        return convertirEntidad(persistencia.obtener(id));
    }

    /**
     * @generated
     */
    public EmpresaDTO guardar(EmpresaDTO dto) {
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
    public void actualizar(EmpresaDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public Empresa convertirDTO(EmpresaDTO dto) {
        if (dto == null) {
            return null;
        }
        Empresa entidad = new Empresa();
        entidad.setId(dto.getId());
        entidad.setRazonSocial(dto.getRazonSocial());

        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }

        return entidad;
    }

    /**
     * @generated
     */
    public List<Empresa> convertirDTO(List<EmpresaDTO> dtos) {
        List<Empresa> entidades = new ArrayList<Empresa>();
        for (EmpresaDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public EmpresaDTO convertirEntidad(Empresa entidad) {
        EmpresaDTO dto = new EmpresaDTO();
        dto.setId(entidad.getId());
        dto.setRazonSocial(entidad.getRazonSocial());

        if (entidad.getPersona() != null) {
            dto.setPersona(personaLogica.convertirEntidad(entidad.getPersona()));
        }

        return dto;
    }

    /**
     * @generated
     */
    public List<EmpresaDTO> convertirEntidad(List<Empresa> entidades) {
        List<EmpresaDTO> dtos = new ArrayList<EmpresaDTO>();
        for (Empresa entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

}
