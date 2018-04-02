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
  *  @author daperador
  */
@Stateless
public class TipoGarantiaBancariaLogica {
	@EJB
    private TipoGarantiaBancariaDAO persistencia;


	
	/**
	* retorna una lista con los TipoGarantiaBancaria que se encuentran en la base de datos
	* @return retorna una lista de TipoGarantiaBancariaDTO
	* @generated
	*/
	public List<TipoGarantiaBancariaDTO> obtenerTodos(){
		return convertirEntidad(persistencia.obtenerTodos());
	}
	
	/**
	* @param id identificador del elemento TipoGarantiaBancaria
	* @return TipoGarantiaBancaria del id dado
	* @generated
	*/
	public TipoGarantiaBancariaDTO obtener(Long id){
		return convertirEntidad(persistencia.obtener(id));
	}
	
	
	/**
	 * almacena la informacion de TipoGarantiaBancaria
	 * @param dto TipoGarantiaBancaria a guardar
	 * @return TipoGarantiaBancaria con los cambios realizados por el proceso de guardar
	 * @generated
	 */
	public TipoGarantiaBancariaDTO guardar(TipoGarantiaBancariaDTO dto){
		return convertirEntidad(persistencia.guardar(convertirDTO(dto)));
	}
	
	
	/**
	 * Elimina el registro TipoGarantiaBancaria con el identificador dado
	 * @param id identificador del TipoGarantiaBancaria
	 * @generated 
	 */
	public void borrar(Long id){
		persistencia.borrar(id);
	}
	
	
	/**
	 * actualiza la informacion de TipoGarantiaBancaria
	 * @param dto TipoGarantiaBancaria a guardar
	 * @return TipoGarantiaBancaria con los cambios realizados por el proceso de guardar
	 * @generated
	 */
	public void actualizar(TipoGarantiaBancariaDTO dto){
		persistencia.actualizar(convertirDTO(dto));
	}
	
	
	/**
	* @generated
	*/
	public TipoGarantiaBancaria convertirDTO(TipoGarantiaBancariaDTO dto){
		if(dto==null)return null;
		TipoGarantiaBancaria entidad=new TipoGarantiaBancaria();
		entidad.setId(dto.getId());
			entidad.setNombre(dto.getNombre());
		
		
		return entidad;
	}
	
	
	/**
	* @generated
	*/
	public List<TipoGarantiaBancaria> convertirDTO(List<TipoGarantiaBancariaDTO> dtos){
		List<TipoGarantiaBancaria> entidades=new ArrayList<TipoGarantiaBancaria>();
		for(TipoGarantiaBancariaDTO dto:dtos){
			entidades.add(convertirDTO(dto));
		}
		return entidades;
	}
	
	
	/**
	* @generated
	*/
	public TipoGarantiaBancariaDTO convertirEntidad(TipoGarantiaBancaria entidad){
		TipoGarantiaBancariaDTO dto=new TipoGarantiaBancariaDTO();
		dto.setId(entidad.getId());
			dto.setNombre(entidad.getNombre());
		
		
		return dto;
	}
	
	
	/**
	* @generated
	*/
	public List<TipoGarantiaBancariaDTO> convertirEntidad(List<TipoGarantiaBancaria> entidades){
		List<TipoGarantiaBancariaDTO> dtos=new ArrayList<TipoGarantiaBancariaDTO>();
		for(TipoGarantiaBancaria entidad:entidades){
			dtos.add(convertirEntidad(entidad));
		}
		return dtos;
	}
	
	
}
