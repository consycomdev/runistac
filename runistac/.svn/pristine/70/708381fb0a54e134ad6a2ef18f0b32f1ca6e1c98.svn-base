package co.com.runt.runistac.persistencia.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Table(name = "RU_RegistroTarea")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "RegistroTarea.obtenerTodos", query = "select e from RegistroTarea e")
})
public class RegistroTarea {

    @Id
    @Column(name = "RegistroTarea_id")
    @GeneratedValue(generator = "RegistroTareaGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "RegistroTareaGen", sequenceName = "RegistroTarea_SEQ", allocationSize = 1)
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
    @Column(name = "registrotarea_fecha")
    private Date fecha;

    /**
     * @generated 1-1-false
     */
    @Column(name = "registrotarea_cantRegistros")
    private Integer cantidadRegistros;

    @Column(name = "registrotarea_error")
    private String error;
    
    @Column(name = "registrotarea_tipotarea")
    @Enumerated(EnumType.STRING)
    private TipoTarea tipoTarea;
    
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
    public Integer getCantidadRegistros() {
        return this.cantidadRegistros;
    }

    /**
     * @generated
     */
    public void setCantidadRegistros(Integer cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public TipoTarea getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(TipoTarea tipoTarea) {
        this.tipoTarea = tipoTarea;
    }

    
    
}
