/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.runt.runistac.logica;

import co.com.runt.runistac.persistencia.CertificadoCancelacionMatriculaDAO;
import co.com.runt.runistac.persistencia.PostulacionDAO;
import co.com.runt.runistac.persistencia.RegistroTareaDAO;
import co.com.runt.runistac.persistencia.entity.RegistroTarea;
import co.com.runt.runistac.persistencia.entity.TipoTarea;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * @author danie
 */
@Stateless
public class TareasLogica {

    @EJB
    private CertificadoCancelacionMatriculaLogica certificadoCancelacionMatriculaLogica;

    @EJB
    private ParametroSistemaLogica parametroSistemaLogica;

    @EJB
    private PostulacionLogica postulacionLogica;

    @EJB
    private RegistroTareaDAO registroTareaDAO;
    
    @EJB
    private AutomotorLogica automotorLogica;
    
    @EJB
    private PostulacionDAO postulacionDAO;

    @EJB
    private CertificadoCancelacionMatriculaDAO certificadoCancelacionMatriculaDAO;
    
    
    /**
     * Valida el nombre del nodo de ejecucion de la tarea
     *
     * @return
     */
    public boolean nombreNodo() {
        //WEBLOGIC
        //NOMBRE_NODO_EJECUTOR_INTERFAZ
        String triggerHostmame = parametroSistemaLogica.obtener("NODO_EJECUCION_TAREA_PROGRAMADA").getValor();
        String hostName = System.getProperty("weblogic.Name");
        Logger.getLogger(TareasLogica.class.getName()).log(Level.INFO, "HostName {0}", hostName + " - " + triggerHostmame);
        return triggerHostmame.contains(hostName);
    }
    
    public void registroTarea(int conteo, TipoTarea tipoTarea){
        //guardar log
        RegistroTarea registroTarea = new RegistroTarea();
        registroTarea.setFecha(new Date());
        registroTarea.setCantidadRegistros(conteo);
        registroTarea.setTipoTarea(tipoTarea);
        registroTareaDAO.guardar(registroTarea);
    }
    
    public void registroTarea(int conteo, TipoTarea tipoTarea, Throwable error){
        //guardar log
        RegistroTarea registroTarea = new RegistroTarea();
        registroTarea.setFecha(new Date());
        registroTarea.setCantidadRegistros(conteo);
        registroTarea.setTipoTarea(tipoTarea);
        String stacktrace=ExceptionUtils.getStackTrace(error);
        registroTarea.setError(stacktrace.substring(0, (stacktrace.length()<4000?stacktrace.length():4000)));
        registroTareaDAO.guardar(registroTarea);
    }

    @Schedule(hour = "1", minute = "0", second = "0", persistent = false)
    public void generarCCM() {
        if (nombreNodo()) {
            try {
                int conteo=certificadoCancelacionMatriculaLogica.generarCCM();
                registroTarea(conteo, TipoTarea.GENERAR_CCM);
            } catch (Throwable e) {
                registroTarea(0, TipoTarea.GENERAR_CCM, e);
            }
        }
    }

    @Schedule(hour = "*", minute = "0/2", second = "0", persistent = false)
    public void validarPago() {
        //obtiene la lista de postulaciones en estado registrado y que ya fueron pagados
        if (nombreNodo()) {
            try {
                int conteo=postulacionLogica.validarPago();
                registroTarea(conteo, TipoTarea.VALIDAR_PAGO);
            } catch (Throwable e) { 
                registroTarea(0, TipoTarea.VALIDAR_PAGO, e);
            }
        }
    }

    @Schedule(hour = "0", minute = "0/5", second = "30", persistent = false)
    public void validarPagosNoRealizados() {
        //obtiene la lista de postulaciones en estado registrado y que ya fueron pagados
        if (nombreNodo()) {
            try {
                int conteo=postulacionLogica.validarPagosNoRealizados();
                registroTarea(conteo, TipoTarea.VALIDAR_PAGOS_NO_REALIZADOS);
            } catch (Throwable e) {
                registroTarea(0, TipoTarea.VALIDAR_PAGOS_NO_REALIZADOS, e);
            }
            
        }
    }

    @Schedule(hour = "1", minute = "5", second = "0", persistent = false)
    public void rechazarPolizasNoCargadas() {
        //obtiene la lista de postulaciones en estado registrado y que ya fueron pagados
        if (nombreNodo()) {
            try {
                int conteo=postulacionLogica.rechazarPolizasNoCargadas();
                registroTarea(conteo, TipoTarea.RECHAZAR_POLIZAS_NO_CARGADAS);
            } catch (Throwable e) {
                registroTarea(0, TipoTarea.RECHAZAR_POLIZAS_NO_CARGADAS, e);
            }
        }
    }

    @Schedule(hour = "1", minute = "10", second = "0", persistent = false)
    public void validarInformacionVehiculo() {
        if (nombreNodo()) {
            try {
                int conteo=postulacionLogica.validarInformacionVehiculo();
                registroTarea(conteo, TipoTarea.VALIDAR_INFORMACION_VEHICULO);
            } catch (Throwable e) {
                registroTarea(0, TipoTarea.VALIDAR_INFORMACION_VEHICULO, e);
            }
        }
    }
    
    @Schedule(hour = "1", minute = "30", second = "0", persistent = false)
    public void generarCertificadosCumplimientoFechaLimite() {
        if (nombreNodo()) {
            String valor=parametroSistemaLogica.obtener("FECHA_FIN_VIGENCIA").getValor();
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            if(sdf.format(new Date()).compareTo(valor)==0){
                try {
                    int conteo=postulacionLogica.validarInformacionVehiculo();
                    registroTarea(conteo, TipoTarea.POLIZAS_VENCIDAS);
                } catch (Throwable e) {
                    registroTarea(0, TipoTarea.POLIZAS_VENCIDAS, e);
                }
            }
        }
    }
    
    @Schedule(hour = "12", minute = "03", second = "0", persistent = false)
    public void generarAlertasSinCarroceria() {
        if (nombreNodo()) {
            String valor=parametroSistemaLogica.obtener("FECHA_FIN_VIGENCIA").getValor();
            String valor1=parametroSistemaLogica.obtener("DIAS_PARA_ALERTA_SIN_CARROCERIA").getValor();
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            Calendar c=Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DAY_OF_YEAR, Integer.valueOf(valor1));
            if(sdf.format(c.getTime()).compareTo(valor)==0){
                try {
                    int conteo=automotorLogica.validarSinCarroceria();
                    registroTarea(conteo, TipoTarea.ALERTA_SIN_CARROCERIA);
                } catch (Throwable e) {
                    registroTarea(0, TipoTarea.ALERTA_SIN_CARROCERIA, e);
                }
            }
        }
    }
    
    /**
     * Metodo recursivo que obtiene la excepcion original
     */
    private Throwable obtenerUltimaExcepcion(Throwable e) {
        if (e!=null && e.getCause() != null) {
            return obtenerUltimaExcepcion(e.getCause());
        } else {
            return e;
        }
    }
    
    
    @Schedule(hour = "04", minute = "00", second = "0", persistent = false)
    public void liberarCCM(){
        if (nombreNodo()) {
            try {
                int conteo=postulacionLogica.liberarCCM();
                registroTarea(conteo, TipoTarea.LIBERAR_CCM);
            } catch (Throwable e) {
                registroTarea(0, TipoTarea.LIBERAR_CCM, e);
            }
        }
    }
    
    public static void main(String[] args) {
        Calendar c=Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, 30);
        System.out.println(c);
    }
    
    @Asynchronous
    @Schedule(hour = "05", minute = "00", second = "0", persistent = false)
    public void regenerarGraficosPostulacion(){
        postulacionDAO.regenerarVistaGraficos();
    }
    
    @Asynchronous
    @Schedule(hour = "05", minute = "00", second = "0", persistent = false)
    public void regenerarGraficosCCM(){
        certificadoCancelacionMatriculaDAO.regenerarVistaGraficos();
    }

}
