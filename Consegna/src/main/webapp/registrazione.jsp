<%--
  Created by IntelliJ IDEA.
  User: Lethal Muriel
  Date: 03/01/2022
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Registrazione</title>
  <link rel="stylesheet" href="./bootstrap-4.5.3-dist/css/bootstrap.css"/>
  <link rel="stylesheet" href="./customcss/general.css"/>

</head>
<body class="text-center">
<script src="./jslibraries/jQuery.js"></script>

<div id = "pageContenent">

    <form class = "form-signin" action="./RegistraUtente" method="post" onsubmit="return validateData()">
        <img class="mb-4" src="./images/logo3.png" alt="" width="130" height="90">
        <h1 style="padding: 10px; background-color: rgba(0,0,0,0.3); text-align: center">Registrati</h1>
        <label for="username" class="sr-only" >Nome Utente:</label>
        <input type="text" id="username" name="username" class="form-control" onfocusout="controlliUsername()" placeholder="Nome Utente" required="" autofocus="">
        <span id="usernametest-alert" class="alert-info " hidden>Questo nome utente esiste già!</span>

        <label for="email" class="sr-only">Email</label>
        <input type="email" id="email" name="email" class="form-control" onfocusout="existingEmail()" placeholder="Email" required="">
        <span id="email-alert" class="alert-info " hidden>Questa email risulta già iscritta!</span>

        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password"  name="password"  class="form-control" placeholder="Password" required="" onfocusout="passwordRegexCheck()">
        <input type="password" id="passwordTest"  name="passwordTest"  class="form-control" placeholder="Conferma la password" required="" >

        <span id="password-info">La password deve contenere 8-15 caratteri, <br>almeno una lettera Maiuscola,<br> almeno una minuscola,<br>e almeno un numero</span><br>
        <span id="passwordTest-alert"class="alert-info " hidden> Le due password non coincidono!</span><br>

        <span id="password-alert"class="alert-info " hidden> La password non rispetta le caratteristiche richieste</span><br>
        <input type="checkbox" value="true" id="eula" name="eula"  required=""> Accetto le condizioni sulla privacy

        <input id= "submit-registration" class="btn btn-lg btn-primary btn-block" type="submit" value="Conferma">

    </form>
</div>

<p class="mt-5 mb-3 text-muted">©E.M.A.A. corp</p>

<script>
    let regexPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,15}$"

  function validateData(){
    controlliUsername();
    existingEmail();
    let submitable = true;
    let emailvalid=true;
    let email=document.getElementById("email");


    if(!email.checkValidity){
      submitable =false;
      emailvalid=false;
      document.getElementById("email-alert").hidden=false;
    }

    else document.getElementById("email-alert").hidden=true;
    if(!emailvalid) {
      submitable = false;
      document.getElementById("email-alert").innerText="Incorrect Email";
      document.getElementById("email-alert").hidden=false;
    }

    else document.getElementById("email-alert").hidden=true;




    if(!passwordTest())
        submitable= false
     if(!passwordRegexCheck())
         submitable = false


    return submitable;
  }
  function passwordTest(){
      let pass = document.getElementById("password").value.match(regexPattern);
      let passTest = document.getElementById("passwordTest").value;
      if(passTest != pass)
      {
          document.getElementById("passwordTest-alert").hidden=false
          return false
      }else {
          document.getElementById("passwordTest-alert").hidden = true
          return true
      }
  }

  function passwordRegexCheck(){
      let pass = document.getElementById("password").value.match(regexPattern);
      if(pass!=document.getElementById("password").value){

          document.getElementById("password-alert").hidden=false
          document.getElementById("passwordTest-alert").hidden=true
          return false
      }
      else {
          document.getElementById("password-alert").hidden = true;
          return true
      }

  }


  function controlliUsername(){
    let xhttp = new XMLHttpRequest();
    let usernametestalert = document.getElementById("usernametest-alert");
    let submit = document.getElementById("submit-registration");
    if(document.getElementById("username").value.length>20)
    {
        usernametestalert.innerText="Nome utente troppo lungo"
        usernametestalert.hidden = false;
        return;
    }
    if(document.getElementById("username").value==""){
        usernametestalert.innerText="Nessun nome utente inserito"
        usernametestalert.hidden = false;
        return;
    }

    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        if (this.responseText == "true") {
          submit.disabled = true;
          usernametestalert.innerText="Questo nome utente esiste già!"
          usernametestalert.hidden = false;
          console.log("username rejected");
        } else{
          submit.disabled = false;
          usernametestalert.hidden = true;
        }
      }
    };
    xhttp.open("POST", "/Sito_war_exploded/UsernamePresente", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("username="+document.getElementById("username").value);
  }

  function existingEmail(){
    let xhttp = new XMLHttpRequest();
    let emailalert = document.getElementById("email-alert");
    let submit = document.getElementById("submit-registration");
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        if (this.responseText == "true") {
          emailalert.innerText = "Email già presente";
          submit.disabled = true;
          emailalert.hidden = false;
          console.log("email rejected");
        } else{
          submit.disabled = false;
          emailalert.hidden = true;
        }
      }
    };
    xhttp.open("POST", "/Sito_war_exploded/EmailPresente", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("email="+document.getElementById("email").value);
  }
</script>

</body>
</html>
