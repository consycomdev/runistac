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
public class AseguradoraDAO {
	@PersistenceContext
    private EntityManager em;

	
	/**
	* @generated
	*/
	public List<Aseguradora> obtenerTodos(){
		return em.createNamedQuery("Aseguradora.obtenerTodos").getResultList();
	}
	
	/**
	* @generated
	*/
	public Aseguradora obtener(Long id){
		return em.find(Aseguradora.class, id);
	}
	
	
	/**
	* @generated
	*/
	public Aseguradora guardar(Aseguradora entidad){
		em.persist(entidad);
		return entidad;
	}
	
	
	/**
	* @generated
	*/
	public void borrar(Long id){
		em.remove(em.find(Aseguradora.class, id));
	}
	
	
	/**
	* @generated
	*/
	public void actualizar(Aseguradora entidad){
		em.merge(entidad);
	}
	
	
}
