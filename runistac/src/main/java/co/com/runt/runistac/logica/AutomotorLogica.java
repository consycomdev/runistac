package co.com.runt.runistac.logica;

import co.com.runt.common.excepciones.ApplicationException;
import co.com.runt.runistac.dto.*;
import co.com.runt.runistac.persistencia.*;
import co.com.runt.runistac.persistencia.entity.*;
import co.com.runt.runistac.utils.FechaUtils;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @generated
 */
@Stateless
public class AutomotorLogica {

    @EJB
    private AutomotorDAO persistencia;

    @EJB
    private PostulacionLogica postulacionLogica;

    @EJB
    private ParametroSistemaDAO parametroSistemaDAO;

    @EJB
    private EnvioCorreoLogica envioCorreoLogica;
    
    @EJB
    private ParametroTextoLogica parametroTextoLogica;
    
    @EJB
    private PersonaDAO personaDAO;

    /**
     * @generated
     */
    public List<AutomotorDTO> obtenerTodos() {
        return convertirEntidad(persistencia.obtenerTodos());
    }

    /**
     * @generated
     */
    public AutomotorDTO obtener(Long id) {
        return convertirEntidad(persistencia.obtener(id));
    }

    /**
     * @generated
     */
    public AutomotorDTO guardar(AutomotorDTO dto) {
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
    public void actualizar(AutomotorDTO dto) {
        persistencia.actualizar(convertirDTO(dto));
    }

    /**
     * @generated
     */
    public Automotor convertirDTO(AutomotorDTO dto) {
        if (dto == null) {
            return null;
        }
        Automotor entidad = new Automotor();
        entidad.setId(dto.getId());
        entidad.setPlaca(dto.getPlaca());

        return entidad;
    }

    /**
     * @generated
     */
    public List<Automotor> convertirDTO(List<AutomotorDTO> dtos) {
        List<Automotor> entidades = new ArrayList<Automotor>();
        for (AutomotorDTO dto : dtos) {
            entidades.add(convertirDTO(dto));
        }
        return entidades;
    }

    /**
     * @generated
     */
    public AutomotorDTO convertirEntidad(Automotor entidad) {
        AutomotorDTO dto = new AutomotorDTO();
        dto.setId(entidad.getId());
        dto.setPlaca(entidad.getPlaca());

        return dto;
    }

    /**
     * @generated
     */
    public List<AutomotorDTO> convertirEntidad(List<Automotor> entidades) {
        List<AutomotorDTO> dtos = new ArrayList<AutomotorDTO>();
        for (Automotor entidad : entidades) {
            dtos.add(convertirEntidad(entidad));
        }
        return dtos;
    }

    public List<ConsultaVehiculosDTO> consultarInformacionPropiedades(Long idPersona) {
        List<Object[]> lista = persistencia.consultarInformacionPropiedades(idPersona);
        List<ConsultaVehiculosDTO> resultado = new ArrayList<>();
        for (Object[] elemento : lista) {
            ConsultaVehiculosDTO consulta = new ConsultaVehiculosDTO();
            consulta.setPlaca(elemento[0].toString());
            consulta.setClase(elemento[1].toString());
            consulta.setCarroceria(elemento[2].toString());
            consulta.setModelo(elemento[3].toString());
            consulta.setEjes(elemento[4].toString());
            consulta.setPeso(elemento[5].toString());
            consulta.setServicio(elemento[6].toString());
            consulta.setPropietarios(elemento[7].toString());
            resultado.add(consulta);
        }
        return resultado;
    }

    public ConsultaVINDTO consultarVin(String vin) {
        List<Object[]> lista = persistencia.consultarVehiculoVIN(vin);
        ConsultaVINDTO consulta = new ConsultaVINDTO();
        if (lista.isEmpty()) {
            throw new ApplicationException("El VIN digitado no se encontr&oacute; o no se encuentra en estado REGISTRADO");
        }
        Integer pbv = Integer.parseInt(parametroSistemaDAO.obtener("MINIMO_PESO_BRUTO_VEHICULAR").getValor());

        for (Object[] obj : lista) {
            if (obj[0] != null) {
                consulta.setId((String) obj[0]);
            }
            if (obj[1] != null) {
                consulta.setMotor((String) obj[1]);
            }
            if (obj[2] != null) {
                consulta.setSerie((String) obj[2]);
            }
            if (obj[3] != null) {
                consulta.setChasis((String) obj[3]);
            }
            if (obj[4] != null) {
                consulta.setMarca((String) obj[4]);
            }
            if (obj[5] != null) {
                consulta.setLinea((String) obj[5]);
            }
            if (obj[6] != null) {
                consulta.setClase((String) obj[6]);
            }
            if(obj[7]==null){
                throw new ApplicationException("No se encontro la Ficha T\u00E9cnica de Homologaci\u00F3n con la informaci\u00F3n suministrada");
            }
            if (((String) obj[7]).compareTo("1") == 0) {
                consulta.setFthChasis((String) obj[8]);
            } else if (((String) obj[7]).compareTo("2") == 0) {
                consulta.setFthCarroceria((String) obj[8]);
            } else if (((String) obj[7]).compareTo("3") == 0) {
                consulta.setFthCarroceria((String) obj[8]);
                consulta.setFthChasis((String) obj[8]);
            }
            if (obj[9] != null) {
                consulta.setVin((String) obj[9]);
            }
            if (obj[10] != null) {
                if (((java.math.BigDecimal) obj[10]).intValue() < pbv) {
                    if(consulta.getClase().compareTo("TRACTOCAMION") == 0){//consultar si el vehiculo es tractocamion
                        //validar los pesos combinados
                        boolean cumple=false;
                        if(!cumple && ((String)obj[17]).matches("\\d+")){ //combinado 1 - 17
                            if(Integer.parseInt(((String)obj[17]))>=pbv){//convertir a numero y verificar si es mayor al pbv
                                cumple=true;
                            }
                        }
                        if(!cumple && ((String)obj[18]).matches("\\d+")){ //combinado 2 - 17
                            if(Integer.parseInt(((String)obj[18]))>=pbv){//convertir a numero y verificar si es mayor al pbv
                                cumple=true;
                            }
                        }
                        if(!cumple && ((String)obj[19]).matches("\\d+")){ //combinado 3 - 17
                            if(Integer.parseInt(((String)obj[19]))>=pbv){//convertir a numero y verificar si es mayor al pbv
                                cumple=true;
                            }
                        }
                        
                        if(!cumple){
                            throw new ApplicationException("El veh\u00EDculo no cumple con las caracter\u00EDsticas de Peso Bruto Vehicular");
                        }
                    }else{
                        throw new ApplicationException("El veh\u00EDculo no cumple con las caracter\u00EDsticas de Peso Bruto Vehicular");
                    }
                } else {
                    consulta.setPesoBrutoVehicular(obj[10].toString());
                }
            }
            if (obj[11] != null) {
                consulta.setCarroceria((String) obj[11]);
            }
            if (obj[12] != null) {
                consulta.setNumeroEjes((String) obj[12]);
            }
            if (obj[13] != null) {
                consulta.setModelo((String) obj[13]);
            }
            if (obj[14] != null) {
                consulta.setConfiguracion((String) obj[14]);
            }
            if (obj[15] != null) {
                throw new ApplicationException("El tipo de carrocer\u00EDa no puede ser exenta de carga");
            }
            if (obj[16] != null) {
                consulta.setEstado((String) obj[16]);
            }
            if (consulta.getClase() != null && consulta.getClase().compareTo("SIN CLASE") != 0 &&  consulta.getClase().compareTo("CAMION") != 0 && consulta.getClase().compareTo("TRACTOCAMION") != 0) {
                throw new ApplicationException("El veh&iacute;culo debe tener clase CAMION o TRACTOCAMION");
            }
        }
        return consulta;
    }

    public String validarFTHCarroceria(Long id, String fth) {
        Object[] info = persistencia.consultarFichaTecnica(fth);
        //validar que se encuentre en estado aprobado y sea de carroceria
        if (((String) info[0]).compareTo("APROBADO") != 0 && ((String) info[1]).compareTo("2") != 0) {
            throw new ApplicationException("La ficha t\u00E9cnica debe estar en estado APROBADO y debe ser de tipo Carrocer\u00EDa");
        }
        //tipo de carroceria no es exento
        if (info[7] != null) {
            throw new ApplicationException("La Ficha T\u00E9cnica de carrocer\u00EDa digitada corresponde a una carrocer\u00EDa exenta y no puede ser usada en este tr\u00E1mite");
        }
        //valida que tenga una carroceria
        if(info[2]==null){
            throw new ApplicationException("La ficha t\u00E9cnica de homologación no tiene un Tipo de Carrocer\u00EDa asociado.");
        }
        //la fth esta asociada al chasis
        PostulacionDTO postulacion = postulacionLogica.obtener(id);
        ConsultaVINDTO infoVIN = consultarVin(postulacion.getVin());
        if (info[1]==null || ((String) info[1]).compareTo(infoVIN.getFthChasis()) != 0) {
            throw new ApplicationException("La FTH de carrocer\u00EDa no esta asociada al chasis");//1
        }
        //validar que la ficha tecnica de homologacion sea Camion
        if (((BigDecimal) info[8]).intValue()!=4) {
            throw new ApplicationException("La Ficha T\u00E9cnica de carrocer\u00EDa digitada no corresponde clase CAMION");
        }
        //validar que la carroceria este activa
        if (((String) info[5]).compareTo("A")!=0) {
            throw new ApplicationException("La Carrocer\u00EDa de la ficha t\u00E9cnica de homologación debe estar Activa");
        }
        
        //devuelve la carroceria que tiene asociada
        return (String) info[4]; 
    }

    public Integer validarSinCarroceria() {
        //consultar todas las postulaciones
        List<PostulacionDTO> postulaciones = postulacionLogica.obtenerRegistroPoliza();
        String receptormt = parametroSistemaDAO.obtener("CORREO_MT_ALERTA_SIN_CARROCERIA").getValor();
        //consultar el vin
        int i = 0;
        for (PostulacionDTO p : postulaciones) {
            ConsultaVINDTO vin = consultarVin(p.getVin());
            if (vin.getFthCarroceria() == null) {
                //enviar correo
                String titulo = "Alerta Solicitud del Tr&aacute;mite RUNISTAC";
                String textoEnvioCorreo = parametroTextoLogica.obtener("CORREO_ALERTA_NO_CARROCERIA").getValor();
                textoEnvioCorreo=textoEnvioCorreo.replaceAll("\\$solicitud", p.getSolicitud().toString());
                textoEnvioCorreo=textoEnvioCorreo.replaceAll("\\$vin", p.getVin());
                
                String mensaje = EnvioCorreoLogica.contenidoEmailHtmlRunt(titulo, "CIUDADANO", textoEnvioCorreo);
                envioCorreoLogica.enviarCorreo(personaDAO.obtenerCorreoUsuario(p.getPersona().getTipoDocumento().getId(), p.getPersona().getNumeroDocumento()), mensaje);
                envioCorreoLogica.enviarCorreo(receptormt, mensaje);
                i++;
            }
        }
        return i;
    }

    public void registrarFTH(String vin, String fthCarroceria) {
        persistencia.registrarFTH(vin, fthCarroceria);
    }

}
