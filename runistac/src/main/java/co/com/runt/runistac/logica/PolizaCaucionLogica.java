package co.com.runt.runistac.logica;

import co.com.runt.common.excepciones.ApplicationException;
import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.persistencia.*;
import co.com.runt.runistac.persistencia.entity.*;
import co.com.runt.runistac.utils.FechaUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import sun.misc.BASE64Decoder;

/**
 * @generated
 */
@Stateless
public class PolizaCaucionLogica {

    @EJB
    private PolizaCaucionDAO persistencia;

    @EJB
    private PostulacionDAO postulacionDAO;

    @EJB
    private ParametroSistemaLogica parametroSistemaLogica;

    @EJB
    private PersonaNaturalDAO personaNaturalDAO;

    @EJB
    private TipoAnexoLogica tipoAnexoLogica;

    @EJB
    private AnexoLogica anexoLogica;

    private final SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * @generated
     */
    public List<PolizaCaucionDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    public PolizaCaucionDTO obtenerPorPostulacion(Long idPostulacion) {
        PolizaCaucion poliza = persistencia.obtenerPorPostulacion(idPostulacion);
        if (poliza == null) {
            Postulacion postulacion = postulacionDAO.obtener(idPostulacion);
            String tomador = postulacion.getPersona().getTipoDocumento().getAbreviatura() + " - " + postulacion.getPersona().getNumeroDocumento();
            PersonaNatural persona = personaNaturalDAO.obtener(postulacion.getPersona().getId());
            tomador += " " + persona.getPrimerNombre();
            tomador += " " + persona.getSegundoNombre();
            tomador += " " + persona.getPrimerApellido();
            tomador += " " + persona.getSegundoApellido();

            String asegurado = parametroSistemaLogica.obtener("NOMBRE_ASEGURADO").getValor();
            PolizaCaucionDTO respuesta = new PolizaCaucionDTO();
            respuesta.setAsegurado(asegurado);
            respuesta.setTomador(tomador);
            respuesta.setFechaFinVigencia(parametroSistemaLogica.obtener("FECHA_FIN_VIGENCIA").getValor());
            respuesta.setTipo(TipoTituloValor.POLIZA_CAUCION);

            return respuesta;
        }
        return convertirEntidad(poliza);
    }

    /**
     * @generated
     */
    public PolizaCaucionDTO obtener(Long id) {
        return convertirEntidad(persistencia.obtener(id));
    }

    /**
     * @generated
     */
    public PolizaCaucionDTO guardar(PolizaCaucionDTO dto) {
        //se incluye el cambio de la vigencia a un valor fijo parametrico
        if(dto.getTipo()==TipoTituloValor.POLIZA_CAUCION){
            dto.setFechaFinVigencia(parametroSistemaLogica.obtener("FECHA_FIN_VIGENCIA").getValor());
        }
        //validar campos
        validarCampos(dto);
        Postulacion postulacion = postulacionDAO.obtener(dto.getPostulacion().getId());
        postulacion.setEstado(EstadoPostulacion.REGISTRO_POLIZA);

        //guardar archivos
        String directorio = parametroSistemaLogica.obtener(Constantes.ParametroSistema.DIRECTORIO_BASE).getValor();
        if (dto.getAnexo() != null) {
            for (AnexoDTO anexo : dto.getAnexo()) {
                try {
                    TipoAnexoDTO anexo1 = tipoAnexoLogica.obtener(anexo.getId());
                    anexo.setTipoAnexo(new TipoAnexoDTO(anexo.getId()));
                    anexo.setId(null);
                    String ubicacion = "" + postulacion.getSolicitud() + "/" + anexo1.getNombre() + ".pdf";
                    new File(directorio + "/" + postulacion.getSolicitud() + "/").mkdirs();
                    FileOutputStream fos = new FileOutputStream(directorio + "/" + ubicacion);
                    if (anexo.getArchivo() == null) {
                        throw new ApplicationException("Debe cargar el archivo " + anexo1.getNombre());
                    }
                    String b64 = new String(new BASE64Decoder().decodeBuffer(anexo.getArchivo()));
                    fos.write(new BASE64Decoder().decodeBuffer(b64.substring(b64.indexOf(",") + 1)));
                    fos.close();
                    anexo.setUbicacion(ubicacion);
                    anexo.setPostulacion(new PostulacionDTO(postulacion.getId()));
                    anexoLogica.guardar(anexo);
                } catch (IOException ex) {
                    Logger.getLogger(PostulacionLogica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
    public void actualizar(PolizaCaucionDTO dto) {
        //validar campos
        validarCampos(dto);
        Postulacion postulacion = postulacionDAO.obtener(dto.getPostulacion().getId());
        postulacion.setEstado(EstadoPostulacion.REGISTRO_POLIZA);

        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public PolizaCaucion convertirDTO(PolizaCaucionDTO dto) {
        if (dto == null) {
            return null;
        }
        PolizaCaucion entidad = new PolizaCaucion();
        entidad.setId(dto.getId());
        entidad.setAsegurado(dto.getAsegurado());
        if (dto.getFechaExpedicion() != null) {
            try {
                entidad.setFechaExpedicion(fecha.parse(dto.getFechaExpedicion()));
            } catch (ParseException ex) {
                throw new RuntimeException("Error al convertir la fecha FechaExpedicion " + dto.getFechaExpedicion());
            }
        }
        if (dto.getFechaInicioVigencia() != null) {
            try {
                entidad.setFechaInicioVigencia(fecha.parse(dto.getFechaInicioVigencia()));
            } catch (ParseException ex) {
                throw new RuntimeException("Error al convertir la fecha FechaInicioVigencia " + dto.getFechaInicioVigencia());
            }
        }
        if (dto.getFechaFinVigencia() != null) {
            try {
                entidad.setFechaFinVigencia(fecha.parse(dto.getFechaFinVigencia()));
            } catch (ParseException ex) {
                throw new RuntimeException("Error al convertir la fecha FechaFinVigencia " + dto.getFechaFinVigencia());
            }
        }
        entidad.setValorAsegurado(dto.getValorAsegurado());

        if (dto.getPostulacion() != null) {
            entidad.setPostulacion(new Postulacion());
            entidad.getPostulacion().setId(dto.getPostulacion().getId());
        }
        if (dto.getEntidadFinanciera()!= null) {
            entidad.setEntidadFinanciera(new EntidadFinanciera());
            entidad.getEntidadFinanciera().setId(dto.getEntidadFinanciera().getId());
        }
        entidad.setValorVehiculo(dto.getValorVehiculo());
        entidad.setValorAsegurado(dto.getValorAsegurado());
        entidad.setNumero(dto.getNumero());
        entidad.setNumeroFactura(dto.getNumeroFactura());
        entidad.setTipo(dto.getTipo());
        if(dto.getTipoGarantia()!=null){
            entidad.setTipoGarantia(new TipoGarantiaBancaria());
            entidad.getTipoGarantia().setId(dto.getTipoGarantia().getId());
            entidad.getTipoGarantia().setNombre(dto.getTipoGarantia().getNombre());
        }
        entidad.setTomador(entidad.getTomador());
        return entidad;
    }

    /**
     * @generated
     */
    public List<PolizaCaucion> convertirDTO(List<PolizaCaucionDTO> dtos) {
        List<PolizaCaucion> entidades = new ArrayList<PolizaCaucion>();
        for (PolizaCaucionDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public PolizaCaucionDTO convertirEntidad(PolizaCaucion entidad) {
        PolizaCaucionDTO dto = new PolizaCaucionDTO();
        dto.setId(entidad.getId());
        dto.setAsegurado(entidad.getAsegurado());
        if (entidad.getFechaExpedicion() != null) {
            dto.setFechaExpedicion(fecha.format(entidad.getFechaExpedicion()));
        }
        if (entidad.getFechaInicioVigencia() != null) {
            dto.setFechaInicioVigencia(fecha.format(entidad.getFechaInicioVigencia()));
        }
        if (entidad.getFechaFinVigencia() != null) {
            dto.setFechaFinVigencia(fecha.format(entidad.getFechaFinVigencia()));
        }
        dto.setValorAsegurado(entidad.getValorAsegurado());

        if (entidad.getEntidadFinanciera()!= null) {
            dto.setEntidadFinanciera(
                    new EntidadFinancieraDTO(
                            entidad.getEntidadFinanciera().getId()));
            dto.getEntidadFinanciera().setNombre(entidad.getEntidadFinanciera().getNombre());
            dto.getEntidadFinanciera().setCorreo(entidad.getEntidadFinanciera().getCorreo());
        }
        if (entidad.getPostulacion() != null) {
            dto.setPostulacion(
                    new PostulacionDTO(
                            entidad.getPostulacion().getId()));
        }
        if (entidad.getPostulacion() != null && entidad.getPostulacion().getPersona() != null) {
            Postulacion postulacion = postulacionDAO.obtener(entidad.getPostulacion().getId());
            String tomador = postulacion.getPersona().getTipoDocumento().getAbreviatura() + " - " + postulacion.getPersona().getNumeroDocumento();
            PersonaNatural persona = personaNaturalDAO.obtener(postulacion.getPersona().getId());
            tomador += " " + (persona.getPrimerNombre() == null ? "" : persona.getPrimerNombre());
            tomador += " " + (persona.getSegundoNombre() == null ? "" : persona.getSegundoNombre());
            tomador += " " + (persona.getPrimerApellido() == null ? "" : persona.getPrimerApellido());
            tomador += " " + (persona.getSegundoApellido() == null ? "" : persona.getSegundoApellido());
            dto.setTomador(tomador);
        }
        
        if(entidad.getTipo()!=null){
            dto.setTipo(TipoTituloValor.POLIZA_CAUCION); 
        }
        dto.setValorVehiculo(entidad.getValorVehiculo());
        dto.setValorAsegurado(entidad.getValorAsegurado());
        dto.setNumero(entidad.getNumero());
        dto.setNumeroFactura(entidad.getNumeroFactura());
        dto.setTipo(entidad.getTipo());

        if(entidad.getTipoGarantia()!=null){
            dto.setTipoGarantia(new TipoGarantiaBancariaDTO());
            dto.getTipoGarantia().setId(entidad.getTipoGarantia().getId());
            dto.getTipoGarantia().setNombre(entidad.getTipoGarantia().getNombre());
        }
        
        return dto;
    }

    /**
     * @generated
     */
    public List<PolizaCaucionDTO> convertirEntidad(List<PolizaCaucion> entidades) {
        List<PolizaCaucionDTO> dtos = new ArrayList<PolizaCaucionDTO>();
        for (PolizaCaucion entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

    private void validarCampos(PolizaCaucionDTO dto) {
        try {
            if (dto.getFechaExpedicion() == null || fecha.parse(dto.getFechaExpedicion()).after(new Date())) {
                throw new ApplicationException("La fecha de expedici\u00F3n es un campo obligatorio y debe ser menor o igual a la del sistema");
            }
        } catch (ParseException ex) {
            throw new ApplicationException("La fecha de expedici\u00F3n es un campo obligatorio y debe ser menor o igual a la del sistema");
        }

        try {
            if (dto.getFechaInicioVigencia() == null || fecha.parse(dto.getFechaInicioVigencia()).before(fecha.parse(dto.getFechaExpedicion()))) {
                throw new ApplicationException("La fecha de inicio de la p\u00F3liza es un campo obligatorio y debe ser mayor o igual  a la fecha de expedici\u00F3n");
            }
        } catch (ParseException ex) {
            throw new ApplicationException("La fecha de inicio de la p\u00F3liza es un campo obligatorio y debe ser mayor o igual a la fecha de expedici\u00F3n");
        }

        try {
            if (dto.getFechaFinVigencia() == null || fecha.parse(dto.getFechaFinVigencia()).before(fecha.parse(dto.getFechaInicioVigencia()))) {
                throw new ApplicationException("La fecha Fin de Vigencia es un campo obligatorio y debe ser mayor o igual a la fecha de inicio de vigencia");
            }
        } catch (ParseException ex) {
            throw new ApplicationException("La fecha Fin de Vigencia es un campo obligatorio y debe ser mayor o igual a la fecha de inicio de vigencia");
        }

        if (dto.getValorAsegurado() == null) {
            throw new ApplicationException("El valor asegurado es obligatorio");
        } else {
            if (dto.getValorAsegurado() <= 0) {
                throw new ApplicationException("El valor asegurado debe ser mayor a 0");
            }
            //validar longitud de campos
            BigDecimal valorAsegurado = new java.math.BigDecimal(dto.getValorAsegurado());
            valorAsegurado = valorAsegurado.setScale(2, RoundingMode.FLOOR);
            String numero = valorAsegurado.toPlainString();
            //validar 15 enteros 2 decimales con expresiones regulares
            if (!numero.matches("^[0-9]{1,16}(\\.[0-9]{1,2})?$")) {
                throw new ApplicationException("El valor asegurado debe cumplir con el estandar de 16 digitos y 2 decimales");
            }
        }
        
        if (dto.getValorVehiculo()== null) {
            throw new ApplicationException("El valor del vehículo es obligatorio");
        } else {
            if (dto.getValorVehiculo() <= 0) {
                throw new ApplicationException("El valor del vehículo debe ser mayor a 0");
            }
            //validar longitud de campos
            BigDecimal valorVehiculo = new java.math.BigDecimal(dto.getValorAsegurado());
            valorVehiculo = valorVehiculo.setScale(2, RoundingMode.FLOOR);
            String numero = valorVehiculo.toPlainString();
            //validar 15 enteros 2 decimales con expresiones regulares
            if (!numero.matches("^[0-9]{1,16}(\\.[0-9]{1,2})?$")) {
                throw new ApplicationException("El valor del vehiculo debe cumplir con el estandar de 16 digitos y 2 decimales");
            }
        }

        if (dto.getEntidadFinanciera()== null || dto.getEntidadFinanciera().getId() == null) {
            throw new ApplicationException("Debe seleccionar una Entidad Financiera");
        }
        
        if(dto.getTipo()==TipoTituloValor.GARANTIA_BANCARIA && dto.getTipoGarantia()==null){
            throw new ApplicationException("Debe seleccionar un tipo de Garant\u00eda Bancaria");
        }
        
        if (dto.getEntidadFinanciera()== null || dto.getEntidadFinanciera().getId() == null) {
            throw new ApplicationException("Debe seleccionar una Entidad Financiera");
        }
        
        if (dto.getEntidadFinanciera()== null || dto.getEntidadFinanciera().getId() == null) {
            throw new ApplicationException("Debe seleccionar una Entidad Financiera");
        }
        
        if (dto.getNumero()== null) {
            throw new ApplicationException("Debe digitar el número del título valor ");
        }
        
        if (dto.getNumeroFactura()== null) {
            throw new ApplicationException("Debe digitar el número de factura");
        }
        //Cuando el tipo de garantía es CDT la cantidad de días entre la fecha del sistema y la fecha fin de vigencia del título valor debe ser menor o igual al valor de un parámetro.
        String maximoDiasCDT=parametroSistemaLogica.obtener("MAXIMO_DIAS_CDT").getValor();
        String idCDT=parametroSistemaLogica.obtener("ID_CDT").getValor();
        if(dto.getTipoGarantia()!=null && idCDT.compareTo(String.valueOf(dto.getTipoGarantia().getId()))==0){
            //es cdt
            Date fechaInicial=new Date();
            Date fechaFinal=FechaUtils.convertirFecha(dto.getFechaFinVigencia());
            int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);
            if(dias>Integer.parseInt(maximoDiasCDT)){
                throw new ApplicationException("El CDT supera el limite máximo de días para fecha fin de vigencia");
            }
        }
        
        
    }

}
