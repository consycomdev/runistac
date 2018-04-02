package co.com.runt.runistac.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.ArrayList;

/**
* @generated
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistroVINDTO {

    public RegistroVINDTO(){
        //constructor base
    }
    
    public RegistroVINDTO(Long id){
        this.id=id;
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
    private String vin;
    
    
    /**
    * @generated
    */
    private AutomotorDTO automotor;
    
    /**
    * @generated
    */
    private PostulacionDTO postulacion;
    
    /**
    * @generated
    */
    public String getVin() {
        return this.vin;
    }
    
    /**
    * @generated
    */
    public void setVin(String vin) {
        this.vin = vin;
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
	/**
	* @generated
	*/
	public PostulacionDTO getPostulacion() {
	    return this.postulacion;
	}
	
	/**
	* @generated
	*/
	public void setPostulacion(PostulacionDTO postulacion) {
	    this.postulacion = postulacion;
	}
	
}
