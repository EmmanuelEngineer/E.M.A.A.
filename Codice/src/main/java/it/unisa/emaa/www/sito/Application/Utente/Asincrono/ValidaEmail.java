package it.unisa.emaa.www.sito.Application.Utente.Asincrono;

import it.unisa.emaa.www.sito.Data.dao.UtenteDao;
import it.unisa.emaa.www.sito.Utils.Validazione;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * La servlet effettua un controllo durante la registrazione che l'email inserita non sia già presente sulla piattaforma.
 * @author Alessandro Marigliano
 */
@WebServlet(name="EmailPresente",urlPatterns = "/EmailPresente")
public class ValidaEmail extends HttpServlet {
    private UtenteDao utenteDao;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            validaEmail(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void validaEmail(HttpServletRequest req,HttpServletResponse resp) throws IOException, SQLException {
        String email = req.getParameter("email");
        resp.setContentType("plain/text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(Validazione.emailIsPresent(email,utenteDao));
    }
    public ValidaEmail(){
        utenteDao = new UtenteDao();
    }
    public ValidaEmail(UtenteDao utenteDao){
        this.utenteDao = utenteDao;
    }
}
