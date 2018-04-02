package co.com.runt.runistac.persistencia;

import co.com.runt.common.excepciones.ApplicationException;
import co.com.runt.common.seguridad.InfoUsuario;
import co.com.runt.runistac.dto.ConsultaPostulacionDTO;
import co.com.runt.runistac.persistencia.entity.*;
import co.com.runt.runistac.utils.FechaUtils;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.persistence.*;
import javax.ejb.Stateless;

/**
 * @generated
 */
@Stateless
public class PostulacionDAO {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private PersonaDAO personaDAO;

    @EJB
    private CertificadoCancelacionMatriculaDAO certificadoCancelacionMatriculaDAO;

    @EJB
    private ParametroSistemaDAO parametroSistemaDAO;

    @EJB
    private AutomotorDAO automotorDAO;

    /**
     * @generated
     */
    public List<Postulacion> obtenerTodos() {
        return em.createNamedQuery("Postulacion.obtenerTodos").getResultList();
    }

    /**
     * @generated
     */
    public List<Postulacion> obtenerPorEstado(EstadoPostulacion estado) {
        return em.createNamedQuery("Postulacion.obtenerPorEstado").setParameter("estado", estado).getResultList();
    }

    public Object[] obtenerPostulacionPorCCM(Long idCCM) {
        String sql = "select to_char(POSTULACION_SOLICITUD), \n"
                + "CASE WHEN EMPRESA_RAZOSOCIA IS NULL THEN PERSNATUR_NOMBRE1||' '||PERSNATUR_NOMBRE2||' '||PERSNATUR_APELLIDO1||' '||PERSNATUR_APELLIDO2 ELSE EMPRESA_RAZOSOCIA END, \n"
                + "to_char(ASIGNACIONCCM_FECHA,'DD/MM/YYYY'), \n"
                + "to_char(sum(PAGO_VALORPAGADO)), \n "
                + "PERSONA_TIPOIDENT_IDTIPDOC||' '||PERSONA_NRODOCUME\n"
                + "from ru_ccm, ru_asignacionccm,  ru_postulacion\n"
                + "left join RUNTPROD.TR_PERSNATUR on PERSNATUR_PERSONA_IDPERSONA=POSTULACION_PERSONA_IDPERSONA\n"
                + "left join RUNTPROD.GE_EMPRESA on EMPRESA_PERSONA_IDPERSONA=POSTULACION_PERSONA_IDPERSONA\n"
                + "left join RUNTPROD.TR_PERSONA ON PERSONA_IDPERSONA=POSTULACION_PERSONA_IDPERSONA\n"
                + "left join ru_pagosccm on PAGO_POSTULACION=POSTULACION_ID\n"
                + "where CCM_ASIGNACIONCCM_ID=ru_asignacionccm.ASIGNACIONCCM_ID\n"
                + "  and ru_asignacionccm.ASIGNACIONCCM_ID=POSTULACION_ASIGNACION\n"
                + "  and ru_ccm.CCM_ID=? \n"
                + "group by to_char(POSTULACION_SOLICITUD), \n"
                + "CASE WHEN EMPRESA_RAZOSOCIA IS NULL THEN PERSNATUR_NOMBRE1||' '||PERSNATUR_NOMBRE2||' '||PERSNATUR_APELLIDO1||' '||PERSNATUR_APELLIDO2 ELSE EMPRESA_RAZOSOCIA END, to_char(ASIGNACIONCCM_FECHA,'DD/MM/YYYY'),PERSONA_TIPOIDENT_IDTIPDOC||' '||PERSONA_NRODOCUME";
        List<Object[]> lista = em.createNativeQuery(sql)
                .setParameter(1, idCCM)
                .getResultList();
        return lista.isEmpty() ? null : lista.get(0);
    }

    /**
     * @generated
     */
    public Postulacion obtener(Long id) {
        return em.find(Postulacion.class, id);
    }

    /**
     * @generated
     */
    public Postulacion guardar(Postulacion entidad) {
        entidad.setFecha(new Date());
        entidad.setFechaCreacion(new Date());
        Persona persona = personaDAO.obtener(entidad.getPersona().getId());
        entidad.setUsuarioCreacion(persona.getTipoDocumento().getAbreviatura() + "-" + persona.getNumeroDocumento());
        List<Anexo> anexos = entidad.getAnexo();
        em.persist(entidad);

        for (Anexo anexo : anexos) {
            anexo.setPostulacion(entidad);
            anexo.setFechaCreacion(new Date());
            anexo.setUsuarioCreacion(entidad.getPersona().getNumeroDocumento());
            em.persist(anexo);
        }
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(Postulacion.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(Postulacion entidad) {
        for (Anexo anexo : entidad.getAnexo()) {
            anexo.setPostulacion(entidad);
        }
        em.merge(entidad);
    }

    public List<Postulacion> obtenerPostulaciones(String tipoDocumento, String numeroDocumento) {
        return em.createNamedQuery("Postulacion.obtenerPorPersona").setParameter("tipoDocumento", tipoDocumento)
                .setParameter("numeroDocumento", numeroDocumento)
                .getResultList();
    }

    public List<Postulacion> obtenerPostulaciones(String tipoDocumento, String numeroDocumento, EstadoPostulacion estado) {
        return em.createNamedQuery("Postulacion.obtenerPorPersonaEstado").setParameter("tipoDocumento", tipoDocumento)
                .setParameter("numeroDocumento", numeroDocumento)
                .setParameter("estado", estado)
                .getResultList();
    }

    public void validarPago() {
        String sql = "update RUNTPROD.LQ_PROCETRAN set LQPROCTRA_CANTUTILI=1\n"
                + "where LQPROCTRA_IDENTIFIC in (\n"
                + "select LQPROCTRA_IDENTIFIC\n"
                + "from RUNTPROD.LQ_PROCETRAN, ru_postulacion\n"
                + "where LQPROCTRA_IDENTIFIC=POSTULACION_LIQUIDACION\n"
                + "  and LQPROCTRA_ESTALIQUI_ESTLIQNOM='PAGADO'\n"
                + "  and POSTULACION_ESTADO='REGISTRADO'\n"
                + ")";
        em.createNativeQuery(sql).executeUpdate();

        sql = "update ru_CCM set CCM_ESTADO='ASIGNADO'\n"
                + "where CCM_ASIGNACIONCCM_ID in (\n"
                + "select POSTULACION_ASIGNACION\n"
                + "from RUNTPROD.LQ_PROCETRAN, ru_postulacion\n"
                + "where LQPROCTRA_IDENTIFIC=POSTULACION_LIQUIDACION\n"
                + "  and LQPROCTRA_ESTALIQUI_ESTLIQNOM='PAGADO'\n"
                + "  and POSTULACION_ESTADO='REGISTRADO'\n"
                + ")";
        em.createNativeQuery(sql).executeUpdate();

        sql = "update ru_postulacion set POSTULACION_ESTADO='EN_TRAMITE', POSTULACION_FECHAPAGO=systimestamp\n"
                + "where POSTULACION_ID in (\n"
                + "select POSTULACION_ID\n"
                + "from RUNTPROD.LQ_PROCETRAN, ru_postulacion\n"
                + "where LQPROCTRA_IDENTIFIC=POSTULACION_LIQUIDACION\n"
                + "  and LQPROCTRA_ESTALIQUI_ESTLIQNOM='PAGADO'\n"
                + "  and POSTULACION_ESTADO='REGISTRADO'\n"
                + ")";
        em.createNativeQuery(sql).executeUpdate();

    }

    public void anularLiquidacion(String idLiquidacion) {
        //validar que la liquidacion anterior no este paga
        String sql = "select LQPROCTRA_ESTALIQUI_ESTLIQNOM from RUNTPROD.LQ_PROCETRAN where LQPROCTRA_IDENTIFIC=? and LQPROCTRA_ESTALIQUI_ESTLIQNOM='PAGADO'";
        List<String> liquidacion = em.createNativeQuery(sql).setParameter(1, idLiquidacion).getResultList();
        if (!liquidacion.isEmpty()) {
            throw new ApplicationException("No se puede generar un nuevo CUPL para esta solicitud, por favor verifique que no se encuentre ya pagada");
        }
        sql = "update RUNTPROD.LQ_PROCETRAN set LQPROCTRA_ESTALIQUI_ESTLIQNOM='ANULADO'\n"
                + "where LQPROCTRA_IDENTIFIC=?";
        em.createNativeQuery(sql).setParameter(1, idLiquidacion).executeUpdate();
    }

    public void validarPagoNoRealizados(Integer horas) {
        String sql = "update ru_postulacion set POSTULACION_ESTADO='NO_PAGO', POSTULACION_OBSRECHAZO='El pago no fue realizado en el tiempo establecido para este'\n"
                + "where POSTULACION_ESTADO='REGISTRADO'\n"
                + "  and POSTULACION_FECHA<?";
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, horas * -1);
        em.createNativeQuery(sql).setParameter(1, c.getTime()).executeUpdate();

    }

    public BigDecimal validarAnularAutorizacion(String vin, Long solicitud, InfoUsuario infoUsuario){
        String sql = "select AUTOMOTOR_ESTAVEHIC_NOMBRE from runtprod.ra_automotor where AUTOMOTOR_IDVEUNINT=?";
        List<String> vehiculo = em.createNativeQuery(sql).setParameter(1, vin).getResultList();
        if (vehiculo.isEmpty()) {
            //no se encontro el vehiculo
            throw new ApplicationException("No se encontró el vehículo con el VIN asociado a la solicitud");
        } else {
            if ("REGISTRADO".compareTo(vehiculo.get(0)) != 0) {
                //se encuentra matriculado
                throw new ApplicationException("El vehículo asociado a la solicitud se encuentra en un estado diferente a REGISTRADO");
            }
        }
        //validar que el certificado este en estado no utilizado
        sql = "select CERCUMREQ_IDREGDOC,CERCUMREQ_ESTADOCUM_NOMBRE from RUNTPROD.RA_CERCUMREQ where CERCUMREQ_AUTOMOTOR_NROREGVEH=(select AUTOMOTOR_NROREGVEH from RUNTPROD.RA_AUTOMOTOR where AUTOMOTOR_IDVEUNINT=?) and CERCUMREQ_NUMECERTI=?";
        List<Object[]> certificados = em.createNativeQuery(sql).setParameter(1, vin).setParameter(2, String.valueOf(solicitud)).getResultList();
        if (certificados.isEmpty()) {
            //no se encontro el certificado de cumplimiento de requisitos
            throw new ApplicationException("No se encontró el Certificado de cumplimiento de requisitos asociado a la solicitud");
        } else {
            if ("NO UTILIZADO".compareTo((String) certificados.get(0)[1]) == 0) {
                //ok
                return (BigDecimal) certificados.get(0)[0];
            } else {
                //el certificado se encuentra en otro estado
                throw new ApplicationException("El certificado de cumplimiento de requisitos no se encuentra en estado 'NO UTILIZADO'");
            }
        }
        
    }
    
    public void anularAutorizacion(String vin, Long solicitud, InfoUsuario infoUsuario) {
        //validar que el vehiculo no se haya matriculado
        BigDecimal id=validarAnularAutorizacion(vin, solicitud, infoUsuario);
        
        //anular el certificado
        String sql = "update RUNTPROD.RA_CERCUMREQ \n"
                + "set CERCUMREQ_ESTADOCUM_NOMBRE='ANULADO' ,\n"
                + "    CERCUMREQ_FECHANULA=sysdate,\n"
                + "    CERCUMREQ_USUARIO=?,\n"
                + "    CERCUMREQ_IPANULACI=?\n"
                + "where CERCUMREQ_IDREGDOC=?";

        em.createNativeQuery(sql)
                .setParameter(1, infoUsuario.getUsuario())
                .setParameter(2, infoUsuario.getIp())
                .setParameter(3, id)
                .executeUpdate();
    }

    public void guardarAutorizacion(String vin, Long idSolicitud) {
        Postulacion postulacion = obtener(idSolicitud);
        Integer ccmxVehiculo = Integer.parseInt(parametroSistemaDAO.obtener("NUMERO_CCM_SOLICITUD").getValor());
        //obtener los ccm asignados
        List<CertificadoCancelacionMatricula> certificados = certificadoCancelacionMatriculaDAO.obtenerPorAsignacionYEstado(postulacion.getAsignacionCCM().getId(), EstadoCCM.ASIGNADO);
        //obtener informacion del vehiculo
        List<Object[]> vehiculoList = automotorDAO.consultarVehiculoMatInicial(vin);
        Object[] vehiculo = vehiculoList.get(0);
        String carroceria = null;
        if (!vehiculoList.isEmpty() && vehiculoList.size() > 1) {
            carroceria = (String) vehiculoList.get(1)[2];
        }

        Long idCertRequisitos = ((BigDecimal) em.createNativeQuery("select RUNTPROD.RA_CERCUMREQ_SEQ.NEXTVAL from dual").getSingleResult()).longValue();

        String sql1 = "INSERT INTO RUNTPROD.RA_CERCUMREQ ("
                + "CERCUMREQ_IDREGDOC, CERCUMREQ_NUMECERTI, CERCUMREQ_FECHEXPED, CERCUMREQ_AUTOTRANS_IDAUTTRA, "//4
                + "CERCUMREQ_ESTADOCUM_NOMBRE, CERCUMREQ_TIPCERREP_ID, CERCUMREQ_CAPACARGA, CERCUMREQ_PESO, CERCUMREQ_ESTACERTI_NOMBRE, "//9
                + "CERCUMREQ_TIPOSERVI_IDETIPSER, CERCUMREQ_FICTECHOM_NROFICHA, CERCUMREQ_FICTECHOM_NROFICCAR, CERCUMREQ_CONFIGURA_NOMBRE, "//13
                + "CERCUMREQ_AUTOMOTOR_NROREGVEH, CERCUMREQ_FECHANULA, CERCUMREQ_USUARIO, CERCUMREQ_SRS, CERCUMREQ_USUAREGIS, CERCUMREQ_FECHREGIS, "//19
                + "CERCUMREQ_IPREGISTR, CERCUMREQ_ANUCERCAR_IDENTIFIC, CERCUMREQ_IPANULACI, CERCUMREQ_TIPPROAUT_IDENTIFIC, CERCUMREQ_CLASVEHIC_IDCLASE, "//24
                + "CERCUMREQ_TIPOCARRO_IDCARROCE, CERCUMREQ_NUMEJES, CERCUMREQ_POLICAUCI_IDENTIFIC, CERCUMREQ_TIPAUTAUT_ID, CERCUMREQ_EMAILSOLI)\n"
                +//29
                "VALUES (?,?,?,'11042000',"//4
                + "'NO UTILIZADO','CUMPLIMIENTO REQUISITOS',NULL,?,'ACTIVO',"//9
                + "?,?,?,'3S',"//13
                + "?,NULL,NULL,NULL,NULL,systimestamp,"//19
                + "NULL,NULL,NULL,'9',?,"//24
                + "NULL,'3',NULL,'1',NULL)";//29
        em.createNativeQuery(sql1)
                .setParameter(1, idCertRequisitos)
                .setParameter(2, postulacion.getSolicitud())
                .setParameter(3, postulacion.getFecha())
                .setParameter(4, vehiculo[0])
                .setParameter(5, 2)//se deja el tipo de servicio en p&uacute;blico
                .setParameter(6, vehiculo[2])//fth de chasis
                .setParameter(7, carroceria)//fth de carroceria
                .setParameter(8, vehiculo[3])//
                .setParameter(9, vehiculo[4])//
                .setParameter(10, vehiculo[5])
                .executeUpdate();
        if (postulacion.getLeasing() == null || postulacion.getLeasing() == Boolean.FALSE) {
            String sql2 = "INSERT INTO RUNTPROD.RA_POLCAUPRO ("
                    + "POLCAUPRO_IDENTIFIC, POLCAUPRO_PERSONA_IDPERSONA, "
                    + "POLCAUPRO_CERCUMREQ_IDREGDOC, POLCAUPRO_POLICAUCI_IDENTIFIC)\n"
                    + "VALUES (RUNTPROD.RA_POLCAUPRO_SEQ.NEXTVAL, ?, ?, NULL)";
            em.createNativeQuery(sql2)
                    .setParameter(1, postulacion.getPersona().getId())
                    .setParameter(2, idCertRequisitos)
                    .executeUpdate();
        } else {
            for (Persona p : postulacion.getLocatarios()) {
                String sql2 = "INSERT INTO RUNTPROD.RA_POLCAUPRO ("
                        + "POLCAUPRO_IDENTIFIC, POLCAUPRO_PERSONA_IDPERSONA, "
                        + "POLCAUPRO_CERCUMREQ_IDREGDOC, POLCAUPRO_POLICAUCI_IDENTIFIC)\n"
                        + "VALUES (RUNTPROD.RA_POLCAUPRO_SEQ.NEXTVAL, ?, ?, NULL)";
                em.createNativeQuery(sql2)
                        .setParameter(1, p.getId())
                        .setParameter(2, idCertRequisitos)
                        .executeUpdate();
            }
        }

        String sql3 = "INSERT INTO RUNTPROD.RA_CERCUMVEH (CERCUMVEH_IDENTIFIC, CERCUMVEH_CERCUMREQ_IDREGDOC, CERCUMVEH_AUTOMOTOR_NROREGVEH, CERCUMVEH_NUMPLACA,\n"
                + "CERCUMVEH_CAPCARRNA, CERCUMVEH_CAPCARGMT, CERCUMVEH_PESORNA, CERCUMVEH_PESOMT, CERCUMVEH_CESIDEREC, CERCUMVEH_TRASPASEG) "
                + "VALUES (RUNTPROD.RA_CERCUMVEH_SEQ.NEXTVAL, ?, ?, ?, NULL, NULL, NULL, NULL, 'SI', 'NO')";
        String sql4 = "UPDATE RUNTPROD.RA_AUTOMOTOR SET AUTOMOTOR_ESTAREPOS = 'S' WHERE AUTOMOTOR_NROREGVEH=?";

        for (int i = 0; i < ccmxVehiculo; i++) {
            em.createNativeQuery(sql3)
                    .setParameter(1, idCertRequisitos)
                    .setParameter(2, certificados.get(i).getAutomotor().getId())
                    .setParameter(3, certificados.get(i).getAutomotor().getPlaca())
                    .executeUpdate();

            em.createNativeQuery(sql4)
                    .setParameter(1, certificados.get(i).getAutomotor().getId())
                    .executeUpdate();

            certificados.get(i).setEstado(EstadoCCM.UTILIZADO);
        }

        if (certificados.size() - ccmxVehiculo - ccmxVehiculo < 0) {
            postulacion.setEstado(EstadoPostulacion.UTILIZADO);
        }
    }

    public List<Postulacion> obtenerPostulacionesAprobadasPorFecha(String tipoDocumento, String numeroDocumento, Date fechaInicio, Date fechaFin) {
        return em.createNamedQuery("Postulacion.obtenerPorEstadoFecha")
                .setParameter("tipoDocumento", tipoDocumento)
                .setParameter("numeroDocumento", numeroDocumento)
                .setParameter("finicio", fechaInicio)
                .setParameter("ffin", fechaFin)
                .setParameter("estado", EstadoPostulacion.RECHAZADO)
                .getResultList();
    }

    public List<Postulacion> obtenerPostulacionesActivaPorVin(String vin) {
        return em.createNamedQuery("Postulacion.obtenerVINEstado")
                .setParameter("vin", vin)
                .setParameter("estado", EstadoPostulacion.RECHAZADO)
                .getResultList();
    }

    public void rechazarPolizasNoCargadas() {

    }

    public void actualizarLiquidacion(Long idPostulacion, String idLiquidacion) {
        Postulacion postulacion = obtener(idPostulacion);
        //anular postulacion anterior
        anularLiquidacion(postulacion.getLiquidacion());
        postulacion.setLiquidacion(idLiquidacion);
        actualizar(postulacion);
    }

    public List<Postulacion> obtenerPorFiltro(ConsultaPostulacionDTO dto) {
        return em.createNamedQuery("Postulacion.obtenerPorFiltro")
                .setParameter("estado", dto.getEstadoPostulacion())
                .setParameter("fechaInicio", FechaUtils.convertirFecha(dto.getFechaInicio()))
                .setParameter("fechaFin", FechaUtils.convertirFecha(dto.getFechaFin()))
                .setParameter("numeroDocumento", dto.getNumeroDocumento())
                .setParameter("solicitud", dto.getNumeroSolicitud())
                .setParameter("tipoDocumento", (dto.getTipoDocumento() == null ? null : dto.getTipoDocumento().getId()))
                .setParameter("tipo", dto.getTituloValor()).getResultList();
    }

    public void regenerarVistaGraficos() {
        String sqlDelete = "delete from v_postulacion";
        String sqlInsert = "insert into v_postulacion\n"
                + "select po.POSTULACION_ESTADO, extract(year from po.POSTULACION_FECHA), extract(month from po.postulacion_fecha), au.AUTOMOTOR_MODELO, au.automotor_estavehic_nombre,\n"
                + "MARCA_NOMBRE, TIPOCARRO_NOMBRE\n"
                + "from ru_postulacion po\n"
                + "left join runtprod.ra_automotor au on au.AUTOMOTOR_IDVEUNINT=po.POSTULACION_VIN\n"
                + "left join runtprod.pa_marca marca on marca.MARCA_IDMARCA=au.AUTOMOTOR_MARCA_IDMARCA\n"
                + "left join RUNTPROD.PA_TIPOCARRO carroceria on carroceria.TIPOCARRO_IDCARROCE=au.AUTOMOTOR_TIPOCARRO_IDCARROCE";

        em.createNativeQuery(sqlDelete).executeUpdate();
        em.createNativeQuery(sqlInsert).executeUpdate();
    }

}
