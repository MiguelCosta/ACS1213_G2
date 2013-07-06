/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JorgeMaia
 */
public class DataHoraValidator {

    public boolean verificaDataHora(String dataI, String dataF, String horaI, String horaF, HttpServletRequest request) {

        if (dataI.equals("") || dataF.equals("") || horaF.equals("") || horaI.equals("")) {
            request.setAttribute("MessageError", "Existem campos por preencher!");
            return false;
        }
        if (dataI == null || dataF == null || horaF == null || horaI == null) {
             request.setAttribute("MessageError", "Existem campos por preencher!");
            return false;
        }
        return true;
    }
}
