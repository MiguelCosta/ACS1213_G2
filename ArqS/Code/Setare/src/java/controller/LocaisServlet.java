/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cidade;
import entity.Local;
import entity.Utilizador;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CidadeFacade;
import session.LocalFacade;
/**
 *
 * @author JorgeMaia
 */
@WebServlet(name = "LocaisServlet", urlPatterns = {"/Local/register", "/Local/add", "/Local", "/Local/view", "/Local/update", "/Local/delete", "/Local/index"})
public class LocaisServlet extends HttpServlet {

    @EJB
    private CidadeFacade cidadeFacade;
    @EJB
    private LocalFacade localFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String erro = "";
        boolean ok = true; // variavel para indicar se a acao foi executada correta ou nao
        String userPath = request.getServletPath();
        String url = "";
        String nome = null;
        String latitude = null;
        String longitude = null;
        String cidadeid = "1";
       
        HttpSession session = request.getSession();

        Local local;

         

        
       if (userPath.equals("/Local/register")) {
            url = "/register";
        } else if (userPath.equals("/Local/add")) {

            nome = (String) request.getParameter("nomelocal");
            latitude = request.getParameter("latitude");
            longitude = request.getParameter("longitude");
            

            BigDecimal lati;
            BigDecimal longi;


            if (!validate.CoordenadaValidator.validateFormRegister(latitude, longitude, nome, request)) {
                lati = new BigDecimal(latitude);
                longi = new BigDecimal(longitude);
            } else {
                session.setAttribute("MessageError", request.getAttribute("MessageError"));

                response.sendRedirect("/Setare/Local/register");
                return;
            }

           
            try {
                

                local = new Local();
                local.setNome(nome);
                local.setLatitude(lati);
                local.setLongitude(longi);  
                Cidade cid =  (Cidade) cidadeFacade.find(Integer.parseInt(cidadeid));
                local.setCidadeid(cid);

               localFacade.create(local);
            } catch (Exception ex) {
                erro = "Erro ao inserir local";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/Setare/Local/register");
                return;
            }

            response.sendRedirect(request.getServletContext().getContextPath() + "/Local");
        } else if (userPath.equals("/Local")) {
            response.sendRedirect("/Setare/Local/index?page="+1);
        }else if(userPath.equals("/Local/index")){
            //request.setAttribute("listartigos", artigoFacade.ArtigoPages(page-1));
            
            try{
                int page = 1;
                page = new Integer(request.getParameter("page"));
                int ct = localFacade.count();
                int nrpages;
                if(ct<=localFacade.limitepage) nrpages= 1;
                else if((ct)%localFacade.limitepage == 0) nrpages = ct/localFacade.limitepage;
                else nrpages = (ct/localFacade.limitepage)+1;
                if(Integer.parseInt(request.getParameter("page")) > nrpages || page == 0){
                    page = 1;
                    response.sendRedirect("/Setare/Local/index?page="+1);
                }
                request.setAttribute("nrpages", String.valueOf(nrpages));
                int max = page*localFacade.limitepage;
                if(max > ct) max = ct;
                request.setAttribute("resultado", localFacade.findAll().subList((page-1)*localFacade.limitepage, max));
                url = "/index";
            }
            catch(NumberFormatException e){
                response.sendRedirect("/Setare/Local/index?page="+1);
            }  
        } else if (userPath.equals("/Local/view")) {
            int id = Integer.parseInt(request.getParameter("id"));
            local = localFacade.find(id);
            session.setAttribute("local", local);
            url = "/edit";
            
        } else if (userPath.equals("/Local/delete")) {
            
            try{
                int localid = Integer.parseInt(request.getParameter("id"));
                Local localdelete = localFacade.find(localid);
                localFacade.remove(localdelete);
            }
            catch(Exception e){
                erro = "Não foi possível remover o local";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/Setare/Local/index");
                return;
            }
            
            response.sendRedirect("/Setare/Local/index");
            
            url = "/index";
        
        } else if (userPath.equals("/Local/update")) {

            nome = (String) request.getParameter("nomelocal");
            latitude = request.getParameter("latitude");
            longitude = request.getParameter("longitude");
            

            BigDecimal lati;
            BigDecimal longi;

            if (!validate.CoordenadaValidator.validateFormRegister(latitude, longitude, nome, request)) {
                lati = new BigDecimal(latitude);
                longi = new BigDecimal(longitude);
            } else {
                session.setAttribute("MessageError", "Coordenadas Inválidas!");
                response.sendRedirect("/Setare/Local/register");
                return;
            }

            local = (Local) session.getAttribute("local");
            
            try {
                local.setNome(nome);
                local.setLatitude(lati);
                local.setLongitude(longi);
                localFacade.edit(local);

            } catch (Exception ex) {
                erro = "Erro ao atualizar o Local!";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/Setare/Local");
                return;
            }
            session.setAttribute("MessageSuccess", "Atualizado com sucesso!");
            response.sendRedirect("/Setare/Local");
            return;
        }
        try {
            request.getRequestDispatcher("/WEB-INF/view/Local" + url + ".jsp").forward(request, response);
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
}
