/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Miguel
 */
public class PercursoValidator {
    
    public static boolean validateFormRegister(String nome,
            String datainicio,
            String datafim,
            HttpServletRequest request) {

        boolean errorFlag = true;
        Date dataInic = new Date(0, 0, 0);
        Date dataFim = new Date(0, 0, 0);
        
        if(nome.equals("")){
            errorFlag = false;
            request.setAttribute("MessageError", "Nome inválido.");
        }
        
        if(datainicio.equals("")){
            errorFlag = false;
            request.setAttribute("MessageError", "Data inicio inválida.");
        }
        
        if(datafim.equals("")){
            errorFlag = false;
            request.setAttribute("MessageError", "Data fim inválida.");
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
    
    public static Date formataData(String data) throws Exception {
        if (data == null || data.equals("")) {
            return null;
        }

        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = (java.util.Date) formatter.parse(data);
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }
    
}
