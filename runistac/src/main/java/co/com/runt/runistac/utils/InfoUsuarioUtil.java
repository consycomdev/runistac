/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.runt.runistac.utils;

import co.com.runt.common.seguridad.InfoUsuario;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Usuario
 */
public class InfoUsuarioUtil {
    
    public static InfoUsuario obtenerUsuario(HttpServletRequest request){
        InfoUsuario infoUsuario=new InfoUsuario();
        infoUsuario.setUsuario((String)request.getSession().getAttribute("usuario"));
        infoUsuario.setIp((String)request.getSession().getAttribute("ip"));
        return infoUsuario;
    }
    
}
