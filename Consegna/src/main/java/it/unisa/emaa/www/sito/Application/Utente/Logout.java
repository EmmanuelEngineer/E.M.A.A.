package it.unisa.emaa.www.sito.Application.Utente;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * La servlet effettua il logout dell'utente dalla piattaforma.
 * Viene effettuato un controllo in caso si siano presentati problemi con un logout precedente.
 * @author Alessandro Marigliano
 */
@WebServlet(name="Logout",value="/Logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logout(request,response);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session != null)
            session.invalidate();
        session = request.getSession(true);
        session.setAttribute("uscito",true);
        response.sendRedirect("/Sito_war_exploded/");
    }
}
