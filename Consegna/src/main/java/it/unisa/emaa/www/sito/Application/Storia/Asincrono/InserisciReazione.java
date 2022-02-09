package it.unisa.emaa.www.sito.Application.Storia.Asincrono;

import it.unisa.emaa.www.sito.Data.dao.ReazioneDao;
import it.unisa.emaa.www.sito.Data.entity.Reazione;
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
 * Questa servlet gestisce l'inserimento di una reazione da parte di un utente.
 * L'operazione fallisce se la reazione è già presente.
 * @author Alessandro Marigliano
 */
@WebServlet(name = "InserisciReazione",urlPatterns = "/InserisciReazione")
public class InserisciReazione extends HttpServlet {
    private ReazioneDao reazioneDao;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            inserisciReazione(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void inserisciReazione(HttpServletRequest req,HttpServletResponse resp) throws SQLException {
        HttpSession session = req.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        int idStoria = Integer.parseInt(req.getParameter("storia"));
        if(!inserimentoReazione(utente.getId(),idStoria)) {
            resp.setStatus(500);
            throw new RuntimeException("Errore nell'insermento della Reazione");
        }

    }
    private boolean inserimentoReazione(String email,int idStoria) throws SQLException {
        if(Validazione.reactionIsPresent(email,idStoria,reazioneDao))
            return false;
        Reazione reazione = new Reazione();
        reazione.setIdStoria(idStoria);
        reazione.setEmailUtente(email);
        return reazioneDao.doSave(reazione);
    }
    public InserisciReazione(){
        reazioneDao = new ReazioneDao();
    }
    public InserisciReazione(ReazioneDao reazioneDao){
        this.reazioneDao = reazioneDao;
    }
}
