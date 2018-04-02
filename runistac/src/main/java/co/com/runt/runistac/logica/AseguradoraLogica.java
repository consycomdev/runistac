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
public class AseguradoraLogica {
	@EJB
    private AseguradoraDAO persistencia;


	
	/**
	* @generated
	*/
	public List<AseguradoraDTO> obtenerTodos(){
		return convertirEntidad(persistencia.obtenerTodos());
	}
	
	/**
	* @generated
	*/
	public AseguradoraDTO obtener(Long id){
		return convertirEntidad(persistencia.obtener(id));
	}
	
	
	/**
	* @generated
	*/
	public AseguradoraDTO guardar(AseguradoraDTO dto){
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
	public void actualizar(AseguradoraDTO dto){
		persistencia.actualizar(convertirDTO(dto));
	}
	
	
	/**
	* @generated
	*/
	public Aseguradora convertirDTO(AseguradoraDTO dto){
		if(dto==null)return null;
		Aseguradora entidad=new Aseguradora();
		entidad.setId(dto.getId());
			entidad.setNombre(dto.getNombre());
			entidad.setCorreo(dto.getCorreo());
		
		
		return entidad;
	}
	
	
	/**
	* @generated
	*/
	public List<Aseguradora> convertirDTO(List<AseguradoraDTO> dtos){
		List<Aseguradora> entidades=new ArrayList<Aseguradora>();
		for(AseguradoraDTO dto:dtos){
			entidades.add(convertirDTO(dto));
		}
		return entidades;
	}
	
	
	/**
	* @generated
	*/
	public AseguradoraDTO convertirEntidad(Aseguradora entidad){
		AseguradoraDTO dto=new AseguradoraDTO();
		dto.setId(entidad.getId());
			dto.setNombre(entidad.getNombre());
			dto.setCorreo(entidad.getCorreo());
		
		
		return dto;
	}
	
	
	/**
	* @generated
	*/
	public List<AseguradoraDTO> convertirEntidad(List<Aseguradora> entidades){
		List<AseguradoraDTO> dtos=new ArrayList<AseguradoraDTO>();
		for(Aseguradora entidad:entidades){
			dtos.add(convertirEntidad(entidad));
		}
		return dtos;
	}
	
	
}
