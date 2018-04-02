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
  * org.eclipse.emf.ecore.impl.EAnnotationImpl@404c41b0 (source: genmymodel)
  *   * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@4e6caaca (key: uuid, value: _yk3-YL-8EDS3NZ5BWgUNeg)
    * uuid-_yk3-YL-8EDS3NZ5BWgUNeg
  *  @generated
  */
@Stateless
@Path("/RegistroVIN")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistroVINServicio {
	@EJB
    private RegistroVINLogica logica;

	
	/**
	* retorna una lista con los RegistroVIN que se encuentran en la base de datos
	* @return retorna una lista de RegistroVINDTO
	* @generated
	*/
	@GET
	public List<RegistroVINDTO> obtenerTodosRegistroVINs(){
		return logica.obtenerTodos();
	}
	
	/**
	* @param id identificador del elemento RegistroVIN
	* @return RegistroVIN del id dado
	* @generated
	*/
	@GET
	@Path("/{id}")
	public RegistroVINDTO obtenerRegistroVIN(@PathParam("id") Long id){
		return logica.obtener(id);
	}
	
	
	/**
	 * almacena la informacion de RegistroVIN
	 * @param dto RegistroVIN a guardar
	 * @return RegistroVIN con los cambios realizados por el proceso de guardar
	 * @generated
	 */
	@POST
	public RegistroVINDTO guardarRegistroVIN(RegistroVINDTO dto){
	    if(dto.getId()!=null){
	        logica.actualizar(dto);
	        return dto;
	    }else{
	        return logica.guardar(dto);
	    }
	}
	
	
	/**
	 * elimina el registro RegistroVIN con el identificador dado
	 * @param id identificador del RegistroVIN
	 * @generated 
	 */
	@DELETE
	@Path("/{id}")
	public void borrarRegistroVIN(@PathParam("id") Long id){
		logica.borrar(id);
	}
	
	
}
