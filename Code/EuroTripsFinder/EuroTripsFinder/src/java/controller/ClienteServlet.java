/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.UtilizadorServlet.formataData;
import entity.Cliente;
import entity.Contrato;
import entity.Percurso;
import entity.Utilizador;
import entity.Viagem;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ArtigopublicitarioFacade;
import session.ClienteFacade;
import session.ContratoFacade;
import session.UtilizadorFacade;

/**
 *
 * @author miltonnunes52
 */
@WebServlet(name = "ClienteServlet",
  urlPatterns = {"/Cliente",
                "/Cliente/register",
                "/Cliente/add",
                "/Cliente/view",
                "/Cliente/update",
                "/Cliente/index"})
public class ClienteServlet extends HttpServlet {

    @EJB
    private  ClienteFacade clienteFacade;
    @EJB
    private ArtigopublicitarioFacade artigoFacade;
    @EJB
    private  UtilizadorFacade utilizadorFacade;
    
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
        
        Cliente cliente;
        Utilizador user;
        
        Date dataNasc = new Date(0, 0, 0);
        String email;
        String username;
        String password;
        String nome;
        String morada;
        String datanascimnto;
        String nif;
        String contacto;
        String clienteid;
        
         
        // Publiciade
        session.setAttribute("artigorandom", artigoFacade.ArtigoRandom());

        
        if(userPath.equals("/Cliente")){
            response.sendRedirect("/EuroTripsFinder/Cliente/index?page="+1);
        }else if(userPath.equals("/Cliente/index")){
            //request.setAttribute("listartigos", artigoFacade.ArtigoPages(page-1));
            
            try{
                int page = 1;
                page = new Integer(request.getParameter("page"));
                int ct = clienteFacade.count();
                int nrpages;
                if(ct<=20) nrpages= 1;
                else if((ct)%clienteFacade.limitepage == 0) nrpages = ct/clienteFacade.limitepage;
                else nrpages = (ct/clienteFacade.limitepage)+1;
                if(Integer.parseInt(request.getParameter("page")) > nrpages || page == 0){
                    page = 1;
                    response.sendRedirect("/EuroTripsFinder/Cliente/index?page="+1);
                }
                request.setAttribute("nrpages", String.valueOf(nrpages));
                int max = page*clienteFacade.limitepage;
                if(max > ct) max = ct;
                request.setAttribute("listclientes", clienteFacade.findAll().subList((page-1)*clienteFacade.limitepage, max));
                url = "index";
            }
            catch(NumberFormatException e){
                response.sendRedirect("/EuroTripsFinder/Cliente/index?page="+1);
            }
        }else if(userPath.equals("/Cliente/register")){ 
            url = "register";
        }else if (userPath.equals("/Cliente/add")) {

            email = request.getParameter("email");
            username = request.getParameter("username");
            password = request.getParameter("password");
            nome = request.getParameter("nome");
            morada = request.getParameter("morada");
            nif = request.getParameter("nif");
            contacto = request.getParameter("contacto");
            datanascimnto = request.getParameter("datanascimento");

            // Valida de informação do form
            ok = validate.ClienteValidator.validateFormRegister(email,
                    username,
                    password,
                    nome,
                    morada,
                    datanascimnto,
                    nif,
                    contacto,
                    request);

            try {
                dataNasc = formataData(datanascimnto);
            } catch (Exception e) {
                erro = "Formato da Data inválido";
            }

            if (!ok || !erro.isEmpty()) {
                session.setAttribute("MessageError", request.getAttribute("MessageError"));
                response.sendRedirect("/EuroTripsFinder/Cliente/register");
                return;
            }

            user = new Utilizador();
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            user.setNome(nome);
            user.setMorada(morada);
            user.setDatanascimento(dataNasc);

            user.setDataregisto(new Date());
            user.setFuncao("Cliente");
            user.setPercursoCollection(new ArrayList<Percurso>());
            user.setViagemCollection(new ArrayList<Viagem>());
            
            
            cliente = new Cliente();
            cliente.setContacto(contacto);
            cliente.setNif(nif);
            cliente.setContratoCollection(new ArrayList<Contrato>());

            
            try {
                
               
                int i = utilizadorFacade.UtilizadorUltimo().getId();
                i++;
                cliente.setId(i);
                user.setCliente(cliente);
                utilizadorFacade.create(user);
                //isto é para actualizar o index logo direito
                Utilizador usertemp = utilizadorFacade.find(i);
                cliente.setUtilizador(usertemp);
                clienteFacade.edit(cliente);
                  
                

            } catch (Exception ex) {
                erro = "Erro ao inserir cliente";
                session.setAttribute("MessageError", erro);
                response.sendRedirect("/EuroTripsFinder/Cliente/register");
                return;
            }

            session.setAttribute("MessageSuccess", "Cliente registado.");

            response.sendRedirect("/EuroTripsFinder/Cliente");
            return;
        } else if(userPath.equals("/Cliente/view")){
            url= "view";
            
            cliente = clienteFacade.find(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("cliente", cliente);
            
            request.setAttribute("contratos", cliente.getContratoCollection());

        }else if(userPath.equals("/Cliente/update")){
            email = request.getParameter("email");
            username = request.getParameter("username");
            password = request.getParameter("password");
            nome = request.getParameter("nome");
            morada = request.getParameter("morada");
            nif = request.getParameter("nif");
            contacto = request.getParameter("contacto");
            datanascimnto = request.getParameter("datanascimento");
            clienteid = request.getParameter("id");

            // Valida de informação do form
            ok = validate.ClienteValidator.validateFormRegister(email,
                    username,
                    password,
                    nome,
                    morada,
                    datanascimnto,
                    nif,
                    contacto,
                    request);

            try {
                dataNasc = formataData(datanascimnto);
            } catch (Exception e) {
                erro = "Formato da Data inválido";
            }

            if (!ok || !erro.isEmpty()) {
                session.setAttribute("MessageError", request.getAttribute("MessageError"));
                response.sendRedirect("/EuroTripsFinder/Cliente/view?id="+clienteid);
                return;
            }
            
            
            cliente = clienteFacade.find(Integer.parseInt(request.getParameter("id")));
            cliente.setContacto(contacto);
            cliente.setNif(nif);
            
            user = cliente.getUtilizador();
            user.setEmail(email);
            user.setPassword(password);
            user.setNome(nome);
            user.setMorada(morada);
            user.setDatanascimento(dataNasc);
            user.setCliente(cliente);
            
            
            
            try {
                //nao sei se é assim
                utilizadorFacade.edit(user);
            } catch (Exception e) {
                session.setAttribute("MessageError", "Erro ao atualizar a informação.");
                response.sendRedirect("/EuroTripsFinder/Cliente/view?id="+clienteid);
                return;
            }

            session.setAttribute("MessageSuccess", "Cliente actualizado.");
            
            response.sendRedirect("/EuroTripsFinder/Cliente");
            return;        
        }
         
         
        try {
            request.getRequestDispatcher("/WEB-INF/view/Cliente/" + url + ".jsp").forward(request, response);
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
