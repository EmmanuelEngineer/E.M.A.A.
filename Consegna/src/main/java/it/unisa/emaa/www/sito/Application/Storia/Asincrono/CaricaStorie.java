package it.unisa.emaa.www.sito.Application.Storia.Asincrono;

import com.google.gson.Gson;
import it.unisa.emaa.www.sito.Data.dao.ReazioneDao;
import it.unisa.emaa.www.sito.Data.dao.StoriaDao;
import it.unisa.emaa.www.sito.Data.entity.Storia;
import it.unisa.emaa.www.sito.Data.entity.StoriaReazioni;
import it.unisa.emaa.www.sito.Data.entity.Utente;
import it.unisa.emaa.www.sito.Utils.Validazione;
import org.eclipse.persistence.oxm.MediaType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * La servlet si occupa del caricamento delle storie nella bacheca.
 * Il caricamento viene effettuato secondo un numero di pagina per suddividere i risultati durante il caricamento.
 * L'invio avviene trasformando gli oggetti in una stringa JSON.
 * @author Alessandro Marigliano
 */
@WebServlet(name="CaricaStorie" ,value="/CaricaStorie" )
public class CaricaStorie extends HttpServlet {
    private StoriaDao storiaDao;
    private ReazioneDao reazioneDao;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            caricaStorie(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void caricaStorie(HttpServletRequest req,HttpServletResponse resp) throws IOException, SQLException {
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("utente");
        String stringPagina = req.getParameter("pagina");
        if(obj == null || stringPagina == null){
            resp.setStatus(500);
            throw new RuntimeException("pagina o utente non passati");
        }
        Utente utente = (Utente) obj;
        int pagina = Integer.parseInt(stringPagina);
        ArrayList<StoriaReazioni> storieReazioni = recuperaListaStorie(pagina,utente.getId());
        Gson gson = new Gson();
        String json = gson.toJson(storieReazioni);
        resp.setContentType(String.valueOf(MediaType.APPLICATION_JSON));
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(json);

    }
    private ArrayList<StoriaReazioni> recuperaListaStorie(int pagina, String email) throws SQLException {
        ArrayList<StoriaReazioni> storieReazioni = new ArrayList<>();
        ArrayList<Storia> listaStorie = new ArrayList<>(storiaDao.doRetrieveByPage(30,pagina*30));
        boolean present;
        for (Storia s : listaStorie) {
            StoriaReazioni storia = new StoriaReazioni();
            storia.setStoria(s);
            present = Validazione.reactionIsPresent(email, s.getId(), reazioneDao);
            storia.setReazionata(present);
            storieReazioni.add(storia);
        }
        return storieReazioni;
    }
    public CaricaStorie(){
        storiaDao = new StoriaDao();
        reazioneDao = new ReazioneDao();
    }
    public CaricaStorie(StoriaDao storiaDao,ReazioneDao reazioneDao){
        this.storiaDao = storiaDao;
        this.reazioneDao = reazioneDao;
    }





}
