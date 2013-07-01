/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cidade;
import entity.Coordenada;
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
import session.ArtigopublicitarioFacade;
import session.CidadeFacade;
import session.CoordenadaFacade;

/**
 *
 * @author JorgeMaia
 */
@WebServlet(name = "CidadeServlet", urlPatterns = {"/Cidade/register", "/Cidade/add", "/Cidade", "/Cidade/Atividade", "/Cidade/view", "/Cidade/update", "/Cidade/index", "/Cidade/delete"})
public class CidadeServlet extends HttpServlet {

    @EJB
    private CoordenadaFacade coordenadaFacade;
    @EJB
    private CidadeFacade cidadeFacade;
    @EJB
    private ArtigopublicitarioFacade artigoFacade;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String erro = "";
        boolean ok = true; // variavel para indicar se a acao foi executada correta ou nao
        String userPath = request.getServletPath();
        String url = "";
        String nome = null;
        String latitude = null;
        String longitude = null;
        String distrito = null;
        String pais = null;
        String regiao = null;
        HttpSession session = request.getSession();
        Coordenada coordenada;
        Cidade cidade;

         
        // Publiciade
        session.setAttribute("artigorandom", artigoFacade.ArtigoRandom());

        
       if (userPath.equals("/Cidade/register")) {
            url = "/register";
        } else if (userPath.equals("/Cidade/add")) {

            nome = (String) request.getParameter("nomeCidade");
            latitude = request.getParameter("latitude");
            longitude = request.getParameter("longitude");
            distrito = request.getParameter("distrito");
            pais = request.getParameter("pais");
            regiao = request.getParameter("regiao");

            BigDecimal lati;
            BigDecimal longi;


            if (!validate.CoordenadaValidator.validateFormRegister(latitude, longitude, nome, request)) {
                lati = new BigDecimal(latitude);
                longi = new BigDecimal(longitude);
            } else {
                session.setAttribute("MessageError", request.getAttribute("MessageError"));

                response.sendRedirect("/EuroTripsFinder/Cidade/register");
                return;
            }

            if (coordenadaFacade.checkIfExists(lati, longi, nome) == true) {
                erro = "Já existe!";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Cidade/register");
                return;
            }
            try {
                //adiciona nova Coordenada     
                coordenada = new Coordenada();
                coordenada.setLatitude(lati);
                coordenada.setLongitude(longi);
                coordenada.setNome(nome);

                cidade = new Cidade();
                cidade.setDistrito(distrito);
                cidade.setPais(pais);
                cidade.setNome(nome);
                cidade.setRegiao(regiao);

                if (!cidadeFacade.register(coordenada, cidade)) {
                    erro = "Erro ao inserir Cidade";
                    session.setAttribute("MessageError", erro);
                    response.sendRedirect("/EuroTripsFinder/Cidade/register");
                    return;
                }
            } catch (Exception ex) {
                erro = "Erro ao inserir Cidade";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Cidade/register");
                return;
            }

            response.sendRedirect(request.getServletContext().getContextPath() + "/Cidade");
        } else if (userPath.equals("/Cidade")) {
            response.sendRedirect("/EuroTripsFinder/Cidade/index?page="+1);
        }else if(userPath.equals("/Cidade/index")){
            //request.setAttribute("listartigos", artigoFacade.ArtigoPages(page-1));
            
            try{
                int page = 1;
                page = new Integer(request.getParameter("page"));
                int ct = cidadeFacade.count();
                int nrpages;
                if(ct<=cidadeFacade.limitepage) nrpages= 1;
                else if((ct)%cidadeFacade.limitepage == 0) nrpages = ct/cidadeFacade.limitepage;
                else nrpages = (ct/cidadeFacade.limitepage)+1;
                if(Integer.parseInt(request.getParameter("page")) > nrpages || page == 0){
                    page = 1;
                    response.sendRedirect("/EuroTripsFinder/Cidade/index?page="+1);
                }
                request.setAttribute("nrpages", String.valueOf(nrpages));
                int max = page*cidadeFacade.limitepage;
                if(max > ct) max = ct;
                request.setAttribute("resultado", cidadeFacade.findAll().subList((page-1)*cidadeFacade.limitepage, max));
                url = "/index";
            }
            catch(NumberFormatException e){
                response.sendRedirect("/EuroTripsFinder/Cidade/index?page="+1);
            }  
        } else if (userPath.equals("/Cidade/view")) {
            int id = Integer.parseInt(request.getParameter("id"));
            cidade = cidadeFacade.cidade(id);
            session.setAttribute("cidade", cidade);
            url = "/view";
            
        } else if (userPath.equals("/Cidade/delete")) {
            
            try{
                int cidadeid = Integer.parseInt(request.getParameter("id"));
                Cidade cidadedelete = cidadeFacade.find(cidadeid);
                cidadeFacade.remove(cidadedelete);
            }
            catch(Exception e){
                erro = "Não foi possível remover a cidade";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Cidade/index");
                return;
            }
            
            response.sendRedirect("/EuroTripsFinder/Cidade/index");
            
            url = "/index";
        
        } else if (userPath.equals("/Cidade/update")) {

            nome = (String) request.getParameter("nomeCidade");
            latitude = request.getParameter("latitude");
            longitude = request.getParameter("longitude");
            distrito = request.getParameter("distrito");
            pais = request.getParameter("pais");
            regiao = request.getParameter("regiao");

            BigDecimal lati;
            BigDecimal longi;

            if (!validate.CoordenadaValidator.validateFormRegister(latitude, longitude, nome, request)) {
                lati = new BigDecimal(latitude);
                longi = new BigDecimal(longitude);
            } else {
                session.setAttribute("MessageError", "Coordenadas Inválidas!");
                response.sendRedirect("/EuroTripsFinder/Cidade/register");
                return;
            }

            cidade = (Cidade) request.getAttribute("cidade");
         
            coordenada = cidade.getCoordenadaid();
            try {
                coordenada.setLatitude(lati);
                coordenada.setLongitude(longi);
                coordenada.setNome(nome);
                coordenadaFacade.edit(coordenada);
                cidade.setCoordenadaid(coordenada);
                cidade.setDistrito(distrito);
                cidade.setNome(nome);
                cidade.setRegiao(regiao);
                cidade.setPais(pais);
                cidadeFacade.edit(cidade);

            } catch (Exception ex) {
                erro = "Erro ao atualizar a Cidade!";
                session.setAttribute("MessageError", erro);
                
                return;
            }
            session.setAttribute("MessageSuccess", "Atualizado com sucesso!");
            response.sendRedirect("/EuroTripsFinder/Cidade");
            return;
        }
        try {
            request.getRequestDispatcher("/WEB-INF/view/Cidade" + url + ".jsp").forward(request, response);
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
