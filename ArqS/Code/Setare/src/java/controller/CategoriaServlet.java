/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Categoria;
import entity.Cidade;
import entity.Local;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CategoriaFacade;
import session.CidadeFacade;
import session.LocalFacade;

/**
 *
 * @author miltonnunes52
 */
@WebServlet(name = "CategoriaServlet", urlPatterns = {"/Categoria/register", "/Categoria/add", "/Categoria", "/Categoria/view", "/Categoria/update", "/Categoria/delete", "/Categoria/index"})
public class CategoriaServlet extends HttpServlet {
    
    @EJB
    private CategoriaFacade categoriaFacade;
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String erro = "";
        boolean ok = true; // variavel para indicar se a acao foi executada correta ou nao
        String userPath = request.getServletPath();
        String url = "";
        String nome = null;
        String descricao = null;
        String deposito = null;
        String hora = null;
        
       
        HttpSession session = request.getSession();

        Categoria categoria;

         

        
       if (userPath.equals("/Categoria/register")) {
            url = "/register";
        } else if (userPath.equals("/Categoria/add")) {

            nome = (String) request.getParameter("nome");
            descricao = (String) request.getParameter("descricao");
            deposito = request.getParameter("deposito");
            hora = request.getParameter("hora");
            BigDecimal hr;
            BigDecimal dep;

            


            if (validate.CategoriaValidator.validateFormRegister(descricao, nome, request)) {
                
                session.setAttribute("MessageError", request.getAttribute("MessageError"));

                response.sendRedirect("/Setare/Categoria/register");
                return;
            }

            try{
                dep = new BigDecimal(deposito);
                
            }catch (NumberFormatException nfe){
               session.setAttribute("MessageError", "Preço depósito inválido");

                response.sendRedirect("/Setare/Categoria/register");
                return;
            }
            
            try{
                hr = new BigDecimal(hora);
               
                
            }catch (NumberFormatException nfe){
               session.setAttribute("MessageError", "Preço hora inválido");

                response.sendRedirect("/Setare/Categoria/register");
                return;
            }   
            
            try {
                

                categoria = new Categoria();
                categoria.setNome(nome);
                categoria.setDescricao(descricao);
                categoria.setPrecoDeposito(dep);
                categoria.setPrecoPorHora(hr);
                
                categoriaFacade.create(categoria);
            } catch (Exception ex) {
                erro = "Erro ao inserir categoria";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/Setare/Categoria/register");
                return;
            }

            response.sendRedirect(request.getServletContext().getContextPath() + "/Categoria");
        } else if (userPath.equals("/Categoria")) {
            response.sendRedirect("/Setare/Categoria/index?page="+1);
        }else if(userPath.equals("/Categoria/index")){
            //request.setAttribute("listartigos", artigoFacade.ArtigoPages(page-1));
            
            try{
                int page = 1;
                page = new Integer(request.getParameter("page"));
                int ct = categoriaFacade.count();
                int nrpages;
                if(ct<=categoriaFacade.limitepage) nrpages= 1;
                else if((ct)%categoriaFacade.limitepage == 0) nrpages = ct/categoriaFacade.limitepage;
                else nrpages = (ct/categoriaFacade.limitepage)+1;
                if(Integer.parseInt(request.getParameter("page")) > nrpages || page == 0){
                    page = 1;
                    response.sendRedirect("/Setare/Categoria/index?page="+1);
                }
                request.setAttribute("nrpages", String.valueOf(nrpages));
                int max = page*categoriaFacade.limitepage;
                if(max > ct) max = ct;
                request.setAttribute("resultado", categoriaFacade.findAll().subList((page-1)*categoriaFacade.limitepage, max));
                url = "/index";
            }
            catch(NumberFormatException e){
                response.sendRedirect("/Setare/Categoria/index?page="+1);
            }  
        } else if (userPath.equals("/Categoria/view")) {
            int id = Integer.parseInt(request.getParameter("id"));
            categoria = categoriaFacade.find(id);
            session.setAttribute("categoria", categoria);
            url = "/view";
            
        } else if (userPath.equals("/Categoria/delete")) {
            
            try{
                int categoriaid = Integer.parseInt(request.getParameter("id"));
                Categoria categoriadelete = categoriaFacade.find(categoriaid);
                categoriaFacade.remove(categoriadelete);
            }
            catch(Exception e){
                erro = "Não foi possível remover a categoria";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/Setare/Categoria/index");
                return;
            }
            
            response.sendRedirect("/Setare/Categoria/index");
            
            url = "/index";
        
        } else if (userPath.equals("/Categoria/update")) {

            nome = (String) request.getParameter("nome");
            descricao = request.getParameter("descricao");
            deposito = request.getParameter("deposito");
            hora = request.getParameter("hora");
            BigDecimal hr;
            BigDecimal dep;

            


            if (validate.CategoriaValidator.validateFormRegister(descricao, nome, request)) {
                
                session.setAttribute("MessageError", request.getAttribute("MessageError"));

                response.sendRedirect("/Setare/Categoria");
                return;
            }

            try{
                dep = new BigDecimal(deposito);
                
            }catch (NumberFormatException nfe){
               session.setAttribute("MessageError", "Preço depósito inválido");

                response.sendRedirect("/Setare/Categoria");
                return;
            }
            
            try{
                hr = new BigDecimal(hora);
               
                
            }catch (NumberFormatException nfe){
               session.setAttribute("MessageError", "Preço hora inválido");

                response.sendRedirect("/Setare/Categoria");
                return;
            }   


            categoria = (Categoria) session.getAttribute("categoria");
            
            try {
                categoria.setNome(nome);
                categoria.setDescricao(descricao);
                categoria.setPrecoDeposito(dep);
                categoria.setPrecoPorHora(hr);
                categoriaFacade.edit(categoria);

            } catch (Exception ex) {
                erro = "Erro ao atualizar a categoria!";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/Setare/Categoria");
                return;
            }
            session.setAttribute("MessageSuccess", "Atualizado com sucesso!");
            response.sendRedirect("/Setare/Categoria");
            return;
        }
        try {
            request.getRequestDispatcher("/WEB-INF/view/Categoria" + url + ".jsp").forward(request, response);
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
