/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.runt.runistac.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class FechaUtils {
    
    public static String obtenerFechaTexto(Date fecha){
        Calendar c=Calendar.getInstance();
        int dia=c.get(Calendar.DAY_OF_MONTH);
        int mes=c.get(Calendar.MONTH);
        int anno=c.get(Calendar.YEAR);
        
        return ""+dia+" de "+obtenerMes(mes)+" de "+anno;
    }
    
    
    public static String obtenerMes(int mes){
        switch(mes){
            case 0: return "Enero";
            case 1: return "Febrero";
            case 2: return "Marzo";
            case 3: return "Abril";
            case 4: return "Mayo";
            case 5: return "Junio";
            case 6: return "Julio";
            case 7: return "Agosto";
            case 8: return "Septiembre";
            case 9: return "Octubre";
            case 10: return "Noviembre";
            case 11: return "Diciembre";
            default: return null;
        }
    }
    
    public static final SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    
    public static Date convertirFecha(String fecha){
        try {
            if(fecha==null){
                return null;
            }
            return sdf.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(FechaUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
