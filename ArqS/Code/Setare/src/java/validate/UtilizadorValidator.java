/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html
 */
package validate;

import static controller.UtilizadorServlet.formataData;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author tgiunipero
 */
public class UtilizadorValidator {

    // performs simple validation on checkout form
    public static boolean validateFormRegister(String email,
            String username,
            String password,
            HttpServletRequest request) {

        boolean errorFlag = true;
       
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
        } 
        

        return errorFlag;

    }
}