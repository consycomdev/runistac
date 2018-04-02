package co.com.runt.runistac.persistencia;

import co.com.runt.runistac.dto.TipoCausalRechazo;
import co.com.runt.runistac.persistencia.entity.*;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import javax.ejb.Stateless;

/**
 * @generated
 */
@Stateless
public class CausalesRechazoDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    public List<CausalesRechazo> obtenerTodos() {
        return em.createNamedQuery("CausalesRechazo.obtenerTodos").getResultList();
    }

    /**
     * @generated
     */
    public CausalesRechazo obtener(Long id) {
        return em.find(CausalesRechazo.class, id);
    }

    /**
     * @generated
     */
    public CausalesRechazo guardar(CausalesRechazo entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(CausalesRechazo.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(CausalesRechazo entidad) {
        em.merge(entidad);
    }

    public List<CausalesRechazo> obtenerPorTipo(TipoCausalRechazo tipoCausalRechazo){
        return em.createNamedQuery("CausalesRechazo.obtenerPorTipo")
                .setParameter("tipo", tipoCausalRechazo)
                .getResultList();
    }
    
}
