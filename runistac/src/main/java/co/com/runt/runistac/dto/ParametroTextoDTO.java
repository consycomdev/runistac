package co.com.runt.runistac.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.ArrayList;

/**
* @generated
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParametroTextoDTO {

    public ParametroTextoDTO(){
        //constructor base
    }
    
    public ParametroTextoDTO(Long id){
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
    private String clave;
    
    /**
    * @generated
    */
    private String valor;
    
    
    /**
    * @generated
    */
    public String getClave() {
        return this.clave;
    }
    
    /**
    * @generated
    */
    public void setClave(String clave) {
        this.clave = clave;
    }
    /**
    * @generated
    */
    public String getValor() {
        return this.valor;
    }
    
    /**
    * @generated
    */
    public void setValor(String valor) {
        this.valor = valor;
    }
    
	
}
