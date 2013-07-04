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
@WebServlet(name = "PDFServlet", urlPatterns = {"/PDFServlet"})
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
        
        Font bfBold20 = new Font(FontFamily.TIMES_ROMAN, 20, Font.BOLD, new BaseColor(0, 0, 0));         
        Font bfBold18 = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD, new BaseColor(0, 0, 0)); 
        Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC, new BaseColor(0, 0, 0)); 
        Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12); 

		
		response.setContentType("application/pdf"); // Code 1
		Document document = new Document();
		try{
			PdfWriter.getInstance(document, 
				response.getOutputStream()); // Code 2
			document.open();
			
            
            document.add(new Paragraph("EurotripsFinder", bfBold20));
            document.add(new Paragraph(" ",bfBold18));
            document.add(new Paragraph("Bilhete número: 20012", bfBold18));
            document.add(new Paragraph(" ",bfBold18));
            document.add(new Paragraph("Utilizador: antonio jose", bf12));
            document.add(new Paragraph(" ",bfBold12));
            document.add(new Paragraph("Data: 15/03/2014 15:00h", bf12));
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
	}
	
}