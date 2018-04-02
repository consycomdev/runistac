package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;
import co.com.runt.runistac.servicio.*;

/**
 *
 * @author daniel
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.com.runt.runistac.servicio.AnexoServicio.class);
        resources.add(co.com.runt.runistac.servicio.AseguradoraServicio.class);
        resources.add(co.com.runt.runistac.servicio.AsignacionCCMServicio.class);
        resources.add(co.com.runt.runistac.servicio.AutomotorServicio.class);
        resources.add(co.com.runt.runistac.servicio.CausalesRechazoServicio.class);
        resources.add(co.com.runt.runistac.servicio.CertificadoCancelacionMatriculaServicio.class);
        resources.add(co.com.runt.runistac.servicio.ConsecutivoSolicitudServicio.class);
        resources.add(co.com.runt.runistac.servicio.EmpresaServicio.class);
        resources.add(co.com.runt.runistac.servicio.EntidadFinancieraServicio.class);
        resources.add(co.com.runt.runistac.servicio.PagosMTServicio.class);
        resources.add(co.com.runt.runistac.servicio.ParametroSistemaServicio.class);
        resources.add(co.com.runt.runistac.servicio.ParametroTextoServicio.class);
        resources.add(co.com.runt.runistac.servicio.PersonaNaturalServicio.class);
        resources.add(co.com.runt.runistac.servicio.PersonaServicio.class);
        resources.add(co.com.runt.runistac.servicio.PolizaCaucionServicio.class);
        resources.add(co.com.runt.runistac.servicio.PostulacionServicio.class);
        resources.add(co.com.runt.runistac.servicio.RegistroEstadosServicio.class);
        resources.add(co.com.runt.runistac.servicio.RegistroVINServicio.class);
        resources.add(co.com.runt.runistac.servicio.ServicioConsulta.class);
        resources.add(co.com.runt.runistac.servicio.TareasServicio.class);
        resources.add(co.com.runt.runistac.servicio.TipoAnexoServicio.class);
        resources.add(co.com.runt.runistac.servicio.TipoDocumentoServicio.class);
        resources.add(co.com.runt.runistac.servicio.TipoGarantiaBancariaService.class);
        resources.add(co.com.runt.runistac.servicio.TipoTransportadorServicio.class);
        resources.add(co.com.runt.runistac.servicio.ValorPolizaServicio.class);
        resources.add(org.netbeans.rest.application.config.GsonMessageBodyHandler.class);
    }

}
