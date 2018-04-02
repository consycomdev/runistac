package co.com.runt.runistac.persistencia.entity;

import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Cacheable(false)
@Table(name = "RU_Aseguradora")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "Aseguradora.obtenerTodos", query = "select e from Aseguradora e order by e.nombre")
})
public class Aseguradora extends RegistroAuditoria{

    @Id
    @Column(name = "Aseguradora_id")
    @GeneratedValue(generator = "AseguradoraGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "AseguradoraGen", sequenceName = "Aseguradora_SEQ", allocationSize = 1)
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
    @Column(name = "aseguradora_nombre")
    private String nombre;

    /**
     * @generated 1-1-false
     */
    @Column(name = "aseguradora_correo")
    private String correo;

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
    public String getCorreo() {
        return this.correo;
    }

    /**
     * @generated
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
