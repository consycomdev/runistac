package co.com.runt.runistac.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutomotorDTO {

    public AutomotorDTO() {
        //constructor base
    }

    public AutomotorDTO(Long id) {
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
    private String placa;

    /**
     * @generated
     */
    public String getPlaca() {
        return this.placa;
    }

    /**
     * @generated
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

}
