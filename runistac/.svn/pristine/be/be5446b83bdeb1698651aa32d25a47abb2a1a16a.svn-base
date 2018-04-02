package co.com.runt.runistac.persistencia.entity;

import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Cacheable(false)
@Table(name = "ra_automotor", schema="RUNTPROD")
@NamedQueries({
    @NamedQuery(name = "Automotor.obtenerTodos", query = "select e from Automotor e")
})
public class Automotor {

    @Id
    @Column(name = "AUTOMOTOR_NROREGVEH")
    @GeneratedValue(generator = "AutomotorGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "AutomotorGen", sequenceName = "Automotor_SEQ", allocationSize = 1)
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
    @Column(name = "AUTOMOTOR_PLACA_NUMPLACA")
    private String placa;

    /**
     * @generated
     */
    public String getPlaca() {
        return this.placa;
    }

    /**
     * @generated
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

}
