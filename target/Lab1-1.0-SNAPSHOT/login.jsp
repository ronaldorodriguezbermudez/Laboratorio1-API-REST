<%-- 
    Document   : login
    Created on : 15 abr. 2022, 14:49:47
    Author     : Josué
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        
        <%
            response.setHeader("Cache-Control", "no-chache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expieres", 0);
        %>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
        <script src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>

        <link rel="stylesheet" type="text/css" href="styles/login.css" th:href="@{/css/index.css}">
    </head>
    <body>
        <div class="modal-dialog text-center">
            <div class="col-sm-8 main-section">
                <div class="modal-content">
                    <div class="col-12 user-img">
                        <img src="styles/img/user.png" th:src="@{/img/user.png}"/>
                    </div>
                    <form class="col-12" method="post" action="Login" name="formulario">
                        <div class="form-group" id="user-group">
                            <input type="text" class="form-control" placeholder="Nombre de usuario" name="username" id="username" required="true"/>
                        </div>
                        <div class="form-group" id="contrasena-group">
                            <input type="password" class="form-control" placeholder="Contrasena" name="password" id="password" required="true"/>
                        </div>
                        <button type="submit" class="btn btn-primary"><i class="fas fa-sign-in-alt"></i>  Ingresar </button>
                    </form>
                    <!-- 
                    <div th:if="${param.error}" class="alert alert-danger" role="alert">
                        Invalid username and password.
                    </div>
                    <div th:if="${param.logout}" class="alert alert-success" role="alert">
                        You have been logged out.
                    </div>-->
                </div>
            </div>
        </div>
        <script src="scripts/indexLogin.js" type="text/javascript"></script>
    </body>
</html>
