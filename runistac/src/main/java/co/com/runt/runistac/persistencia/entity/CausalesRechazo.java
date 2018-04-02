package co.com.runt.runistac.persistencia.entity;

import co.com.runt.runistac.dto.TipoCausalRechazo;
import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Table(name = "RU_CausalesRechazo")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "CausalesRechazo.obtenerTodos", query = "select e from CausalesRechazo e order by e.nombre"),
    @NamedQuery(name = "CausalesRechazo.obtenerPorTipo", query = "select e from CausalesRechazo e where e.tipoCausalRechazo=:tipo order by e.nombre")
})
public class CausalesRechazo extends RegistroAuditoria {

    public CausalesRechazo(){
        
    }
    
    public CausalesRechazo(Long id){
        this.id=id;
    }
    
    @Id
    @Column(name = "CausalesRechazo_id")
    @GeneratedValue(generator = "CausalesRechazoGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "CausalesRechazoGen", sequenceName = "CausalesRechazo_SEQ", allocationSize = 1)
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @generated 1-1-false
     */
    @Column(name = "CausalesRechazo_nombre")
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name="CAUSALESRECHAZO_TIPO")
    private TipoCausalRechazo tipoCausalRechazo;
    
    /**
     * @generated
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * @generated
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoCausalRechazo getTipoCausalRechazo() {
        return tipoCausalRechazo;
    }

    public void setTipoCausalRechazo(TipoCausalRechazo tipoCausalRechazo) {
        this.tipoCausalRechazo = tipoCausalRechazo;
    }

}
