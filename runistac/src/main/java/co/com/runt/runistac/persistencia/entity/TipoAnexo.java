package co.com.runt.runistac.persistencia.entity;

import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Cacheable(false)
@Table(name = "RU_TipoAnexo")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "TipoAnexo.obtenerTodos", query = "select e from TipoAnexo e order by e.id"),
    @NamedQuery(name = "TipoAnexo.obtenerPorEstado", query = "select e from TipoAnexo e where e.tipoProceso=:proceso order by e.id")
})
public class TipoAnexo extends RegistroAuditoria {

    @Id
    @Column(name = "TipoAnexo_id")
    @GeneratedValue(generator = "TipoAnexoGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "TipoAnexoGen", sequenceName = "TipoAnexo_SEQ", allocationSize = 1)
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
    @Column(name = "tipoanexo_nombre")
    private String nombre;

    /**
     * @generated 1-1-false
     */
    @Column(name = "tipoanexo_descripcion", columnDefinition = "varchar2(4000)")
    private String descripcion;
    
    @Column(name = "tipoanexo_proceso", columnDefinition = "varchar2(40)")
    @Enumerated(EnumType.STRING)
    private TipoProceso tipoProceso;

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
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * @generated
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoProceso getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(TipoProceso tipoProceso) {
        this.tipoProceso = tipoProceso;
    }
    
}
