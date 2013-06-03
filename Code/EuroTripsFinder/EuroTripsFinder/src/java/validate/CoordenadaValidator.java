/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;

import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import session.CoordenadaFacade;

/**
 *
 * @author JorgeMaia
 */
public class CoordenadaValidator {

    /**
     * metodo que valida o formulário Coordenada
     *
     * @param lat latitude
     * @param lon longitude
     * @param nome nome
     * @param request httpRequest
     * @return devolve se os dados do formulário são válidos
     */
    public static boolean validateFormRegister(String lat, String lon, String nome, HttpServletRequest request) {

        boolean errorFlag = false;

        if (lat == null || lat.equals("")) {
            errorFlag = true;
            request.setAttribute("MessageError", "A coordenada da latitude não é válida!");
        } else if (lon == null || lon.equals("")) {
            errorFlag = true;
            request.setAttribute("MessageError", "A coordenada da longitude inserida não é válida!");
        } else if (nome == null || nome.equals("")) {
            errorFlag = true;
            request.setAttribute("MessageError", "O Nome não é válido!");
        }
        try {
            BigDecimal latitude = new BigDecimal(lat);
            BigDecimal longitude = new BigDecimal(lon);
        } catch (Exception e) {
            errorFlag = true;
            request.setAttribute("MessageError", "As coordenadas não são válidas!");
        }

        return errorFlag;
    }
}
