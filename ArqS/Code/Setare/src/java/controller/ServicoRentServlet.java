/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.msv.datatype.xsd.TotalDigitsFacet;
import entity.Carro;
import entity.Categoria;
import entity.Local;
import entity.Servico;
import entity.Utilizador;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CarroFacade;
import session.CategoriaFacade;
import session.LocalFacade;
import session.ServicoFacade;

/**
 *
 * @author JorgeMaia
 */
@WebServlet(name = "ServicoRentServlet", urlPatterns = {"/ServicoRentServlet", "/ServicoRent", "/ServicoRent/register", "/ServicoRent/registerContinua", "/ServicoRent/registerFinaliza"})
public class ServicoRentServlet extends HttpServlet {

    @EJB
    private LocalFacade localFacade;
    @EJB
    private CategoriaFacade categoriaFacade;
    @EJB
    private ServicoFacade servicoFacade;
    @EJB
    private CarroFacade carroFacade;

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

        String erro = "";
        boolean ok = true; // variavel para indicar se a acao foi executada correta ou nao

        HttpSession session = request.getSession();
        String userPath = request.getServletPath();
        String url = "";

        Utilizador user = new Utilizador();
        user = (Utilizador) session.getAttribute("user");










        if (userPath.equals("/ServicoRent")) {
            session.setAttribute("listalocais", localFacade.findAll());
            session.setAttribute("listacategorias", categoriaFacade.findAll());

            url = "register";
        } else if (userPath.equals("/ServicoRent/register")) {

            //if hora e Data e Veiculo disponiveis fixe
            //verifica Hora e Data
            //Verifica Veiculo Disponivel

            int categoriaId = Integer.parseInt((String) request.getParameter("categoria"));
            int localLevantamentoId = Integer.parseInt((String) request.getParameter("localorigem"));
            int localEntregaId = Integer.parseInt((String) request.getParameter("localchegada"));

            List<Carro> listacarros = categoriaFacade.listacarros(categoriaId);

            session.setAttribute("listacarros", listacarros);
            session.setAttribute("localLevantamentoId", localLevantamentoId);
            session.setAttribute("localEntregaId", localEntregaId);
            session.setAttribute("categoriaId", categoriaId);

            String horaLevantamento = (String) request.getParameter("horalevantamento");
            String horaEntrega = (String) request.getParameter("horaentrega");
            String dataEntrega = (String) request.getParameter("datachegada");
            String dataLevantamento = (String) request.getParameter("datapartida");

            session.setAttribute("horaLevantamento", horaLevantamento);
            session.setAttribute("horaEntrega", horaEntrega);
            session.setAttribute("dataEntrega", dataEntrega);
            session.setAttribute("dataLevantamento", dataLevantamento);

            Date DatahoraLevantamento = formatDate(dataLevantamento, horaLevantamento);
            Date DatahoraEntrega = formatDate(dataEntrega, horaEntrega);

            int diffInDays = (int) ((DatahoraEntrega.getTime() - DatahoraLevantamento.getTime()) / (1000 * 60 * 60 * 24));
            session.setAttribute("dias", diffInDays);



            url = "registerContinua";

        } else if (userPath.equals("/ServicoRent/registerContinua")) {

            if (user == null) {
                session.setAttribute("MessageError", "Tem de ter Login efetuado para efectuar reserva!");
                response.sendRedirect("/Setare/Utilizador");
            } else {

                Categoria cat = categoriaFacade.find((Integer) session.getAttribute("categoriaId"));
                int carroId = Integer.parseInt((String) request.getParameter("id"));
                BigDecimal precoHora = (BigDecimal) cat.getPrecoPorHora();
                BigDecimal dias = new BigDecimal(session.getAttribute("dias").toString());
                BigDecimal total = dias.multiply(precoHora);

                request.setAttribute("categoria", cat);
                session.setAttribute("carro", carroFacade.find(carroId));
                request.setAttribute("localLevantamento", localFacade.find((Integer) session.getAttribute("localLevantamentoId")));
                request.setAttribute("localEntrega", localFacade.find((Integer) session.getAttribute("localEntregaId")));

                String aux;

                String condExtra = "condExtra" + carroId;
                String sctr = "sctr" + carroId;
                String gps = "gps" + carroId;
                String cadeira = "cadeira" + carroId;
                String deposito = "deposito" + carroId;
                String descricao = null;


                int valorExtras = 0;
                aux = request.getParameter(condExtra);
                if (aux != null) {
                    valorExtras = 5 * (Integer) session.getAttribute("dias");
                    descricao = "Condutor Adicional; ";
                    session.setAttribute("condExtra", true);
                }
                else{session.setAttribute("condExtra", false);}
                
                aux = request.getParameter(deposito);
                if (aux != null) {
                    valorExtras = valorExtras + 50;
                    descricao += "Deposito Cheio; ";
                    session.setAttribute("deposito", true);
                }else{session.setAttribute("deposito", false);}
                
                aux = request.getParameter(sctr);
                if (aux != null) {

                    valorExtras = valorExtras + 35 * (Integer) session.getAttribute("dias");
                    descricao += "Seguro Contra todos os risco; ";
                    session.setAttribute("sctr", true);
                }else{session.setAttribute("sctr", false);}
                
                aux = request.getParameter(gps);
                if (aux != null) {
                    valorExtras = valorExtras + 6 * (Integer) session.getAttribute("dias");
                    descricao += "GPS; ";
                    session.setAttribute("gps", true);
                }else{session.setAttribute("gps", false);}
                
                aux = request.getParameter(cadeira);
                if (aux != null) {
                    valorExtras = valorExtras + 8 * (Integer) session.getAttribute("dias");
                    descricao += "Cadeira de Bébé; ";
                    session.setAttribute("cadeira", true);
                }else{session.setAttribute("cadeira", false);}
                
                BigDecimal totalTudo = new BigDecimal(total.intValue() + valorExtras);
                request.setAttribute("extras", descricao);
                request.setAttribute("valorExtras", valorExtras);
                session.setAttribute("total", totalTudo);

            }


            url = "registerFinaliza";


        } else if (userPath.equals("/ServicoRent/registerFinaliza")) {

            String horaLevantamento = (String) session.getAttribute("horaLevantamento");
            String horaEntrega = (String) session.getAttribute("horaEntrega");
            String dataEntrega = (String) session.getAttribute("dataEntrega");
            String dataLevantamento = (String) session.getAttribute("dataLevantamento");
            int localLevantamentoId = (Integer) session.getAttribute("localLevantamentoId");
            int localEntregaId = (Integer) session.getAttribute("localEntregaId");
            Carro carro = (Carro) session.getAttribute("carro");
            //BigDecimal total = new BigDecimal (carro.getId());






            Date DatahoraLevantamento = formatDate(dataLevantamento, horaLevantamento);
            Date DatahoraEntrega = formatDate(dataEntrega, horaEntrega);




            Local partida = localFacade.find(localLevantamentoId);
            Local chegada = localFacade.find(localEntregaId);

            boolean deposito = (Boolean) session.getAttribute("deposito");
            BigDecimal preco = (BigDecimal)session.getAttribute("total");
            boolean cadeira = (Boolean) session.getAttribute("cadeira");
            boolean condExtra = (Boolean) session.getAttribute("condExtra");
            boolean sctr = (Boolean) session.getAttribute("sctr");
            boolean gps = (Boolean) session.getAttribute("gps");


            Servico serv = new Servico();
            serv.setCarroid(carro);
            serv.setDataChegada(DatahoraEntrega);
            serv.setDataPartida(DatahoraLevantamento);
            serv.setDepositoCheio(deposito);
            serv.setLocalPartidaid(partida);
            serv.setLocalChegadaid(chegada);
            serv.setPreco(preco);
            serv.setUtilizadorid(user);
            serv.setCondutorExtra(condExtra);
            serv.setGps(gps);
            serv.setCadeiraBebe(cadeira);
            serv.setSeguroCTRiscos(sctr);

            servicoFacade.create(serv);

            session.setAttribute("MessageSuccess", "Inserido com sucesso");

            response.sendRedirect("/Setare");

        }


        try {
            request.getRequestDispatcher("/WEB-INF/view/ServicoRent/" + url + ".jsp").forward(request, response);
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

    public Date formatDate(String data, String hora) {
        if (data == null || data.equals("")) {
            return null;
        }
        Date date = null;
        String d = data + " " + hora;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = (java.util.Date) formatter.parse(d);
        } catch (ParseException e) {
        }

        return date;
    }
}
