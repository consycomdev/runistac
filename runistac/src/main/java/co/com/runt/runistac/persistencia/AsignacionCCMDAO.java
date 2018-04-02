package co.com.runt.runistac.persistencia;

import co.com.runt.runistac.persistencia.entity.*;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import javax.ejb.Stateless;

/**
 * @generated
 */
@Stateless
public class AsignacionCCMDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    public List<AsignacionCCM> obtenerTodos() {
        return em.createNamedQuery("AsignacionCCM.obtenerTodos").getResultList();
    }

    /**
     * @generated
     */
    public AsignacionCCM obtener(Long id) {
        return em.find(AsignacionCCM.class, id);
    }

    /**
     * @generated
     */
    public AsignacionCCM guardar(AsignacionCCM entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(AsignacionCCM.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(AsignacionCCM entidad) {
        em.merge(entidad);
    }

}
