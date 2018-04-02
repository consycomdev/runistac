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
public class ParametroSistemaDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    public List<ParametroSistema> obtenerTodos() {
        return em.createNamedQuery("ParametroSistema.obtenerTodos").getResultList();
    }

    /**
     * @generated
     */
    public ParametroSistema obtener(String id) {
        return em.find(ParametroSistema.class, id);
    }

    /**
     * @generated
     */
    public ParametroSistema guardar(ParametroSistema entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(String id) {
        em.remove(em.find(ParametroSistema.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(ParametroSistema entidad) {
        em.merge(entidad);
    }

}
