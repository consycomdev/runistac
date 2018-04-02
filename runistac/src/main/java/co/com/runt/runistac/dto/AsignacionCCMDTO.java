package co.com.runt.runistac.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * @generated
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AsignacionCCMDTO {

    public AsignacionCCMDTO() {
        //constructor base
    }

    public AsignacionCCMDTO(Long id) {
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
    private String fecha;

    /**
     * @generated
     */
    /*private List<CertificadoCancelacionMatriculaDTO> ccm;
    
    public List<CertificadoCancelacionMatriculaDTO> getCcm(){
    	if(this.ccm==null){
    		ccm=new ArrayList<CertificadoCancelacionMatriculaDTO>();
    	}
    	return ccm;
    }
    
    public void setCcm(List<CertificadoCancelacionMatriculaDTO> ccm){
    	this.ccm=ccm;
    }
     */
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

}
