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
public class PersonaLogica {

    @EJB
    private PersonaDAO persistencia;
    
    @EJB
    private PersonaNaturalDAO personaNaturalDAO;

    @EJB
    private EmpresaDAO empresaDAO;

    
    @EJB
    private TipoDocumentoLogica tipoDocumentoLogica;

    /**
     * @generated
     */
    public List<PersonaDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * @generated
     */
    public PersonaDTO obtener(Long id) {
        return convertirEntidad(persistencia.obtener(id));
    }
    
    public PersonaDTO obtenerTipoYNumeroDocumento(String tipoDocumento, String numeroDocumento) {
        Persona persona=persistencia.obtenerTipoYNumeroDocumento(tipoDocumento, numeroDocumento);
        if(persona.getTipoDocumento()!=null && "N".equals(persona.getTipoDocumento().getId())){
            //persona juridica
            Empresa empresa=empresaDAO.obtener(persona.getId());
            PersonaDTO personaDTO=convertirEntidad(persona);
            personaDTO.setNombre(empresa.getRazonSocial());
            return personaDTO;
        }else{
            PersonaNatural pn=personaNaturalDAO.obtener(persona.getId());
            PersonaDTO personaDTO=convertirEntidad(persona);
            personaDTO.setNombre(pn.getPrimerNombre()+" "+pn.getSegundoNombre()+" "+
                    pn.getPrimerApellido()+" "+pn.getSegundoApellido());
            return personaDTO;
        }
    }

    /**
     * @generated
     */
    public PersonaDTO guardar(PersonaDTO dto) {
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
    public void actualizar(PersonaDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public Persona convertirDTO(PersonaDTO dto) {
        if (dto == null) {
            return null;
        }
        Persona entidad = new Persona();
        entidad.setId(dto.getId());
        entidad.setNumeroDocumento(dto.getNumeroDocumento());

        if (dto.getTipoDocumento() != null) {
            entidad.setTipoDocumento(new TipoDocumento());
            entidad.getTipoDocumento().setId(dto.getTipoDocumento().getId());
        }

        return entidad;
    }

    /**
     * @generated
     */
    public List<Persona> convertirDTO(List<PersonaDTO> dtos) {
        List<Persona> entidades = new ArrayList<Persona>();
        for (PersonaDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public PersonaDTO convertirEntidad(Persona entidad) {
        PersonaDTO dto = new PersonaDTO();
        dto.setId(entidad.getId());
        dto.setNumeroDocumento(entidad.getNumeroDocumento());

        if (entidad.getTipoDocumento() != null) {
            dto.setTipoDocumento(tipoDocumentoLogica.convertirEntidad(entidad.getTipoDocumento()));
            if("N".compareTo(entidad.getTipoDocumento().getId())==0){
                Empresa empresa = empresaDAO.obtener(entidad.getId());
                dto.setNombre(empresa.getRazonSocial());
            }else{
                String nombre="";
                PersonaNatural persona = personaNaturalDAO.obtener(entidad.getId());
                nombre += " " + (persona.getPrimerNombre() == null ? "" : persona.getPrimerNombre());
                nombre += " " + (persona.getSegundoNombre() == null ? "" : persona.getSegundoNombre());
                nombre += " " + (persona.getPrimerApellido() == null ? "" : persona.getPrimerApellido());
                nombre += " " + (persona.getSegundoApellido() == null ? "" : persona.getSegundoApellido());
                dto.setNombre(nombre);
            }
        }

        return dto;
    }

    /**
     * @generated
     */
    public List<PersonaDTO> convertirEntidad(List<Persona> entidades) {
        List<PersonaDTO> dtos = new ArrayList<PersonaDTO>();
        for (Persona entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

}
