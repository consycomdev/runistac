package co.com.runt.runistac.persistencia;

import co.com.runt.common.excepciones.ApplicationException;
import co.com.runt.runistac.persistencia.entity.*;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.persistence.*;
import javax.ejb.Stateless;

/**
 * @generated
 */
@Stateless
public class AutomotorDAO {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private ParametroSistemaDAO parametroSistemaDAO;

    /**
     * @generated
     */
    public List<Automotor> obtenerTodos() {
        return em.createNamedQuery("Automotor.obtenerTodos").getResultList();
    }

    /**
     * @generated
     */
    public Automotor obtener(Long id) {
        return em.find(Automotor.class, id);
    }

    /**
     * @generated
     */
    public Automotor guardar(Automotor entidad) {
        em.persist(entidad);
        return entidad;
    }

    /**
     * @generated
     */
    public void borrar(Long id) {
        em.remove(em.find(Automotor.class, id));
    }

    /**
     * @generated
     */
    public void actualizar(Automotor entidad) {
        em.merge(entidad);
    }

    public List<Object[]> consultarInformacionPropiedades(Long idPersona) {
        Integer pbv = Integer.parseInt(parametroSistemaDAO.obtener("MINIMO_PESO_BRUTO_VEHICULAR").getValor());
        return em.createNativeQuery("select AUTOMOTOR_PLACA_NUMPLACA, CLASVEHIC_NOMBRE, TIPOCARRO_NOMBRE, AUTOMOTOR_MODELO, TECNAUTOM_NROEJES, TECNAUTOM_PESO, TIPOSERVI_NOMBRE, count(1) propietarios\n"
                + "from runtprod.tr_propietar prop1,runtprod.ra_automotor, RUNTPROD.PA_CLASVEHIC, runtprod.pa_tipocarro, runtprod.RA_TECNAUTOM, runtprod.pa_tiposervi, runtprod.tr_propietar props\n"
                + "where AUTOMOTOR_NROREGVEH=prop1.PROPIETAR_AUTOMOTOR_NOREGVEHI\n"
                + "  and prop1.PROPIETAR_PERSONA_IDPERSONA=?\n"
                + "  and AUTOMOTOR_TIPOSERVI_IDETIPSER=TIPOSERVI_IDETIPSER\n"
                + "  and prop1.propietar_estado_nombre='ACTIVO'\n"
                + "  and props.propietar_estado_nombre='ACTIVO'\n"
                + "  and CLASVEHIC_IDCLASE=AUTOMOTOR_CLASVEHIC_IDCLASE\n"
                + "  and AUTOMOTOR_TIPOCARRO_IDCARROCE=TIPOCARRO_IDCARROCE\n"
                + "  and TECNAUTOM_AUTOMOTOR_NROREGVEH = AUTOMOTOR_NROREGVEH\n"
                + "  and AUTOMOTOR_NROREGVEH=props.PROPIETAR_AUTOMOTOR_NOREGVEHI\n"
                + "  and TECNAUTOM_PESO>=?\n"
                + "  and AUTOMOTOR_NROREGVEH>0\n"
                + "group by AUTOMOTOR_PLACA_NUMPLACA, CLASVEHIC_NOMBRE, TIPOCARRO_NOMBRE, AUTOMOTOR_MODELO, TECNAUTOM_NROEJES, \n"
                + "TECNAUTOM_PESO, TIPOSERVI_NOMBRE")
                .setParameter(1, idPersona)
                .setParameter(2, pbv)
                .getResultList();
    }

    public List<Object[]> consultarVehiculoVIN(String vin) {
        String sql = "select to_char(AUTOMOTOR_NROREGVEH), AUTOMOTOR_NROMOTOR, AUTOMOTOR_NROSERIE, AUTOMOTOR_NROCHASIS,\n"
                + "  MARCA_NOMBRE, LINEA_NOMBRE, CLASVEHIC_NOMBRE,\n"
                + "  FICTECHOM_TIPOHOMOL_TIPOHOMOL, FICHOMVEH_FICTECHOM_NROCERHOM, AUTOMOTOR_IDVEUNINT, \n"
                + "  TECNAUTOM_PESO, TIPOCARRO_NOMBRE, to_char(TECNAUTOM_NROEJES), to_char(automotor_modelo), CONFIGURA_NOMBRE, \n"
                + "  TIPCARCAR_IDENTIFIC, AUTOMOTOR_ESTAVEHIC_NOMBRE, PBVC1.VALITEFIC_VALIRITEM,PBVC2.VALITEFIC_VALIRITEM,PBVC3.VALITEFIC_VALIRITEM \n"
                + "from runtprod.ra_automotor\n"
                + "left join RUNTPROD.PA_MARCA on AUTOMOTOR_MARCA_IDMARCA=MARCA_IDMARCA\n"
                + "left join RUNTPROD.PA_CLASVEHIC on CLASVEHIC_IDCLASE=AUTOMOTOR_CLASVEHIC_IDCLASE\n"
                + "left join runtprod.RA_TECNAUTOM on TECNAUTOM_AUTOMOTOR_NROREGVEH = AUTOMOTOR_NROREGVEH\n"
                + "left join runtprod.pa_tiposervi on AUTOMOTOR_TIPOSERVI_IDETIPSER=TIPOSERVI_IDETIPSER\n"
                + "left join RUNTPROD.PA_TIPOCARRO on AUTOMOTOR_TIPOCARRO_IDCARROCE=TIPOCARRO_IDCARROCE\n"
                + "left join runtprod.pa_tipcarcar on AUTOMOTOR_TIPOCARRO_IDCARROCE=TIPCARCAR_TIPOCARRO_IDCARROCE\n"
                + "left join RUNTPROD.RA_FICHOMVEH on FICHOMVEH_AUTOMOTOR_NROREGVEH=AUTOMOTOR_NROREGVEH \n"
                + "left join RUNTPROD.RH_FICTECHOM on FICHOMVEH_FICTECHOM_NROCERHOM=FICTECHOM_NROFICHA and FICTECHOM_ESTFICTEC_NOMBRE='APROBADO' and FICTECHOM_MODASERVI_IDEMODSER=2\n"
                + "left join RUNTPROD.PA_LINEA on AUTOMOTOR_LINEA_IDLINEA=LINEA_IDLINEA\n"
                + "left join RUNTPROD.PA_CONFIGURA on CONFIGURA_NUMEJES=TECNAUTOM_NROEJES and CONFIGURA_CLASVEHIC_IDCLASE=CLASVEHIC_IDCLASE\n"
                + "left join RUNTPROD.RH_VALITEFIC PBVC1 on PBVC1.VALITEFIC_FICTECHOM_NROFICHA=FICHOMVEH_FICTECHOM_NROCERHOM and PBVC1.VALITEFIC_ITEMFTH_ITEMFTHID=155\n"
                + "left join RUNTPROD.RH_VALITEFIC PBVC2 on PBVC1.VALITEFIC_FICTECHOM_NROFICHA=PBVC2.VALITEFIC_FICTECHOM_NROFICHA and PBVC2.VALITEFIC_ITEMFTH_ITEMFTHID=695\n"
                + "left join RUNTPROD.RH_VALITEFIC PBVC3 on PBVC1.VALITEFIC_FICTECHOM_NROFICHA=PBVC3.VALITEFIC_FICTECHOM_NROFICHA and PBVC3.VALITEFIC_ITEMFTH_ITEMFTHID=696\n"
                + "where AUTOMOTOR_IDVEUNINT=?";
        return em.createNativeQuery(sql)
                .setParameter(1, vin)
                .getResultList();
    }

    public List<Object[]> consultarVehiculoMatInicial(String vin) {
        String sql = "select TECNAUTOM_PESO, TIPOSERVI_IDETIPSER, FICHOMVEH_FICTECHOM_NROCERHOM, AUTOMOTOR_NROREGVEH, "
                + "  CLASVEHIC_IDCLASE, TECNAUTOM_NROEJES, FICTECHOM_TIPOHOMOL_TIPOHOMOL\n"
                + "from runtprod.ra_automotor\n"
                + "left join RUNTPROD.PA_CLASVEHIC on CLASVEHIC_IDCLASE=AUTOMOTOR_CLASVEHIC_IDCLASE\n"
                + "left join runtprod.RA_TECNAUTOM on TECNAUTOM_AUTOMOTOR_NROREGVEH = AUTOMOTOR_NROREGVEH\n"
                + "left join runtprod.pa_tiposervi on AUTOMOTOR_TIPOSERVI_IDETIPSER=TIPOSERVI_IDETIPSER\n"
                + "left join RUNTPROD.RA_FICHOMVEH on FICHOMVEH_AUTOMOTOR_NROREGVEH=AUTOMOTOR_NROREGVEH\n"
                + "left join RUNTPROD.RH_FICTECHOM on FICHOMVEH_FICTECHOM_NROCERHOM=FICTECHOM_NROFICHA\n"
                + "where AUTOMOTOR_IDVEUNINT=?\n"
                + "order by FICTECHOM_TIPOHOMOL_TIPOHOMOL";
        return em.createNativeQuery(sql)
                .setParameter(1, vin)
                .getResultList();

    }

    public Object[] consultarFichaTecnica(String fth) {
        String sql = "select FICTECHOM_ESTFICTEC_NOMBRE, FICTECHOM_FICTECHOM_NROFICHA, FICTECHOM_TIPOCARRO_IDCARROCE, \n"//3
                + "  TIPOCARRO_IDCARROCE, TIPOCARRO_NOMBRE, TIPOCARRO_ESTADO, \n"//6
                + "  FICTECHOM_TIPOHOMOL_TIPOHOMOL, TIPCARCAR_IDENTIFIC, \n"//8
                + "  FICTECHOM_CLASVEHIC_IDCLASE\n"//9
                + "from RUNTPROD.RH_FICTECHOM\n"
                + "left join runtprod.pa_tipcarcar on FICTECHOM_TIPOCARRO_IDCARROCE=TIPCARCAR_TIPOCARRO_IDCARROCE\n"
                + "left join RUNTPROD.PA_TIPOCARRO on FICTECHOM_TIPOCARRO_IDCARROCE=TIPOCARRO_IDCARROCE\n"
                + "where FICTECHOM_NROFICHA=?";
        List<Object[]> lista = em.createNativeQuery(sql)
                .setParameter(1, fth)
                .getResultList();
        if (lista.isEmpty()) {
            throw new ApplicationException("No se encontro la Ficha T\u00E9cnica de Homologaci\u00F3n con la informaci\u00F3n suministrada");
        }
        return lista.get(0);
    }

    public void registrarFTH(String vin, String fthCarroceria) {
        //insertar ficha
        String sql = "insert into RUNTPROD.RA_FICHOMVEH values(runtprod.RA_FICHOMVEH_seq.nextval, ?, (select automotor_nroregveh from runtprod.ra_automotor where AUTOMOTOR_IDVEUNINT=?), sysdate, null)";
        em.createNativeQuery(sql)
                .setParameter(1, fthCarroceria)
                .setParameter(2, vin)
                .executeUpdate();

        //actualizar carroceria
        String sql1 = "update RUNTPROD.RA_AUTOMOTOR \n"
                + "set AUTOMOTOR_TIPOCARRO_IDCARROCE=(select FICTECHOM_TIPOCARRO_IDCARROCE from RUNTPROD.RH_FICTECHOM where FICTECHOM_NROFICHA=?)\n"
                + "where AUTOMOTOR_IDVEUNINT=?";
        em.createNativeQuery(sql1)
                .setParameter(1, fthCarroceria)
                .setParameter(2, vin)
                .executeUpdate();
    }
}
