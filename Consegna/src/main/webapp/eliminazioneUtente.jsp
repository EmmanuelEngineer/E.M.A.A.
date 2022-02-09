<%--
  Created by IntelliJ IDEA.
  User: Lethal Muriel
  Date: 07/01/2022
  Time: 02:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>eliminazioneUtente</title>
    <link rel="stylesheet" href="bootstrap-4.5.3-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="bootstrap-4.5.3-dist/css/bootstrap-grid.css"/>
    <link rel="stylesheet" href="bootstrap-4.5.3-dist/css/bootstrap-reboot.css"/>
    <link rel="stylesheet" href="customcss/general.css"/>

</head>
<body>
    <div id = "pageContenent">
        <form class="form-signin" id="form-elimina-utente" >
            <div class="text-center mb-4">
                <img class="mb-4" src="images/logo3.png" alt="" width="100" height="84">
                <h1 class="h3 mb-3 font-weight-normal" style=" background-color: rgba(0,0,0,0.3)">Eliminazione account</h1>
                <p>Per eliminare il proprio account, compilare questi campi e poi cliccare su "conferma"</p>
            </div>

            <div class="form-label-group">
                <label for="inputEmail">Nome Utente</label>
                <input type="string" name="username" id="inputNomeUtente" class="form-control" placeholder="Nome Utente" required="" autofocus="">
            </div>

            <div class="form-label-group">
                <label for="inputEmail">Email</label>
                <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" required="" autofocus="">
            </div>

            <div class="form-label-group">
                <label for="inputPassword">Password</label>
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required="">
            </div>
            <div class="form-label-group">
                <span class="btn btn-lg btn-primary btn-block" onclick="return controllo()">Conferma</span>
            </div>

        </form>
    </div>




</body>

<footer>
    <p class="mt-5 mb-3 text-muted text-center">© E.M.A.A. Corp </p>
</footer>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="./jslibraries/popper.js"></script>
<script src="./jslibraries/holder.min.js"></script>
<script src="./jslibraries/bootstrap.min.js"></script>
<script src="./jslibraries/utils.js"></script>

<script>
    function controllo(){
        eliminaUtente()
        return false
    }
function eliminaUtente(){
    let username= document.getElementById("inputNomeUtente").value;
    let email= document.getElementById("inputEmail").value;
    let password = document.getElementById("inputPassword").value;


        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                paginaPrincipale()
                return false
            }else if (this.readyState == 4) {
                if (this.responseText.includes("Loggati prima di effetture l'eliminazione")) {
                    createToast("Errore Eliminazione", "Effettuare il login e poi ritentare")
                    return false
                }
                if (this.responseText.includes("Inserisci i dati del tuo profilo")) {
                    createToast("Errore Eliminazione", "Non inserire dati di altri utenti")
                    return false
                }
                if (this.responseText.includes("Dati inseriti non validi")) {
                    createToast("Errore Eliminazione", "Dati inseriti non validi")
                    return false
                }
            }
        }
        xhttp.open("POST", "./EliminazioneUtente", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("password="+password+"&email="+email+"&username="+username);
        return false
}

function paginaPrincipale(){
    location.replace("./")
}

</script>
</html>
