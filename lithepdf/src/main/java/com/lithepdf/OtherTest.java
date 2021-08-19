package com.lithepdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

public class OtherTest {
	
	

	public static void main(String args[]) throws IOException {
		//PdfWriter writer = new PdfWriter("/Users/mlopez/Desktop/output.pdf");
		//PdfDocument pdf = new PdfDocument(writer);
		//Document document = new Document(pdf);
		//document.add(new Paragraph("Hello World!"));
		//document.close();


		// IO
		//File htmlSource = new File("/Users/mlopez/Desktop/rules.html");
		//File pdfDest = new File("/Users/mlopez/Desktop/output.pdf");
		// pdfHTML specific code
		//ConverterProperties converterProperties = new ConverterProperties();
		//HtmlConverter.convertToPdf(new FileInputStream(htmlSource), 
		//new FileOutputStream(pdfDest), converterProperties);
		
		String html = "<h1>Test</h1><p>Hello World</p><img src=\"fileviewer.png\">Exce";
		String baseUri = "/Users/mlopez/Desktop/rules_files";
		
		String remoteUrl = "https://apps.flexwm.com/elcielo/portal/frm/co/elcielo_salecontract.jsp?h=1628179104299WFlowFormat&w=EXT&z=1.6473625E8&resource=";
		
		//new OtherTest().createPdf(baseUri, html, "/Users/mlopez/Desktop/newoutput.pdf");
		new OtherTest().createPdf(new URL(remoteUrl), "/Users/mlopez/Desktop/simpleoutput.pdf");
	}
	
	public void createPdf(String baseUri, String html, String dest) throws IOException {
		ConverterProperties properties = new ConverterProperties();
	    properties.setBaseUri(baseUri);
	    HtmlConverter.convertToPdf(html, new FileOutputStream(dest));
	}
	
	public void createPdf(URL url, String dest) throws IOException {
	    HtmlConverter.convertToPdf(url.openStream(), new FileOutputStream(dest));
	}

}
