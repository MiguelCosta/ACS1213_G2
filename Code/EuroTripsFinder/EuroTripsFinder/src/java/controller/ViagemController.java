/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.*;

/**
 *
 * @author Miguel
 */
@WebServlet(name = "ViagemController", urlPatterns = {"/Viagem", "/Viagem/view"})
public class ViagemController extends HttpServlet {

    @EJB
    private ViagemFacade viagemFacade;
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
        String erro = "";
        boolean ok = true; // variavel para indicar se a acao foi executada correta ou nao

        HttpSession session = request.getSession();
        String userPath = request.getServletPath();
        String url = "";

        Viagem viag;

        Utilizador user = (Utilizador) session.getAttribute("user");

        // Publiciade
        session.setAttribute("artigorandom", artigoFacade.ArtigoRandom());



        if (userPath.equals("/Viagem")) {
            Collection<Viagem> viagens = null;

            try {
                viagens = user.getViagemCollection();
            } catch (Exception e) {
                session.setAttribute("MessageError", "Erro ao obter as viagens." + e.getMessage());
            }

            request.setAttribute("viagens", viagens);

            url = "index";
        } else if (userPath.equals("/Viagem/view")) {
            int Id = Integer.parseInt((String) request.getParameter("id"));
            viag = viagemFacade.getViagemDados(Id);
            request.setAttribute("viagem", viag);

            //separacao de pontos para mostrar no mapa
            ArrayList<String> intermedios = new ArrayList<String>();
            String origem = null;
            String destino = null;
            int index = 0;
            for (Etapa x : viag.getPercursoid().getEtapaCollection()) {
                if (index == 0) {
                    origem = x.getLocalparageminicialid().getCoordenadaid().getLatitude().toString() + " , "
                            + x.getLocalparageminicialid().getCoordenadaid().getLongitude().toString();
                } else {
                    intermedios.add(x.getLocalparagemdestinoid().getCoordenadaid().getLatitude().toString() + " , "
                            + x.getLocalparagemdestinoid().getCoordenadaid().getLongitude().toString());
                }
                index++;
            }
            destino = intermedios.get(intermedios.size() - 1);
            intermedios.remove(destino);
            request.setAttribute("origem", origem);
            request.setAttribute("destino", destino);
            request.setAttribute("intermedios", intermedios.toArray());
            url = "view";
        }
        try {
            request.getRequestDispatcher("/WEB-INF/view/Viagem/" + url + ".jsp").forward(request, response);
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
