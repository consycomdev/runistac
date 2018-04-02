package co.com.runt.runistac.persistencia.entity;

import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Table(name = "TR_PERSONA", schema="RUNTPROD")
@NamedQueries({
    @NamedQuery(name = "Persona.obtenerTodos", query = "select e from Persona e"),
    @NamedQuery(name = "Persona.obtenerPorTipoyNumero", query = "select e from Persona e where e.numeroDocumento=:numeroDocumento and e.tipoDocumento.id=:tipoDocumento")
})
@Cacheable(false)
public class Persona {

    @Id
    @Column(name = "PERSONA_IDPERSONA")
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
    @Column(name = "PERSONA_NRODOCUME")
    private String numeroDocumento;

    /**
     * @generated 1-1-false
     */
    @Column(name = "PERSONA_ESTAPERSO_NOMBRE")
    private String estado;

    /**
     * @generated 0-1-false
     */
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSONA_TIPOIDENT_IDTIPDOC")
    private TipoDocumento tipoDocumento;
    
    /**
     * @generated
     */
    public String getNumeroDocumento() {
        return this.numeroDocumento;
    }

    /**
     * @generated
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * @generated
     */
    public String getEstado() {
        return this.estado;
    }

    /**
     * @generated
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @generated
     */
    public TipoDocumento getTipoDocumento() {
        return this.tipoDocumento;
    }

    /**
     * @generated
     */
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

}
