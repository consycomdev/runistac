package co.com.runt.runistac.logica;

import co.com.runt.common.excepciones.ApplicationException;
import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.persistencia.*;
import co.com.runt.runistac.persistencia.entity.*;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @generated
 */
@Stateless
public class PagosMTLogica {

    @EJB
    private PagosMTDAO persistencia;

    @EJB
    private EmpresaLogica empresaLogica;

    @EJB
    private PostulacionLogica postulacionLogica;
    
    @EJB
    private EntidadFinancieraLogica bancoLogica;

    private final SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * @generated
     */
    public List<PagosMTDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * obtiene la lista de pagos por postulaci&oacute;n
     *
     * @param idPostulacion
     * @return
     */
    public List<PagosMTDTO> obtenerPorPostulacion(Long idPostulacion) {
        //validar que solo se puedan visualizar si esta en estado aprobado
        return convertirEntidad(persistencia.obtenerPorPostulacion(idPostulacion));
    }

    /**
     * @generated
     */
    public PagosMTDTO obtener(Long id) {
        return convertirEntidad(persistencia.obtener(id));
    }

    /**
     * @generated
     */
    public PagosMTDTO guardar(PagosMTDTO dto) {

        //la fecha no puede ser mayor a la del sistmema
        Date fechaPago=null;
        try {
            fechaPago = fecha.parse(dto.getFechaPago());
        } catch (ParseException ex) {
            Logger.getLogger(PagosMTLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (fechaPago.after(new Date())) {
            throw new ApplicationException("La fecha no puede ser mayor a la del sistema");
        }
        //validar que solo se puedan visualizar si esta en estado aprobado
        PostulacionDTO postulacion = postulacionLogica.obtener(dto.getPostulacion().getId());
        if (postulacion.getEstado() != EstadoPostulacion.APROBADO) {
            throw new ApplicationException("No se pueden editar los pagos en un estado diferente a Aprobado");
        }
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
    public void actualizar(PagosMTDTO dto) {
        PostulacionDTO postulacion = postulacionLogica.obtener(dto.getPostulacion().getId());
        if (postulacion.getEstado() != EstadoPostulacion.APROBADO) {
            throw new ApplicationException("No se pueden editar los pagos en un estado diferente a Aprobado");
        }
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public PagosMT convertirDTO(PagosMTDTO dto) {
        if (dto == null) {
            return null;
        }
        PagosMT entidad = new PagosMT();
        entidad.setId(dto.getId());
        if (dto.getFechaPago() != null) {
            try {
                entidad.setFechaPago(fecha.parse(dto.getFechaPago()));
            } catch (ParseException ex) {
                throw new RuntimeException("Error al convertir la fecha FechaPago " + dto.getFechaPago());
            }
        }
        entidad.setNumeroAprobacion(dto.getNumeroAprobacion());
        entidad.setValorPagado(dto.getValorPagado());

        if (dto.getBanco() != null) {
            entidad.setBanco(dto.getBanco().getId());

        }
        if (dto.getPostulacion() != null) {
            entidad.setPostulacion(new Postulacion());
            entidad.getPostulacion().setId(dto.getPostulacion().getId());
        }

        return entidad;
    }

    /**
     * @generated
     */
    public List<PagosMT> convertirDTO(List<PagosMTDTO> dtos) {
        List<PagosMT> entidades = new ArrayList<PagosMT>();
        for (PagosMTDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public PagosMTDTO convertirEntidad(PagosMT entidad) {
        PagosMTDTO dto = new PagosMTDTO();
        dto.setId(entidad.getId());
        if (entidad.getFechaPago() != null) {
            dto.setFechaPago(fecha.format(entidad.getFechaPago()));
        }
        dto.setNumeroAprobacion(entidad.getNumeroAprobacion());
        dto.setValorPagado(entidad.getValorPagado());

        if (entidad.getPostulacion() != null) {
            dto.setPostulacion(
                    new PostulacionDTO(
                            entidad.getPostulacion().getId()));
        }
        if (entidad.getBanco() != null) {
            dto.setBanco(bancoLogica.obtener(
                            entidad.getBanco()));
        }

        return dto;
    }

    /**
     * @generated
     */
    public List<PagosMTDTO> convertirEntidad(List<PagosMT> entidades) {
        List<PagosMTDTO> dtos = new ArrayList<PagosMTDTO>();
        for (PagosMT entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

}
