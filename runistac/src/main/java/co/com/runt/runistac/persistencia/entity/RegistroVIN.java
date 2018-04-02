package co.com.runt.runistac.persistencia.entity;

import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Table(name = "RU_RegistroVIN")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "RegistroVIN.obtenerTodos", query = "select e from RegistroVIN e"),
    @NamedQuery(name = "RegistroVIN.obtenerPorSolicitud", query = "select e from RegistroVIN e where e.postulacion.id=:idSolicitud")
})
public class RegistroVIN extends RegistroAuditoria{

    @Id
    @Column(name = "RegistroVIN_id")
    @GeneratedValue(generator = "RegistroVINGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "RegistroVINGen", sequenceName = "RegistroVIN_SEQ", allocationSize = 1)
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
    @Column(name = "RegistroVIN_vin")
    private String vin;

    /**
     * @generated 0-1-false
     */
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "RegistroVIN_postulacion")
    private Postulacion postulacion;

    /**
     * @generated 0-1-false
     */
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "RegistroVIN_automotor")
    private Automotor automotor;

    /**
     * @generated
     */
    public String getVin() {
        return this.vin;
    }

    /**
     * @generated
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * @generated
     */
    public Automotor getAutomotor() {
        return this.automotor;
    }

    /**
     * @generated
     */
    public void setAutomotor(Automotor automotor) {
        this.automotor = automotor;
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
