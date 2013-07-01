/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.ContratoServlet.formataData;
import entity.Percurso;
import entity.Utilizador;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.PercursoFacade;
import session.UtilizadorFacade;
import validate.PercursoValidator;

/**
 *
 * @author Miguel
 */
@WebServlet(name = "PercursoServlet", urlPatterns = {"/Percurso", "/Percurso/view", "/Percurso/add", "/Percurso/register", "/Percurso/update"})
public class PercursoServlet extends HttpServlet {

    @EJB
    private PercursoFacade percursoFacade;
    
    @EJB
    private UtilizadorFacade utilizadorFacade;

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

        String erro = "";
        boolean ok = true; // variavel para indicar se a acao foi executada correta ou nao

        Percurso percurso;

        HttpSession session = request.getSession();
        String userPath = request.getServletPath();
        String url = "";
        
        Collection<Percurso> pers = ((Utilizador) session.getAttribute("user")).getPercursoCollection();
        
        if (userPath.equals("/Percurso")) {
            request.setAttribute("percursos", ((Utilizador) session.getAttribute("user")).getPercursoCollection());
            url = "index";
        } else if (userPath.equals("/Percurso/view")) {
            int Id = Integer.parseInt((String) request.getParameter("id"));
            percurso = percursoFacade.find(Id);
            session.setAttribute("percurso", percurso);
            
            url = "view";
        } else if (userPath.equals("/Percurso/register")) {
            url = "register";
        } else if (userPath.equals("/Percurso/add")) {

            percurso = new Percurso();
            percurso.setLimiteetapas(25);
            percurso.setUtilizadorid((Utilizador) session.getAttribute("user"));
            percurso.setNumeroetapas(0);
            percurso.setValortotal(BigDecimal.ZERO);
            
            try {
                percursoFacade.create(percurso);
            } catch (Exception ex) {
                erro = "Erro ao inserir contrato";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Percurso/register");
                return;
            }

            session.setAttribute("MessageSuccess", "Percurso registado.");

            response.sendRedirect("/EuroTripsFinder/Percurso");
            return;
            
        }

        try {
            request.getRequestDispatcher("/WEB-INF/view/Percurso/" + url + ".jsp").forward(request, response);
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
