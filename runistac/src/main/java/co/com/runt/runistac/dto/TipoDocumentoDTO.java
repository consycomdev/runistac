package co.com.runt.runistac.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @generated
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoDocumentoDTO {

    public TipoDocumentoDTO() {
        //constructor base
    }

    public TipoDocumentoDTO(String id) {
        this.id = id;
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @generated
     */
    private String abreviatura;

    /**
     * @generated
     */
    private String nombre;

    /**
     * @generated
     */
    public String getAbreviatura() {
        return this.abreviatura;
    }

    /**
     * @generated
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    /**
     * @generated
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * @generated
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
