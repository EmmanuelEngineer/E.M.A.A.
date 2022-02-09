package it.unisa.emaa.www.sito.Application.Storia;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Questa servlet gestisce la visualizzazione della bacheca(homepage).
 * Viene richiesto il numero di pagina per selezionare solo una parte delle storie da visualizzare.
 * @author Alessandro Marigliano
 */
@WebServlet(name = "VisualizzaHome",urlPatterns = "/VisualizzaHome")
public class VisualizzaBacheca extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        visualizzaBacheca(req,resp);
    }
    public void visualizzaBacheca(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object user = session.getAttribute("utente");
        if(user == null) {
            resp.setStatus(403);
            throw new RuntimeException("Utente non è loggato");
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/visualizzaBacheca.jsp");
        dispatcher.forward(req,resp);
    }
    public VisualizzaBacheca(){
    }
}
