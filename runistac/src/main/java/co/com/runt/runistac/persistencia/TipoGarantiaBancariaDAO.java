package co.com.runt.runistac.persistencia;

import co.com.runt.runistac.persistencia.entity.*;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import javax.ejb.Stateless;

/**
  *  @generated
  *  @author daperador
  */
@Stateless
public class TipoGarantiaBancariaDAO {
	@PersistenceContext
    private EntityManager em;

	
	/**
	* @generated
	*/
	public List<TipoGarantiaBancaria> obtenerTodos(){
		return em.createNamedQuery("TipoGarantiaBancaria.obtenerTodos").getResultList();
	}
	
	/**
	* @generated
	*/
	public TipoGarantiaBancaria obtener(Long id){
		return em.find(TipoGarantiaBancaria.class, id);
	}
	
	
	/**
	* @generated
	*/
	public TipoGarantiaBancaria guardar(TipoGarantiaBancaria entidad){
		em.persist(entidad);
		return entidad;
	}
	
	
	/**
	* @generated
	*/
	public void borrar(Long id){
		em.remove(em.find(TipoGarantiaBancaria.class, id));
	}
	
	
	/**
	* @generated
	*/
	public void actualizar(TipoGarantiaBancaria entidad){
		em.merge(entidad);
	}
	
	
}
