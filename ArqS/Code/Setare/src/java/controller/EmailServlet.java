package controller;

import entity.Servico;
import entity.Servicotaxi;
import entity.Utilizador;
import mail.EmailUtility;
import java.io.IOException;
import javax.ejb.EJB;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.UtilizadorFacade;
 
/**
 *
 * @author miltonnunes52
 */
@WebServlet(name = "EmailServlet",
  urlPatterns = {"/enviarmailrent",
                "/enviarmailtaxi"})
public class EmailServlet extends HttpServlet {
    @EJB
    private UtilizadorFacade utilizadorFacade;
    
    @EJB
    private UtilizadorFacade servicoFacade;
    
    private String host;
    private String port;
    private String user;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
    
    public void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException{
		doPost(request, response);
	}
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String userPath = request.getServletPath();
        
        
        Utilizador utilizador = (Utilizador) session.getAttribute("user");
        
        
        if (userPath.equals("/enviarmailrent")) {
            Servico servico = (Servico) request.getAttribute("servico");
            String recipient = utilizador.getEmail();
            String subject = "Confirmacao de Servico";
            String content = "\nConfirmamos a compra do nosso serviço. Abaixo apresenta-se os detalhes do serviço:"
              + "\n"
              + "\n     Utilizador: " + utilizador.getNome()
              + "\n     Categoria do veículo: " + servico.getCarroid().getCategoriaid().getNome()
              + "\n     Descrição do veículo: " + servico.getCarroid().getDescricao()
              + "\n     Estação de partida: " + servico.getLocalPartidaid().getNome()
              + "\n     Data: "+ servico.getDataPartida()
              + "\n     Estação de entrega: " + servico.getLocalChegadaid().getNome()
              + "\n     Data: " + servico.getDataChegada()
              + "\n     Extras: "
              + "\n         Cadeira bebe: " + servico.getCadeiraBebe()
              + "\n         GPS: " + servico.getGps()
              + "\n         Depósito Cheio: " + servico.getDepositoCheio()
              + "\n         Condutor Adicional: " + servico.getCondutorExtra()
              + "\n         Seguro contra todos os riscos: " + servico.getSeguroCTRiscos()
              + "\n     Total pago: " + servico.getPreco()
              + "\n  "
              + "\nAgradecemos a sua escolha e desejamos-lhe uma excelente viagem!" 
              + "\n"
              + "\n";

            String resultMessage = "";

            try {
                EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
                        content);
                resultMessage = "The e-mail was sent successfully";
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = "There were an error: " + ex.getMessage();
            } finally {

                request.setAttribute("Message", resultMessage);
                request.getRequestDispatcher("/bilheterent").forward(request, response);


            }
            
        
        
        } else if (userPath.equals("/enviarmailtaxi")) {
            
            Servicotaxi servico = (Servicotaxi) request.getAttribute("servico");
            
            String recipient = utilizador.getEmail();
            String subject = "Confirmacao de Servico";
            String content = "\nConfirmamos a compra do nosso serviço. Abaixo apresenta-se os detalhes do serviço:"
              + "\n  "
              + "\n     Utilizador: " + utilizador.getNome()
              + "\n     Taxi: " + servico.getCodigotaxi()
              + "\n     Estação de partida: " + servico.getLocalpartidaid().getNome()
              + "\n     Data: "+ servico.getDatapartida()
              + "\n     Estação de entrega: " + servico.getLocalchegadaid().getNome()
              + "\n     Bagagem: " + servico.getBagagem()
              + "\n     Passageiros: " + servico.getPassageiros()
              + "\n  "
              + "\nAgradecemos a sua escolha e desejamos-lhe uma excelente viagem!" 
              + "\n"
              + "\n";

            String resultMessage = "";

            try {
                EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
                        content);
                resultMessage = "The e-mail was sent successfully";
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = "There were an error: " + ex.getMessage();
            } finally {

                request.setAttribute("Message", resultMessage);
                request.getRequestDispatcher("/bilhetetaxi").forward(request, response);


            }
        
        }
        
        
       
    }
}