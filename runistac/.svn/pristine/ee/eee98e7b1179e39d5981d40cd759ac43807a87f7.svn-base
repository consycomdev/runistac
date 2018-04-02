/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.runt.runistac.logica;

import co.com.runt.runistac.dto.ConsultaPostulacionDTO;
import co.com.runt.runistac.persistencia.ReporteDAO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author danie
 */
@Stateless
public class ReporteLogica {

    @EJB
    private ReporteDAO reporteDAO;
    
    public byte[] consultarReporte(ConsultaPostulacionDTO reporte) {
        try {
            InputStream bais = ReporteLogica.class.getResourceAsStream("SolicitudRunistac.xlsx");
            
//                reporte.setFechaFin(FechaUtil.fechaToDMY(new Date()));                
            List<Object[]> info = reporteDAO.obtenerSolicitudes(reporte);
            Map<String, String> parametros = new HashMap<>();
            parametros.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            byte[] data = generar(bais, parametros, info);
            return data;
        } catch (Exception ex) {
            Logger.getLogger(ReporteLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static byte[] generar(InputStream plantilla, Map<String, String> parametros, List<Object[]> datos) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook(plantilla);
        XSSFSheet mySheet = wb.getSheetAt(0);
        for (int i = 0; i < mySheet.getLastRowNum(); i++) {
            Row row = mySheet.getRow(i);
            if (row != null && row.getCell(0) != null && row.getCell(0).getCellTypeEnum() == CellType.STRING) {
                for(String key:parametros.keySet()){
                    String valor = row.getCell(0).getStringCellValue();
                    valor = valor.replaceAll("\\{" + key + "\\}", parametros.get(key));
                    row.getCell(0).setCellValue(valor);
                }
            }
        }

        int rows = mySheet.getLastRowNum();
        int i = 0;
        Row base = mySheet.getRow(rows);
        CellStyle[] cs = null;
        if (!datos.isEmpty()) {
            int cant = datos.get(0).length;
            cs = new CellStyle[cant];
            for (int j = 0; j < cant; j++) {
                cs[j] = base.getCell(j).getCellStyle();
            }
        }
        for (Object[] o : datos) {
            Row row = mySheet.createRow(rows + i);
            for (int j = 0; j < o.length; j++) {
                Cell c = row.createCell(j);
                String value = "";
                if (o[j] != null) {
                    if (o[j] instanceof String) {
                        value = (String) o[j];
                        c.setCellValue(value);
                    } else if (o[j] instanceof Integer) {//integer
                        c.setCellValue((Integer) o[j]);
                    } else if (o[j] instanceof Double) {
                        c.setCellValue((Double) o[j]);
                    } else if (o[j] instanceof Float) {
                        c.setCellValue((Float) o[j]);
                    } else if (o[j] instanceof BigDecimal) {
                        c.setCellValue(((BigDecimal) o[j]).doubleValue());
                    } else if (o[j] instanceof Date) {
                        c.setCellValue(((Date) o[j]));
                    } else if (o[j] instanceof BigInteger) {
                        c.setCellValue(((BigInteger) o[j]).intValue());
                    } else {
                        c.setCellValue(o[j].toString());
                        System.out.println("No se encontro tipo: " + j + "-" + o[j].getClass());
                    }
                }
                c.setCellStyle(cs[j]);
            }
            i++;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        wb.write(baos);

        return baos.toByteArray();
    }
}
