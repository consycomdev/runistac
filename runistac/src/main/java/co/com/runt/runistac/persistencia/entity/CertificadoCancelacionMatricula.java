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
@Table(name = "RU_CCM")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "CertificadoCancelacionMatricula.obtenerTodos", query = "select e from CertificadoCancelacionMatricula e order by e.fecha asc"),
    @NamedQuery(name = "CertificadoCancelacionMatricula.obtenerTodosGenerado", query = "select e from CertificadoCancelacionMatricula e where e.estado=:estado order by e.fecha asc"),
    @NamedQuery(name = "CertificadoCancelacionMatricula.obtenerTodosFiltro", query = "select e from CertificadoCancelacionMatricula e where (e.estado=:estado or :estado is null) and (e.automotor.placa=:placa or :placa is null) and (e.fecha>=:fechaInicio or :fechaInicio is null) and (e.fecha<=:fechaFin or :fechaFin is null) and (e.motivo=:motivo or :motivo is null) and (e.numeroCertificado=:numeroCertificado or :numeroCertificado is null) order by e.fecha asc"),
    @NamedQuery(name = "CertificadoCancelacionMatricula.obtenerSinNumero", query = "select e from CertificadoCancelacionMatricula e where e.numeroCertificado is null order by e.fecha asc"),
    @NamedQuery(name = "CertificadoCancelacionMatricula.obtenerCantidadDisponibles", query = "select COUNT(e) from CertificadoCancelacionMatricula e where e.estado=:estado"),
    @NamedQuery(name = "CertificadoCancelacionMatricula.obtenerAsignados", query = "select e from CertificadoCancelacionMatricula e where e.asignacionCCM.id=:idAsignacion"),
    @NamedQuery(name = "CertificadoCancelacionMatricula.obtenerAsignadosyEstado", query = "select e from CertificadoCancelacionMatricula e where e.asignacionCCM.id=:idAsignacion and e.estado=:estado")
})
public class CertificadoCancelacionMatricula extends RegistroAuditoria{

    @Id
    @Column(name = "ccm_id")
    @GeneratedValue(generator = "CertificadoCancelacionMatriculaGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "CertificadoCancelacionMatriculaGen", sequenceName = "ccm_SEQ", allocationSize = 1)
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ccm_numeroCertificado")
    private Long numeroCertificado;
    
    /**
     * @generated 1-1-false
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ccm_fecha")
    private Date fecha;

    /**
     * @generated 1-1-false
     */
    @Column(name = "ccm_motivo")
    private String motivo;

    /**
     * @generated 1-1-false
     */
    @Column(name = "ccm_estado")
    @Enumerated(EnumType.STRING)
    private EstadoCCM estado;

    /**
     * @generated 0-1-false
     */
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ccm_automotor_id")
    private Automotor automotor;
    
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "CCM_ASIGNACIONCCM_ID")
    private AsignacionCCM asignacionCCM;

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

    /**
     * @generated
     */
    public String getMotivo() {
        return this.motivo;
    }

    /**
     * @generated
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @generated
     */
    public EstadoCCM getEstado() {
        return this.estado;
    }

    /**
     * @generated
     */
    public void setEstado(EstadoCCM estado) {
        this.estado = estado;
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

    public AsignacionCCM getAsignacionCCM() {
        return asignacionCCM;
    }

    public void setAsignacionCCM(AsignacionCCM asignacionCCM) {
        this.asignacionCCM = asignacionCCM;
    }

    public Long getNumeroCertificado() {
        return numeroCertificado;
    }

    public void setNumeroCertificado(Long numeroCertificado) {
        this.numeroCertificado = numeroCertificado;
    }
    
}
