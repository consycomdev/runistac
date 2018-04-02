package co.com.runt.runistac.persistencia.entity;

import co.com.runt.common.excepciones.ApplicationException;
import co.com.runt.runistac.dto.PersonaDTO;
import co.com.runt.runistac.persistencia.RegistroEstadosDAO;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Cacheable(false)
@Table(name = "RU_Postulacion")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "Postulacion.obtenerTodos", query = "select e from Postulacion e order by e.fecha asc"),
    @NamedQuery(name = "Postulacion.obtenerPorEstado", query = "select e from Postulacion e where e.estado=:estado order by e.fecha asc"),
    @NamedQuery(name = "Postulacion.obtenerPorPersona", query = "select e from Postulacion e where e.persona.tipoDocumento.id=:tipoDocumento and e.persona.numeroDocumento=:numeroDocumento order by e.fecha desc"),
    @NamedQuery(name = "Postulacion.obtenerPorPersonaEstado", query = "select e from Postulacion e where e.persona.tipoDocumento.id=:tipoDocumento and e.persona.numeroDocumento=:numeroDocumento and e.estado=:estado order by e.fecha desc"),
    @NamedQuery(name = "Postulacion.obtenerPorEstadoFecha", query = "select e from Postulacion e left join fetch e.locatarios l where ((e.persona.tipoDocumento.id=:tipoDocumento and e.persona.numeroDocumento=:numeroDocumento) or (l.tipoDocumento.id=:tipoDocumento and l.numeroDocumento=:numeroDocumento)) and e.estado<>:estado and e.fecha between :finicio and :ffin order by e.fecha desc"),
    @NamedQuery(name = "Postulacion.obtenerVINEstado", query = "select e from Postulacion e where e.vin=:vin and e.estado<>:estado"),
    @NamedQuery(name = "Postulacion.obtenerPorFiltro", query = "select e from Postulacion e left join e.tituloValor t where (e.estado=:estado or :estado is null) and (e.solicitud=:solicitud or :solicitud is null) and (e.persona.tipoDocumento.id=:tipoDocumento or :tipoDocumento is null) and (e.persona.numeroDocumento=:numeroDocumento or :numeroDocumento is null) and (e.fecha >= :fechaInicio or :fechaInicio is null) and (e.fecha <= :fechaFin or :fechaFin is null) and (t.tipo = :tipo or :tipo is null)")
})
public class Postulacion extends RegistroAuditoria {

    @Id
    @Column(name = "Postulacion_id")
    @GeneratedValue(generator = "PostulacionGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PostulacionGen", sequenceName = "Postulacion_SEQ", allocationSize = 1)
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
    @Column(name = "postulacion_fecha")
    private Date fecha;

    /**
     * @generated 1-1-false
     */
    @Column(name = "postulacion_numeroCCM")
    private Integer numeroCCM;

    /**
     * @generated 1-1-false
     */
    @Column(name = "postulacion_solicitud")
    private Long solicitud;

    /**
     * @generated 1-1-false
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "postulacion_estado")
    private EstadoPostulacion estado;

    /**
     * @generated 0-1-false
     */
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "postulacion_asignacion")
    private AsignacionCCM asignacionCCM;

    /**
     * @generated 0--1-true
     */
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE},fetch=javax.persistence.FetchType.LAZY, mappedBy = "postulacion")
    private List<Anexo> anexo;
    
    /**
     * @generated 0-1-false
     */
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "POSTULACION_PERSONA_IDPERSONA")
    private Persona persona;
    
    @Column(name = "postulacion_obsrechazo")
    private String observacionesRechazo;
    
    @Column(name = "postulacion_liquidacion")
    private String liquidacion;
    
    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "postulacion_causalrechazo")
    private CausalesRechazo causalesRechazo;
    
    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "postulacion_tipotransportador")
    private TipoTransportador tipoTransportador;
    
    @Column(name = "postulacion_ccmasignados")
    private Integer numeroCCMAsignados;
    
    @Column(name = "POSTULACION_FECHAPAGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    
    @Column(name = "POSTULACION_VIN")
    private String vin;
    
    @Column(name = "POSTULACION_LEASING")
    private Boolean leasing;
    
    @OneToMany(cascade = {}, fetch = FetchType.LAZY)
    @JoinTable(name = "RU_POSTLOCATARIOS",
            joinColumns = {@JoinColumn(name = "POSTLOCATARIO_POSTULACION")}, 
            inverseJoinColumns = {@JoinColumn(name="POSTLOCATARIO_LOCATARIO")} )
    private List<Persona> locatarios;
    
    @OneToOne(cascade = {}, fetch = FetchType.EAGER, mappedBy = "postulacion")
    private PolizaCaucion tituloValor;

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
    public Integer getNumeroCCM() {
        return this.numeroCCM;
    }

    /**
     * @generated
     */
    public void setNumeroCCM(Integer numeroCCM) {
        this.numeroCCM = numeroCCM;
    }

    /**
     * @generated
     */
    public Long getSolicitud() {
        return this.solicitud;
    }

    /**
     * @generated
     */
    public void setSolicitud(Long solicitud) {
        this.solicitud = solicitud;
    }

    /**
     * @generated
     */
    public EstadoPostulacion getEstado() {
        return this.estado;
    }

    /**
     * @generated
     */
    public void setEstado(EstadoPostulacion estado) {
        this.estado = estado;
    }

    /**
     * @generated
     */
    public Persona getPersona() {
        return this.persona;
    }

    /**
     * @generated
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * @generated
     */
    public AsignacionCCM getAsignacionCCM() {
        return this.asignacionCCM;
    }

    /**
     * @generated
     */
    public void setAsignacionCCM(AsignacionCCM asignacionCCM) {
        this.asignacionCCM = asignacionCCM;
    }

    public List<Anexo> getAnexo(){
        if(anexo==null){
                anexo=new ArrayList<Anexo>(); 
        }
        return this.anexo;
    }

    public void setAnexo(List<Anexo> anexo){
        this.anexo=anexo;
    }

    public CausalesRechazo getCausalesRechazo() {
        return causalesRechazo;
    }

    public void setCausalesRechazo(CausalesRechazo causalesRechazo) {
        this.causalesRechazo = causalesRechazo;
    }

    public String getObservacionesRechazo() {
        return observacionesRechazo;
    }

    public void setObservacionesRechazo(String observacionesRechazo) {
        this.observacionesRechazo = observacionesRechazo;
    }

    public String getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(String liquidacion) {
        this.liquidacion = liquidacion;
    }

    public Integer getNumeroCCMAsignados() {
        return numeroCCMAsignados;
    }

    public void setNumeroCCMAsignados(Integer numeroCCMAsignados) {
        this.numeroCCMAsignados = numeroCCMAsignados;
    }

    public TipoTransportador getTipoTransportador() {
        return tipoTransportador;
    }

    public void setTipoTransportador(TipoTransportador tipoTransportador) {
        this.tipoTransportador = tipoTransportador;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Boolean getLeasing() {
        return leasing;
    }

    public void setLeasing(Boolean leasing) {
        this.leasing = leasing;
    }

    public List<Persona> getLocatarios() {
        return locatarios;
    }

    public void setLocatarios(List<Persona> locatarios) {
        this.locatarios = locatarios;
    }
    
    @PostPersist
    @Override
    public void postPersist() {
        super.postPersist();
        //registrar estado
        registrarEstado();
    }
    
    @PostUpdate
    @Override
    public void postUpdate() {
        super.postUpdate();
        //registrar estado
        registrarEstado();
    }
    
    public void registrarEstado(){
        try {
            InitialContext ic = new InitialContext();
            RegistroEstadosDAO registroDAO = (RegistroEstadosDAO) ic.lookup("java:module/RegistroEstadosDAO");
            RegistroEstados registro=new RegistroEstados();
            registro.setIdPostulacion(this.id);
            registro.setFecha(new Date());
            registro.setEstado(this.estado);
            registro=registroDAO.guardar(registro);
            System.out.println("registro: "+registro.getId());
        } catch (Exception ex) {
            Logger.getLogger(Postulacion.class.getName()).log(Level.SEVERE, null, ex);
            throw new ApplicationException("Ocurrio un error al guardar la entidad(Auditoria)");
        }
    }

    public PolizaCaucion getTituloValor() {
        return tituloValor;
    }

    public void setTituloValor(PolizaCaucion tituloValor) {
        this.tituloValor = tituloValor;
    }
    
}
