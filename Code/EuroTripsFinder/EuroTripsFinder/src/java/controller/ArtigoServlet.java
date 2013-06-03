/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Artigopublicitario;
import entity.Contrato;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ArtigopublicitarioFacade;
import session.ContratoFacade;

/**
 *
 * @author miltonnunes52
 */
@WebServlet(name = "ArtigoServlet", 
  urlPatterns = {"/Artigo",
                "/Artigo/register",
                "/Artigo/add",
                "/Artigo/view",
                "/Artigo/update"})
public class ArtigoServlet extends HttpServlet {
    
    @EJB
    private ArtigopublicitarioFacade artigoFacade;
    @EJB
    private ContratoFacade contratoFacade;
    
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
        HttpSession session = request.getSession();
         
        String userPath = request.getServletPath();
        String url = "";
       
        String erro = "";
        boolean ok = true;
        
        Artigopublicitario artigo;
        
        String nome;
        String conteudo;
        String artigoid;
        String contratoid;
        
        if(userPath.equals("/Artigo")){
            request.setAttribute("listartigos", artigoFacade.findAll());
            url = "index";
        }else if(userPath.equals("/Artigo/register")){ 
            request.setAttribute("listcontratos", contratoFacade.findAll());
            url = "register";
        }else if (userPath.equals("/Artigo/update")) {
            nome = request.getParameter("nome");
            conteudo = request.getParameter("conteudo");
            artigoid = request.getParameter("id");
            
            ok = validate.ArtigoValidator.validateFormRegister(nome,
                    conteudo,                  
                    request);
            
            if (!ok || !erro.isEmpty()) {
                session.setAttribute("MessageError", request.getAttribute("MessageError"));
                response.sendRedirect("/EuroTripsFinder/Artigo/view?id="+artigoid);
                return;
            }
            
            
            artigo = artigoFacade.find(Integer.parseInt(request.getParameter("id")));

            artigo.setConteudo(conteudo);
            artigo.setNome(nome);
            
            
             try {
                artigoFacade.edit(artigo);
            } catch (Exception ex) {             
                session.setAttribute("MessageError", "Erro ao atualizar a informação.");
                response.sendRedirect("/EuroTripsFinder/Artigo/view?id="+artigoid);
                return;
            }
             
            session.setAttribute("MessageSuccess", "Artigo Publicitário actualizado.");
            
            response.sendRedirect("/EuroTripsFinder/Artigo");
            return;
            
        }else if(userPath.equals("/Artigo/view")){
            url= "view";
            request.setAttribute("artigo", artigoFacade.find(Integer.parseInt(request.getParameter("id"))));
        }else if(userPath.equals("/Artigo/add")){
            
            nome = request.getParameter("nome");
            conteudo = request.getParameter("conteudo");
            contratoid = request.getParameter("contratoid");
            
            ok = validate.ArtigoValidator.validateFormRegister(nome,
                    conteudo,                  
                    request);
            
            if (!ok || !erro.isEmpty()) {
                session.setAttribute("MessageError", request.getAttribute("MessageError"));
                response.sendRedirect("/EuroTripsFinder/Artigo/register");
                return;
            }
            
            artigo = new Artigopublicitario();
            artigo.setConteudo(conteudo);
            artigo.setNome(nome);
            Contrato contrato = (Contrato) contratoFacade.find(Integer.parseInt(contratoid));
            artigo.setContratoid(contrato);
            //falta meter aqui o contrato id
            
             try {
                //nao sei se é preciso adicionar mais qualquer coisa
                artigoFacade.create(artigo);
            } catch (Exception ex) {
                erro = "Erro ao inserir artigo publicitário";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Artigo/register");
                return;
            }
             
            session.setAttribute("MessageSuccess", "Artigo Publicitário registado.");

            response.sendRedirect("/EuroTripsFinder/Artigo");
            return;
            
            
        }
        
        try {
            request.getRequestDispatcher("/WEB-INF/view/Artigo/" + url + ".jsp").forward(request, response);
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
