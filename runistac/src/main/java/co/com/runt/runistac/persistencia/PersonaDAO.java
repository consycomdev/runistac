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
public class PersonaDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    public List<Persona> obtenerTodos() {
        return em.createNamedQuery("Persona.obtenerTodos").getResultList();
    }

    /**
     * @generated
     */
    public Persona obtener(Long id) {
        return em.find(Persona.class, id);
    }
    
    public Persona obtenerTipoYNumeroDocumento(String tipoDocumento, String numeroDocumento) {
        List<Persona> resultado = em.createNamedQuery("Persona.obtenerPorTipoyNumero")
                .setParameter("tipoDocumento", tipoDocumento)
                .setParameter("numeroDocumento", numeroDocumento)
                .getResultList();
        
        return resultado.isEmpty()?null:resultado.get(0);
    }

    /**
     * @generated
     */
    public Persona guardar(Persona entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(Persona.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(Persona entidad) {
        em.merge(entidad);
    }
    
    public String obtenerCorreoUsuario(String tipoDocumento, String numeroDocumento){
        List<String> lista = em.createNativeQuery("select PERSONA_EMAIL from SC_PERSONA where persona_tipodoc=? and persona_numdoc=?")
                .setParameter(1, tipoDocumento)
                .setParameter(2, numeroDocumento)
                .getResultList();
        return lista.isEmpty()?null:lista.get(0);
    }

}
