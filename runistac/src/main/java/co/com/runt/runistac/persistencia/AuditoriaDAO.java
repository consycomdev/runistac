package co.com.runt.runistac.persistencia;

import co.com.runt.runistac.persistencia.entity.Auditoria;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
  *  @generated
  */
@Stateless
public class AuditoriaDAO {
	@PersistenceContext
    private EntityManager em;

	
	/**
	* @generated
	*/
	public List<Auditoria> obtenerTodos(){
		return em.createNamedQuery("Auditoria.obtenerTodos").getResultList();
	}
	
	/**
	* @generated
	*/
	public Auditoria obtener(Long id){
		return em.find(Auditoria.class, id);
	}
	
	
	/**
	* @generated
	*/
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
        public Auditoria guardar(Auditoria entidad){
		em.persist(entidad);
		return entidad;
	}
	
	
	/**
	* @generated
	*/
	public void borrar(Long id){
		em.remove(em.find(Auditoria.class, id));
	}
	
	
	/**
	* @generated
	*/
	public void actualizar(Auditoria entidad){
		em.merge(entidad);
	}

    public List<Auditoria> buscar(Auditoria auditoria) {
        return em.createNamedQuery("Auditoria.buscar")
                .setParameter("evento", auditoria.getEvento().toUpperCase()+"%")
                .getResultList();
    }
	
	
}
