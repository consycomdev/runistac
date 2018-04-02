/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.runt.runistac.persistencia.entity;

import co.com.runt.common.excepciones.ApplicationException;
import co.com.runt.common.seguridad.InfoUsuario;
import co.com.runt.runistac.persistencia.AuditoriaDAO;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author daniel
 */
@MappedSuperclass
public class RegistroAuditoria {

    @Column(name = "FECHA_CREACION", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "USUARIO_CREACION", updatable = false)
    private String usuarioCreacion;

    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @Column(name = "USUARIO_MODIFICACION")
    private String usuarioModificacion;

    @Column(name = "IP_CREACION", updatable = false)
    private String ipCreacion;

    @Column(name = "IP_MODIFICACION")
    private String ipModificacion;

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public String getIpCreacion() {
        return ipCreacion;
    }

    public void setIpCreacion(String ipCreacion) {
        this.ipCreacion = ipCreacion;
    }

    public String getIpModificacion() {
        return ipModificacion;
    }

    public void setIpModificacion(String ipModificacion) {
        this.ipModificacion = ipModificacion;
    }

    /*@PrePersist
    public void prePersist() {
        try {
            InitialContext ic = new InitialContext();
            InfoUsuario infoUsuario = (InfoUsuario) ic.lookup("java:module/InfoUsuario");

            this.setFechaCreacion(new Date());
            String usuario=infoUsuario.getUsuario();
            if(usuario==null){
                this.setUsuarioCreacion("TareaProgramada");
            }else{
                this.setUsuarioCreacion(usuario);
            }
            this.setIpCreacion(infoUsuario.getIp());
        } catch (Exception ex) {
            Logger.getLogger(RegistroAuditoria.class.getName()).log(Level.SEVERE, null, ex);
            throw new ApplicationException("Ocurrio un error al guardar la entidad(Auditoria)");
        }
    }*/

    @PostPersist
    public void postPersist() {
        registrarAuditoria("CREACION", true);
    }

    /*@PreUpdate
    public void preUpdate() {
        try {
            InitialContext ic = new InitialContext();
            InfoUsuario infoUsuario = (InfoUsuario) ic.lookup("java:module/InfoUsuario");

            this.setFechaModificacion(new Date());
            String usuario=infoUsuario.getUsuario();
            if(usuario==null){
                this.setUsuarioModificacion("TareaProgramada");
            }else{
                this.setUsuarioModificacion(usuario);
            }
            this.setIpModificacion(infoUsuario.getIP());
        } catch (Exception ex) {
            Logger.getLogger(RegistroAuditoria.class.getName()).log(Level.SEVERE, null, ex);
            throw new ApplicationException("Ocurrio un error al guardar la entidad(Auditoria)");
        }
    }*/

    @PostUpdate
    public void postUpdate() {
        registrarAuditoria("ACTUALIZACION", true);
    }

    @PostLoad
    public void postLoad() {
        //registrarAuditoria("CONSULTA", false);
    }

    private void registrarAuditoria(String evento, boolean incluirInformacion) {
        try {
            InitialContext ic = new InitialContext();
            AuditoriaDAO auditoriaDAO = (AuditoriaDAO) ic.lookup("java:module/AuditoriaDAO");

            //guardar en auditoria
            //obtener la entidad
            Object entidad = this;

            Method mId = entidad.getClass().getMethod("getId");
            Long valorId = (Long) mId.invoke(entidad);

            //consular atributos
            Auditoria auditoria = new Auditoria();

            auditoria.setTabla(entidad.getClass().getSimpleName());
            try {
                Method mRelId = entidad.getClass().getMethod("UsuarioModificacion");
                Object usuario = mRelId.invoke(entidad);
                auditoria.setUsuario((String)usuario);
            } catch (Throwable e) {
                auditoria.setUsuario("TareaProgramada");
            }
            auditoria.setFecha(new Date());
            auditoria.setIdTabla(valorId);

            auditoria.setEvento(evento);
            if (incluirInformacion) {
                String informacion = obtenerInformacion(entidad);
                auditoria.setInformacion(informacion.substring(0, informacion.length() - 1) + "}");
            }
            //almacenarla
            auditoriaDAO.guardar(auditoria);
        } catch (Exception ex) {
            Logger.getLogger(RegistroAuditoria.class.getName()).log(Level.SEVERE, null, ex);
            throw new ApplicationException("Ocurrio un error al guardar la entidad(Auditoria)");
        }
    }

    private String obtenerInformacion(Object entidad) throws Exception {
        StringBuilder sb = new StringBuilder();
        Method[] metodos = entidad.getClass().getDeclaredMethods();
        sb.append("{");
        for (Method m : metodos) {
            if (m.getName().startsWith("get")) {
                Object valor = m.invoke(entidad);
                String info = null;
                if (valor != null) {
                    if (!valor.getClass().isEnum() && valor.getClass().getName().contains("entity")) {
                        Method mRelId = valor.getClass().getMethod("getId");
                        Object id = mRelId.invoke(valor);
                        info = String.valueOf(id);
                    } else {
                        info = String.valueOf(valor);
                    }
                }
                if (info != null) {
                    String field = m.getName();
                    field = field.substring(3);
                    field = field.substring(0, 1).toLowerCase() + field.substring(1);
                    sb.append("\"").append(field).append("\":\"").append(info.replaceAll("\"", "&quot;")).append("\",\n");
                }
            }
        }
        return sb.toString();
    }

}
