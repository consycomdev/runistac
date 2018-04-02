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
public class TipoTransportadorDAO {
	@PersistenceContext
    private EntityManager em;

	
	/**
	* @generated
	*/
	public List<TipoTransportador> obtenerTodos(){
		return em.createNamedQuery("TipoTransportador.obtenerTodos").getResultList();
	}
	
	/**
	* @generated
	*/
	public TipoTransportador obtener(Long id){
		return em.find(TipoTransportador.class, id);
	}
	
	
	/**
	* @generated
	*/
	public TipoTransportador guardar(TipoTransportador entidad){
		em.persist(entidad);
		return entidad;
	}
	
	
	/**
	* @generated
	*/
	public void borrar(Long id){
		em.remove(em.find(TipoTransportador.class, id));
	}
	
	
	/**
	* @generated
	*/
	public void actualizar(TipoTransportador entidad){
		em.merge(entidad);
	}
	
	
}
