/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;


import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miltonnunes52
 */
public class ArtigoValidator {
    
    public static boolean validateFormRegister(String nome,
            String conteudo,
            HttpServletRequest request) {

        boolean errorFlag = true;

        if (nome == null
                || nome.equals("")
                || nome.length() < 5) {
            errorFlag = false;
            request.setAttribute("MessageError", "Nome inválido.");
        } else if (conteudo == null
                || conteudo.equals("")
                || conteudo.length() < 9) {
            errorFlag = false;
            request.setAttribute("MessageError", "Conteudo inválido");
        } 

        return errorFlag;

    }
}
