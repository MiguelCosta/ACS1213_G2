/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Coordenada;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.convert.BigDecimalConverter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CoordenadaFacade;

/**
 *
 * @author JorgeMaia
 */
@WebServlet(name = "CoordenadaController", urlPatterns = {"/Coordenada", "/Coordenada/register", "/Coordenada/add", "/Coordenada/view", "/Coordenada/update"})
public class CoordenadaServlet extends HttpServlet {

    @EJB
    private CoordenadaFacade coordenadaFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

        String erro = "";
        boolean ok = true; // variavel para indicar se a acao foi executada correta ou nao
        HttpSession session = request.getSession();

        String userPath = request.getServletPath();
        String url = "";

        String nome;
        String latitude;
        String longitude;
        Coordenada coordenada;



        if (userPath.equals("/Coordenada/add")) {
            nome = (String) request.getParameter("nomecoordenada");
            latitude = request.getParameter("latitude");
            longitude = request.getParameter("longitude");

            BigDecimal lati = null;
            BigDecimal longi = null;


            if (!validate.CoordenadaValidator.validateFormRegister(latitude, longitude, nome, request)) {
                lati = new BigDecimal(latitude);
                longi = new BigDecimal(longitude);
            } else {
                session.setAttribute("MessageError", "Campos inválidos.");
                response.sendRedirect("/EuroTripsFinder/Coordenada/register");
                return;
            }

            if (coordenadaFacade.checkIfExists(lati, longi, nome) == true) {
                erro = "Já existe!";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Coordenada/register");
                return;
            }
            try {
                //adiciona nova Coordenada     
                coordenada = new Coordenada();
                coordenada.setLatitude(lati);
                coordenada.setLongitude(longi);
                coordenada.setNome(nome);
                coordenadaFacade.create(coordenada);
            } catch (Exception ex) {
                erro = "Erro ao inserir Coordenada";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Coordenada/register");
                return;
            }

            response.sendRedirect(request.getServletContext().getContextPath() + "/Coordenada");
        } else if (userPath.equals("/Coordenada")) {
            List<Coordenada> myList = coordenadaFacade.findAll();
            request.setAttribute("resultado", myList);
            url = "/view";
            //listar coordenadas
        } else if (userPath.equals("/Coordenada/view")) {
            int id = 0;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (Exception ex) {
                erro = "Erro ao receber parametro!";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Coordenada");
                return;
            }
            //verifica se existe coordenada com o ID 
            coordenada = coordenadaFacade.verifica(id);
            if (coordenada == null) {
                erro = "A coodenada não existe!";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Coordenada");
                return;
            } else {
                session.setAttribute("coordenada", coordenada);
            }
            url = "/update";
        } else if (userPath.equals("/Coordenada/register")) {
            url = "/create";
        } else if (userPath.equals("/Coordenada/update")) {
            nome = (String) request.getParameter("nomecoordenada");
            latitude = request.getParameter("latitude");
            longitude = request.getParameter("longitude");

            BigDecimal lati = null;
            BigDecimal longi = null;
            if (!validate.CoordenadaValidator.validateFormRegister(latitude, longitude, nome, request)) {
                lati = new BigDecimal(latitude);
                longi = new BigDecimal(longitude);
            } else {
                session.setAttribute("MessageError", request.getAttribute("MessageError"));
                response.sendRedirect("/EuroTripsFinder/Coordenada");
                return;
            }
            coordenada = (Coordenada) session.getAttribute("coordenada");
            try {
                coordenada.setLatitude(lati);
                coordenada.setLongitude(longi);
                coordenada.setNome(nome);
                coordenadaFacade.edit(coordenada);
            } catch (Exception ex) {
                erro = "Erro ao atualizar coordenada";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Coordenada");
                return;
            }
            session.setAttribute("MessageSuccess", "Atualizado com sucesso!");
            response.sendRedirect("/EuroTripsFinder/Coordenada");

        }


        try {
            request.getRequestDispatcher("/WEB-INF/view/Coordenada" + url + ".jsp").forward(request, response);
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
