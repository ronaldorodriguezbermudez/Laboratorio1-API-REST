<%-- 
    Document   : AgregarCarrera
    Created on : 17/04/2022, 02:05:29 PM
    Author     : Ronaldo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Carrera</title>
       <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
        <link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" href="styles/index.css">
    </head>
    <body>

        <form class="abs-center" method="POST" name="FormAgregarCarrera" action="servletAgregarCarrera">
            <fieldset>

                <div>
                    <h2 style="color: white" >Agregar Carrera</h2>
                    <br>

                </div>

                <div class="form-group">
                    <label style="color: white" for="codigoCarrera">Codigo</label>
                    <input id="codigoCarrera" type="text" class="form-control" value="" name="codigo" placeholder="Ej: EIF">
                </div>
                <div class="form-group">
                    <label style="color: white" for="codigoCarrera">Nombre</label>
                    <input id="codigoCarrera" type="text" class="form-control" value="" name="codigo" placeholder="Ej: Informatica">
                </div>
                <div class="form-group">
                    <label style="color: white" for="codigoCarrera">Titulo</label>
                    <input id="codigoCarrera" type="text" class="form-control" value="" name="codigo" placeholder="Ej: Bachillerato">
                </div>


                <button type="submit" class="btn btn-primary">Agregar Carrera</button>
                <a style="color: red" href="/prueba2/">Salir</a>


            </fieldset>
        </form>



    </body>
</html>
