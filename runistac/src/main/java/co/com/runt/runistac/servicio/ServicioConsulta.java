/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.runt.runistac.servicio;

import co.com.runt.runistac.dto.DatoDTO;
import java.util.ArrayList;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

/**
 *
 * @author Usuario
 */
@Path("consulta")
@Stateless
public class ServicioConsulta {

    @PersistenceContext
    private EntityManager em;

    @POST
    @Path("{tabla}")
    public Map<String, Map<String, DatoDTO>> obtenerDatos(@PathParam("tabla") String tabla, Map<String, List<String>> filtros) {
        String select = "";
        List<String> posicionFiltro = new ArrayList<>();
        for (String filtro : filtros.keySet()) {
            select += filtro + ",";
            posicionFiltro.add(filtro);
        }
        select = select+"1";

        Map<String, Map<String, DatoDTO>> respuesta = new HashMap<>();

        String where = " 1=1 ";

        List<String> y = new ArrayList<>();
        for (String attr : filtros.keySet()) {
            if (!filtros.get(attr).isEmpty() && !filtros.get(attr).contains("Seleccionar Todos")) {
                String o = "";
                for (String valor : filtros.get(attr)) {
                    o += "'" + valor + "',";
                }
                y.add(attr + " in (" + o.substring(0, o.length() - 1) + ") ");
            }
            respuesta.put(attr, new HashMap<String, DatoDTO>());
        }
        if (!y.isEmpty()) {
            for(String y1:y){
                where += "  and " + y1 + " ";
            }
        }

        String sql = "select " + select + " from " + tabla + " where " + where;

        List<Object[]> cursor = em.createNativeQuery(sql).getResultList();

        for (Object[] value : cursor) {
            for (String attr : filtros.keySet()) {
                Map<String, DatoDTO> mapa = respuesta.get(attr);
                Integer cantidad = 0;
                if (mapa.get(obtenerValor(value[posicionFiltro.indexOf(attr)])) != null) {
                    //ya existe el dato
                    cantidad = mapa.get(obtenerValor(value[posicionFiltro.indexOf(attr)])).getCantidad();
                }
                mapa.put(obtenerValor(value[posicionFiltro.indexOf(attr)]), new DatoDTO((String) obtenerValor(value[posicionFiltro.indexOf(attr)]), cantidad + 1));
            }
        }

        for (String key : respuesta.keySet()) {
            Integer id = 1;
            for (String attr : respuesta.get(key).keySet()) {
                respuesta.get(key).get(attr).setId(id);
                id++;
            }
        }
        return respuesta;
    }

    @GET
    @Path("/{tabla}/{columna}")
    public List<String> obtenerValores(@PathParam("tabla") String tabla, @PathParam("columna") String columna) {
        List<Object> distinct = em.createNativeQuery("select distinct(" + columna + ") from " + tabla+" order by 1").getResultList();

        List<String> lista = new ArrayList<>();
        lista.add("Seleccionar Todos");
        for (Object o : distinct) {
            lista.add(obtenerValor(o));
        }
        return lista;
    }

    private String obtenerValor(Object o) {
        if (o == null) {
            return "Sin Informaci\u00f3n";
        }
        return o.toString();
    }

}
