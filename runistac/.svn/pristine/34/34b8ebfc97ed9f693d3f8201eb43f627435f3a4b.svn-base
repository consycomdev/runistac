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
  * org.eclipse.emf.ecore.impl.EAnnotationImpl@69017905 (source: genmymodel)
  *   * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@5fb06d1e (key: uuid, value: _aWqaYLjLEDSWntwNo5onzQ)
    * uuid-_aWqaYLjLEDSWntwNo5onzQ
  *  @generated
  */
@Stateless
@Path("/TipoTransportador")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoTransportadorServicio {
	@EJB
    private TipoTransportadorLogica logica;

	
	/**
	* retorna una lista con los TipoTransportador que se encuentran en la base de datos
	* @return retorna una lista de TipoTransportadorDTO
	* @generated
	*/
	@GET
	public List<TipoTransportadorDTO> obtenerTodosTipoTransportadors(){
		return logica.obtenerTodos();
	}
	
	/**
	* @param id identificador del elemento TipoTransportador
	* @return TipoTransportador del id dado
	* @generated
	*/
	@GET
	@Path("/{id}")
	public TipoTransportadorDTO obtenerTipoTransportador(@PathParam("id") Long id){
		return logica.obtener(id);
	}
	
	
	/**
	 * almacena la informacion de TipoTransportador
	 * @param dto TipoTransportador a guardar
	 * @return TipoTransportador con los cambios realizados por el proceso de guardar
	 * @generated
	 */
	@POST
	public TipoTransportadorDTO guardarTipoTransportador(TipoTransportadorDTO dto){
	    if(dto.getId()!=null){
	        logica.actualizar(dto);
	        return dto;
	    }else{
	        return logica.guardar(dto);
	    }
	}
	
	
	/**
	 * elimina el registro TipoTransportador con el identificador dado
	 * @param id identificador del TipoTransportador
	 * @generated 
	 */
	@DELETE
	@Path("/{id}")
	public void borrarTipoTransportador(@PathParam("id") Long id){
		logica.borrar(id);
	}
	
	
}
