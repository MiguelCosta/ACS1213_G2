/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Utilizador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.UtilizadorFacade;

/**
 *
 * @author Miguel
 */
@WebServlet(name = "UtilizadorServlet", urlPatterns = {
    "/Utilizador", 
    "/Utilizador/login", 
    "/Utilizador/logout"})
public class UtilizadorServlet extends HttpServlet {

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
        String userPath = request.getServletPath();

        if (userPath.equals("/Utilizador")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Utilizador user = utilizadorFacade.UtilizadorLogin(username, password);
            HttpSession session = request.getSession();

            if (user != null) {
                session.setAttribute("user", user);
            } else {
                session.setAttribute("user", null);
            }
        } else if (userPath.equals("/Utilizador/logout")) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("/EuroTripsFinder");
            return;
        }




        try {
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
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
