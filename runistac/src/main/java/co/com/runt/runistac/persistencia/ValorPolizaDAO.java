package co.com.runt.runistac.persistencia;

import co.com.runt.runistac.persistencia.entity.ValorPoliza;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import javax.ejb.Stateless;

/**
  *  @generated
  */
@Stateless
public class ValorPolizaDAO {
	@PersistenceContext
    private EntityManager em;

	
	/**
	* @generated
	*/
	public List<ValorPoliza> obtenerTodos(){
		return em.createNamedQuery("ValorPoliza.obtenerTodos").getResultList();
	}
	
	/**
	* @generated
	*/
	public ValorPoliza obtener(Long id){
		return em.find(ValorPoliza.class, id);
	}
	
	
	/**
	* @generated
	*/
	public ValorPoliza guardar(ValorPoliza entidad){
		em.persist(entidad);
		return entidad;
	}
	
	
	/**
	* @generated
	*/
	public void borrar(Long id){
		em.remove(em.find(ValorPoliza.class, id));
	}
	
	
	/**
	* @generated
	*/
	public void actualizar(ValorPoliza entidad){
		em.merge(entidad);
	}
	
	
}
