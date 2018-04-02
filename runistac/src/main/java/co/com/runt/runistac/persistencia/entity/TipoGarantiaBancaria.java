package co.com.runt.runistac.persistencia.entity;

import javax.persistence.*;

/**
 * @generated @author daperador
 */
@Entity
@Table(name = "ru_TipoGarantiaBancaria")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "TipoGarantiaBancaria.obtenerTodos", query = "select e from TipoGarantiaBancaria e")
})
public class TipoGarantiaBancaria {

    @Id
    @Column(name = "TipoGarantiaBancaria_id")
    @GeneratedValue(generator = "TipoGarantiaBancariaGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "TipoGarantiaBancariaGen", sequenceName = "TipoGarantiaBancaria_SEQ", allocationSize = 1)
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
    @Column(name = "TipoGarantiaBancaria_nombre")
    private String nombre;

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

}
