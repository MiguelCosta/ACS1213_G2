/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Utilizador;
import java.io.IOException;
import java.text.DateFormat;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.UtilizadorFacade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Miguel
 */
@WebServlet(name = "UtilizadorServlet", urlPatterns = {
    "/Utilizador",
    "/Utilizador/login",
    "/Utilizador/logout",
    "/Utilizador/register",
    "/Utilizador/addUser"})
public class UtilizadorServlet extends HttpServlet {

    @EJB
    private UtilizadorFacade utilizadorFacade;

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
        String erro;
        Utilizador user;

        if (userPath.equals("/Utilizador")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            user = utilizadorFacade.UtilizadorLogin(username, password);

            if (user != null) {
                session.setAttribute("user", user);
            } else {
                session.setAttribute("user", null);
            }
            url = "login";

        } else if (userPath.equals("/Utilizador/logout")) {
            session.invalidate();
            response.sendRedirect("/EuroTripsFinder");
            return;
        } else if (userPath.equals("/Utilizador/register")) {
            url = "register";
        } else if (userPath.equals("/Utilizador/addUser")) {

            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String nome = request.getParameter("nome");
            String morada = request.getParameter("morada");
            String datanascimnto = request.getParameter("datanascimento");
            Date dataNasc = new Date(0, 0, 0);

            erro = "";
            // verifica valdiade dos campos
            if (email == null || email.isEmpty()) {
                erro = "O email é obrigatório!";
            } else if (username == null || username.isEmpty()) {
                erro = "O username é obrigatório!";
            } else if (password == null || password.isEmpty()) {
                erro = "A password é obrigatória!";
            } else if (nome == null || nome.isEmpty()) {
                erro = "O nome é obrigatório!";
            }

            try {
                dataNasc = formataData(datanascimnto);
            } catch (Exception e) {
                erro = "Formato da Data inválido";
            }

            if (erro != null && !erro.isEmpty()) {
                session.setAttribute("erro", erro);
                response.sendRedirect("/EuroTripsFinder/Utilizador/register");
                return;
            }

            try {
                user = new Utilizador();
                user.setEmail(email);
                user.setUsername(username);
                user.setPassword(password);
                user.setNome(nome);
                user.setMorada(morada);
                user.setDatanascimento(dataNasc);
                boolean sucesso = utilizadorFacade.UtilizadorInsert(user);
//                utilizadorFacade.create(user);

                if (!sucesso) {
                    erro = "erro ao inserrir utilizador ";
                    session.setAttribute("erro", erro);
                    response.sendRedirect("/EuroTripsFinder/Utilizador/register");
                    return;
                }

            } catch (Exception e) {
                erro = "erro: " + e.getLocalizedMessage();
                session.setAttribute("erro", erro);
                response.sendRedirect("/EuroTripsFinder/Utilizador/register");
                return;
            }

            response.sendRedirect("/EuroTripsFinder");
            return;
        }

        try {
            request.getRequestDispatcher("/WEB-INF/view/" + url + ".jsp").forward(request, response);
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

    /**
     * Converte uma String para um objeto Date. Caso a String seja vazia ou
     * nula, retorna null - para facilitar em casos onde formulários podem ter
     * campos de datas vazios.
     *
     * @param data String no formato dd/MM/yyyy a ser formatada
     * @return Date Objeto Date ou null caso receba uma String vazia ou nula
     * @throws Exception Caso a String esteja no formato errado
     */
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
}
