package co.com.runt.runistac.servicio;

import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.logica.*;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
  *  @author daperador
  *  @generated
  */
@Stateless
@Path("/TipoGarantiaBancaria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoGarantiaBancariaService {
	@EJB
    private TipoGarantiaBancariaLogica logica;

	
	/**
	* retorna una lista con los TipoGarantiaBancaria que se encuentran en la base de datos
	* @return retorna una lista de TipoGarantiaBancariaDTO
	* @generated
	*/
	@GET
	public List<TipoGarantiaBancariaDTO> obtenerTodosTipoGarantiaBancarias(){
		return logica.obtenerTodos();
	}
	
	/**
	* @param id identificador del elemento TipoGarantiaBancaria
	* @return TipoGarantiaBancaria del id dado
	* @generated
	*/
	@GET
	@Path("/{id}")
	public TipoGarantiaBancariaDTO obtenerTipoGarantiaBancaria(@PathParam("id") Long id){
		return logica.obtener(id);
	}
	
	
	/**
	 * almacena la informacion de TipoGarantiaBancaria
	 * @param dto TipoGarantiaBancaria a guardar
	 * @return TipoGarantiaBancaria con los cambios realizados por el proceso de guardar
	 * @generated
	 */
	@POST
	public TipoGarantiaBancariaDTO guardarTipoGarantiaBancaria(TipoGarantiaBancariaDTO dto){
	    if(dto.getId()!=null){
	        logica.actualizar(dto);
	        return dto;
	    }else{
	        return logica.guardar(dto);
	    }
	}
	
	
	/**
	 * elimina el registro TipoGarantiaBancaria con el identificador dado
	 * @param id identificador del TipoGarantiaBancaria
	 * @generated 
	 */
	@DELETE
	@Path("/{id}")
	public void borrarTipoGarantiaBancaria(@PathParam("id") Long id){
		logica.borrar(id);
	}
	
	
}
