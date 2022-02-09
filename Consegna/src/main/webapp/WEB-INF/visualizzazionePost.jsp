<%--
  Created by IntelliJ IDEA.
  User: Lethal Muriel
  Date: 12/01/2022
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>visualizzazione post</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="./bootstrap-4.5.3-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="./bootstrap-4.5.3-dist/css/bootstrap-grid.css"/>
    <link rel="stylesheet" href="./bootstrap-4.5.3-dist/css/bootstrap-reboot.css"/>
    <link rel="stylesheet" href="./customcss/general.css"/>

</head>

<body >

<jsp:include page = "../pageComponents/navbar4.jsp"></jsp:include>
<div id = "pageContenent">
    <main role="main">
        <div class="album py-5" style= "background-color: rgba(0,0,0,0.3)">
            <div class="container" id = "bacheca">
            </div>

            <div class="container" >
                <div class="card mb-4 box-"  style = "background-color: var(--bg-secondary-color)">
                    <div class="card-body" >
                        <p class="card-text ">Scrolla verso il basso per visualizzare i commenti meno recenti.</p>
                        <div class="align-items-center" id="lista-commenti">
                        </div>
                    </div>
                </div>

            </div>


        </div>

    </main>



    <!-- QUESTA E' LA NAVBAR DI SOTTO -->

    <div class=" collapse" id="navbarBottom" style="">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-md-7 py-4">
                    <h4 class="text-white">About</h4>
                    <p class="text-muted">Add some information about the album below, the author, or any other background context. Make it a few sentences long so folks can pick up some informative tidbits. Then, link them off to some social networking sites or contact information.</p>
                </div>
                <div class="col-sm-4 offset-md-1 py-4">
                    <h4 class="text-white">Contact</h4>
                    <ul class="list-unstyled">
                        <li><a href="#" class="text-white">Follow on Twitter</a></li>
                        <li><a href="#" class="text-white">Like on Facebook</a></li>
                        <li><a href="#" class="text-white">Email me</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>


    <div class="navbar navbar-dark bg-dark fixed-bottom">


        <form class = "PubblicaCommento" action="./InserisciCommento" method="post" onsubmit="return validaCommento()" style="display: block" aria-multiline="true" text-indent = "initial">
           <input type="hidden" name="storia" id="id-storia-form" value="storia">
            <table>
                <tr>
                    <td style="width: 95%">

                        <textarea id="commento" name="commento" style="width: 100%" rows="3" placeholder="Scrivi un commento..."></textarea>
                        <span id="lenght-alert" class="alert-info " hidden>Questo commento non ha il numero adeguato di caratteri!</span>
                    </td>
                    <td style="width:5%; height: 100%">
                        <button class="btn btn-lg btn-primary btn-block" style="width: 100%; height: 100%" type="submit">
                            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
                            <path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
                            </svg>
                        </button>
                    </td>
                </tr>
            </table>

        </form>

    </div>
</div>
<span id = "post" hidden="">${post}</span>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="jslibraries/popper.js"></script>
<script src="jslibraries/holder.min.js"></script>
<script src="jslibraries/bootstrap.min.js"></script>
<script src="jslibraries/utils.js"></script>

<script>
let listaCommenti;
let storia;
let reazione;

document.onload = avvio()
    function avvio() {
    let temp = document.getElementById("post").innerHTML;
    temp = JSON.parse(temp);
    storia = temp.storia;
    listaCommenti = temp.commenti;
    reazione = temp.reazione;
    caricaStoria();
    document.getElementById("id-storia-form").value= storia.id;
    listaCommenti.forEach(listatoreCommenti);
}

function caricaStoria(){
    let variabileBacheca;
    variabileBacheca = document.getElementById("bacheca");
    let storiaCard =document.createElement("div")
        storiaCard.classList=["card","box-shadow"];
        storiaCard.style = "background-color: var(--bg-default-color)";

        let stringa =
        '<div class="card-body">'+
        '<h4>'+storia.username+'</h4>'+
        '<p class="card-text" onclick = "visualizzaPost('+storia.id+')">'+storia.contenuto+'</p>'+
        '<div class="d-flex justify-content-between align-items-center">'+
        '<div class="btn-group">'+
        '<button id = ' + storia.id + ' class="btn btn-sm btn-outline-secondary" onclick="aggiungiReazione('+storia.id+')" >'+
        '<span id = '+ storia.id +'r>'+storia.nReazioni+'</span>'+
        '<div style="display: inline-block">';

    if(!reazione)
    {
        stringa += '<svg xmlns="http://www.w3.org/2000/svg" id = '+ storia.id +'i width="27" height="27" fill="currentColor" class="bi bi-moon false" viewBox="0 0 16 16">'+
            '<path d="M6 .278a.768.768 0 0 1 .08.858 7.208 7.208 0 0 0-.878 3.46c0 4.021 3.278 7.277 7.318 7.277.527 0 1.04-.055 1.533-.16a.787.787 0 0 1 .81.316.733.733 0 0 1-.031.893A8.349 8.349 0 0 1 8.344 16C3.734 16 0 12.286 0 7.71 0 4.266 2.114 1.312 5.124.06A.752.752 0 0 1 6 .278zM4.858 1.311A7.269 7.269 0 0 0 1.025 7.71c0 4.02 3.279 7.276 7.319 7.276a7.316 7.316 0 0 0 5.205-2.162c-.337.042-.68.063-1.029.063-4.61 0-8.343-3.714-8.343-8.29 0-1.167.242-2.278.681-3.286z"/>'+
            '</svg>'
    }
    else{
        stringa += '<svg xmlns="http://www.w3.org/2000/svg"  id = '+ storia.id +'i width="27" height="27" fill="currentColor" class="bi bi-moon-fill true" viewBox="0 0 16 16">'+
            '<path d="M6 .278a.768.768 0 0 1 .08.858 7.208 7.208 0 0 0-.878 3.46c0 4.021 3.278 7.277 7.318 7.277.527 0 1.04-.055 1.533-.16a.787.787 0 0 1 .81.316.733.733 0 0 1-.031.893A8.349 8.349 0 0 1 8.344 16C3.734 16 0 12.286 0 7.71 0 4.266 2.114 1.312 5.124.06A.752.752 0 0 1 6 .278z"/>'+
            '</svg>'
    }
    stringa += (
        '</div>'+
        '</button>'+
        ' <button type="button" class="btn btn-sm btn-outline-secondary" onclick="visualizzaPost('+storia.id+')">'+
        '<span>'+storia.nCommenti+' </span>'+
        '<div style="display: inline-block">'+
        '<svg xmlns="http://www.w3.org/2000/svg" width="27" height="27" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">'+
        '<path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/></svg>'+
        '</div>'+
        '</button>'+
        '</div></div></div> ');
    storiaCard.innerHTML=stringa

    variabileBacheca.appendChild(storiaCard);
}

    function listatoreCommenti(commento){
    let temp = document.createElement("div")
        temp.className="card"
        temp.style="background-color: var(--bg-default-color)";

    temp.innerHTML =
        '<h5>'+commento.username+'</h5>'+
        '<p class="card-text">'+commento.contenuto+'</p>';
        document.getElementById("lista-commenti").append(temp);

    }


    function validaCommento(){
        let commento = document.getElementById("commento").value;
        if (commento.length<3)
        {
            createToast("Errore Commento","Il commento deve essere di almeno 3 caratteri")
            return false;
        }else if(commento.length>100){
                createToast("Errore Commento","Il commento deve essere meno di 100 caratteri")
                return false;
            }
        else return true;

    }


function impostaReazione(storia)
{
    let xhttp = new XMLHttpRequest();
    let contatore;

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            contatore = document.getElementById(storia + "r").innerHTML;
            contatore = parseInt(contatore);
            contatore++;
            document.getElementById(storia + "r").innerHTML = contatore;
            alert("La tua reazione è stata aggiunta!");
            var oggettoStoria = document.getElementById(storia);
            var pulsante = oggettoStoria.getElementsByTagName("div")[1].childNodes[1];
            pulsante.innerHTML = '<svg id = "'+storia.id+'i" xmlns="http://www.w3.org/2000/svg" width="27" height="27" fill="currentColor" class="bi bi-moon-fill true" viewBox="0 0 16 16">'+
                '<path d="M6 .278a.768.768 0 0 1 .08.858 7.208 7.208 0 0 0-.878 3.46c0 4.021 3.278 7.277 7.318 7.277.527 0 1.04-.055 1.533-.16a.787.787 0 0 1 .81.316.733.733 0 0 1-.031.893A8.349 8.349 0 0 1 8.344 16C3.734 16 0 12.286 0 7.71 0 4.266 2.114 1.312 5.124.06A.752.752 0 0 1 6 .278z"/>'+
                '</svg>';
        }

    }

    xhttp.open("POST", "./InserisciReazione", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("storia="+storia);
    console.log("pubblicando la reazione a "+storia);
}

function aggiungiReazione(storia)
{
    var icona =document.getElementById(storia+"i");

    if(icona.classList.contains("false"))
    {
        console.log("accepting reaction")
        impostaReazione(storia);
    }
}

function impostaReazione(storia)
{

    let xhttp = new XMLHttpRequest();
    let contatore;

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            contatore = document.getElementById(storia + "r").innerHTML;
            contatore = parseInt(contatore);
            contatore++;
            document.getElementById(storia + "r").innerHTML = contatore;
            alert("La tua reazione è stata aggiunta!");
            var oggettoStoria = document.getElementById(storia);
            var pulsante = oggettoStoria.getElementsByTagName("div")[1].childNodes[1];
            pulsante.innerHTML = '<svg id = "'+storia.id+'i" xmlns="http://www.w3.org/2000/svg" width="27" height="27" fill="currentColor" class="bi bi-moon-fill true" viewBox="0 0 16 16">'+
                '<path d="M6 .278a.768.768 0 0 1 .08.858 7.208 7.208 0 0 0-.878 3.46c0 4.021 3.278 7.277 7.318 7.277.527 0 1.04-.055 1.533-.16a.787.787 0 0 1 .81.316.733.733 0 0 1-.031.893A8.349 8.349 0 0 1 8.344 16C3.734 16 0 12.286 0 7.71 0 4.266 2.114 1.312 5.124.06A.752.752 0 0 1 6 .278z"/>'+
                '</svg>';
        }

    }

    xhttp.open("POST", "./InserisciReazione", true);

    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");


    xhttp.send("storia="+storia);
    console.log("pubblicando la reazione a "+storia);

}
</script>

</html>
