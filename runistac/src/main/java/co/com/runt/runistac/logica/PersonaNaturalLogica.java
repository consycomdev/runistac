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
public class PersonaNaturalLogica {

    @EJB
    private PersonaNaturalDAO persistencia;
    
    @EJB
    private PersonaLogica personaLogica;

    /**
     * @generated
     */
    public List<PersonaNaturalDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * @generated
     */
    public PersonaNaturalDTO obtener(Long id) {
        //obtener persona natural
        PersonaNatural pn=persistencia.obtener(id);
        return convertirEntidad(pn);
    }

    /**
     * @generated
     */
    public PersonaNaturalDTO guardar(PersonaNaturalDTO dto) {
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
    public void actualizar(PersonaNaturalDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public PersonaNatural convertirDTO(PersonaNaturalDTO dto) {
        if (dto == null) {
            return null;
        }
        PersonaNatural entidad = new PersonaNatural();
        entidad.setId(dto.getId());
        entidad.setPrimerNombre(dto.getPrimerNombre());
        entidad.setPrimerApellido(dto.getPrimerApellido());
        entidad.setSegundoNombre(dto.getSegundoNombre());
        entidad.setSegundoApellido(dto.getSegundoApellido());

        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }

        return entidad;
    }

    /**
     * @generated
     */
    public List<PersonaNatural> convertirDTO(List<PersonaNaturalDTO> dtos) {
        List<PersonaNatural> entidades = new ArrayList<PersonaNatural>();
        for (PersonaNaturalDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public PersonaNaturalDTO convertirEntidad(PersonaNatural entidad) {
        PersonaNaturalDTO dto = new PersonaNaturalDTO();
        dto.setId(entidad.getId());
        dto.setPrimerNombre(entidad.getPrimerNombre());
        dto.setPrimerApellido(entidad.getPrimerApellido());
        dto.setSegundoNombre(entidad.getSegundoNombre());
        dto.setSegundoApellido(entidad.getSegundoApellido());

        if (entidad.getPersona() != null) {
            dto.setPersona(personaLogica.convertirEntidad(entidad.getPersona()));
        }

        return dto;
    }

    /**
     * @generated
     */
    public List<PersonaNaturalDTO> convertirEntidad(List<PersonaNatural> entidades) {
        List<PersonaNaturalDTO> dtos = new ArrayList<PersonaNaturalDTO>();
        for (PersonaNatural entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

}
