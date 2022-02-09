<%--
  Created by IntelliJ IDEA.
  User: Lethal Muriel
  Date: 13/01/2022
  Time: 01:37
  To change this template use File | Settings | File Templates.
--%>

<header>
    <div class="bg-dark collapse" id="navbarHeader" style="">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-md-7 py-4">
                    <h4 class="text-white">Contact</h4>
                    <ul class="list-unstyled">
                        <li><a href="./Logout" class="text-white">Disconnettimi</a></li>

                        <li><a href="./eliminazioneUtente.jsp" class="text-white">Elimina il mio account</a></li>
                    </ul>
                </div>
                <div class="col-sm-4 offset-md-1 py-4">
                    <!--                    <h4 class="text-white">About</h4>
                                        <p class="text-muted">A.</p>   -->
                </div>
            </div>
        </div>
    </div>


    <div class="navbar navbar-dark bg-dark box-shadow fixed-top">
        <div class="container d-flex justify-content-between">

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <a href="/Sito_war_exploded/VisualizzaHome" class="navbar-brand d-flex align-items-center">
                <%@include file="/images/icone/home.svg"%>         <!--   <strong>  Home</strong>-->
            </a>

        </div>
    </div>

</header>