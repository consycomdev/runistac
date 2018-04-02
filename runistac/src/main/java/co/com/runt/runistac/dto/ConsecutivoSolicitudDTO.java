package co.com.runt.runistac.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsecutivoSolicitudDTO {

    public ConsecutivoSolicitudDTO() {
        //constructor base
    }

    /**
     * @generated
     */
    private String secuencia;

    /**
     * @generated
     */
    private Long valor;

    /**
     * @generated
     */
    public String getSecuencia() {
        return this.secuencia;
    }

    /**
     * @generated
     */
    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    /**
     * @generated
     */
    public Long getValor() {
        return this.valor;
    }

    /**
     * @generated
     */
    public void setValor(Long valor) {
        this.valor = valor;
    }

}
