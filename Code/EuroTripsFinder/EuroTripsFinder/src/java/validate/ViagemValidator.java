/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;

import entity.Viagem;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author JorgeMaia
 */
public class ViagemValidator {

    public Date formataData(String data) throws Exception {
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

    public boolean validaRegisto(String DtInicio, String nome, String DtFim, HttpServletRequest request) {
        boolean errorFlag = true;

        if (DtInicio != null && !DtInicio.isEmpty()) {
            try {

                formataData(DtInicio);
            } catch (Exception e) {
                errorFlag = false;
                request.setAttribute("MessageError", "Data de Fim Inválida");
            }
        }else{
                errorFlag = false;
                request.setAttribute("MessageError", "Data não pode ser nula");
        }
        
        
         if (DtFim != null && !DtFim.isEmpty()) {
            try {

                formataData(DtFim);
            } catch (Exception e) {
                errorFlag = false;
                request.setAttribute("MessageError", "Data Inválida");
            }
        }else{
                errorFlag = false;
                request.setAttribute("MessageError", "Data não pode ser nula");
        }
        
        
        
        
        
     

        if (nome == null || nome.isEmpty()){
            errorFlag = false;
            request.setAttribute("MessageError", "Nome Inválido");
        }

        return errorFlag;
    }
}
