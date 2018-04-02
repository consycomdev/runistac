/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.runt.runistac.logica;

import co.com.runt.clienteenviocorreo.ClienteEnvioCorreo;
import co.com.runt.clienteenviocorreo.dto.ArchivoDTO;
import co.com.runt.clienteenviocorreo.dto.EnvioCorreoDTO;
import co.com.runt.clienteenviocorreo.utils.TipoCorreo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Usuario
 */
@Stateless
public class EnvioCorreoLogica {

    private static final Logger LOG=Logger.getLogger(EnvioCorreoLogica.class.getName());
    
    @EJB
    private ParametroSistemaLogica parametroSistemaLogica;
    
    public void enviarCorreo(String destinatario, String contenido) {
        try {

            EnvioCorreoDTO envioCorreoDTO = new EnvioCorreoDTO();
            List<String> listaDestinatarios = new ArrayList<>();
            listaDestinatarios.add(destinatario);
            envioCorreoDTO.setPara(listaDestinatarios);
            envioCorreoDTO.setAsunto("Tr\u00e1mite RUNISTAC");
            envioCorreoDTO.setContenido(contenido);
            envioCorreoDTO.setAdjuntos(new ArrayList<ArchivoDTO>());

            //Envio de correo por la cuenta de Historico vehicular
            envioCorreoDTO.setTipoCorreo(TipoCorreo.GENERAL);
            //Se envia el correo electronico
            ClienteEnvioCorreo clienteEnvioCorreo = new ClienteEnvioCorreo(parametroSistemaLogica.obtener("URL_SERVICIO_CORREO").getValor());
            clienteEnvioCorreo.enviarCorreo(envioCorreoDTO);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al enviar el correo", e);
        }
    }
    
    public static String contenidoEmailHtmlRunt(final String titulo, final String destinatario, final String contenido) {
        //Preproduccion
//        String imagenHeader = "http://190.254.17.42/runt/apppub/PortalCiudadano/img/header_mail_runt.jpg";
        //Produccion
        String imagenHeader = "http://www.runt.com.co/runt/apppub/PortalCiudadano/img/header_mail_runt.jpg";

        String cont = "<tbody>\n"
                + "    <tr>\n"
                + "        <td style=\"background-color:#FFFFFF; width:100%\" align=\"center\" valign=\"top\">\n"
                + "            <table class=\"innerContainer\" style=\"margin:0; padding:0; width:600px!important; background-color:#FFFFFF\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n"
                + "                <tbody>\n"
                + "                    <tr>\n"
                + "                        <td align=\"left\">\n"
                + "                            <table class=\"bodyContainer\" style=\"margin:0; padding:0; width:600px!important\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n"
                + "                                <tbody>\n"
                + "                                    <tr>\n"
                + "                                        <td>\n"
                + "                                            <table class=\"tobBlock\" style=\"background-color:#FFFFFF\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                                <tbody>\n"
                + "                                                    <tr>\n"
                + "                                                        <td class=\"\" style=\"padding:10px 20px; font-family:Arial,Helvetica,sans-serif\" valign=\"top\">\n"
                + "                                                            <div class=\"tobEditableHtml\" style=\"min-height:100px\">\n"
                + "                                                                <div style=\"color:#333333; font-size:13px; text-align:center\"><img src=\"" + imagenHeader + "\" title=\"\" alt=\"\" style=\"line-height: normal; width: 100%; float: none; margin: 0px; display: inline; height: 135px; -moz-user-select: none;\" height=\"135\" border=\"0\" width=\"560\"></div>\n"
                + "                                                                <div style=\"color:#333333; text-align:center\"><b>" + titulo + "</b></div>\n"
                + "                                                                <div style=\"color:#333333; text-align:center\"><b><br>\n"
                + "                                                                    </b></div>\n"
                + "                                                                <div><b><font color=\"#333333\">Señor : " + destinatario + "</font></b></div>\n"
                + "                                                                <div style=\"color:#333333; font-size:13px; text-align:center\">\n"
                + "                                                                    <b style=\"line-height:normal\"><br></b>\n"
                + "                                                                </div>\n"
                + "                                                                <div style=\"color:#333333; text-align:center; font-size:13px; text-align:justify; padding:0px; margin:0.3em 0px 0.8em; font-family:Arial,Helvetica,sans-serif; line-height:normal\">\n"
                + "                                                                    <p class=\"MsoNormal\" style=\"\">\n"
                + "                                                                        <span style=\"font-size:10.5pt; line-height:107%; font-family:\">\n"
                + "                                                                            \n"
                + "                                                                            \n"
                + "                                                                            " + contenido + ""
                + "                                                                        \n"
                + "                                                                        </span>\n"
                + "                                                                    </p>\n"
                + "                                                                    <p>\n"
                + "                                                                        <span style=\"font-size:10.5pt; line-height:107%; font-family:\">\n"
                + "                                                                          <br>  Reciba un cordial saludo.\n"
                + "                                                                        </span>\n"
                + "                                                                    </p>\n"
                + "                                                                                                                   \n"
                + "                                                                    <!--fin contenido correo-->\n"
                + "                                                                    <p class=\"MsoNormal\" style=\"font-size:13px; text-align:justify; padding:0px; margin:0.3em 0px 0.8em; font-family:Arial,Helvetica,sans-serif; line-height:normal\">\n"
                + "                                                                        <span style=\"font-size:10.5pt; line-height:107%; font-family:\"><br>\n"
                + "                                                                        </span>\n"
                + "                                                                    </p>\n"
                + "                                                                    <p class=\"MsoNormal\" style=\"font-size:13px; text-align:justify; padding:0px; margin:0.3em 0px 0.8em; font-family:Arial,Helvetica,sans-serif; line-height:normal\">\n"
                + "                                                                        <span style=\"font-size:10.5pt; line-height:107%; font-family:\"><br>\n"
                + "                                                                        </span>\n"
                + "                                                                    </p>\n"
                + "\n"
                + "                                                                    <p class=\"MsoNormal\" style=\"text-align:center; padding:0px; margin:0.3em 0px 0.8em; font-family:Arial,Helvetica,sans-serif; line-height:normal\">\n"
                + "                                                                        <span style=\"line-height:107%\"><i><font size=\"2\">Para cualquier información adicional favor comunicarse con nuestro centro de ayuda y servicios de apoyo línea nacional 01 8000 93 00 60 o en Bogotá al 4232221</font></i></span></p>\n"
                + "                                                                    <p class=\"MsoNormal\" style=\"font-size:13px; text-align:justify; padding:0px; margin:0.3em 0px 0.8em; font-family:Arial,Helvetica,sans-serif; line-height:normal\">\n"
                + "                                                                        <span style=\"font-size:10.5pt; line-height:107%; font-family:\"></span></p>\n"
                + "                                                                    <p class=\"MsoListParagraphCxSpLast\" style=\"font-size:13px; text-align:justify; text-indent:-18pt; padding:0px; margin:0.3em 0px 0.8em; font-family:Arial,Helvetica,sans-serif; line-height:normal\">\n"
                + "                                                                        <img src=\"http://81494.track.tstes.net/web_public_shared/image/81494/imagen2.jpg\" title=\"\" alt=\"\" style=\"width: 100%; float: none; margin: 0px auto; display: block; height: 107px; -moz-user-select: none;\" height=\"107\" border=\"0\" width=\"560\"></p>\n"
                + "                                                                </div>\n"
                + "                                                            </div>\n"
                + "                                                        </td>\n"
                + "                                                    </tr>\n"
                + "                                                </tbody>\n"
                + "                                            </table>\n"
                + "                                        </td>\n"
                + "                                    </tr>\n"
                + "                                </tbody>\n"
                + "                            </table>\n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                </tbody>\n"
                + "            </table>\n"
                + "        </td>\n"
                + "    </tr>\n"
                + "</tbody>";
        return cont;
    }

}
