/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;

import static controller.UtilizadorServlet.formataData;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miltonnunes52
 */
public class ClienteValidator {
    
    public static boolean validateFormRegister(String email,
            String username,
            String password,
            String nome,
            String morada,
            String datanascimento,
            String nif,
            String contacto,
            HttpServletRequest request) {

        boolean errorFlag = true;
        Date dataNasc = new Date(0, 0, 0);

        if (email == null
                || email.equals("")
                || !email.contains("@")) {
            errorFlag = false;
            request.setAttribute("MessageError", "Email inválido.");
        } else if (username == null
                || username.equals("")
                || username.length() < 5) {
            errorFlag = false;
            request.setAttribute("MessageError", "Username inválido");
        } else if (password == null
                || password.equals("")
                || password.length() < 4) {
            errorFlag = false;
            request.setAttribute("MessageError", "Password inválida");
        } else if (nome == null
                || nome.equals("")
                || nome.length() < 4) {
            errorFlag = false;
            request.setAttribute("MessageError", "Nome inválido");
        } else if (nif == null
                || nif.equals("")
                || nif.length() != 9
                || numeric(nif)) {
            errorFlag = false;
            request.setAttribute("MessageError", "NIF inválido");
        } else if (contacto == null
                || contacto.equals("")
                || contacto.length() != 9
                || numeric(contacto)){
            errorFlag = false;
            request.setAttribute("MessageError", "Contacto inválido");
        }
         if(datanascimento.equals("")){
            errorFlag = false;
            request.setAttribute("MessageError", "Data fim inválida.");
        }

        try {
            dataNasc = formataData(datanascimento);
        } catch (Exception e) {
            errorFlag = false;
            request.setAttribute("MessageError", "Data de nascimento inválida");
        }

        return errorFlag;

    }
    
    public static boolean numeric(String t){
        for (int i=0;i<t.length();i++){
            if (!Character.isDigit(t.charAt(i))){
                return true;
            }
        }
        return false;   
    }
    
}
