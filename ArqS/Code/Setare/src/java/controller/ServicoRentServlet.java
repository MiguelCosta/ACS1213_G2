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
import java.util.Calendar;
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
import validate.DataHoraValidator;

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
        DataHoraValidator dhv = new DataHoraValidator();


        if (userPath.equals("/ServicoRent")) {
            session.setAttribute("listalocais", localFacade.findAll());
            session.setAttribute("listacategorias", categoriaFacade.findAll());

            url = "register";
        } else if (userPath.equals("/ServicoRent/register")) {

            String horaLevantamento = (String) request.getParameter("horalevantamento");
            String horaEntrega = (String) request.getParameter("horaentrega");
            String dataEntrega = (String) request.getParameter("datachegada");
            String dataLevantamento = (String) request.getParameter("datapartida");

            if (dhv.verificaDataHora(dataLevantamento, dataEntrega, horaLevantamento, horaEntrega, request) == true) {
                Date DatahoraLevantamento = formatDate(dataLevantamento, horaLevantamento);
                Date DatahoraEntrega = formatDate(dataEntrega, horaEntrega);
                if (DatahoraLevantamento.compareTo(DatahoraEntrega) > 0) {

                    session.setAttribute("MessageError", "A Data de Levantamento não pode ser inferior a de chegada!");
                    url = "register";

                } else {

                    int categoriaId = Integer.parseInt((String) request.getParameter("categoria"));
                    int localLevantamentoId = Integer.parseInt((String) request.getParameter("localorigem"));
                    int localEntregaId = Integer.parseInt((String) request.getParameter("localchegada"));

                    List<Carro> listacarros = categoriaFacade.listacarros(categoriaId);                   

                    session.setAttribute("listacarros", listacarros);
                    session.setAttribute("localLevantamentoId", localLevantamentoId);
                    session.setAttribute("localEntregaId", localEntregaId);
                    session.setAttribute("categoriaId", categoriaId);
                    request.setAttribute("precoCategoria", categoriaFacade.find(categoriaId).getPrecoPorHora().intValue());


                    request.setAttribute("horaLevantamento", horaLevantamento);
                    request.setAttribute("horaEntrega", horaEntrega);
                    session.setAttribute("dataEntrega", dataEntrega);
                    session.setAttribute("dataLevantamento", dataLevantamento);


                    session.setAttribute("dataPartida", DatahoraLevantamento);
                    session.setAttribute("dataChegada", DatahoraEntrega);

                    int diffInDays = (int) ((DatahoraEntrega.getTime() - DatahoraLevantamento.getTime()) / (1000 * 60 * 60 * 24));
                    request.setAttribute("dias", diffInDays);
                    url = "registerContinua";
                }
            } else {
                session.setAttribute("MessageError", "As datas/ horas não podem ser nulas");
                url = "register";
            }


        } else if (userPath.equals("/ServicoRent/registerContinua")) {

            if (user == null) {
                session.setAttribute("MessageError", "Tem de ter Login efetuado para efectuar reserva!");
                response.sendRedirect("/Setare/Utilizador");
            } else {


                int carroId = Integer.parseInt((String) request.getParameter("id"));
                String horaLevantamento = request.getParameter("horalevantamento");
                String horaEntrega = request.getParameter("horaEntrega");
                String dataEntrega = request.getParameter("datachegada");
                String dataLevantamento = request.getParameter("datapartida");
                Date DatahoraLevantamento = formatDate(dataLevantamento, horaLevantamento);
                Date DatahoraEntrega = formatDate(dataEntrega, horaEntrega);
                int diffInDays = (int) ((DatahoraEntrega.getTime() - DatahoraLevantamento.getTime()) / (1000 * 60 * 60 * 24));
                request.setAttribute("dias", diffInDays);
                if (DatahoraLevantamento.compareTo(DatahoraEntrega) > 0) {
                    session.setAttribute("MessageError", "A Data de Levantamento não pode ser inferior a de chegada!");
                    url = "registerContinua";

                } else {

                    if (servicoFacade.verificaCarro(carroFacade.find(carroId), DatahoraEntrega, DatahoraLevantamento)) {


                        Categoria cat = categoriaFacade.find((Integer) session.getAttribute("categoriaId"));
                        BigDecimal precoHora = new BigDecimal(cat.getPrecoPorHora().intValue() * 24);

                        BigDecimal dias = new BigDecimal(String.valueOf(diffInDays));
                        BigDecimal total = dias.multiply(precoHora);

                        request.setAttribute("categoria", cat);
                        session.setAttribute("carro", carroFacade.find(carroId));
                        session.setAttribute("localLevantamento", localFacade.find(Integer.parseInt(request.getParameter("localorigem"))));
                        session.setAttribute("localEntrega", localFacade.find(Integer.parseInt(request.getParameter("localchegada"))));
                        request.setAttribute("dias", dias);
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
                            valorExtras = 5 * Integer.parseInt(request.getParameter("dias"));
                            descricao = "Condutor Adicional; ";
                            session.setAttribute("condExtra", true);
                        } else {
                            session.setAttribute("condExtra", false);
                        }

                        aux = request.getParameter(deposito);
                        if (aux != null) {
                            valorExtras = valorExtras + cat.getPrecoDeposito().intValue();
                            descricao += "Deposito Cheio; ";
                            session.setAttribute("deposito", true);
                        } else {
                            session.setAttribute("deposito", false);
                        }

                        aux = request.getParameter(sctr);
                        if (aux != null) {

                            valorExtras = valorExtras + 35 * Integer.parseInt(request.getParameter("dias"));
                            descricao += "Seguro Contra todos os risco; ";
                            session.setAttribute("sctr", true);
                        } else {
                            session.setAttribute("sctr", false);
                        }

                        aux = request.getParameter(gps);
                        if (aux != null) {
                            valorExtras = valorExtras + 6 * Integer.parseInt(request.getParameter("dias"));
                            descricao += "GPS; ";
                            session.setAttribute("gps", true);
                        } else {
                            session.setAttribute("gps", false);
                        }

                        aux = request.getParameter(cadeira);
                        if (aux != null) {
                            valorExtras = valorExtras + 8 * Integer.parseInt(request.getParameter("dias"));
                            descricao += "Cadeira de Bébé; ";
                            session.setAttribute("cadeira", true);
                        } else {
                            session.setAttribute("cadeira", false);
                        }
                        if (descricao.equals("")) {
                        descricao = "Não foi requisitado nenhum extra no pedido.";
                        }

                        BigDecimal totalTudo = new BigDecimal(total.intValue() + valorExtras);
                        request.setAttribute("extras", descricao);
                        request.setAttribute("valorExtras", valorExtras);
                        session.setAttribute("total", totalTudo);
                        session.setAttribute("dataPartida", DatahoraLevantamento);
                        session.setAttribute("dataChegada", DatahoraEntrega);


                        request.setAttribute("datachegadaString", dataEntrega);
                        request.setAttribute("datapartidaString", dataLevantamento);
                        request.setAttribute("horaEntrega", horaEntrega);
                        request.setAttribute("horaLevantamento", horaLevantamento);



                        url = "registerFinaliza";
                    } else {
                        session.setAttribute("MessageError", "Esta viatura já se encontra reservada neste intervalo de datas!");
                        url = "registerContinua";

                    }



                }
            }




        } else if (userPath.equals(
                "/ServicoRent/registerFinaliza")) {


            Carro carro = (Carro) session.getAttribute("carro");
            //BigDecimal total = new BigDecimal (carro.getId());


            Date DatahoraLevantamento = (Date) session.getAttribute("dataPartida");
            Date DatahoraEntrega = (Date) session.getAttribute("dataChegada");


            Local partida = (Local) session.getAttribute("localLevantamento");
            Local chegada = (Local) session.getAttribute("localEntrega");

            boolean deposito = (Boolean) session.getAttribute("deposito");
            BigDecimal preco = (BigDecimal) session.getAttribute("total");
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
