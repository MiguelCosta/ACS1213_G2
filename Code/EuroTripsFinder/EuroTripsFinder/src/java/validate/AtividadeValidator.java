/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;


import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author JorgeMaia
 */
public class AtividadeValidator {
    
    public static boolean validateFormRegister(String nome, String c, HttpServletRequest request) {

        boolean errorFlag = true;

        if ( c == null || c.equals("")) {
            errorFlag = false;
            request.setAttribute("MessageError", "A descricao não pode ser vazia!");
        }  else if (nome == null || nome.equals("")) {
            errorFlag = false;
            request.setAttribute("MessageError", "Tem de estar um nome Associado!");
        }      

        return errorFlag;
    }
    
}
