package co.com.runt.runistac.persistencia;

import co.com.runt.runistac.persistencia.entity.*;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * @generated
 */
@Stateless
public class RegistroTareaDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    public List<RegistroTarea> obtenerTodos() {
        return em.createNamedQuery("RegistroTarea.obtenerTodos").getResultList();
    }

    /**
     * @generated
     */
    public RegistroTarea obtener(Long id) {
        return em.find(RegistroTarea.class, id);
    }

    /**
     * @generated
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RegistroTarea guardar(RegistroTarea entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(RegistroTarea.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(RegistroTarea entidad) {
        em.merge(entidad);
    }

}
