package co.com.runt.runistac.persistencia.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Table(name = "RU_PolizaCaucion")//, schema="${schema}")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "PolizaCaucion.obtenerTodos", query = "select e from PolizaCaucion e order by e.fechaExpedicion")
    ,
    @NamedQuery(name = "PolizaCaucion.obtenerPorSolicitud", query = "select e from PolizaCaucion e where e.postulacion.id=:postulacion")
})
public class PolizaCaucion extends RegistroAuditoria{

    @Id
    @Column(name = "POLIZACAUCION_id")
    @GeneratedValue(generator = "PolizaCaucionGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PolizaCaucionGen", sequenceName = "PolizaCaucion_SEQ", allocationSize = 1)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "PolizaCaucion_tipo")
    @Enumerated(EnumType.STRING)
    private TipoTituloValor tipo;

    @Column(name = "PolizaCaucion_tomador")
    private String tomador;

    @Column(name = "PolizaCaucion_asegurado")
    private String asegurado;

    @Column(name = "PolizaCaucion_numero")
    private String numero;

    @Column(name = "PolizaCaucion_numerofactura")
    private String numeroFactura;

    @Column(name = "PolizaCaucion_valorvehic")
    private Double valorVehiculo;

    @Column(name = "POLIZACAUCION_VALORASEGURADO")
    private Double valorAsegurado;

    /**
     * @generated 0-1-false
     */
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "PolizaCaucion_postulacion")
    private Postulacion postulacion;
    
    /**
     * @generated 1-1-false
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "PolizaCaucion_fechaExpedicion")
    private Date fechaExpedicion;

    /**
     * @generated 1-1-false
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "PolizaCaucion_fechaIniVigencia")
    private Date fechaInicioVigencia;

    /**
     * @generated 1-1-false
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "PolizaCaucion_fechaFinVigencia")
    private Date fechaFinVigencia;

    /**
     * @generated 0-1-false
     */
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "PolizaCaucion_aseguradora")
    private EntidadFinanciera entidadFinanciera;
    
    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "PolizaCaucion_tipoGarantia")
    private TipoGarantiaBancaria tipoGarantia;
    

    public TipoTituloValor getTipo() {
        return tipo;
    }

    public void setTipo(TipoTituloValor tipo) {
        this.tipo = tipo;
    }

    public String getTomador() {
        return tomador;
    }

    public void setTomador(String tomador) {
        this.tomador = tomador;
    }

    public String getAsegurado() {
        return asegurado;
    }

    public void setAsegurado(String asegurado) {
        this.asegurado = asegurado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Double getValorVehiculo() {
        return valorVehiculo;
    }

    public void setValorVehiculo(Double valorVehiculo) {
        this.valorVehiculo = valorVehiculo;
    }

    public Double getValorAsegurado() {
        return valorAsegurado;
    }

    public void setValorAsegurado(Double valorAsegurado) {
        this.valorAsegurado = valorAsegurado;
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

    /**
     * @generated
     */
    public Date getFechaExpedicion() {
        return this.fechaExpedicion;
    }

    /**
     * @generated
     */
    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    /**
     * @generated
     */
    public Date getFechaInicioVigencia() {
        return this.fechaInicioVigencia;
    }

    /**
     * @generated
     */
    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    /**
     * @generated
     */
    public Date getFechaFinVigencia() {
        return this.fechaFinVigencia;
    }

    /**
     * @generated
     */
    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public TipoGarantiaBancaria getTipoGarantia() {
        return tipoGarantia;
    }

    public void setTipoGarantia(TipoGarantiaBancaria tipoGarantia) {
        this.tipoGarantia = tipoGarantia;
    }

    public EntidadFinanciera getEntidadFinanciera() {
        return entidadFinanciera;
    }

    public void setEntidadFinanciera(EntidadFinanciera entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }
    
    
}
