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
public class AnexoDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    public List<Anexo> obtenerTodos() {
        return em.createNamedQuery("Anexo.obtenerTodos").getResultList();
    }

    /**
     * @generated
     */
    public Anexo obtener(Long id) {
        return em.find(Anexo.class, id);
    }
    
    public List<Anexo> obtenerPorTipoAnexo(Long idTipoAnexo){
        return em.createNamedQuery("Anexo.obtenerPorTipoAnexo")
                .setParameter("tipoAnexo", idTipoAnexo)
                .getResultList();
    }

    /**
     * @generated
     */
    public Anexo guardar(Anexo entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(Anexo.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(Anexo entidad) {
        em.merge(entidad);
    }

}
