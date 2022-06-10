<%-- 
    Document   : AgregarCurso
    Created on : 17/04/2022, 02:05:29 PM
    Author     : Ronaldo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Curso</title>
        <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
        <link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" href="styles/index.css">
    </head>
    <body>

        <form class="abs-center" method="POST" name="FormAgregarCarrera" action="/">
            <fieldset>

                <div>
                    <h2 style="color: white" >Curso</h2>
                    <br>
                </div>
                <div class="form-group">
                    <input id="Text_Buscar" type="text" class="form-control" value="" name="buscar" placeholder="Codigo Curso" >
                    <br>
                    <button id="Boton_Buscar" type="button" class="btn btn-primary">Buscar</button>
                </div>

                <div class="form-group">
                    <label style="color: white" for="codigoCurso">Codigo</label>
                    <input id="Text_Codigo" type="text" class="form-control" value="" name="codigo" >
                </div>
                <div class="form-group">
                    <label style="color: white" for="nomre">Nombre</label>
                    <input id="Text_Nombre" type="text" class="form-control" value="" name="Nombre" >
                </div>
                <div class="form-group">
                    <label style="color: white" for="creditos">Creditos</label>
                    <input id="Text_Creditos" type="text" class="form-control" value="" name="Creditos" >
                </div>
                <div class="form-group">
                    <label style="color: white" for="HorasSemanales">Horas Semanales</label>
                    <input id="Text_HorasSemanales" type="text" class="form-control" value="" name="Horas">
                </div>


                <button id="Boton_Agregar" type="button" class="btn btn-primary">Agregar</button>
                <button id="Boton_Modificar" type="button" class="btn btn-primary">Modificar</button>
                <a style="color: red" href="/Lab1/">Salir</a>


            </fieldset>
        </form>
    </body>
    <script src="scripts/prueba.js" type="text/javascript"></script>

</html>
