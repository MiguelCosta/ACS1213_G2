/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cidade;
import entity.Etapa;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CidadeFacade;
import session.TipomeiotransporteFacade;
import session.LocalparagemFacade;

/**
 *
 * @author JorgeMaia
 */
@WebServlet(name = "EtapaServlet", urlPatterns = {"/Etapa", "/Etapa/register", "/Etapa/add", "/Etapa/update", "/Etapa/view",
    "/Etapa/pontoInteresse"})
public class EtapaServlet extends HttpServlet {

    @EJB
    private CidadeFacade cidadeFacade;
    @EJB
    private TipomeiotransporteFacade tipomeiotransporteFacade;
    @EJB
    private LocalparagemFacade LocalparagemFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String erro = "";
        boolean ok = true; // variavel para indicar se a acao foi executada correta ou nao
        String userPath = request.getServletPath();
        String url = "";

        HttpSession session = request.getSession();


        if (userPath.equals("/Etapa/register")) {
//            List<Cidade> myList = cidadeFacade.findAll();
//            request.setAttribute("listmeiostransporte", tipomeiotransporteFacade.findAll());
            //request.setAttribute("cidades", myList);
            request.setAttribute("locais", LocalparagemFacade.findAll());


            url = "/register";
        } else if (userPath.equals("/Etapa/add")) {
        } else if (userPath.equals("/Etapa")) {

            url = "/index";
        } else if (userPath.equals("/Etapa/view")) {

            url = "/view";
        } else if (userPath.equals("/Etapa/update")) {
        } else if (userPath.equals("/Etapa/pontoInteresse")) {
            response.sendRedirect("/EuroTripsFinder/PaginasIndependentes/pontoInteresse.jsp");
        }
        try {
            request.getRequestDispatcher("/WEB-INF/view/Etapa" + url + ".jsp").forward(request, response);
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
