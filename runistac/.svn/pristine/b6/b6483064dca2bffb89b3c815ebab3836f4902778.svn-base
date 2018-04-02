package co.com.runt.runistac.persistencia.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Table(name = "RU_AuditoriaEntidad")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "Auditoria.obtenerTodos", query = "select e from Auditoria e")
    ,
        @NamedQuery(name = "Auditoria.buscar", query = "select e from Auditoria e where (UPPER(e.evento) LIKE :evento or :evento is null)")
})
public class Auditoria {

    @Id
    @Column(name = "Auditoria_id")
    @GeneratedValue(generator = "AuditoriaGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "AuditoriaGen", sequenceName = "Auditoria_SEQ", allocationSize = 1)
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
    @Column(name = "Auditoria_tabla")
    private String tabla;

    
    @Column(name = "Auditoria_idtabla")
    private Long idTabla;

    /**
     * @generated 1-1-false
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "auditoria_fecha")
    private Date fecha;

    /**
     * @generated 1-1-false
     */
    @Column(name = "auditoria_usuario")
    private String usuario;

    /**
     * @generated 1-1-false
     */
    @Column(name = "auditoria_evento")
    private String evento;

    /**
     * @generated 1-1-false
     */
    @Lob
    @Column(name = "auditoria_informacion", columnDefinition = "CLOB")
    private String informacion;

    /**
     * @generated
     */
    public String getTabla() {
        return this.tabla;
    }

    /**
     * @generated
     */
    public void setTabla(String tabla) {
        this.tabla = tabla;
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

    /**
     * @generated
     */
    public String getUsuario() {
        return this.usuario;
    }

    /**
     * @generated
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @generated
     */
    public String getEvento() {
        return this.evento;
    }

    /**
     * @generated
     */
    public void setEvento(String evento) {
        this.evento = evento;
    }

    /**
     * @generated
     */
    public String getInformacion() {
        return this.informacion;
    }

    /**
     * @generated
     */
    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public Long getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(Long idTabla) {
        this.idTabla = idTabla;
    }

}
