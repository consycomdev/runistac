package co.com.runt.runistac.persistencia.entity;

import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Table(name = "RU_EntidadFinanciera")//, schema="${schema}")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "EntidadFinanciera.obtenerTodos", query = "select e from EntidadFinanciera e"),
    @NamedQuery(name = "EntidadFinanciera.obtenerPorDocumento", query = "select e from EntidadFinanciera e where e.nit=:nit"),
    @NamedQuery(name = "EntidadFinanciera.obtenerPorTipo", query = "select e from EntidadFinanciera e where e.tipoEntidadFinanciera=:tipoEntidad")
})
public class EntidadFinanciera extends RegistroAuditoria{

    @Id
    @Column(name = "ENTIDAD_id")
    @GeneratedValue(generator = "EntidadFinancieraGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "EntidadFinancieraGen", sequenceName = "EntidadFinanciera_SEQ", allocationSize = 1)
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
    @Column(name = "ENTIDAD_nombre")
    private String nombre;
    
    @Column(name = "ENTIDAD_nit")
    private String nit;
    
    @Column(name = "ENTIDAD_correo")
    private String correo;
    
    @Column(name = "ENTIDAD_garantiaBancaria")
    private Boolean garantiaBancaria;
    
    @Column(name = "ENTIDAD_solicitudCCM")
    private Boolean solicitudCCM;

    @Column(name="entidad_tipo")
    @Enumerated(EnumType.STRING)
    private TipoEntidadFinanciera tipoEntidadFinanciera;
    
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Boolean getSolicitudCCM() {
        return solicitudCCM;
    }

    public void setSolicitudCCM(Boolean solicitudCCM) {
        this.solicitudCCM = solicitudCCM;
    }

    public Boolean getGarantiaBancaria() {
        return garantiaBancaria;
    }

    public void setGarantiaBancaria(Boolean garantiaBancaria) {
        this.garantiaBancaria = garantiaBancaria;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public TipoEntidadFinanciera getTipoEntidadFinanciera() {
        return tipoEntidadFinanciera;
    }

    public void setTipoEntidadFinanciera(TipoEntidadFinanciera tipoEntidadFinanciera) {
        this.tipoEntidadFinanciera = tipoEntidadFinanciera;
    }
    
}
