/*
 * LithePDF Project
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package com.lithepdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

@MultipartConfig
public class LithePdfServlet extends HttpServlet {
	private static final long serialVersionUID =1L;

	// Obtiene los parametros
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String filenameText = request.getParameter("filenametext");
		
			// Decodifica la cadena
			String urlText = decodeQueryString(request.getParameter("urltext"));
		
			// Determina ubicacion y nombre a asignar
			String absolutePath = request.getServletContext().getRealPath("") + File.separator + "fileupload" + File.separator;
		
			//HtmlToPdf.createPdf(new URL(urlText), absolutePath + filenameText);
			System.out.println("LithePDF: Generando PDF de URL decodificada: " + urlText + ", Query original: " + request.getParameter("urltext"));
			
			HtmlConverter.convertToPdf(new URL(urlText).openStream(), new FileOutputStream(absolutePath + filenameText));
		
			// Obtiene archivo
			File file = new File(absolutePath + filenameText);
		
			// Prepara archivo para enviarse al navegador
			OutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0){
				out.write(buffer, 0, length);
			}
			in.close();
			out.flush();
		
			// Elimina el archivo creado
			file.delete();	
			
		} catch (Exception e) {
			System.out.println("Error PDF: " + e.toString());
			// Set response content type
		      response.setContentType("text/html");

		      // Actual logic goes here.
		      PrintWriter out = response.getWriter();
		      out.println("<h1>Error PDF: </h1>" + e.toString());
		}
	}

	// Obtiene los parametros
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String filenameText = parameterToString(request, "filenametext");
			String isDownload = parameterToString(request, "isdownload");
			String urlTextRaw = parameterToString(request, "urltext");
			String orient = parameterToString(request, "ornt");
	
			// Revisa parametros
			if (urlTextRaw != null && !urlTextRaw.equals("")) {
				// Decodifica la cadena
				String urlText = decodeQueryString(urlTextRaw);
		
				// Determina ubicacion y nombre a asignar
				String absolutePath = request.getServletContext().getRealPath("") + File.separator + "fileupload" + File.separator;
		
				//HtmlToPdf.createPdf(new URL(urlText), absolutePath + filenameText);
				System.out.println("LithePDF: Generando PDF de URL decodificada: " + urlText);
				
				if (isDownload != null && isDownload.equals("1")) {
					response.setHeader("Content-disposition","attachment; filename=" + filenameText);
				}
		
				// Propiedades
			    ConverterProperties properties = new ConverterProperties();
			    properties.setFontProvider(new DefaultFontProvider());
				
			    // Documento
			    PdfDocument pdfDocument = new PdfDocument(new PdfWriter(new FileOutputStream(absolutePath + filenameText)));
			    
			    // Determina orientacion
			    if (orient.equalsIgnoreCase("h"))
			    	pdfDocument.setDefaultPageSize(PageSize.LETTER.rotate());
			    
			    // Convierte
			    HtmlConverter.convertToPdf(new URL(urlText).openStream(), pdfDocument, properties);
		
				// Obtiene archivo
				//System.out.println("Ruta: " + absolutePath + fileName);
				File file = new File(absolutePath + filenameText);
		
				// Prepara archivo para enviarse al navegador
				OutputStream out = response.getOutputStream();
				FileInputStream in = new FileInputStream(file);
				byte[] buffer = new byte[4096];
				int length;
				while ((length = in.read(buffer)) > 0){
					out.write(buffer, 0, length);
				}
				in.close();
				out.flush();
		
				// Elimina el archivo creado
				file.delete();	
			
			} else {
				System.out.println("Error: parametros nulos");
				// Set response content type
			      response.setContentType("text/html");
	
			      // Actual logic goes here.
			      PrintWriter out = response.getWriter();
			      out.println("<h1>Error: parametros nulos</h1>");
			}
		} catch (Exception e) {
			System.out.println("Error PDF: " + e.toString());
			// Set response content type
		      response.setContentType("text/html");

		      // Actual logic goes here.
		      PrintWriter out = response.getWriter();
		      out.println("<h1>Error PDF: </h1>" + e.toString());
		}
	}

	public static String parameterToString(HttpServletRequest request, String name) {
		String parameter = request.getParameter(name);

		if (parameter != null && !parameter.equals("")) {
			return parameter; 
		} else {
			if (request.getAttribute(name) instanceof String) {
				parameter = (String)request.getAttribute(name);
				if (parameter != null && !parameter.equals("")) {
					return parameter;
				} else {
					return "";				
				}				
			} else {
				return "";
			}
		}  
	}
	
	// Decodifica una cadena query GET
	public static String decodeQueryString(String s) {
		String url = s.replaceAll("%26", "&");
		url = url.replace("%3F", "?");
		url = url.replace("%3D", "=");
		
		return url;
	}
}
