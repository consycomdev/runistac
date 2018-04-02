package co.com.runt.runistac.persistencia;

import co.com.runt.runistac.persistencia.entity.*;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import javax.ejb.Stateless;

/**
  *  @generated
  */
@Stateless
public class ParametroTextoDAO {
	@PersistenceContext
    private EntityManager em;

	
	/**
	* @generated
	*/
	public List<ParametroTexto> obtenerTodos(){
		return em.createNamedQuery("ParametroTexto.obtenerTodos").getResultList();
	}
	
	/**
	* @generated
	*/
	public ParametroTexto obtener(String clave){
		return em.find(ParametroTexto.class, clave);
	}
	
	
	/**
	* @generated
	*/
	public ParametroTexto guardar(ParametroTexto entidad){
		em.persist(entidad);
		return entidad;
	}
	
	
	/**
	* @generated
	*/
	public void borrar(String clave){
		em.remove(em.find(ParametroTexto.class, clave));
	}
	
	
	/**
	* @generated
	*/
	public void actualizar(ParametroTexto entidad){
		em.merge(entidad);
	}
	
	
}
