/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;

import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author miltonnunes52
 */
public class CarroValidator {

    /**
     * metodo que valida o formulário Coordenada
     *
     * @param lat latitude
     * @param lon longitude
     * @param nome nome
     * @param request httpRequest
     * @return devolve se os dados do formulário são válidos
     */
    public static boolean validateFormRegister(String desc, String nome, HttpServletRequest request) {

        boolean errorFlag = false;

        if (nome == null
                || nome.equals("")
                || nome.length() < 8) {
            errorFlag = true;
            request.setAttribute("MessageError", "Matrícula inválida!");
        } else if (desc == null
                || desc.equals("")
                || desc.length() < 4) {
            errorFlag = true;
            request.setAttribute("MessageError", "Descrição inválida!");
        }
        

        return errorFlag;
    }
}
