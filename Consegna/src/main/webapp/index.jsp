<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>
    <title>BENVENUTO IN E.M.A.A.</title>
    <link rel="stylesheet" href="./bootstrap-4.5.3-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="./customcss/general.css"/>
</head>
<span id = "uscito" hidden="">${uscito}</span>
<span id = "eliminato" hidden="">${eliminato}</span>
<body class="text-center">

<div id = "pageContenent">
    <div>

        <h1 style="padding: 10px; background-color: rgba(0,0,0,0.3); text-align: center">
            BENVENUTO IN E.M.A.A.!!!
        </h1>

        <img class="mb-4" src="./images/logo3.png" alt="" width="130" height="90">

    </div>

    <div class = "buttonContainer">
        <div>
            <a class="btn btn-lg btn-primary btn-block" href="./login.jsp">Accedi</a>
        </div>

        <div>
            <a class="btn btn-lg btn-primary btn-block" href="./registrazione.jsp">Registrati</a>
        </div>
    </div>

</div>
<p class="mt-5 mb-3 text-muted">© E.M.A.A. corp</p>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="./jslibraries/utils.js"></script>

<script>
    /**
     * Controlla se vi è scritto "true" nell'oggetto html eliminato per poter mostrare il messaggio di account eliminato
     */
    window.onload= function(){
        if(document.getElementById("uscito").innerText==="true")
            createToast("Arrivederci","Disconnessione effettuata con successo")
        if(document.getElementById("eliminato").innerText==="true")
            createToast("Successo","Account eliminato con successo")

    }
</script>
</html>
<% session.invalidate();%>