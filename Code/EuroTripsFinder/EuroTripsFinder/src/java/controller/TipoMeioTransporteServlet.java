/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Tipomeiotransporte;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ArtigopublicitarioFacade;
import session.TipomeiotransporteFacade;

/**
 *
 * @author miltonnunes52
 */
@WebServlet(name = "TipoMeioTransporteServlet", 
  urlPatterns = {"/TipoMeioTransporte",
                "/TipoMeioTransporte/register",
                "/TipoMeioTransporte/add",
                "/TipoMeioTransporte/view",
                "/TipoMeioTransporte/update",
                "/TipoMeioTransporte/index"})
public class TipoMeioTransporteServlet extends HttpServlet {
    
    @EJB
    private  TipomeiotransporteFacade tipomeiotransporteFacade;
    @EJB
    private ArtigopublicitarioFacade artigoFacade;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        
        String userPath = request.getServletPath();
        String url = "";
       
        String erro = "";
        boolean ok = true;
        
        Tipomeiotransporte tipomeiotransporte;
        
        String nome;
        String tipoid;
         
        // Publiciade
        session.setAttribute("artigorandom", artigoFacade.ArtigoRandom());

        
       
        if(userPath.equals("/TipoMeioTransporte")){
            response.sendRedirect("/EuroTripsFinder/TipoMeioTransporte/index?page="+1);
        }else if(userPath.equals("/TipoMeioTransporte/index")){
            //request.setAttribute("listartigos", artigoFacade.ArtigoPages(page-1));
            
            try{
                int page = 1;
                page = new Integer(request.getParameter("page"));
                int ct = tipomeiotransporteFacade.count();
                int nrpages;
                if(ct<=20) nrpages= 1;
                else if((ct)%tipomeiotransporteFacade.limitepage == 0) nrpages = ct/tipomeiotransporteFacade.limitepage;
                else nrpages = (ct/tipomeiotransporteFacade.limitepage)+1;
                if(Integer.parseInt(request.getParameter("page")) > nrpages || page == 0){
                    page = 1;
                    response.sendRedirect("/EuroTripsFinder/TipoMeioTransporte/index?page="+1);
                }
                request.setAttribute("nrpages", String.valueOf(nrpages));
                int max = page*tipomeiotransporteFacade.limitepage;
                if(max > ct) max = ct;
                request.setAttribute("listmeiostransporte", tipomeiotransporteFacade.findAll().subList((page-1)*tipomeiotransporteFacade.limitepage, max));
                url = "index";
            }
            catch(NumberFormatException e){
                response.sendRedirect("/EuroTripsFinder/TipoMeioTransporte/index?page="+1);
            }   
        } else if(userPath.equals("/TipoMeioTransporte/register")){
            url= "register";
        } else if(userPath.equals("/TipoMeioTransporte/add")){
            nome = request.getParameter("nome");
            ok = validate.TipoMeioTransporteValidator.validateFormRegister(nome, request);
            
            if (!ok || !erro.isEmpty()) {
                session.setAttribute("MessageError", request.getAttribute("MessageError"));
                response.sendRedirect("/EuroTripsFinder/TipoMeioTransporte/register");
                return;
            }
            
            
            tipomeiotransporte = new Tipomeiotransporte();
            tipomeiotransporte.setNome(nome);
            
            request.setAttribute("tipo", tipomeiotransporte);
            
            try {
                tipomeiotransporteFacade.create(tipomeiotransporte);
            } catch (Exception ex) {
                erro = "Erro ao inserir tipo de meio de transporte ";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/TipoMeioTransporte/register");
                return;
            }

            session.setAttribute("MessageSuccess", "Tipo de meio de transporte inserido.");

            response.sendRedirect("/EuroTripsFinder/TipoMeioTransporte");
            return;
        } else if(userPath.equals("/TipoMeioTransporte/view")){
            
            url= "view";
            request.setAttribute("tipo", tipomeiotransporteFacade.find(Integer.parseInt(request.getParameter("id"))));
        } else if(userPath.equals("/TipoMeioTransporte/update")){
            nome = request.getParameter("nome");
            tipoid = request.getParameter("id");

            ok = validate.TipoMeioTransporteValidator.validateFormRegister(nome, request);
            if (!ok || !erro.isEmpty()) {
                session.setAttribute("MessageError", request.getAttribute("MessageError"));
                response.sendRedirect("/EuroTripsFinder/TipoMeioTransporte/view?id="+tipoid);
                return;
            }
            
            tipomeiotransporte = tipomeiotransporteFacade.find(Integer.parseInt(request.getParameter("id")));
            tipomeiotransporte.setNome(nome);
            
            try {
                tipomeiotransporteFacade.edit(tipomeiotransporte);
            } catch (Exception ex) {
                erro = "Erro ao actualizar tipo de meio de transporte ";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/TipoMeioTransporte/view?id="+tipoid);
                return;
            }

            session.setAttribute("MessageSuccess", "Tipo de meio de transporte actualizado.");

            response.sendRedirect("/EuroTripsFinder/TipoMeioTransporte");
            return;
            
        }
        
        try {
            request.getRequestDispatcher("/WEB-INF/view/TipoMeioTransporte/" + url + ".jsp").forward(request, response);
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
