package co.com.runt.runistac.persistencia.entity;

import javax.persistence.*;

/**
 * @generated
 */
@Entity
@Cacheable(false)
@Table(name = "RU_ConsecutivoSolicitud")//, schema="${schema}")
@NamedQueries({
    @NamedQuery(name = "ConsecutivoSolicitud.obtenerTodos", query = "select e from ConsecutivoSolicitud e")
})
public class ConsecutivoSolicitud{

    @Id
    @Column(name = "consecutivo_secuencia")
    private String secuencia;

    /**
     * @generated 1-1-false
     */
    @Column(name = "consecutivo_valor")
    private Long valor;

    /**
     * @generated
     */
    public String getSecuencia() {
        return this.secuencia;
    }

    /**
     * @generated
     */
    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    /**
     * @generated
     */
    public Long getValor() {
        return this.valor;
    }

    /**
     * @generated
     */
    public void setValor(Long valor) {
        this.valor = valor;
    }

}
