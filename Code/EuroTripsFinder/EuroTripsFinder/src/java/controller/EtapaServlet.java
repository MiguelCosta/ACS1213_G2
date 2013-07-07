/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cidade;
import entity.Etapa;
import entity.Percurso;
import entity.Tempoparagem;
import entity.Utilizador;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
import session.CidadeFacade;
import session.EtapaFacade;
import session.LocalparagemFacade;
import session.PercursoFacade;
import session.TempoparagemFacade;

/**
 *
 * @author JorgeMaia
 */
@WebServlet(name = "EtapaServlet", urlPatterns = {"/Etapa", "/Etapa/register", "/Etapa/add", "/Etapa/update", "/Etapa/view",
    "/Etapa/pontoInteresse", "/Etapa/addEtapa"})
public class EtapaServlet extends HttpServlet {

    @EJB
    private CidadeFacade cidadeFacade;
    @EJB
    private LocalparagemFacade localparagemFacade;
    @EJB
    private TempoparagemFacade tempoparagemFacade;
    @EJB
    private EtapaFacade etapaFacade;
    @EJB
    private PercursoFacade percursoFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String erro = "";
        boolean ok = true; // variavel para indicar se a acao foi executada correta ou nao
        String userPath = request.getServletPath();
        String url = "";
        int localOrigemId;
        int localDestinoId;
        Utilizador utilizador = (Utilizador) session.getAttribute("user");


        if (userPath.equals("/Etapa/register")) {
//            List<Cidade> myList = cidadeFacade.findAll();
//            request.setAttribute("listmeiostransporte", tipomeiotransporteFacade.findAll());
            //request.setAttribute("cidades", myList);
            request.setAttribute("locais", localparagemFacade.findAll());

            url = "/register";
        } else if (userPath.equals("/Etapa/add")) {
            localOrigemId = Integer.parseInt((String) request.getParameter("localInicial"));
            localDestinoId = Integer.parseInt((String) request.getParameter("localFinal"));
            Date dataInicio = null;
            Date dataFim = null;
            try {
                dataInicio = formataData(request.getParameter("dataInicial"));
                dataFim = formataData(request.getParameter("dataFinal"));
            } catch (Exception ex) {
                Logger.getLogger(EtapaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("caminhos", tempoparagemFacade.caminhos(localOrigemId, localDestinoId, dataInicio, dataFim, 3, 0));

            Etapa et = new Etapa();
            et.setLocalparageminicialid(localparagemFacade.find(localOrigemId));
            et.setLocalparagemdestinoid(localparagemFacade.find(localDestinoId));
            et.setDatapartida(dataInicio);
            et.setDatachegada(dataFim);

            session.setAttribute("etapa", et);

            url = "/selecionaretapa";

        } else if (userPath.equals("/Etapa/addEtapa")) {
            int idInicio;
            int idFim;
            Tempoparagem tempoInicio;
            Tempoparagem tempoFim;
            Date dataInicio;
            Date dataFinal;

            idInicio = Integer.parseInt(request.getParameter("viagemselec").split("-")[0]);
            idFim = Integer.parseInt(request.getParameter("viagemselec").split("-")[1]);

            tempoInicio = tempoparagemFacade.find(idInicio);
            tempoFim = tempoparagemFacade.find(idFim);

            Etapa et = (Etapa) session.getAttribute("etapa");
            dataInicio = et.getDatapartida();
            dataFinal = et.getDatachegada();

            int horas = tempoInicio.getHorapartida().getHours();

            dataInicio.setHours(tempoInicio.getHorapartida().getHours());
            dataInicio.setMinutes(tempoInicio.getHorapartida().getMinutes());
            dataInicio.setSeconds(tempoInicio.getHorapartida().getSeconds());

            dataFinal.setHours(tempoFim.getHorachegada().getHours());
            dataFinal.setMinutes(tempoFim.getHorachegada().getMinutes());
            dataFinal.setSeconds(tempoFim.getHorachegada().getSeconds());

            et.setDatapartida(dataInicio);
            et.setDatachegada(dataFinal);
            et.setValor(BigDecimal.ZERO);


            et.setPercursoCollection(new ArrayList<Percurso>());
            et.getPercursoCollection().add((Percurso) session.getAttribute("percurso"));

            Percurso p = (Percurso) session.getAttribute("percurso");
            p.getEtapaCollection().add(et);

            try {
                percursoFacade.edit(p);
            } catch (Exception ex) {
                erro = "Erro ao inserir etapa. ";
                session.setAttribute("MessageError", erro + ex.getMessage());
                response.sendRedirect("/EuroTripsFinder/Etapa/register");
                return;
            }

            session.setAttribute("MessageSuccess", "Etapa adicionada com sucesso.");
            response.sendRedirect("/EuroTripsFinder/Percurso/view?id="+ p.getId());
            return;

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
