package co.com.runt.runistac.persistencia.entity;

import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Cacheable(false)
@Table(name = "RU_ParametroSistema")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "ParametroSistema.obtenerTodos", query = "select e from ParametroSistema e")
})
public class ParametroSistema {

    @Id
    @Column(name = "parametro_clave")
    private String clave;

    /**
     * @generated 1-1-false
     */
    @Column(name = "parametro_valor")
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
