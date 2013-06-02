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
public class ContratoValidator {
    public static boolean validateFormRegister(String valor,
            String datainicio,
            String datafim,
            HttpServletRequest request) {

        boolean errorFlag = true;
        Date dataInic = new Date(0, 0, 0);
        Date dataFim = new Date(0, 0, 0);

        if (valor == null
                || valor.equals("")
                || numeric(valor)) {
            errorFlag = false;
            request.setAttribute("MessageError", "valor inválido.");
        }

        try {
            dataInic = formataData(datainicio);
        } catch (Exception e) {
            errorFlag = false;
            request.setAttribute("MessageError", "Data de inicio inválida");
        }
        
        try {
            dataFim = formataData(datafim);
        } catch (Exception e) {
            errorFlag = false;
            request.setAttribute("MessageError", "Data de fim inválida");
        }

        return errorFlag;

    }
    
    
    
    public static boolean numeric(String t){
        int count = 0;
        for (int i=0;i<t.length();i++){
            if (!Character.isDigit(t.charAt(i))){
                if(t.charAt(i) == '.' && i != 0 && i < t.length()-1 && count == 0){
                    count++;
                } 
                else{
                    return true;
                }
            }    
        }
        return false;   
    }
    
    
}
