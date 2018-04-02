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
public class EntidadFinancieraDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    public List<EntidadFinanciera> obtenerTodos() {
        return em.createNamedQuery("EntidadFinanciera.obtenerTodos").getResultList();
    }
    
    public List<EntidadFinanciera> obtenerPorTipo(TipoEntidadFinanciera tipo) {
        return em.createNamedQuery("EntidadFinanciera.obtenerPorTipo")
                .setParameter("tipoEntidad", tipo).getResultList();
    }

    /**
     * @generated
     */
    public EntidadFinanciera obtener(Long id) {
        return em.find(EntidadFinanciera.class, id);
    }

    /**
     * @generated
     */
    public EntidadFinanciera guardar(EntidadFinanciera entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(EntidadFinanciera.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(EntidadFinanciera entidad) {
        em.merge(entidad);
    }

    public List<EntidadFinanciera> obtenerBancoPorNit(String nit) {
        return em.createNamedQuery("EntidadFinanciera.obtenerPorDocumento").setParameter("nit", nit).getResultList();
    }

}
