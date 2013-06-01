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
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CidadeFacade;

/**
 *
 * @author JorgeMaia
 */
@WebServlet(name = "AtividadeServlet", urlPatterns = {"/Atividade", "/Atividade/register", "/Atividade/add", "/Atividade/view"})
public class AtividadeServlet extends HttpServlet {
 @EJB
  private CidadeFacade cidadeFacade;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String erro = "";
        boolean ok = true; // variavel para indicar se a acao foi executada correta ou nao
        HttpSession session = request.getSession();        
        String userPath = request.getServletPath();
        String url = "";        
        String nomeCidade;       
        Cidade cidade;
        Collection<Atividade> atividades = null;
        
        if(userPath.equals("/Atividade"))
        {                      
            url = "/view";
        }
        else if(userPath.equals("/Atividade/view"))
        {
            nomeCidade = (String)request.getParameter("nomeCidade");            
            cidade = cidadeFacade.checkIfExistcidade(nomeCidade);
            if(cidadeFacade.checkIfExistcidade(nomeCidade) == null)
            {
                 session.setAttribute("MessageError", "Não foi encontrada uma cidade com esse nome!");
                 response.sendRedirect("/EuroTripsFinder/Atividade");   
                 request.getServletContext().setAttribute("atividades", "");
            }
            else{                
                    atividades = cidadeFacade.atividades(cidade);
                    if(atividades.isEmpty())
                    {
                        session.setAttribute("MessageError", "Não existe nenhuma actividade para essa cidade!");
                        response.sendRedirect("/EuroTripsFinder/Atividade");  
                        request.getServletContext().setAttribute("atividades", "");                        
                    }
                    else
                    {
                        request.getServletContext().setAttribute("atividades", atividades);
                        url = "/view";
                    }
                }   
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
