<%--
  Created by IntelliJ IDEA.
  User: Esterno
  Date: 22/06/2021
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
   Throwable exceptionType = (Throwable) request.getAttribute("javax.servlet.error.exception");
   Integer statusCode=(Integer) request.getAttribute("javax.servlet.error.status_code");
   String errorMessage= exceptionType.getMessage();
   String servletName= (String) request.getAttribute("javax.servlet.error.servlet_name");
   String requestUri=(String) request.getAttribute("javax.servlet.error.request_uri");
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>MYOP-<%=statusCode%></title>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="./bootstrap-4.5.3-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="./bootstrap-4.5.3-dist/css/bootstrap-grid.css"/>
    <link rel="stylesheet" href="./bootstrap-4.5.3-dist/css/bootstrap-reboot.css"/>
    <link rel="stylesheet" href="./customcss/general.css"/>
</head>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>

<script src="./jslibraries/popper.js"></script>
<script src="./jslibraries/holder.min.js"></script>
<script src="./jslibraries/bootstrap.min.js"></script>
<body>
<div class="fullHeightFooter">
    <h1>Ops....Qualcosa è andato storto!!</h1>
    <h2>Messaggio di errore:<br><%=errorMessage%></h2>
    <h3>Tipo Errore:<br><%=exceptionType%></h3>
    <h3>Codice di Errore :<br><%=statusCode%></h3>

<!--<h2>Servlet who gived the error:<br><%=servletName%></h2>-->
<!--<h2>The Uri who caused the error:<br><%=requestUri%></h2>-->
</div>
</body>
</html>
