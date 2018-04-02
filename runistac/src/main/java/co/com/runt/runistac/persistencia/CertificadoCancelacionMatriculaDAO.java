package co.com.runt.runistac.persistencia;

import co.com.runt.common.excepciones.ApplicationException;
import co.com.runt.runistac.dto.ConsultaCCMDTO;
import co.com.runt.runistac.logica.Constantes;
import co.com.runt.runistac.persistencia.entity.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.*;
import javax.ejb.Stateless;

/**
 * @generated
 */
@Stateless
public class CertificadoCancelacionMatriculaDAO {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private ParametroSistemaDAO parametroSistemaDAO;

    @EJB
    private ConsecutivoSolicitudDAO consecutivoSolicitudDAO;

    /**
     * @generated
     */
    public List<CertificadoCancelacionMatricula> obtenerTodos() {
        return em.createNamedQuery("CertificadoCancelacionMatricula.obtenerTodos").getResultList();
    }

    /**
     * @generated
     */
    public CertificadoCancelacionMatricula obtener(Long id) {
        return em.find(CertificadoCancelacionMatricula.class, id);
    }

    /**
     * obtiene la cantidad de certificados disponibles
     *
     * @return
     */
    public Integer obtenerDisponibles() {
        Long cantidad = (Long) em.createNamedQuery("CertificadoCancelacionMatricula.obtenerCantidadDisponibles")
                .setParameter("estado", EstadoCCM.NUEVO)
                .getSingleResult();
        return cantidad.intValue();
    }

    /**
     * @generated
     */
    public CertificadoCancelacionMatricula guardar(CertificadoCancelacionMatricula entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(CertificadoCancelacionMatricula.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(CertificadoCancelacionMatricula entidad) {
        em.merge(entidad);
    }

    /**
     * obtiene la lista de certificados de cancelacion de matricula en estado
     * Generado de acuerdo a la cantidad solicitada
     *
     * @param cantidadSolicitada cantidad solicitada
     * @return lista de certificados de cancelacion de matricula
     */
    public List<CertificadoCancelacionMatricula> obtenerPorCantidad(int cantidadSolicitada) {
        return em.createNamedQuery("CertificadoCancelacionMatricula.obtenerTodosGenerado")
                .setMaxResults(cantidadSolicitada)
                .setParameter("estado", EstadoCCM.NUEVO)
                .getResultList();
    }

    public List<CertificadoCancelacionMatricula> consultar(ConsultaCCMDTO dto) {
        try {
            String motivo = null;
            if (dto.getMotivo() != null) {
                motivo = (String) em.createNativeQuery("select motivo_descripci from RUNTPROD.PA_MOTIVO where motivo_nombre=?")
                        .setParameter(1, dto.getMotivo()).getSingleResult();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return em.createNamedQuery("CertificadoCancelacionMatricula.obtenerTodosFiltro")
                    .setParameter("fechaInicio", (dto.getFechaInicio() == null ? null : sdf.parse(dto.getFechaInicio())))
                    .setParameter("fechaFin", (dto.getFechaFin() == null ? null : sdf.parse(dto.getFechaFin())))
                    .setParameter("motivo", motivo)
                    .setParameter("estado", dto.getEstado())
                    .setParameter("placa", dto.getPlaca())
                    .setParameter("numeroCertificado", (dto.getNumeroCertificado() == null ? null : Long.parseLong(dto.getNumeroCertificado())))
                    .getResultList();
        } catch (ParseException ex) {
            Logger.getLogger(CertificadoCancelacionMatriculaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new ApplicationException("Error al convertir la fecha");
        }
    }

    public int generarCCM() {
        try {
            String sql = "insert into RU_CCM(CCM_ID, CCM_ESTADO, CCM_FECHA, FECHA_CREACION, CCM_MOTIVO, CCM_AUTOMOTOR_ID, CCM_NUMEROCERTIFICADO) \n"
                    + "select CCM_SEQ.nextval, 'NUEVO', AUTOMOTOR_FECHCANCE, SYSTIMESTAMP, POSTULACI_TIPOPOSTU_NOMBRE, AUTOMOTOR_NROREGVEH, null from(\n"
                    + "select 'NUEVO', AU.AUTOMOTOR_FECHCANCE, SYSTIMESTAMP, POS.POSTULACI_TIPOPOSTU_NOMBRE, AU.AUTOMOTOR_NROREGVEH\n"
                    + "FROM RUNTPROD.RA_AUTOMOTOR AU\n"
                    + "INNER JOIN RUNTPROD.GE_POSTULACI POS ON AU.AUTOMOTOR_NROREGVEH = POS.POSTULACI_AUTOMOTOR_NROREGVEH\n"
                    + "INNER JOIN RUNTPROD.RA_TECNAUTOM TEC ON TEC.TECNAUTOM_AUTOMOTOR_NROREGVEH = AU.AUTOMOTOR_NROREGVEH\n"
                    + "WHERE POS.POSTULACI_TIPOPOSTU_NOMBRE = 'Desintegración física total con fines de reconocimiento económico'\n"
                    + "  AND POS.POSTULACI_ESTAPOSTU_NOMBRE in ('ACEPTADA','INCENTIVO RECONOCIDO', 'INCENTIVO PAGADO')\n"
                    + "  AND AU.AUTOMOTOR_ESTAVEHIC_NOMBRE = 'CANCELADO'\n"
                    + "  AND AU.AUTOMOTOR_FECHCANCE> ?\n"
                    + "  AND TECNAUTOM_PESO >= ?\n"
                    + "  AND AU.AUTOMOTOR_NROREGVEH not in (select CCM_AUTOMOTOR_ID from RU_CCM)\n"
                    + "order by AU.AUTOMOTOR_FECHCANCE desc) reg";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            em.createNativeQuery(sql)
                    .setParameter(2, Long.parseLong(parametroSistemaDAO.obtener(Constantes.ParametroSistema.MINIMO_PESO_BRUTO_VEHICULAR).getValor()))
                    .setParameter(1, sdf.parse(parametroSistemaDAO.obtener(Constantes.ParametroSistema.FECHA_GENERACION_CCM).getValor()))
                    .executeUpdate();
            //actualizar el numero de certificado
            List<CertificadoCancelacionMatricula> certificados = em.createNamedQuery("CertificadoCancelacionMatricula.obtenerSinNumero").getResultList();
            ConsecutivoSolicitud consecutivo = consecutivoSolicitudDAO.obtener(Constantes.ConsecutivoSolicitud.CCM);
            Long i = consecutivo.getValor();
            int conteo = 0;
            for (CertificadoCancelacionMatricula certificado : certificados) {
                certificado.setNumeroCertificado(i);
                i++;
                conteo++;
            }
            consecutivo.setValor(i);
            return conteo;
        } catch (ParseException ex) {
            Logger.getLogger(CertificadoCancelacionMatriculaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new ApplicationException("Error al parsear fechas, " + ex.getMessage());
        }
    }

    public List<CertificadoCancelacionMatricula> obtenerPorAsignacion(Long idAsignacion) {
        return em.createNamedQuery("CertificadoCancelacionMatricula.obtenerAsignados")
                .setParameter("idAsignacion", idAsignacion)
                .getResultList();
    }

    public List<CertificadoCancelacionMatricula> obtenerPorAsignacionYEstado(Long idAsignacion, EstadoCCM estado) {
        return em.createNamedQuery("CertificadoCancelacionMatricula.obtenerAsignadosyEstado")
                .setParameter("idAsignacion", idAsignacion)
                .setParameter("estado", estado)
                .getResultList();
    }

    public List<Object[]> consultarCCMPorEstado() {
        return em.createNativeQuery("select to_char(sysdate,'DD/MM/YYYY'), \n"
                + "  case CCM_ESTADO \n"
                + "    when 'PAGADO' then 'USADO' \n"
                + "    when 'RESERVADO' then \n"
                + "        CASE  POSTULACION_ESTADO \n"
                + "             when 'REGISTRO_POLIZA' then 'CON PÓLIZA' \n"
                + "             when 'AUTORIZADO' then 'CON PÓLIZA' \n"
                + "             else 'USADO' end \n"
                + "    when 'ASIGNADO' then \n"
                + "        CASE  POSTULACION_ESTADO \n"
                + "             when 'REGISTRO_POLIZA' then 'CON PÓLIZA' \n"
                + "             when 'AUTORIZADO' then 'CON PÓLIZA' \n"
                + "             else 'USADO' end \n"
                + "    when 'UTILIZADO' THEN 'EN CERTIFICADO CUMPLIMIENTO' \n"
                + "    else CCM_ESTADO end as estado, \n"
                + "    count(1)\n"
                + "from ru_ccm\n"
                + "left join RU_POSTULACION on CCM_ASIGNACIONCCM_ID=POSTULACION_ASIGNACION\n"
                + "group by to_char(sysdate,'DD/MM/YYYY'), \n"
                + "  case CCM_ESTADO \n"
                + "    when 'PAGADO' then 'USADO' \n"
                + "    when 'RESERVADO' then \n"
                + "        CASE  POSTULACION_ESTADO \n"
                + "             when 'REGISTRO_POLIZA' then 'CON PÓLIZA' \n"
                + "             when 'AUTORIZADO' then 'CON PÓLIZA' \n"
                + "             else 'USADO' end \n"
                + "    when 'ASIGNADO' then \n"
                + "        CASE  POSTULACION_ESTADO \n"
                + "             when 'REGISTRO_POLIZA' then 'CON PÓLIZA' \n"
                + "             when 'AUTORIZADO' then 'CON PÓLIZA' \n"
                + "             else 'USADO' end \n"
                + "    when 'UTILIZADO' THEN 'EN CERTIFICADO CUMPLIMIENTO' \n"
                + "    else CCM_ESTADO end \n"
                + "ORDER BY ESTADO")
                .getResultList();
    }

    public void regenerarVistaGraficos() {
        String sqlDelete = "delete from v_ccm";
        String sqlInsert = "insert into v_ccm\n"
                + "select ccm_estado, extract(year from ccm_fecha), extract(month from ccm_fecha) from ru_ccm";

        em.createNativeQuery(sqlDelete).executeUpdate();
        em.createNativeQuery(sqlInsert).executeUpdate();
    }

}
