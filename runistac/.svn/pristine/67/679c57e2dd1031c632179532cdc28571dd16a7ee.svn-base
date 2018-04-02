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
public class TipoTransportadorLogica {
	@EJB
    private TipoTransportadorDAO persistencia;


	
	/**
	* @generated
	*/
	public List<TipoTransportadorDTO> obtenerTodos(){
		return convertirEntidad(persistencia.obtenerTodos());
	}
	
	/**
	* @generated
	*/
	public TipoTransportadorDTO obtener(Long id){
		return convertirEntidad(persistencia.obtener(id));
	}
	
	
	/**
	* @generated
	*/
	public TipoTransportadorDTO guardar(TipoTransportadorDTO dto){
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
	public void actualizar(TipoTransportadorDTO dto){
		persistencia.actualizar(convertirDTO(dto));
	}
	
	
	/**
	* @generated
	*/
	public TipoTransportador convertirDTO(TipoTransportadorDTO dto){
		if(dto==null)return null;
		TipoTransportador entidad=new TipoTransportador();
		entidad.setId(dto.getId());
			entidad.setNombre(dto.getNombre());
			entidad.setNumeroCCM(dto.getNumeroCCM());
		
		
		return entidad;
	}
	
	
	/**
	* @generated
	*/
	public List<TipoTransportador> convertirDTO(List<TipoTransportadorDTO> dtos){
		List<TipoTransportador> entidades=new ArrayList<TipoTransportador>();
		for(TipoTransportadorDTO dto:dtos){
			entidades.add(convertirDTO(dto));
		}
		return entidades;
	}
	
	
	/**
	* @generated
	*/
	public TipoTransportadorDTO convertirEntidad(TipoTransportador entidad){
		TipoTransportadorDTO dto=new TipoTransportadorDTO();
		dto.setId(entidad.getId());
			dto.setNombre(entidad.getNombre());
			dto.setNumeroCCM(entidad.getNumeroCCM());
		
		
		return dto;
	}
	
	
	/**
	* @generated
	*/
	public List<TipoTransportadorDTO> convertirEntidad(List<TipoTransportador> entidades){
		List<TipoTransportadorDTO> dtos=new ArrayList<TipoTransportadorDTO>();
		for(TipoTransportador entidad:entidades){
			dtos.add(convertirEntidad(entidad));
		}
		return dtos;
	}
	
	
}
