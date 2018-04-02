package co.com.runt.runistac.persistencia;

import co.com.runt.common.excepciones.ApplicationException;
import co.com.runt.runistac.persistencia.entity.*;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import javax.ejb.Stateless;

/**
 * @generated
 */
@Stateless
public class PersonaNaturalDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    public List<PersonaNatural> obtenerTodos() {
        return em.createNamedQuery("PersonaNatural.obtenerTodos").getResultList();
    }

    /**
     * @generated
     */
    public PersonaNatural obtener(Long id) {
        PersonaNatural pn=em.find(PersonaNatural.class, id);
        if(pn==null){
            pn=obtenerPersonaJuridica(id);
            if(pn==null){
                throw new ApplicationException("No se encontro la persona registrada en el RUNT");
            }
        }
        return pn;
    }
    
    public PersonaNatural obtenerPersonaJuridica(Long id) {
        List<Object[]> lista = em.createNativeQuery("select EMPRESA_RAZOSOCIA, EMPRESA_MAIL, EMPRESA_PERCONTEL from runtprod.ge_empresa\n" +
                "where EMPRESA_PERSONA_IDPERSONA=?")
                .setParameter(1, id).getResultList();
        if(!lista.isEmpty()){
            Object[] o=lista.get(0);
            PersonaNatural pn=new PersonaNatural();
            pn.setId(id);
            pn.setPrimerNombre((String)o[0]);
            pn.setSegundoNombre("");
            pn.setPrimerApellido("");
            pn.setSegundoApellido("");
            pn.setEmail((String)o[1]);
            pn.setCelular((String)o[2]);
            pn.setPersona(new Persona());
            pn.getPersona().setId(id);
            return pn;
        }
        return null;
    }

    /**
     * @generated
     */
    public PersonaNatural guardar(PersonaNatural entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(PersonaNatural.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(PersonaNatural entidad) {
        em.merge(entidad);
    }

    
}
