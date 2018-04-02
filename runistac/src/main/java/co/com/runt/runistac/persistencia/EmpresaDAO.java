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
public class EmpresaDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    public List<Empresa> obtenerTodos() {
        return em.createNamedQuery("Empresa.obtenerTodos").getResultList();
    }

    /**
     * @generated
     */
    public Empresa obtener(Long id) {
        return em.find(Empresa.class, id);
    }

    /**
     * @generated
     */
    public Empresa guardar(Empresa entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(Empresa.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(Empresa entidad) {
        em.merge(entidad);
    }

}
