package co.com.runt.runistac.persistencia.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Table(name = "RU_RegistroEstados")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "RegistroEstados.obtenerTodos", query = "select e from RegistroEstados e"),
    @NamedQuery(name = "RegistroEstados.obtenerPorPostulacion", query = "select e from RegistroEstados e where e.idPostulacion=:idPostulacion order by e.fecha")
})
public class RegistroEstados {

    @Id
    @Column(name = "Registro_id")
    @GeneratedValue(generator = "RegistroEstadosGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "RegistroEstadosGen", sequenceName = "RegistroEstados_SEQ", allocationSize = 1)
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
    @Column(name = "registro_idPostulacion")
    private Long idPostulacion;

    /**
     * @generated 1-1-false
     */
    @Column(name = "registro_estado")
    @Enumerated(EnumType.STRING)
    private EstadoPostulacion estado;

    /**
     * @generated 1-1-false
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registro_fecha")
    private Date fecha;

    /**
     * @generated
     */
    public Long getIdPostulacion() {
        return this.idPostulacion;
    }

    /**
     * @generated
     */
    public void setIdPostulacion(Long idPostulacion) {
        this.idPostulacion = idPostulacion;
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
    public Date getFecha() {
        return this.fecha;
    }

    /**
     * @generated
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
