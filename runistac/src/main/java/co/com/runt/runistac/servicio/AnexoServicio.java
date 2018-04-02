package co.com.runt.runistac.servicio;

import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.logica.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import static co.com.runt.runistac.utils.InfoUsuarioUtil.obtenerUsuario;
import javax.servlet.http.HttpServletRequest;


/**
 * org.eclipse.emf.ecore.impl.EAnnotationImpl@56c9eac2 (source: genmymodel) *
 * org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl@62539311 (key: uuid,
 * value: _IF6ugK5lEDSvsK-foXulxg) uuid-_IF6ugK5lEDSvsK-foXulxg
 *
 * @generated
 */
@Stateless
@Path("/Anexo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnexoServicio {

    @EJB
    private AnexoLogica logica;
    
    @Context
    private HttpServletResponse response;

    /**
     * retorna una lista con los Anexo que se encuentran en la base de datos
     *
     * @return retorna una lista de AnexoDTO
     * @generated
     */
    @GET
    public List<AnexoDTO> obtenerTodosAnexos() {
        return logica.obtenerTodos();
    }

    /**
     * @param id identificador del elemento Anexo
     * @return Anexo del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public AnexoDTO obtenerAnexo(@PathParam("id") Long id) {
        return logica.obtener(id);
    }

    /**
     * almacena la informacion de Anexo
     *
     * @param dto Anexo a guardar
     * @return Anexo con los cambios realizados por el proceso de guardar
     * @generated
     */
    @POST
    public AnexoDTO guardarAnexo(AnexoDTO dto) {
        if (dto.getId() != null) {
            logica.actualizar(dto);
            return dto;
        } else {
            return logica.guardar(dto);
        }
    }

    /**
     * elimina el registro Anexo con el identificador dado
     *
     * @param id identificador del Anexo
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarAnexo(@PathParam("id") Long id) {
        logica.borrar(id);
    }
    
    /**
     * retorna una en un flujo de bytes el archivo solicitado
     *
     * @return retorna una lista de AnexoDTO
     * @generated
     */
    @GET
    @Path("/descargar/{id}.pdf")
    @Produces("application/pdf")
    public byte[] descargar(@PathParam("id") Long id) {
        byte[] archivo=logica.descargar(id);
        if(archivo[0]==0x25 && archivo[1]==0x50 && archivo[2]==0x44 && archivo[3]==0x46){
            return archivo;
        }else{
            try {
                response.sendRedirect("/runt/app/runistac/webresources/Anexo/descargar/"+id+".jpg");
                return null;
            } catch (IOException ex) {
                Logger.getLogger(AnexoServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    /**
     * retorna una en un flujo de bytes el archivo solicitado
     *
     * @return retorna una lista de AnexoDTO
     * @generated
     */
    @GET
    @Path("/descargar/{id}.jpg")
    @Produces("image/jpg")
    public byte[] descargarjpg(@PathParam("id") Long id) {
        return logica.descargar(id);
    }

}
