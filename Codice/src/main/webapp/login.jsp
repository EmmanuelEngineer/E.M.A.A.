<%--
  Created by IntelliJ IDEA.
  User: Lethal Muriel
  Date: 03/01/2022
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="./bootstrap-4.5.3-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="./customcss/general.css"/>

</head>
<body class="text-center">
<div id = "pageContenent">
    <form class="form-signin" action="./Login" method="post">
        <img class="mb-4" src="./images/logo3.png" alt="" width="130" height="90">
        <h1 class="h3 mb-3 font-weight-normal" style = "background-color: rgba(0,0,0,0.3)">Inserisci le tue credenziali</h1>
        <span id="login-alert"class="alert-info " hidden> Login non riuscito. <br> Controllare Email e Utente</span><br>
        <label for="inputEmail" class="sr-only">Email</label>
        <input type="email" id="inputEmail" name ="email"class="form-control" placeholder="Email address" required="" autofocus="">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name ="password" id="inputPassword" class="form-control" placeholder="Password" required="">

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
</div>
<p class="mt-5 mb-3 text-muted">© E.M.A.A. corp</p>

</body>

<% // @ page import="Data.Utente" %>
<% //Utente utente= (Utente) session.getAttribute("utente");%>

<input type="hidden" id="LoginRiuscito" value="${LoginRiuscito}">
<% // if(session.getAttribute("LoginErrato")!=null)
    //session.removeAttribute("LoginErrato");%>

<script>
    /**
     * Verifica che
     */
    window.onload=function (){
        if(document.getElementById("LoginRiuscito").value==="false")
            document.getElementById("login-alert").hidden=false;
    }
</script>


</html>
