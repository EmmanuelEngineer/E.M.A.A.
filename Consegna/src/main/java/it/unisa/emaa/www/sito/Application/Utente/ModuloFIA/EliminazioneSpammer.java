package it.unisa.emaa.www.sito.Application.Utente.ModuloFIA;


import it.unisa.emaa.www.sito.Data.dao.CommentoDao;
import it.unisa.emaa.www.sito.Data.dao.UtenteDao;
import it.unisa.emaa.www.sito.Data.entity.Commento;
import it.unisa.emaa.www.sito.Data.entity.Utente;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Questa servlet comunica a un modulo di intelligenza artificiale la lista dei commenti presenti nella piattaforma.
 * Riceve poi una lista di utenti trovati come spammer dal modulo e procede a eliminarli.
 * La servlet si avvia all'accensione del server e viene rieseguita ogni giorno, in caso il server rimanesse attivo
 * @author Alessandro Marigliano
 */
public class EliminazioneSpammer extends HttpServlet{
    @Override
    public void init() throws ServletException {
       super.init();
       creaTask();
    }
    public void creaTask(){
        TaskFia task = new TaskFia();
        Timer t = new Timer();
        t.scheduleAtFixedRate(task,0,86400000);
    }
    private static class TaskFia extends TimerTask {
        @Override
        public void run() {
            System.out.println("Task avviato");
            CommentoDao commentoDao = new CommentoDao();
            try {
                List<Commento> commentoList = commentoDao.doRetrieveAll();
                if(commentoList.isEmpty())
                    return;
                ArrayList<Commento> commenti = new ArrayList<>(commentoList);
                ArrayList<String> utentiUsername = AdapterFia.utentiSpammer(commenti);
                UtenteDao utenteDao = new UtenteDao();
                for(String username:utentiUsername){
                    Utente utente = utenteDao.doRetrieveByUsername(username);
                    utenteDao.doDelete(utente.getId());
                }
            } catch (SQLException | ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
