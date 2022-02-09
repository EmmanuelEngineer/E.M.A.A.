package it.unisa.emaa.www.sito.Application.Storia;

import it.unisa.emaa.www.sito.Data.dao.StoriaDao;
import it.unisa.emaa.www.sito.Data.entity.Storia;
import it.unisa.emaa.www.sito.Data.entity.Utente;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Quseta servlet gestisce la pubblicazione di una storia da parte di un utente.
 * L'operazione fallisce se la storia non rispetta la regola di lunghezza.
 * @author Alessandro Marigliano
 */
@WebServlet(name="PubblicaStoria",urlPatterns = "/PubblicaStoria")
public class PubblicaStoria extends HttpServlet {
    private StoriaDao storiaDao;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            pubblicaStoria(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void pubblicaStoria(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException, SQLException {
        HttpSession session = req.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        String storia = req.getParameter("contenuto");
        resp.getWriter().print(pubblicazioneStoria(utente.getUsername(),storia));
        resp.sendRedirect("/Sito_war_exploded/VisualizzaHome");
    }

    private boolean pubblicazioneStoria(String username,String contenuto) throws SQLException {
        if(contenuto.length()<1||contenuto.length()>500)
            return false;
        Storia storia = new Storia();
        storia.setContenuto(contenuto);
        storia.setUsername(username);
        storia.setNCommenti(0);
        storia.setNReazioni(0);
        storia.setDataCreazione(LocalDate.now());
        return storiaDao.doSave(storia);
    }
    public PubblicaStoria(){
        storiaDao = new StoriaDao();
    }
    public PubblicaStoria(StoriaDao storiaDao){
        this.storiaDao = storiaDao;
    }
}
