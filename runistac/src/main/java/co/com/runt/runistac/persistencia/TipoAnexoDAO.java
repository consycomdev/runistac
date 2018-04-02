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
public class TipoAnexoDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    public List<TipoAnexo> obtenerTodos() {
        return em.createNamedQuery("TipoAnexo.obtenerTodos").getResultList();
    }
    
    /**
     * @generated
     */
    public List<TipoAnexo> obtenerAnexosTipo(TipoProceso tipoProceso) {
        return em.createNamedQuery("TipoAnexo.obtenerPorEstado")
                .setParameter("proceso", tipoProceso)
                .getResultList();
    }

    /**
     * @generated
     */
    public TipoAnexo obtener(Long id) {
        return em.find(TipoAnexo.class, id);
    }

    /**
     * @generated
     */
    public TipoAnexo guardar(TipoAnexo entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(TipoAnexo.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(TipoAnexo entidad) {
        em.merge(entidad);
    }

}
