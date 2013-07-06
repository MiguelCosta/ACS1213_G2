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
import entity.Servicotaxi;
import entity.Utilizador;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
import session.ServicotaxiFacade;

/**
 *
 * @author JorgeMaia
 */
@WebServlet(name = "ServicoTaxiServlet", urlPatterns = {"/ServicoTaxiServlet", "/ServicoTaxi", "/ServicosTaxi", "/ServicosTaxi/index", "/ServicoTaxi/index", "/ServicoTaxi/register", "/ServicoTaxi/registerContinua", "/ServicoTaxi/registerFinaliza", "/ServicoTaxi/view"})
public class ServicoTaxiServlet extends HttpServlet {

    @EJB
    private LocalFacade localFacade;
    @EJB
    private CategoriaFacade categoriaFacade;
    @EJB
    private ServicotaxiFacade servicoFacade;
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

        
        if (userPath.equals("/ServicoTaxi")) {
            session.setAttribute("listalocais", localFacade.findAll());
            session.setAttribute("listacategorias", categoriaFacade.findAll());

            url = "register";
        } else if (userPath.equals("/ServicosTaxi")) {
            response.sendRedirect("/Setare/ServicosTaxi/index?page="+1);
            
        }else if(userPath.equals("/ServicoTaxi/view")){
            Servicotaxi servicot = servicoFacade.find(Integer.parseInt(request.getParameter("id")));
            if (servicot == null) {
                session.setAttribute("MessageError", "Não existe nenhum pedido.");
                url = "index";
            } else {
                request.setAttribute("servico", servicot);
            }
            url="view";
                    
        }else if(userPath.equals("/ServicoTaxi/index")){
            //request.setAttribute("listartigos", artigoFacade.ArtigoPages(page-1));
            
            try{
                user = (Utilizador) session.getAttribute("user");
            }catch(Exception e){
                response.sendRedirect("/Setare");
            }
            List<Servicotaxi> resultadostaxi = (List<Servicotaxi>) user.getServicotaxiCollection();      

            if (resultadostaxi.isEmpty()) {
                session.setAttribute("MessageError", "Não possui nenhum pedido.");
                
            } else {
                request.setAttribute("resultadostaxi", resultadostaxi);
            }
            url="indexuser";
            
        }else if(userPath.equals("/ServicosTaxi/index")){
            //request.setAttribute("listartigos", artigoFacade.ArtigoPages(page-1));
            
            try{
                int page = 1;
                page = new Integer(request.getParameter("page"));
                int ct = servicoFacade.count();
                int nrpages;
                if(ct<=servicoFacade.limitepage) nrpages= 1;
                else if((ct)%servicoFacade.limitepage == 0) nrpages = ct/servicoFacade.limitepage;
                else nrpages = (ct/servicoFacade.limitepage)+1;
                if(Integer.parseInt(request.getParameter("page")) > nrpages || page == 0){
                    page = 1;
                    response.sendRedirect("/Setare/ServicosTaxi/index?page="+1);
                }
                request.setAttribute("nrpages", String.valueOf(nrpages));
                int max = page*servicoFacade.limitepage;
                if(max > ct) max = ct;
                request.setAttribute("resultado", servicoFacade.findAll().subList((page-1)*servicoFacade.limitepage, max));
                url = "/index";
            }
            catch(NumberFormatException e){
                response.sendRedirect("/Setare/ServicosTaxi/index?page="+1);
            }  
        } else if (userPath.equals("/ServicoTaxi/register")) {

            //if hora e Data e Veiculo disponiveis fixe
            //verifica Hora e Data
            //Verifica Veiculo Disponivel

            int localLevantamentoId = Integer.parseInt((String) request.getParameter("localorigem"));
            int localEntregaId = Integer.parseInt((String) request.getParameter("localchegada"));

            
            
            
            session.setAttribute("localLevantamentoId", localLevantamentoId);
            session.setAttribute("localEntregaId", localEntregaId);
            
            

            String horaLevantamento = (String) request.getParameter("horalevantamento");
            String dataLevantamento = (String) request.getParameter("datapartida");
            String bagagem = (String) request.getParameter("bagagem");
            int  passageiros = Integer.parseInt((String) request.getParameter("passageiros"));

            session.setAttribute("bagagem", bagagem);
            session.setAttribute("passageiros", passageiros);
            session.setAttribute("horaLevantamento", horaLevantamento);
            session.setAttribute("dataLevantamento", dataLevantamento);

            Date DatahoraLevantamento = formatDate(dataLevantamento, horaLevantamento);

            url = "registerContinua";

        } else if (userPath.equals("/ServicoTaxi/registerContinua")) {
            
             if (user == null) {
                session.setAttribute("MessageError", "Tem de ter Login efetuado para efectuar reserva!");
                response.sendRedirect("/Setare/Utilizador");
            } else {
              
                 
              int localLevantamentoId1 =  Integer.parseInt((String) request.getParameter("localLevantamentoId"));
              int localEntregaId1 = Integer.parseInt((String) request.getParameter("localEntregaId"));
              String horaLevantamento = (String) request.getParameter("horalevantamento");
              String dataLevantamento = (String) request.getParameter("dataLevantamento");
              String bagagem = (String) request.getParameter("bagagem");
              int  passageiros = Integer.parseInt((String) request.getParameter("passageiros"));
              
              Local localLevantamentoId = localFacade.find(localLevantamentoId1);
              Local localEntregaId = localFacade.find(localEntregaId1);
              session.setAttribute("localLevantamentoId", localLevantamentoId);
              session.setAttribute("localEntregaId", localEntregaId);
              session.setAttribute("bagagem", bagagem);
              session.setAttribute("passageiros", passageiros);
              session.setAttribute("horaLevantamento", horaLevantamento);
              session.setAttribute("dataLevantamento", dataLevantamento);
            
            
             } 
              url="registerFinaliza";


        } else if (userPath.equals("/ServicoTaxi/registerFinaliza")) {

            /*
                String horaLevantamento = (String) request.getParameter("horaLevantamento");
                String dataLevantamento = (String) request.getParameter("dataLevantamento");
                int localLevantamentoId = Integer.parseInt(request.getParameter("localLevantamentoId"));
                int localEntregaId = Integer.parseInt(request.getParameter("localEntregaId"));
                int passageiros = Integer.parseInt(request.getParameter("passageiros"));
                String bagagem = (String)request.getParameter("bagagem");
*/
            
                String horaLevantamento = (String) session.getAttribute("horaLevantamento");
                String dataLevantamento = (String) session.getAttribute("dataLevantamento");
                Local localLevantamento = (Local)session.getAttribute("localLevantamentoId");
                Local localEntrega = (Local)session.getAttribute("localEntregaId");
                int passageiros = (Integer)session.getAttribute("passageiros");
                String bagagem = (String)session.getAttribute("bagagem");
                    
                
                
                Date DatahoraLevantamento = formatDate(dataLevantamento, horaLevantamento);
          
                boolean bag=false;
                if(bagagem.equals("Sim")) bag = true;
              
                
                Random gen = new Random();
                int randomInt = gen.nextInt(1000);
                Servicotaxi serv = new Servicotaxi();
                serv.setCodigotaxi(String.valueOf(randomInt));
                serv.setBagagem(bag);
                serv.setPassageiros(passageiros);
    
                serv.setDatapartida(DatahoraLevantamento);
                serv.setLocalchegadaid(localEntrega);
                serv.setLocalpartidaid(localLevantamento);
                
                serv.setUtilizadorid(user);
                servicoFacade.create(serv);

                session.setAttribute("MessageSuccess", "Inserido com sucesso");
                
                response.sendRedirect("/Setare");
          
        }


        try {
            request.getRequestDispatcher("/WEB-INF/view/ServicoTaxi/" + url + ".jsp").forward(request, response);
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
