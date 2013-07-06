/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Carro;
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
import session.CarroFacade;
import session.CategoriaFacade;
import session.CidadeFacade;
import session.LocalFacade;

/**
 *
 * @author miltonnunes52
 */
@WebServlet(name = "CarroServlet", urlPatterns = {"/Carro/register", "/Carro/add", "/Carro", "/Carro/view", "/Carro/update", "/Carro/delete", "/Carro/index"})
public class CarroServlet extends HttpServlet {
    
    @EJB
    private CarroFacade carroFacade;
    @EJB
    private CategoriaFacade categoriaFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String erro = "";
        boolean ok = true; // variavel para indicar se a acao foi executada correta ou nao
        String userPath = request.getServletPath();
        String url = "";
        String matricula = null;
        String descricao = null;
        String imagem = null;
        String categoriaid = null;
        
       
        HttpSession session = request.getSession();

        Carro carro;

         

        
       if (userPath.equals("/Carro/register")) {
           request.setAttribute("listcategorias", categoriaFacade.findAll());
            url = "/register";
        } else if (userPath.equals("/Carro/add")) {

            matricula = (String) request.getParameter("matricula");
            descricao = (String) request.getParameter("descricao");
            imagem = request.getParameter("imagem");
            categoriaid = request.getParameter("categoriaid");
            BigDecimal hr;
            BigDecimal dep;

            


            if (validate.CategoriaValidator.validateFormRegister(descricao,matricula, request)) {
                
                session.setAttribute("MessageError", request.getAttribute("MessageError"));

                response.sendRedirect("/Setare/Carro/register");
                return;
            }

            
            try {
                
                Categoria cat = categoriaFacade.find(Integer.parseInt(categoriaid));
                
                carro = new Carro();
                carro.setMatricula(matricula);
                carro.setDescricao(descricao);
                carro.setImagem(imagem);
                carro.setCategoriaid(cat);
                
                carroFacade.create(carro);
            } catch (Exception ex) {
                erro = "Erro ao inserir carro";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/Setare/Carro/register");
                return;
            }

            response.sendRedirect(request.getServletContext().getContextPath() + "/Carro");
        } else if (userPath.equals("/Carro")) {
            response.sendRedirect("/Setare/Carro/index?page="+1);
        }else if(userPath.equals("/Carro/index")){
            //request.setAttribute("listartigos", artigoFacade.ArtigoPages(page-1));
            
            try{
                int page = 1;
                page = new Integer(request.getParameter("page"));
                int ct = carroFacade.count();
                int nrpages;
                if(ct<=carroFacade.limitepage) nrpages= 1;
                else if((ct)%carroFacade.limitepage == 0) nrpages = ct/carroFacade.limitepage;
                else nrpages = (ct/carroFacade.limitepage)+1;
                if(Integer.parseInt(request.getParameter("page")) > nrpages || page == 0){
                    page = 1;
                    response.sendRedirect("/Setare/Carro/index?page="+1);
                }
                request.setAttribute("nrpages", String.valueOf(nrpages));
                int max = page*carroFacade.limitepage;
                if(max > ct) max = ct;
                request.setAttribute("resultado", carroFacade.findAll().subList((page-1)*carroFacade.limitepage, max));
                url = "/index";
            }
            catch(NumberFormatException e){
                response.sendRedirect("/Setare/Carro/index?page="+1);
            }  
        } else if (userPath.equals("/Carro/view")) {
            int id = Integer.parseInt(request.getParameter("id"));
            carro = carroFacade.find(id);
            session.setAttribute("carro", carro);
            url = "/view";
            
        } else if (userPath.equals("/Carro/delete")) {
            
            try{
                int carroid = Integer.parseInt(request.getParameter("id"));
                Carro carrodelete = carroFacade.find(carroid);
                carroFacade.remove(carrodelete);
            }
            catch(Exception e){
                erro = "Não foi possível remover carro";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/Setare/Carro/index");
                return;
            }
            
            response.sendRedirect("/Setare/Carro/index");
            
            url = "/index";
        
        } else if (userPath.equals("/Carro/update")) {

            matricula = (String) request.getParameter("matricula");
            descricao = request.getParameter("descricao");
            imagem = request.getParameter("imagem");
            categoriaid = request.getParameter("categoriaid");
            

            


            if (validate.CarroValidator.validateFormRegister(descricao, matricula, request)) {
                
                session.setAttribute("MessageError", request.getAttribute("MessageError"));

                response.sendRedirect("/Setare/Carro");
                return;
            }

           

            carro = (Carro) session.getAttribute("carro");
            
            try {
                
                carro.setMatricula(matricula);
                carro.setDescricao(descricao);
                carro.setImagem(imagem);
                carroFacade.edit(carro);

            } catch (Exception ex) {
                erro = "Erro ao atualizar carro!";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/Setare/Carro");
                return;
            }
            session.setAttribute("MessageSuccess", "Atualizado com sucesso!");
            response.sendRedirect("/Setare/Carro");
            return;
        }
        try {
            request.getRequestDispatcher("/WEB-INF/view/Carro" + url + ".jsp").forward(request, response);
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
