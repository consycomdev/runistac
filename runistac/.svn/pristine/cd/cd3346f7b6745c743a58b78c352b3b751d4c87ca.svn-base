package co.com.runt.runistac.persistencia.entity;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Cacheable(false)
@Table(name = "RU_Anexo")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "Anexo.obtenerTodos", query = "select e from Anexo e"),
    @NamedQuery(name = "Anexo.obtenerPorTipoAnexo", query = "select e from Anexo e where e.tipoAnexo.id=:tipoAnexo")
})
public class Anexo extends RegistroAuditoria{

    @Id
    @Column(name = "Anexo_id")
    @GeneratedValue(generator = "AnexoGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "AnexoGen", sequenceName = "Anexo_SEQ", allocationSize = 1)
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
    @Column(name = "anexo_ubicacion")
    private String ubicacion;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ANEXO_POSTULACION_ID")
    private Postulacion postulacion;
    
    /**
     * @generated 0-1-false
     */
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "anexo_tipoAnexo_id")
    private TipoAnexo tipoAnexo;

    /**
     * @generated
     */
    public String getUbicacion() {
        return this.ubicacion;
    }

    /**
     * @generated
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @generated
     */
    public TipoAnexo getTipoAnexo() {
        return this.tipoAnexo;
    }

    /**
     * @generated
     */
    public void setTipoAnexo(TipoAnexo tipoAnexo) {
        this.tipoAnexo = tipoAnexo;
    }

    public Postulacion getPostulacion() {
        return postulacion;
    }

    public void setPostulacion(Postulacion postulacion) {
        this.postulacion = postulacion;
    }
    
}
