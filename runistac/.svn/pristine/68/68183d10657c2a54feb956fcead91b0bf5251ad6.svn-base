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
public class PagosMTDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    public List<PagosMT> obtenerTodos() {
        return em.createNamedQuery("PagosMT.obtenerTodos").getResultList();
    }

    /**
     * @generated
     */
    public PagosMT obtener(Long id) {
        return em.find(PagosMT.class, id);
    }

    /**
     * @generated
     */
    public PagosMT guardar(PagosMT entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(PagosMT.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(PagosMT entidad) {
        em.merge(entidad);
    }
    
    /**
     * obtiene los pagos por postulacion
     * @param idPostulacion identificador de la postulacion
     * @return 
     */
    public List<PagosMT> obtenerPorPostulacion(Long idPostulacion){
        return em.createNamedQuery("PagosMT.obtenerPorPostulacion")
                .setParameter("idPostulacion", idPostulacion).getResultList();
    }

}
