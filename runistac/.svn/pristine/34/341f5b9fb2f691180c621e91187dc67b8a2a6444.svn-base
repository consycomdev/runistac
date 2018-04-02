package co.com.runt.runistac.persistencia.entity;

import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Table(name = "RU_ParametroTexto")//, schema="${schema}")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "ParametroTexto.obtenerTodos", query = "select e from ParametroTexto e")
})
public class ParametroTexto {

    @Id
    @Column(name = "parametro_clave")
    private String clave;

    /**
     * @generated 1-1-false
     */
    @Column(name = "parametro_valor")
    @Lob
    private String valor;

    /**
     * @generated
     */
    public String getClave() {
        return this.clave;
    }

    /**
     * @generated
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @generated
     */
    public String getValor() {
        return this.valor;
    }

    /**
     * @generated
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

}
