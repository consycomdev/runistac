/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.runt.runistac.persistencia.entity;

import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * tabla de auditoria del sistema
 * @author daniel
 */
@Entity
@Table(name = "RU_FIRMA")
@Cacheable(false)
public class Firma {
    
    @Id
    @Column(name = "AUDITORIA_ID")
    @GeneratedValue(generator = "AuditoriaGen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "AuditoriaGen", sequenceName = "Auditoria_SEQ", allocationSize = 1)
    private Long id;
    
    @Column(name = "AUDITORIA_OPERACION")
    @Enumerated(EnumType.STRING)
    private OperacionesAuditoria operacion;
    
    @Column(name = "AUDITORIA_TABLA")
    private String tabla;
    
    @Column(name = "AUDITORIA_IDTABLA")
    private Long idTabla;
    
    @Column(name = "AUDITORIA_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @Column(name = "AUDITORIA_USUARIO")
    private String usuario;
    
    @Column(name = "AUDITORIA_FIRMA")
    private String firma;
    
    /**
     * getter del identificador de la tabla Auditoria
     * @return identificador de la auditoria
     */
    public Long getId() {
        return id;
    }

    /**
     * setter del identificador de la tabla auditoria
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * obtiene la operacion a realizada
     * @return operacion realizada
     */
    public OperacionesAuditoria getOperacion() {
        return operacion;
    }

    /**
     * setter de la operacion realizada
     * @param operacion 
     */
    public void setOperacion(OperacionesAuditoria operacion) {
        this.operacion = operacion;
    }

    /**
     * tabla en donde se realiza el cambio
     * @return tabla
     */
    public String getTabla() {
        return tabla;
    }

    /**
     * setter de la tabla donde se realiza el cambio
     * @param tabla 
     */
    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    /**
     * identificador del elemento que se esta modificando
     * @return id del elemento
     */
    public Long getIdTabla() {
        return idTabla;
    }

    /**
     * setter del identificador del elemento que se esta modificando
     * @param idTabla 
     */
    public void setIdTabla(Long idTabla) {
        this.idTabla = idTabla;
    }

    /**
     * fecha en la que se realiza el cambio
     * @return la fecha en que se realiza el cambio
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * setter de la fecha e nla que se realiza el cambio
     * @param fecha 
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    /**
     * usuario que esta realizando el cambio
     * @return el usuario que realio el cambio
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * setter del usuario que esta realizando el cambio
     * @param usuario 
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * firma digital en formato pkcs7 
     * @return la firma digital
     */
    public String getFirma() {
        return firma;
    }

    /**
     * setter de la firma digital en formato pkcs7
     * @param firma 
     */
    public void setFirma(String firma) {
        this.firma = firma;
    }
    
    
}
