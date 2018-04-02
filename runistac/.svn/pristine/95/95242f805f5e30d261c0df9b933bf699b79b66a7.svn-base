package co.com.runt.runistac.persistencia.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Table(name = "RU_PagosCCM")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "PagosMT.obtenerTodos", query = "select e from PagosMT e"),
    @NamedQuery(name = "PagosMT.obtenerPorPostulacion", query = "select e from PagosMT e where e.postulacion.id=:idPostulacion")
})
public class PagosMT extends RegistroAuditoria{

    @Id
    @Column(name = "pago_id")
    @GeneratedValue(generator = "PagosMTGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PagosMTGen", sequenceName = "PagosMT_SEQ", allocationSize = 1)
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
    @Temporal(TemporalType.DATE)
    @Column(name = "pago_fechaPago")
    private Date fechaPago;

    /**
     * @generated 1-1-false
     */
    @Column(name = "pago_numeroAprobacion")
    private String numeroAprobacion;

    /**
     * @generated 1-1-false
     */
    @Column(name = "pago_valorPagado")
    private Long valorPagado;

    /**
     * @generated 0-1-false
     */
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name="pago_postulacion")
    private Postulacion postulacion;

    /**
     * @generated 0-1-false
     */
    @Column(name="pago_banco_id")
    private Long banco;

    /**
     * @generated
     */
    public Date getFechaPago() {
        return this.fechaPago;
    }

    /**
     * @generated
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * @generated
     */
    public String getNumeroAprobacion() {
        return this.numeroAprobacion;
    }

    /**
     * @generated
     */
    public void setNumeroAprobacion(String numeroAprobacion) {
        this.numeroAprobacion = numeroAprobacion;
    }

    /**
     * @generated
     */
    public Long getValorPagado() {
        return this.valorPagado;
    }

    /**
     * @generated
     */
    public void setValorPagado(Long valorPagado) {
        this.valorPagado = valorPagado;
    }

    /**
     * @generated
     */
    public Long getBanco() {
        return this.banco;
    }

    /**
     * @generated
     */
    public void setBanco(Long banco) {
        this.banco = banco;
    }

    /**
     * @generated
     */
    public Postulacion getPostulacion() {
        return this.postulacion;
    }

    /**
     * @generated
     */
    public void setPostulacion(Postulacion postulacion) {
        this.postulacion = postulacion;
    }

}
