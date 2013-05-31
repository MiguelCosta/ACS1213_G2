/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;

import static controller.UtilizadorServlet.formataData;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miltonnunes52
 */
public class TipoMeioTransporteValidator {
    
    public static boolean validateFormRegister(String nome, HttpServletRequest request){
        boolean errorFlag = true;
        if (nome == null
                || nome.equals("")
                || nome.length() < 4) {
            errorFlag = false;
            request.setAttribute("MessageError", "Nome inválido");
        }
        
        return errorFlag;
        
    }
    
}
