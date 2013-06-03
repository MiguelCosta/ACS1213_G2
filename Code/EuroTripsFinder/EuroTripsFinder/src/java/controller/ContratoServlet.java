/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.UtilizadorServlet.formataData;
import entity.Cliente;
import entity.Contrato;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ClienteFacade;
import session.ContratoFacade;

/**
 *
 * @author miltonnunes52
 */
@WebServlet(name = "ContratoServlet", 
  urlPatterns = {"/Contrato",
                "/Contrato/register",
                "/Contrato/add",
                "/Contrato/view",
                "/Contrato/update"})
public class ContratoServlet extends HttpServlet {
    
    @EJB
    private ContratoFacade contratoFacade;
    @EJB
    private ClienteFacade clienteFacade;

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
        
        Contrato contrato;
        
        
        Date dataInic = new Date(0, 0, 0);
        Date dataFim = new Date(0, 0, 0);
        String valor;
        String datainicio;
        String datafim;
        String clienteid;
        int contratoid;
        
        if(userPath.equals("/Contrato")){
            request.setAttribute("listcontratos", contratoFacade.findAll());
            url = "index";
        }else if(userPath.equals("/Contrato/register")){ 
            request.setAttribute("listclientes", clienteFacade.findAll());
            url = "register";
        }else if (userPath.equals("/Contrato/add")) {
            valor = request.getParameter("valor");
            datainicio = request.getParameter("datainicio");
            datafim = request.getParameter("datafim");
            clienteid = request.getParameter("clienteid");
            
            
            
            ok = validate.ContratoValidator.validateFormRegister(valor,
                    datainicio,
                    datafim,
                    request);
            
            if (!ok || !erro.isEmpty()) {
                session.setAttribute("MessageError", request.getAttribute("MessageError"));
                response.sendRedirect("/EuroTripsFinder/Contrato/register");
                return;
            }
            
             try {
                dataInic = formataData(datainicio);
            } catch (Exception e) {
                erro = "Formato da Data Inicio inválido";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Contrato/register");
                return;
            }
            
            try {
                dataFim = formataData(datafim);
            } catch (Exception e) {
                erro = "Formato da Data Fim inválido";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Contrato/register");
                return;
            }
            
            contrato = new Contrato();
            BigDecimal val = new BigDecimal(valor);
            
            contrato.setValor(val);
            contrato.setDatainicio(dataInic);
            contrato.setDatafim(dataFim);
            Cliente client = (Cliente) clienteFacade.find(Integer.parseInt(clienteid));
            contrato.setClienteid(client);
            
            try {
                contratoFacade.create(contrato);
            } catch (Exception ex) {
                erro = "Erro ao inserir contrato";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Contrato/register");
                return;
            }
             
            session.setAttribute("MessageSuccess", "Contrato registado.");

            response.sendRedirect("/EuroTripsFinder/Contrato");
            return;   
            
        }else if(userPath.equals("/Contrato/view")){
            url= "view";
            contratoid = Integer.parseInt(request.getParameter("id"));
            contrato = contratoFacade.find(contratoid); 
            request.setAttribute("contrato", contrato);
            
            request.setAttribute("artigos", contrato.getArtigopublicitarioCollection());

        }else if(userPath.equals("/Contrato/update")){
            valor = request.getParameter("valor");
            datainicio = request.getParameter("datainicio");
            datafim = request.getParameter("datafim");
            contratoid = Integer.parseInt(request.getParameter("id"));
            
            ok = validate.ContratoValidator.validateFormRegister(valor,
                    datainicio,
                    datafim,
                    request);
            
            if (!ok || !erro.isEmpty()) {
                session.setAttribute("MessageError", request.getAttribute("MessageError"));
                
                request.setAttribute("contrato", contratoFacade.find(contratoid));
                response.sendRedirect("/EuroTripsFinder/Contrato/view?id="+contratoid);
                return;
            }
            
            try {
                dataInic = formataData(datainicio);
            } catch (Exception e) {
                erro = "Formato da Data Inicio inválido";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Contrato/view?id="+contratoid);
                return;
            }
            
            try {
                dataFim = formataData(datafim);
            } catch (Exception e) {
                erro = "Formato da Data Fim inválido";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Contrato/view?id="+contratoid);
                return;
            }
            
            
            contrato = contratoFacade.find(Integer.parseInt(request.getParameter("id")));
            
            contrato.setValor(new BigDecimal(valor));
            contrato.setDatainicio(dataInic);
            contrato.setDatafim(dataFim);
            
             try {
                //nao sei se é preciso adicionar mais qualquer coisa
                contratoFacade.edit(contrato);
            } catch (Exception ex) {             
                session.setAttribute("MessageError", "Erro ao atualizar a informação.");
                response.sendRedirect("/EuroTripsFinder/Contrato/view?id="+contratoid);
                return;
            }
             
            session.setAttribute("MessageSuccess", "Contrato actualizado.");
            
            response.sendRedirect("/EuroTripsFinder/Contrato");
            return;
            
        }
        
        
        
        
        try {
            request.getRequestDispatcher("/WEB-INF/view/Contrato/" + url + ".jsp").forward(request, response);
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
