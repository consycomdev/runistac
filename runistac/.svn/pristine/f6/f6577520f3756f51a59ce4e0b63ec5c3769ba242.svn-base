package co.com.runt.runistac.persistencia.entity;

import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Table(name = "RU_ValorPoliza")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "ValorPoliza.obtenerTodos", query = "select e from ValorPoliza e")
})
public class ValorPoliza {

    @Id
    @Column(name = "ValorPoliza_id")
    @GeneratedValue(generator = "ValorPolizaGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ValorPolizaGen", sequenceName = "ValorPoliza_SEQ", allocationSize = 1)
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
    @Column(name = "ValorPoliza_valor")
    private Double valor;

    /**
     * @generated 1-1-false
     */
    @Column(name = "ValorPoliza_estado")
    private Boolean estado;

    /**
     * @generated
     */
    public Double getValor() {
        return this.valor;
    }

    /**
     * @generated
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * @generated
     */
    public Boolean getEstado() {
        return this.estado;
    }

    /**
     * @generated
     */
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
