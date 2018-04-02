package co.com.runt.runistac.persistencia.entity;

import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Table(name = "RU_TipoTransportador")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "TipoTransportador.obtenerTodos", query = "select e from TipoTransportador e")
})
public class TipoTransportador extends RegistroAuditoria {

    @Id
    @Column(name = "TipoTransportador_id")
    @GeneratedValue(generator = "TipoTransportadorGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "TipoTransportadorGen", sequenceName = "TipoTransportador_SEQ", allocationSize = 1)
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
    @Column(name = "TipoTransportador_nombre")
    private String nombre;

    /**
     * @generated 1-1-false
     */
    @Column(name = "TipoTransportador_numeroCCM")
    private Integer numeroCCM;

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

    /**
     * @generated
     */
    public Integer getNumeroCCM() {
        return this.numeroCCM;
    }

    /**
     * @generated
     */
    public void setNumeroCCM(Integer numeroCCM) {
        this.numeroCCM = numeroCCM;
    }

}
