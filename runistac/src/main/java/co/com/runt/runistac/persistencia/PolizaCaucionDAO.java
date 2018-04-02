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
public class PolizaCaucionDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    public List<PolizaCaucion> obtenerTodos() {
        return em.createNamedQuery("PolizaCaucion.obtenerTodos").getResultList();
    }

    public PolizaCaucion obtenerPorPostulacion(Long idPostulacion) {
        List<PolizaCaucion> lista = em.createNamedQuery("PolizaCaucion.obtenerPorSolicitud")
                .setParameter("postulacion", idPostulacion)
                .getResultList();
        return (lista.isEmpty() ? null : lista.get(0));
    }

    /**
     * @generated
     */
    public PolizaCaucion obtener(Long id) {
        return em.find(PolizaCaucion.class, id);
    }

    /**
     * @generated
     */
    public PolizaCaucion guardar(PolizaCaucion entidad) {
        if (entidad.getId() != null) {
            entidad = em.merge(entidad);
        } else {
            em.persist(entidad);
        }
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(PolizaCaucion.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(PolizaCaucion entidad) {
        em.merge(entidad);
    }

}
