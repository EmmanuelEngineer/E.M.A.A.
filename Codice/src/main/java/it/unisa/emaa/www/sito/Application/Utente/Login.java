package it.unisa.emaa.www.sito.Application.Utente;

import it.unisa.emaa.www.sito.Data.dao.UtenteDao;
import it.unisa.emaa.www.sito.Data.entity.Utente;
import it.unisa.emaa.www.sito.Utils.Validazione;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Questa servlet effettua il login di un utente.
 * L'operazione fallisce se:
 * non è presente nel database un utente con l'email data;
 * è presente un utente con l'email data ma la password non corrisponde con la sua.
 * @author Alessandro Marigliano
 */
@WebServlet(name = "Login",urlPatterns = "/Login")
public class Login extends HttpServlet {
    private UtenteDao utenteDao;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            login(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void login(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException, SQLException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        password = Validazione.passwordHasher(password);
        Utente utente = recuperaUtente(email);
        boolean login = utente!=null&&password.equals(utente.getPassword());
        req.setAttribute("LoginRiuscito",login);
        if(!login) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req,resp);
            return;
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("utente",utente);
        resp.sendRedirect("./VisualizzaHome");
    }
    private Utente recuperaUtente(String email) throws SQLException {
        return utenteDao.doRetrieveByEmail(email);
    }
    public Login(){
        utenteDao = new UtenteDao();
    }
    public Login(UtenteDao utenteDao){
        this.utenteDao = utenteDao;
    }
}
