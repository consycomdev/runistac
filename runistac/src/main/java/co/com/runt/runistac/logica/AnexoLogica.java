package co.com.runt.runistac.logica;

import co.com.runt.common.excepciones.ApplicationException;
import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.persistencia.*;
import co.com.runt.runistac.persistencia.entity.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.io.FileUtils;

/**
 * @generated
 */
@Stateless
public class AnexoLogica {

    @EJB
    private AnexoDAO persistencia;

    @EJB
    private TipoAnexoLogica tipoAnexoLogica;
    
    @EJB
    private ParametroSistemaLogica parametroSistemaLogica;
    /**
     * @generated
     */
    public List<AnexoDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * @generated
     */
    public AnexoDTO obtener(Long id) {
        return convertirEntidad(persistencia.obtener(id));
    }

    /**
     * @generated
     */
    public AnexoDTO guardar(AnexoDTO dto) {
        return convertirEntidad(persistencia.guardar(convertirDTO(dto)));
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        persistencia.borrar(id);
    }

    /**
     * @generated
     */
    public void actualizar(AnexoDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public Anexo convertirDTO(AnexoDTO dto) {
        if (dto == null) {
            return null;
        }
        Anexo entidad = new Anexo();
        entidad.setId(dto.getId());
        entidad.setUbicacion(dto.getUbicacion());

        if (dto.getTipoAnexo() != null) {
            entidad.setTipoAnexo(new TipoAnexo());
            entidad.getTipoAnexo().setId(dto.getTipoAnexo().getId());
        }
        
        if(dto.getPostulacion()!=null){
            entidad.setPostulacion(new Postulacion());
            entidad.getPostulacion().setId(dto.getPostulacion().getId());
        }

        return entidad;
    }

    /**
     * @generated
     */
    public List<Anexo> convertirDTO(List<AnexoDTO> dtos) {
        List<Anexo> entidades = new ArrayList<Anexo>();
        for (AnexoDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public AnexoDTO convertirEntidad(Anexo entidad) {
        AnexoDTO dto = new AnexoDTO();
        dto.setId(entidad.getId());
        dto.setUbicacion(entidad.getUbicacion());

        if (entidad.getTipoAnexo() != null) {
            dto.setTipoAnexo(tipoAnexoLogica.convertirEntidad(entidad.getTipoAnexo()));
        }

        return dto;
    }

    /**
     * @generated
     */
    public List<AnexoDTO> convertirEntidad(List<Anexo> entidades) {
        List<AnexoDTO> dtos = new ArrayList<AnexoDTO>();
        for (Anexo entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

    /**
     * permite generar el flujo de bytes para descargar un archivo
     * @param id del anexo que contiene el archivo
     * @return 
     */
    public byte[] descargar(Long id) {
        try {
            AnexoDTO anexo=obtener(id);
            String directorio=parametroSistemaLogica.obtener(Constantes.ParametroSistema.DIRECTORIO_BASE).getValor();
            if(!directorio.endsWith("/")){
                directorio=directorio+"/";
            }
            return FileUtils.readFileToByteArray(new File(directorio+anexo.getUbicacion()));
        } catch (IOException ex) {
            throw new ApplicationException("Error al buscar el archivo solicitado", ex);
        }
    }

}
