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
public class RegistroVINDAO {
	@PersistenceContext
    private EntityManager em;

	
	/**
	* @generated
	*/
	public List<RegistroVIN> obtenerTodos(){
		return em.createNamedQuery("RegistroVIN.obtenerTodos").getResultList();
	}
	
	/**
	* @generated
	*/
	public RegistroVIN obtener(Long id){
		return em.find(RegistroVIN.class, id);
	}
	
	
	/**
	* @generated
	*/
	public RegistroVIN guardar(RegistroVIN entidad){
		em.persist(entidad);
		return entidad;
	}
        
        public RegistroVIN obtenerPorSolicitud(Long idPostulacion){
            List<RegistroVIN> registros=em.createNamedQuery("RegistroVIN.obtenerPorSolicitud").setParameter("idSolicitud", idPostulacion).getResultList();
            return (registros.isEmpty()?null:registros.get(0));
        }
	
	
	/**
	* @generated
	*/
	public void borrar(Long id){
		em.remove(em.find(RegistroVIN.class, id));
	}
	
	
	/**
	* @generated
	*/
	public void actualizar(RegistroVIN entidad){
		em.merge(entidad);
	}
	
	
}
