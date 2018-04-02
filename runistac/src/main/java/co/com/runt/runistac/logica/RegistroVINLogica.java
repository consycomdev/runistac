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
  *  @generated
  */
@Stateless
public class RegistroVINLogica {
	@EJB
    private RegistroVINDAO persistencia;


	
	/**
	* @generated
	*/
	public List<RegistroVINDTO> obtenerTodos(){
		return convertirEntidad(persistencia.obtenerTodos());
	}
	
	/**
	* @generated
	*/
	public RegistroVINDTO obtener(Long id){
		return convertirEntidad(persistencia.obtener(id));
	}
	
	
	/**
	* @generated
	*/
	public RegistroVINDTO guardar(RegistroVINDTO dto){
		return convertirEntidad(persistencia.guardar(convertirDTO(dto)));
	}
	
	
	/**
	* @generated
	*/
	public void borrar(Long id){
		persistencia.borrar(id);
	}
	
	
	/**
	* @generated
	*/
	public void actualizar(RegistroVINDTO dto){
		persistencia.actualizar(convertirDTO(dto));
	}
	
	
	/**
	* @generated
	*/
	public RegistroVIN convertirDTO(RegistroVINDTO dto){
		if(dto==null)return null;
		RegistroVIN entidad=new RegistroVIN();
		entidad.setId(dto.getId());
			entidad.setVin(dto.getVin());
		
			if(dto.getAutomotor()!=null){
				entidad.setAutomotor(new Automotor());
				entidad.getAutomotor().setId(dto.getAutomotor().getId());
			}
			if(dto.getPostulacion()!=null){
				entidad.setPostulacion(new Postulacion());
				entidad.getPostulacion().setId(dto.getPostulacion().getId());
			}
		
		return entidad;
	}
	
	
	/**
	* @generated
	*/
	public List<RegistroVIN> convertirDTO(List<RegistroVINDTO> dtos){
		List<RegistroVIN> entidades=new ArrayList<RegistroVIN>();
		for(RegistroVINDTO dto:dtos){
			entidades.add(convertirDTO(dto));
		}
		return entidades;
	}
	
	
	/**
	* @generated
	*/
	public RegistroVINDTO convertirEntidad(RegistroVIN entidad){
		RegistroVINDTO dto=new RegistroVINDTO();
		dto.setId(entidad.getId());
			dto.setVin(entidad.getVin());
		
			if(entidad.getPostulacion()!=null){
				dto.setPostulacion(
					new PostulacionDTO(
						entidad.getPostulacion().getId()));
		    }
			if(entidad.getAutomotor()!=null){
				dto.setAutomotor(
					new AutomotorDTO(
						entidad.getAutomotor().getId()));
		    }
		
		return dto;
	}
	
	
	/**
	* @generated
	*/
	public List<RegistroVINDTO> convertirEntidad(List<RegistroVIN> entidades){
		List<RegistroVINDTO> dtos=new ArrayList<RegistroVINDTO>();
		for(RegistroVIN entidad:entidades){
			dtos.add(convertirEntidad(entidad));
		}
		return dtos;
	}
	
	
}
