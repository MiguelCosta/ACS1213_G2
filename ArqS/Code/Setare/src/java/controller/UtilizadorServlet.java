/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entity.Utilizador;
import java.io.IOException;
import java.text.DateFormat;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.UtilizadorFacade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Miguel
 */
@WebServlet(name = "UtilizadorServlet", urlPatterns = {
    "/Utilizador",
    "/Utilizador/login",
    "/Utilizador/logout",
    "/Utilizador/register",
    "/Utilizador/addUser",
    "/Utilizador/perfil",
    "/Utilizador/update",
    "/Utilizador/index",
    "/Utilizador/edit",
    "/Utilizador/updateUser",
    "/Utilizador/delete"})
public class UtilizadorServlet extends HttpServlet {

    @EJB
    private UtilizadorFacade utilizadorFacade;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String erro = "";
        boolean ok = true; // variavel para indicar se a acao foi executada correta ou nao

        HttpSession session = request.getSession();
        String userPath = request.getServletPath();
        String url = "";


        Utilizador user;
        
        String email;
        String username;
        String password;
      
         
     
        
       
        if (userPath.equals("/Utilizador")) {
            username = request.getParameter("nome");
            password = request.getParameter("password");

            user = utilizadorFacade.UtilizadorLogin(username, password);
            
            if (user != null) {
                session.setAttribute("user", user);
                response.sendRedirect("/Setare");
                return;
            } else {
                session.setAttribute("user", null);
                url = "login";
            }
         }else if(userPath.equals("/Utilizador/index")){
           
            try{
                int page = 1;
                page = new Integer(request.getParameter("page"));
                int ct = utilizadorFacade.count();
                int nrpages;
                if(ct<=utilizadorFacade.limitepage) nrpages= 1;
                else if((ct)%utilizadorFacade.limitepage == 0) nrpages = ct/utilizadorFacade.limitepage;
                else nrpages = (ct/utilizadorFacade.limitepage)+1;
                if(Integer.parseInt(request.getParameter("page")) > nrpages || page == 0){
                    page = 1;
                    response.sendRedirect("/Setare/Utilizador/index?page="+1);
                }
                request.setAttribute("nrpages", String.valueOf(nrpages));
                int max = page*utilizadorFacade.limitepage;
                if(max > ct) max = ct;
                request.setAttribute("listutilizadores", utilizadorFacade.findAll().subList((page-1)*utilizadorFacade.limitepage, max));
                url = "index";
            }
            catch(NumberFormatException e){
                response.sendRedirect("/Setare/Utilizador/index?page="+1);
            }  
        } else if (userPath.equals("/Utilizador/logout")) {
            session.invalidate();
            response.sendRedirect("/Setare");
            return;
        } else if (userPath.equals("/Utilizador/register")) {
            url = "register";
        } else if (userPath.equals("/Utilizador/perfil")) {
            url = "perfil";
        
        } else if (userPath.equals("/Utilizador/delete")) {
            
            try{
                int utilizadorid = Integer.parseInt(request.getParameter("id"));
                Utilizador userdelete = utilizadorFacade.find(utilizadorid);
                utilizadorFacade.remove(userdelete);
            }
            catch(Exception e){
                erro = "Não foi possível remover o utilizador";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/Setare/Utilizador/index");
                return;
            }
            
            response.sendRedirect("/Setare/Utilizador/index");
            
            url = "index";
            
            
        //edit outros utilizadores
        } else if (userPath.equals("/Utilizador/edit")) {
            url = "edit";
            int utilizadorid = Integer.parseInt(request.getParameter("id"));
            Utilizador useredit = utilizadorFacade.find(utilizadorid);
            request.setAttribute("useredit", useredit);
            
            
        //update outros utilizadores
        } else if (userPath.equals("/Utilizador/updateUser")) {
            
            int utilizadorid = Integer.parseInt(request.getParameter("id"));
            Utilizador useredit = utilizadorFacade.find(utilizadorid);
            email = request.getParameter("email");
            password = request.getParameter("password");
            username = request.getParameter("nome");

            // Valida de informação do form
            ok = validate.UtilizadorValidator.validateFormRegister(email,
                    useredit.getNome(),
                    password,
                    request);

         

            
            useredit.setEmail(email);
            useredit.setPassword(password);
            useredit.setNome(username);
          

            try {
                utilizadorFacade.edit(useredit);
            } catch (Exception e) {
                session.setAttribute("MessageError", "Erro ao atualizar a informação.");
                response.sendRedirect("/Setare/Utilizador/edit");
                return;
            }

            response.sendRedirect("/Setare/Utilizador/index");

        } else if (userPath.equals("/Utilizador/update")) {

            email = request.getParameter("email");
            password = request.getParameter("password");
            username = request.getParameter("nome");
           

            // Valida de informação do form
            ok = validate.UtilizadorValidator.validateFormRegister(email,
                    ((Utilizador) session.getAttribute("user")).getNome(),
                    password,
                    request);

          

            user = (Utilizador) session.getAttribute("user");
            user.setEmail(email);
            user.setPassword(password);
            user.setNome(username);
           

            try {
                utilizadorFacade.edit(user);
            } catch (Exception e) {
                session.setAttribute("MessageError", "Erro ao atualizar a informação.");
                response.sendRedirect("/Setare/Utilizador/register");
                return;
            }

            url = "perfil";

        } else if (userPath.equals("/Utilizador/addUser")) {

            email = request.getParameter("email");
            username = request.getParameter("nome");
            password = request.getParameter("password");
           

            // Valida de informação do form
            ok = validate.UtilizadorValidator.validateFormRegister(email,
                    username,
                    password,
                    request);

           

            if (!ok || !erro.isEmpty()) {
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/Setare/Utilizador/register");
                return;
            }

            user = new Utilizador();
            user.setEmail(email);         
            user.setPassword(password);
            user.setNome(username);

            try {
                utilizadorFacade.create(user);
            } catch (Exception ex) {
                erro = "Erro ao inserir utilizador ";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/Setare/Utilizador/register");
                return;
            }

            session.setAttribute("MessageSuccess", "Utilizador registado.");

            response.sendRedirect("/Setare");
            return;
        }


        try {
            request.getRequestDispatcher("/WEB-INF/view/" + url + ".jsp").forward(request, response);
        } catch (Exception e) {
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Converte uma String para um objeto Date. Caso a String seja vazia ou
     * nula, retorna null - para facilitar em casos onde formulários podem ter
     * campos de datas vazios.
     *
     * @param data String no formato dd/MM/yyyy a ser formatada
     * @return Date Objeto Date ou null caso receba uma String vazia ou nula
     * @throws Exception Caso a String esteja no formato errado
     */
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
