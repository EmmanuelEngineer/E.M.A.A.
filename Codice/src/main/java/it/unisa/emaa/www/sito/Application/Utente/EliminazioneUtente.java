package it.unisa.emaa.www.sito.Application.Utente;


import it.unisa.emaa.www.sito.Data.dao.UtenteDao;
import it.unisa.emaa.www.sito.Data.entity.Utente;
import it.unisa.emaa.www.sito.Utils.Validazione;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Questa servlet effettua l'eliminazione di un utente dal database.
 * L'operazione fallisce se:
 * L'email inserita non corrisponde a quella dell'utente;
 * L'username inserito non corrisponde a quello dell'utente;
 * la password inserita non corrisponde a quella dell'utente.
 * @see Validazione
 * @author Alessandro Marigliano
 */
@WebServlet(name="EliminazioneUtente",value = "/EliminazioneUtente")
public class EliminazioneUtente extends HttpServlet {
    private UtenteDao utenteDao;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            eliminazioneUtente(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void eliminazioneUtente(HttpServletRequest req,HttpServletResponse resp) throws IOException, SQLException {
        HttpSession session = req.getSession(false);
        Object obj;
        if(session ==null||(obj = session.getAttribute("utente"))==null){
            resp.setStatus(403);
            throw new RuntimeException("Loggati prima di effetture l'eliminazione");

        }
        Utente utente = (Utente) obj;
        String email = req.getParameter("email");
        if(!utente.getId().equals(email)){
            resp.setStatus(406);
            throw new RuntimeException("Inserisci i dati del tuo profilo");
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean matchedPassword = Validazione.datiCorrispondenti(email,username,password,utenteDao);
        session.setAttribute("LoginErrato", !matchedPassword);
        if(!matchedPassword||!eliminaUtente(utente.getId())) {
           throw new RuntimeException("Dati inseriti non validi");
        }
        session.setAttribute("utente",null);
        session.setAttribute("eliminato",true);
        resp.sendRedirect("/Sito_war_exploded/");
    }
    /**
     * Il metodo elimina l'utente con l'email data dal database.
     * @param email L'email dell'utente che si vuole eliminare.
     * @return Il metodo ritorna il risultato dell'operazione.
     */
    private boolean eliminaUtente(String email) throws SQLException {
        return utenteDao.doDelete(email);
    }
    public EliminazioneUtente(){
        utenteDao = new UtenteDao();
    }
    public EliminazioneUtente(UtenteDao utenteDao){
        this.utenteDao = utenteDao;
    }
}
