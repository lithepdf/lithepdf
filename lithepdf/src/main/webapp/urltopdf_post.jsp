<!-- 
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
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lithepdf.*"%>
<%@ page import="java.io.File"%>
<%@ page import="java.net.URL"%>

<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<%

	String urlText = request.getParameter("urltext");
	String filenameText = request.getParameter("filenametext");
	
	// Determina ubicacion y nombre a asignar
	String absolutePath = request.getServletContext().getRealPath("") + File.separator + "fileupload" + File.separator;


	HtmlToPdf.createPdf(new URL(urlText), absolutePath + filenameText);

%>

<body>
<nav class="navbar navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
      <img src="./img/pdf.png" alt="" height="25" class="d-inline-block align-text-top">
      LithePDF
    </a>
  </div>
</nav>

<br>

<div class="card">
	<div class="card-header">
		Archivo Convertido
	</div>
	<div class="card-body">
		Archivo Generado con Exito! 
	</div>
	<div class="card-footer">
		<a class="btn btn-outline-primary" href="./fileupload/<%= filenameText %>" target="_blank">Ver</a>
		<a class="btn btn-outline-success" href="./fileupload/<%= filenameText %>" target="_blank" download>Descargar</a>
		<a class="btn btn-outline-secondary" href="urltopdf.jsp">Reiniciar</a>
	</div>
</div>

</body>
</html>
