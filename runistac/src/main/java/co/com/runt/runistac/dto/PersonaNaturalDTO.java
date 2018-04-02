package co.com.runt.runistac.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonaNaturalDTO {

    public PersonaNaturalDTO() {
        //constructor base
    }

    public PersonaNaturalDTO(Long id) {
        this.id = id;
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @generated
     */
    private String primerNombre;

    /**
     * @generated
     */
    private String primerApellido;

    /**
     * @generated
     */
    private String segundoNombre;

    /**
     * @generated
     */
    private String segundoApellido;

    /**
     * @generated
     */
    private PersonaDTO persona;

    /**
     * @generated
     */
    public String getPrimerNombre() {
        return this.primerNombre;
    }

    /**
     * @generated
     */
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    /**
     * @generated
     */
    public String getPrimerApellido() {
        return this.primerApellido;
    }

    /**
     * @generated
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * @generated
     */
    public String getSegundoNombre() {
        return this.segundoNombre;
    }

    /**
     * @generated
     */
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    /**
     * @generated
     */
    public String getSegundoApellido() {
        return this.segundoApellido;
    }

    /**
     * @generated
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * @generated
     */
    public PersonaDTO getPersona() {
        return this.persona;
    }

    /**
     * @generated
     */
    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

}
