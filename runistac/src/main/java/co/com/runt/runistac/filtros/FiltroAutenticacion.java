/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.runt.runistac.filtros;

import co.com.runt.autenticacionrunt.RespuestaAutenticacionDTO;
import co.com.runt.clienteautenticacion.ClienteAutenticacion;
import co.com.runt.common.excepciones.ApplicationException;
import co.com.runt.common.seguridad.InfoUsuario;
import co.com.runt.runistac.logica.ParametroSistemaLogica;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daniel
 */
@WebFilter(filterName = "FiltroAutenticacion", urlPatterns = {"*.html", "/partials/*", "/js/*", "/webresources/*"})
public class FiltroAutenticacion implements Filter {

    @EJB
    private ParametroSistemaLogica parametroLogica;
    
    private static final Long TIEMPO_VIGENCIA = 24 * 60 * 60 * 1000L;

    private static final String OBS_COOKIE = "ObSSOCookie";
    private static final String PARAMETRO_URI_AUTENTICACION = "URI_AUTENTICACION";

    private static final String PRUEBAS = "MODO_PRUEBAS";

    private static final String ROLES="ROLES_PERMITIDOS";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //inicio del filtro
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (validarFiltro((HttpServletRequest) request)) {
            chain.doFilter(request, response);
            return;
        }

        if (parametroLogica.obtener(PRUEBAS).getValor().equals("S")) {
            ((HttpServletRequest) request).getSession().setAttribute("usuario", "C-79615114");
            ((HttpServletRequest) request).getSession().setAttribute("token_autenticacion", "aaa");
            ((HttpServletRequest) request).getSession().setAttribute("tiempo_autenticacion", System.currentTimeMillis());
            ((HttpServletRequest) request).getSession().setAttribute("ip", getIp((HttpServletRequest) request));
            chain.doFilter(request, response);
            return;
        }
        //validar portal ciudadano
        if (parametroLogica.obtener("IP_PORTAL_CIUDADANO").getValor().contains(request.getRemoteAddr())) {
            if (((HttpServletRequest) request).getHeader("usuario") != null) {
                ((HttpServletRequest) request).getSession().setAttribute("usuario", ((HttpServletRequest) request).getHeader("usuario"));
                ((HttpServletRequest) request).getSession().setAttribute("ip", ((HttpServletRequest) request).getHeader("ip"));
                ((HttpServletRequest) request).getSession().setAttribute("token_autenticacion", "aaa");
                ((HttpServletRequest) request).getSession().setAttribute("tiempo_autenticacion", System.currentTimeMillis());
                chain.doFilter(request, response);
                return;
            }
        }

        try {
            String cookie = obtenerCookie(((HttpServletRequest) request).getCookies());
            boolean cache = validarCache((HttpServletRequest) request, cookie);
            
            if (cache) {
                chain.doFilter(request, response);
                return;
            }
            String baseURI = parametroLogica.obtener(PARAMETRO_URI_AUTENTICACION).getValor();

            ClienteAutenticacion clienteAutenticacion = new ClienteAutenticacion(baseURI);
            RespuestaAutenticacionDTO respuesta = clienteAutenticacion.obtenerLoginCookie(cookie);

            Logger.getLogger(FiltroAutenticacion.class.getName()).log(Level.FINE, "login: {0}", respuesta);
            Logger.getLogger(FiltroAutenticacion.class.getName()).log(Level.FINE, "login: {0}", respuesta.getLogin());

            ((HttpServletRequest) request).getSession().setAttribute("usuario", respuesta.getLogin());
            ((HttpServletRequest) request).getSession().setAttribute("ip", getIp((HttpServletRequest) request));
            ((HttpServletRequest) request).getSession().setAttribute("token_autenticacion", cookie);
            ((HttpServletRequest) request).getSession().setAttribute("tiempo_autenticacion", System.currentTimeMillis());
                
            
            //obtener el rol
            boolean permitido=false;
            for(String rol:parametroLogica.obtener(ROLES).getValor().split(",")){
                if(clienteAutenticacion.validarGrupo(respuesta.getLogin(), rol)){
                    permitido=true;
                    break;
                }
            }
            if (permitido) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect("./error.html");
            }
        } catch (Exception e) {
            Logger.getLogger(FiltroAutenticacion.class.getName()).log(Level.SEVERE, "Error de validacion de informacion de usuario", e);
            ((HttpServletResponse) response).sendRedirect("/");
        }
    }

    /**
     * obtiene la cookie de SSO
     *
     * @param cookies
     * @return valor de la cookie
     */
    public String obtenerCookie(Cookie[] cookies) {
        String cookie = null;
        for (Cookie c : cookies) {
            if (c.getName().compareTo(OBS_COOKIE) == 0) {
                cookie = c.getValue();
            }
        }

        if (cookie == null) {
            throw new ApplicationException("No esta autenticado");
        }
        return cookie;
    }

    /**
     * valida si el filtro debe ejecutarse o no
     *
     * @param request
     * @return
     */
    private boolean validarFiltro(HttpServletRequest request) {
        return request.getRequestURI().contains("login")
                || request.getRequestURI().contains("error")
                || request.getRequestURI().contains("js")
                || request.getRequestURI().contains("css");
    }

    private boolean validarCache(HttpServletRequest request, String cookie) {
        String tokenAutenticacion = (String) request.getSession().getAttribute("token_autenticacion");
        Long tiempoAutenticacion = (Long) request.getSession().getAttribute("tiempo_autenticacion");
        
        return tokenAutenticacion != null
                && tokenAutenticacion.compareTo(cookie) == 0
                && System.currentTimeMillis() < tiempoAutenticacion + TIEMPO_VIGENCIA;
    }

    @Override
    public void destroy() {
        //destruccion del filtro
    }

    private String getIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        return (remoteAddr == null ? "" : remoteAddr) + ";" + (ipAddress == null ? "" : ipAddress);
    }
}
