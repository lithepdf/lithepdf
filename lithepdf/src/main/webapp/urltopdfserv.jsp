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
<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<body>
<nav class="navbar navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
      <img src="./img/pdf.png" alt="" height="25" class="d-inline-block align-text-top">
      LithePDF Servlet
    </a>
  </div>
</nav>

<br>

<div class="card">
	<div class="card-header">
		Convertir
	</div>
	<div class="card-body">
		<form method="POST" action="lithepdfservlet">
			<div class="mb-3">
				<label for="urltext" class="form-label">URL</label>
		    	<input type="text" class="form-control" id="urltext" name="urltext" aria-describedby="urlhelp">
		    	<div id="urlhelp" class="form-text">URL de la pagina a convertir a PDF</div>
		  	</div>
		  	<div class="mb-3">
		    	<label for="filenametext" class="form-label">Nombre Archivo</label>
		    	<input type="text" class="form-control" id="filenametext" name="filenametext" value="lithepdf-demo.pdf">
		    	<div id="emailtexthelp" class="form-text">Nombre del archivo PDF a generar</div>
		  	</div>
			<button type="submit" class="btn btn-primary">Generar</button>
		</form>
	</div>
</div>

</body>
</html>
