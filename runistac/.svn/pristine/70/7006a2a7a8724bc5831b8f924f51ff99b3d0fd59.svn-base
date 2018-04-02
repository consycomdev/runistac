/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.runt.runistac.persistencia;

import co.com.runt.runistac.dto.ConsultaPostulacionDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Stateless
public class ReporteDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Object[]> obtenerSolicitudes(ConsultaPostulacionDTO dto) {
        String sql = "select POSTULACION_SOLICITUD, POSTULACION_FECHA, TIPOIDENT_IDTIPIDE||' '||PERSONA_NRODOCUME, \n"
                + "case when PERSNATUR_PERSONA_IDPERSONA is not null then PERSNATUR_NOMBRE1||' '||PERSNATUR_NOMBRE2||' '||PERSNATUR_APELLIDO1||' '||PERSNATUR_APELLIDO2 else EMPRESA_RAZOSOCIA end as nombre,\n"
                + "POSTULACION_ESTADO, ASIGNACIONCCM_FECHA, listagg(CCM_NUMEROCERTIFICADO,',') within group( order by CCM_NUMEROCERTIFICADO )  ccm, POSTULACION_VIN\n"
                + "from ru_postulacion\n"
                + "left join runtprod.tr_persona on POSTULACION_PERSONA_IDPERSONA=PERSONA_IDPERSONA\n"
                + "left join RUNTPROD.PA_TIPOIDENT on PERSONA_TIPOIDENT_IDTIPDOC=TIPOIDENT_IDTIPIDE\n"
                + "left join RUNTPROD.TR_PERSNATUR on PERSONA_IDPERSONA=PERSNATUR_PERSONA_IDPERSONA\n"
                + "left join RUNTPROD.GE_EMPRESA on PERSONA_IDPERSONA=EMPRESA_PERSONA_IDPERSONA\n"
                + "left join ru_asignacionccm on asignacionccm_id=POSTULACION_ASIGNACION\n"
                + "left join ru_ccm on CCM_ASIGNACIONCCM_ID=POSTULACION_ASIGNACION \n"
                + "left join ru_polizacaucion on POLIZACAUCION_POSTULACION=POSTULACION_ID and (POLIZACAUCION_TIPO=? or ? is null)\n"
                + "where 1=1\n"
                + "  and (POSTULACION_SOLICITUD=? or ? is null)\n"
                + "  and (TIPOIDENT_IDTIPIDE=? or ? is null)\n"
                + "  and (PERSONA_NRODOCUME=? or ? is null)\n"
                + "  and (POSTULACION_FECHA>? or ? is null)\n"
                + "  and (POSTULACION_FECHA<? or ? is null)\n"
                + "  and (POSTULACION_ESTADO=? or ? is null)\n"
                + "group by POSTULACION_SOLICITUD, POSTULACION_FECHA, TIPOIDENT_IDTIPIDE||' '||PERSONA_NRODOCUME, \n"
                + "case when PERSNATUR_PERSONA_IDPERSONA is not null then PERSNATUR_NOMBRE1||' '||PERSNATUR_NOMBRE2||' '||PERSNATUR_APELLIDO1||' '||PERSNATUR_APELLIDO2 else EMPRESA_RAZOSOCIA end,\n"
                + "POSTULACION_ESTADO, ASIGNACIONCCM_FECHA, POSTULACION_VIN";

        return em.createNativeQuery(sql)
                .setParameter(1, dto.getTituloValor())
                .setParameter(2, dto.getTituloValor())
                .setParameter(3, dto.getNumeroSolicitud())
                .setParameter(4, dto.getNumeroSolicitud())
                .setParameter(5, (dto.getTipoDocumento()!=null?dto.getTipoDocumento().getId():null))
                .setParameter(6, (dto.getTipoDocumento()!=null?dto.getTipoDocumento().getId():null))
                .setParameter(7, dto.getNumeroDocumento())
                .setParameter(8, dto.getNumeroDocumento())
                .setParameter(9, dto.getFechaInicio())
                .setParameter(10, dto.getFechaInicio())
                .setParameter(11, dto.getFechaFin())
                .setParameter(12, dto.getFechaFin())
                .setParameter(13, dto.getEstadoPostulacion())
                .setParameter(14, dto.getEstadoPostulacion())
                .getResultList();
    }
}
