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
public class RegistroEstadosDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    public List<RegistroEstados> obtenerTodos() {
        return em.createNamedQuery("RegistroEstados.obtenerTodos").getResultList();
    }

    /**
     * @generated
     */
    public RegistroEstados obtener(Long id) {
        return em.find(RegistroEstados.class, id);
    }

    /**
     * @generated
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RegistroEstados guardar(RegistroEstados entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(RegistroEstados.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(RegistroEstados entidad) {
        em.merge(entidad);
    }

    public List<RegistroEstados> obtenerPorPostulacion(Long id) {
        return em.createNamedQuery("RegistroEstados.obtenerPorPostulacion")
                .setParameter("idPostulacion", id)
                .getResultList();
    }

}
