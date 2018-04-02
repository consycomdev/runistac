package co.com.runt.runistac.persistencia.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Cacheable(false)
@Table(name = "RU_AsignacionCCM")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "AsignacionCCM.obtenerTodos", query = "select e from AsignacionCCM e")
})
public class AsignacionCCM extends RegistroAuditoria{

    @Id
    @Column(name = "AsignacionCCM_id")
    @GeneratedValue(generator = "AsignacionCCMGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "AsignacionCCMGen", sequenceName = "AsignacionCCM_SEQ", allocationSize = 1)
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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "asignacionccm_fecha")
    private Date fecha;

    /**
     * @generated 0--1-true
     */
    @OneToMany(cascade = {}, fetch = javax.persistence.FetchType.LAZY, mappedBy = "asignacionCCM")
    private List<CertificadoCancelacionMatricula> ccm;

    /**
     * @generated
     */
    public Date getFecha() {
        return this.fecha;
    }

    /**
     * @generated
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<CertificadoCancelacionMatricula> getCcm() {
        if (ccm != null) {
            ccm = new ArrayList<CertificadoCancelacionMatricula>();
        }
        return this.ccm;
    }

    public void setCcm(List<CertificadoCancelacionMatricula> ccm) {
        this.ccm = ccm;
    }
}
