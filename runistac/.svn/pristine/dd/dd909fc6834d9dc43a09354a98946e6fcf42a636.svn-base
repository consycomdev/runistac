/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.runt.runistac.servicio;

import co.com.runt.runistac.logica.TareasLogica;
import co.com.runt.runistac.persistencia.entity.RegistroTarea;
import co.com.runt.runistac.persistencia.entity.TipoTarea;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author danie
 */
@Path("tarea")
@Stateless
public class TareasServicio {
    
    @EJB
    private TareasLogica tareasLogica;
    
    @GET
    @Path("/generarCertificadosCumplimientoFechaLimite")
    public String generarCertificadosCumplimientoFechaLimite(){
        tareasLogica.generarCertificadosCumplimientoFechaLimite();
        return "OK";
    }
    
    @GET
    @Path("/generarCCM")
    public String generarCCM() {
        tareasLogica.generarCCM();
        return "OK";
    }

    @GET
    @Path("/validarPago")
    public String validarPago() {
        tareasLogica.validarPago();
        return "OK";
    }

    @GET
    @Path("/validarPagosNoRealizados")
    public String validarPagosNoRealizados() {
        tareasLogica.validarPagosNoRealizados();
        return "OK";
    }

    @GET
    @Path("/rechazarPolizasNoCargadas")
    public String rechazarPolizasNoCargadas() {
        tareasLogica.rechazarPolizasNoCargadas();
        return "OK";
    }

    @GET
    @Path("/validarInformacionVehiculo")
    public String validarInformacionVehiculo() {
        tareasLogica.validarInformacionVehiculo();
        return "OK";
    }
    
    @GET
    @Path("/liberarCCM")
    public String liberarCCM(){
        tareasLogica.liberarCCM();
        return "OK";
    }
    
    @GET
    @Path("/regenerarGraficos")
    public String regenerarGraficos(){
        tareasLogica.regenerarGraficosCCM();
        tareasLogica.regenerarGraficosPostulacion();
        return "ok";
    }
    
}
