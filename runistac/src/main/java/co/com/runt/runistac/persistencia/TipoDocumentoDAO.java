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
public class TipoDocumentoDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    public List<TipoDocumento> obtenerTodos() {
        return em.createNamedQuery("TipoDocumento.obtenerTodos").getResultList();
    }

    /**
     * @generated
     */
    public TipoDocumento obtener(Long id) {
        return em.find(TipoDocumento.class, id);
    }

    /**
     * @generated
     */
    public TipoDocumento guardar(TipoDocumento entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(TipoDocumento.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(TipoDocumento entidad) {
        em.merge(entidad);
    }

}
