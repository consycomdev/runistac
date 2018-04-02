package co.com.runt.runistac.dto;

import co.com.runt.runistac.persistencia.entity.EstadoCCM;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * @generated
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CertificadoCancelacionMatriculaDTO {

    public CertificadoCancelacionMatriculaDTO() {
        //constructor base
    }

    public CertificadoCancelacionMatriculaDTO(Long id) {
        this.id = id;
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long numeroCertificado;
    
    /**
     * @generated
     */
    private String fecha;

    /**
     * @generated
     */
    private String motivo;

    /**
     * @generated
     */
    private EstadoCCM estado;

    /**
     * @generated
     */
    private AutomotorDTO automotor;

    /**
     * @generated
     */
    public String getFecha() {
        return this.fecha;
    }

    /**
     * @generated
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @generated
     */
    public String getMotivo() {
        return this.motivo;
    }

    /**
     * @generated
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @generated
     */
    public EstadoCCM getEstado() {
        return this.estado;
    }

    /**
     * @generated
     */
    public void setEstado(EstadoCCM estado) {
        this.estado = estado;
    }

    /**
     * @generated
     */
    public AutomotorDTO getAutomotor() {
        return this.automotor;
    }

    /**
     * @generated
     */
    public void setAutomotor(AutomotorDTO automotor) {
        this.automotor = automotor;
    }

    public Long getNumeroCertificado() {
        return numeroCertificado;
    }

    public void setNumeroCertificado(Long numeroCertificado) {
        this.numeroCertificado = numeroCertificado;
    }

}
