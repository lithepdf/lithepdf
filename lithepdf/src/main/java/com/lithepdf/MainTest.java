package com.lithepdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.commons.actions.contexts.IMetaInfo;
import java.net.MalformedURLException;
import java.net.URL;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;


public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	public static void test() {
		try {
			String filenameText = "filenametext.pdf";

			// Decodifica la cadena
			String urlText = decodeQueryString("https://sandbox.flexwm.com:443/visual-test/portal/frm/fi/racc_basecfdi.jsp?h=1646839463528format&w=EXT&z=1.0684744E7&resource=");

			// Determina ubicacion y nombre a asignar
			String absolutePath = "C:\\Users\\lobm\\Desktop";

			//HtmlToPdf.createPdf(new URL(urlText), absolutePath + filenameText);
			System.out.println("LithePDF: Generando PDF de URL decodificada: " + urlText);

			ConverterProperties properties = new ConverterProperties();
			properties.setFontProvider(new DefaultFontProvider());

			HtmlConverter.convertToPdf(new URL(urlText).openStream(), new FileOutputStream(absolutePath + filenameText), properties);

			// Obtiene archivo
			//System.out.println("Ruta: " + absolutePath + fileName);
			File file = new File(absolutePath + filenameText);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
