/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Atividade;
import entity.Cidade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
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
import session.AtividadeFacade;
import session.CidadeFacade;
import validate.AtividadeValidator;

/**
 *
 * @author JorgeMaia
 */
@WebServlet(name = "AtividadeServlet", urlPatterns = {"/Atividade", "/Atividade/register", "/Atividade/add", "/Atividade/view", "/Cidade/Atividade/view", "/Atividade/index", "/Atividade/delete"})
public class AtividadeServlet extends HttpServlet {

    @EJB
    private CidadeFacade cidadeFacade;
    @EJB
    private AtividadeFacade atividadeFacade;
    @EJB
    private ArtigopublicitarioFacade artigoFacade;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String erro = "";
        boolean ok = true; // variavel para indicar se a acao foi executada correta ou nao
        HttpSession session = request.getSession();
        String userPath = request.getServletPath();
        String url = "";
        String nomeCidade;
        String nomeAtividade;
        String descricao;
        Cidade cidade;
        Atividade atividade;
        Collection<Atividade> atividades = null;
                
        
        // Publiciade
        session.setAttribute("artigorandom", artigoFacade.ArtigoRandom());

        
       
       
        if (userPath.equals("/Atividade")) {
               response.sendRedirect("/EuroTripsFinder/Atividade/index?page="+1);
        }else if(userPath.equals("/Atividade/index")){
            //request.setAttribute("listartigos", artigoFacade.ArtigoPages(page-1));
            
            try{
                int page = 1;
                page = new Integer(request.getParameter("page"));
                int ct = atividadeFacade.count();
                int nrpages;
                if(ct<=20) nrpages= 1;
                else if((ct)%atividadeFacade.limitepage == 0) nrpages = ct/atividadeFacade.limitepage;
                else nrpages = (ct/atividadeFacade.limitepage)+1;
                if(Integer.parseInt(request.getParameter("page")) > nrpages || page == 0){
                    page = 1;
                    response.sendRedirect("/EuroTripsFinder/Atividade/index?page="+1);
                }
                request.setAttribute("nrpages", String.valueOf(nrpages));
                int max = page*atividadeFacade.limitepage;
                if(max > ct) max = ct;
                request.setAttribute("atividades", atividadeFacade.findAll().subList((page-1)*atividadeFacade.limitepage, max));
                url = "/view";
            }
            catch(NumberFormatException e){
                response.sendRedirect("/EuroTripsFinder/Atividade/index?page="+1);
            }  
        } else if (userPath.equals("/Cidade/Atividade/view") || userPath.equals("/Atividade/view")) {
            nomeCidade = (String) request.getParameter("id");
            cidade = cidadeFacade.checkIfExistcidade(nomeCidade);
            if (cidadeFacade.checkIfExistcidade(nomeCidade) == null) {
                session.setAttribute("MessageError", "Não foi encontrada uma cidade com esse nome!");
                response.sendRedirect("/EuroTripsFinder/Atividade");
                request.getServletContext().setAttribute("atividades", "");
            } else {
                atividades = cidadeFacade.atividades(cidade);
                if (atividades.isEmpty()) {
                    session.setAttribute("MessageError", "Não existe nenhuma actividade para essa cidade!");
                    response.sendRedirect("/EuroTripsFinder/Atividade");
                    request.getServletContext().setAttribute("atividades", "");
                } else {
                    request.getServletContext().setAttribute("atividades", atividades);
                    url = "/view";
                }
            }
            
        } else if (userPath.equals("/Atividade/delete")) {
            
            try{
                int atividadeid = Integer.parseInt(request.getParameter("id"));
                Atividade atividadedelete = atividadeFacade.find(atividadeid);
                atividadeFacade.remove(atividadedelete);
            }
            catch(Exception e){
                erro = "Não foi possível remover a atividade";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Atividade/index");
                return;
            }
            
            response.sendRedirect("/EuroTripsFinder/Atividade/index");
            
            url = "/view";
        
            
            
        } else if (userPath.equals("/Atividade/add")) {
            nomeAtividade = request.getParameter("nomeAtividade");
            descricao = request.getParameter("descricao");

            if (!AtividadeValidator.validateFormRegister(nomeAtividade, descricao, request)) {
                atividade = new Atividade();
                atividade.setDescricao(descricao);
                atividade.setNome(nomeAtividade);
                atividade.setCidadeid((Cidade) session.getAttribute("cidade"));
                atividadeFacade.register(atividade);
                session.setAttribute("MessageSuccess", "Nova Atividade inserida com sucesso!");


            } else {
                session.setAttribute("MessageError", "Ocorreu um erro.");

            }
            response.sendRedirect("/EuroTripsFinder/Cidade");
        } else if (userPath.equals("/Atividade/register")) {
            url = "/register";
        }


        try {
            request.getRequestDispatcher("/WEB-INF/view/Atividade" + url + ".jsp").forward(request, response);
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
