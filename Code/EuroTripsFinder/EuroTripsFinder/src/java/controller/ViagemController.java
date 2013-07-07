/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.*;
import validate.ViagemValidator;

/**
 *
 * @author Miguel
 */
@WebServlet(name = "ViagemController", urlPatterns = {"/Viagem", "/Viagem/view", "/Viagem/add", "/Viagem/register"})
public class ViagemController extends HttpServlet {

    @EJB
    private UtilizadorFacade utilizadorFacade;
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
        ViagemValidator vv = new ViagemValidator();
        Utilizador user = (Utilizador) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("MessageError", "Tem de Iniciar Sessão para Aceder a Viagens!");
            response.sendRedirect("/EuroTripsFinder/Utilizador");
            return;
        }

        Viagem viag = new Viagem();



        // Publiciade
        session.setAttribute("artigorandom", artigoFacade.ArtigoRandom());



        if (userPath.equals("/Viagem")) {
            Collection<Viagem> viagens = null;

            try {
                viagens = user.getViagemCollection();
            } catch (Exception e) {
                session.setAttribute("MessageError", "Erro ao obter as viagens." + e.getMessage());
            }

            if (viagens.isEmpty()) {
                session.setAttribute("MessageError", "Não existem viagens registadas para este Utilizador.");
            }

            request.setAttribute("viagens", viagens);

            url = "index";
        } else if (userPath.equals("/Viagem/view")) {

            int Id = Integer.parseInt((String) request.getParameter("id"));
            viag = viagemFacade.getViagemDados(Id);
            request.setAttribute("viagem", viag);

            if (viag.getPercursoid() == null) {
                session.setAttribute("MessageError", "Esta viagem ainda não possui nenhuma Etapa.");
            } else {

                //separacao de pontos para mostrar no mapa
                ArrayList<String> intermedios = new ArrayList<String>();
                String origem = null;
                String destino = null;
                int index = 0;
                Etapa aux = (Etapa) ((List) viag.getPercursoid().getEtapaCollection()).get(0);
                origem = aux.getLocalparageminicialid().getCoordenadaid().getLatitude().toString() + ","
                        + aux.getLocalparageminicialid().getCoordenadaid().getLongitude().toString();
                aux = (Etapa) ((List) viag.getPercursoid().getEtapaCollection()).get((((List) viag.getPercursoid().getEtapaCollection()).size() - 1));
                destino = aux.getLocalparagemdestinoid().getCoordenadaid().getLatitude().toString() + ","
                        + aux.getLocalparagemdestinoid().getCoordenadaid().getLongitude().toString();
                for (Etapa x : viag.getPercursoid().getEtapaCollection()) {
                    if (index != 0) {

                        intermedios.add(x.getLocalparageminicialid().getCoordenadaid().getLatitude().toString() + ","
                                + x.getLocalparageminicialid().getCoordenadaid().getLongitude().toString());
                    }
                    index++;
                }
                intermedios.remove(destino);
                String pontos = "";
                for (String pontosIntermedios : intermedios) {
                    pontos += pontosIntermedios + " ";
                }
                pontos = pontos.substring(0, pontos.length() - 2);


                request.setAttribute("origem", origem);
                request.setAttribute("destino", destino);
                request.setAttribute("intermedios", pontos);
            }
            url = "view";
        } else if (userPath.equals("/Viagem/add")) {
            String dtInicio = (String) request.getParameter("datainicio");
            String dtFim = (String) request.getParameter("datafim");
            String nome = (String) request.getParameter("nome");
            String descricao = (String) request.getParameter("descricao");

            if (vv.validaRegisto(dtInicio, nome, dtFim, request)) {
                try {
                    viag.setDatafim(vv.formataData(dtFim));
                    viag.setDatainicio(vv.formataData(dtInicio));
                } catch (Exception ex) {
                    session.setAttribute("error", ex.getMessage().toString());
                }

                viag.setNome(nome);
                viag.setDescricao(descricao);
                viag.setUtilizadorid(user);

                Percurso percurso = new Percurso();
                percurso.setLimiteetapas(25);
                percurso.setUtilizadorid((Utilizador) session.getAttribute("user"));
                percurso.setNumeroetapas(0);
                percurso.setValortotal(BigDecimal.ZERO);
                
                viag.setPercursoid(percurso);

                try {
                    viagemFacade.create(viag);
                } catch (Exception ex) {
                    session.setAttribute("MessageError", "Erro ao inserir na base de dados!");
                    response.sendRedirect("/EuroTripsFinder/Viagem/register");
                    return;
                }

                user = utilizadorFacade.find(user.getId());
                session.setAttribute("user", user);

            } else {

                session.setAttribute("MessageError", "Os dados inseridos não são válidos!");
                response.sendRedirect("/EuroTripsFinder/Viagem/register");
                return;
            }

            url = "index";
        } else if (userPath.equals("/Viagem/register")) {
            url = "register";
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
