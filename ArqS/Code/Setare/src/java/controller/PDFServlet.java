/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.BaseColor;
import java.io.IOException;
import javax.servlet.ServletConfig;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import entity.Servico;
import entity.Servicotaxi;
import entity.Utilizador;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author miltonnunes52
 */
@WebServlet(name = "PDFServlet", urlPatterns = {"/bilheterent","/bilhetetaxi"})
public class PDFServlet extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException{
        
        HttpSession session = request.getSession();
        String userPath = request.getServletPath();
        
        Font bfBold20 = new Font(FontFamily.TIMES_ROMAN, 20, Font.BOLD, new BaseColor(0, 0, 0));         
        Font bfBold18 = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD, new BaseColor(0, 0, 0)); 
        Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC, new BaseColor(0, 0, 0)); 
        Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12); 

        
        if (userPath.equals("/bilheterent")) {
            
            response.setContentType("application/pdf"); // Code 1
		Document document = new Document();
		try{
			PdfWriter.getInstance(document, 
				response.getOutputStream()); // Code 2
			document.open();
            
            Utilizador utilizador = (Utilizador) session.getAttribute("user");
            Servico servico = (Servico) request.getAttribute("servico");


            String content = "\nDados do Bilhete:"
              + "\n     Utilizador: " + utilizador.getNome()
              + "\n     Categoria do veículo: " + servico.getCarroid().getCategoriaid().getNome()
              + "\n     Descrição do veículo: " + servico.getCarroid().getDescricao()
              + "\n     Estação de partida: " + servico.getLocalPartidaid().getNome()
              + "\n     Data: "+ servico.getDataPartida()
              + "\n     Estação de entrega: " + servico.getLocalChegadaid().getNome()
              + "\n     Data" + servico.getDataChegada()
              + "\n     Total a pagar: " + servico.getPreco()
              + "\n     Agradecemos a sua escolha e desejamos-lhe uma excelente viagem!" 
              + "\n"
              + "\n";



                document.add(new Paragraph("Seture", bfBold20));
                document.add(new Paragraph(" ",bfBold18));
                document.add(new Paragraph("Bilhete", bfBold18));
                document.add(new Paragraph(" ",bfBold18));
                document.add(new Paragraph(content, bf12));
                document.add(new Paragraph(" ",bfBold12));
                document.add(new Paragraph("Pagamento: efectuado",bf12));
                document.add(new Paragraph(" ",bfBold18));

                document.add(new Paragraph("Não perca este bilhete. A sua viagem depende dele!",bfBold12));
                document.add(new Paragraph("",bfBold18));
                document.add(new Paragraph("Muito Obrigado pela sua preferência!",bfBold12));


                document.close(); 
            }catch(DocumentException e){
                e.printStackTrace();
            }
            
        } else if (userPath.equals("/bilhetetaxi")) {
            
            response.setContentType("application/pdf"); // Code 1
		Document document = new Document();
		try{
			PdfWriter.getInstance(document, 
				response.getOutputStream()); // Code 2
			document.open();
            
            Utilizador utilizador = (Utilizador) session.getAttribute("user");
            Servicotaxi servico = (Servicotaxi) request.getAttribute("servico");


            String content = "\nDados do Bilhete:"
              + "\n     Utilizador: " + utilizador.getNome()
              + "\n     Descrição: Bilhete de Taxi "
              + "\n     Taxi: " + servico.getCodigotaxi()
              + "\n     Estação de partida: " + servico.getLocalpartidaid().getNome()
              + "\n     Data: "+ servico.getDatapartida()
              + "\n     Estação de entrega: " + servico.getLocalchegadaid().getNome()
              + "\n     Agradecemos a sua escolha e desejamos-lhe uma excelente viagem!" 
              + "\n"
              + "\n";



                document.add(new Paragraph("Seture", bfBold20));
                document.add(new Paragraph(" ",bfBold18));
                document.add(new Paragraph("Bilhete", bfBold18));
                document.add(new Paragraph(" ",bfBold18));
                document.add(new Paragraph(content, bf12));
                document.add(new Paragraph(" ",bfBold18));

                document.add(new Paragraph("Não perca este bilhete. A sua viagem depende dele!",bfBold12));
                document.add(new Paragraph("",bfBold18));
                document.add(new Paragraph("Muito Obrigado pela sua preferência!",bfBold12));


                document.close(); 
            }catch(DocumentException e){
                e.printStackTrace();
            }
            
            
        }
		
		

	}
	
}
