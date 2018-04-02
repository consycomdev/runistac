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
public class ConsecutivoSolicitudDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    public List<ConsecutivoSolicitud> obtenerTodos() {
        return em.createNamedQuery("ConsecutivoSolicitud.obtenerTodos").getResultList();
    }

    /**
     * @generated
     */
    public ConsecutivoSolicitud obtener(String id) {
        return em.find(ConsecutivoSolicitud.class, id);
    }

    /**
     * @generated
     */
    public ConsecutivoSolicitud guardar(ConsecutivoSolicitud entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(String id) {
        em.remove(em.find(ConsecutivoSolicitud.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(ConsecutivoSolicitud entidad) {
        em.merge(entidad);
    }

}
