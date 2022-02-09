package it.unisa.emaa.www.sito.Application.Storia;

import com.google.gson.Gson;
import it.unisa.emaa.www.sito.Data.dao.CommentoDao;
import it.unisa.emaa.www.sito.Data.dao.ReazioneDao;
import it.unisa.emaa.www.sito.Data.dao.StoriaDao;
import it.unisa.emaa.www.sito.Data.entity.Commento;
import it.unisa.emaa.www.sito.Data.entity.Post;
import it.unisa.emaa.www.sito.Data.entity.Storia;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Questa servlet gestisce la visualizzazione di una storia con i relativi commenti.
 * Crea un oggetto Post che contiene le due entità.
 * @author Alessandro Marigliano
 */
@WebServlet(name = "VisualizzaPost",urlPatterns = "/VisualizzaPost")
public class VisualizzaPost extends HttpServlet {
    private CommentoDao commentoDao;
    private StoriaDao storiaDao;
    private ReazioneDao reazioneDao;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            visualizzaPost(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void visualizzaPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException, SQLException {
        HttpSession session = req.getSession();
        String idStoriaString = req.getParameter("storia");
        Object obj = session.getAttribute("utente");
        if(obj == null){
            resp.setStatus(403);
            throw new RuntimeException("Utente non loggato");
        }
        if(idStoriaString==null||idStoriaString.isEmpty()){
            resp.setStatus(500);
            throw new RuntimeException("Id Storia non trovato");
        }
        int idStoria = Integer.parseInt(idStoriaString);
        Utente utente = (Utente) obj;
        String email = utente.getId();
        Post post = recuperaPost(idStoria,email);
        Gson gson = new Gson();
        String json = gson.toJson(post);
        req.setAttribute("post",json);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/visualizzazionePost.jsp");
        dispatcher.forward(req,resp);
    }
    private Post recuperaPost(int idStoria,String email) throws SQLException {
        Storia storia = storiaDao.doRetrieveById(idStoria);
        List<Commento> commenti = commentoDao.doRetrieveByStoria(idStoria);
        ArrayList<Commento> listaCommenti = commenti==null?new ArrayList<>():new ArrayList<>(commenti);
        Post post = new Post();
        post.setStoria(storia);
        post.setCommenti(listaCommenti);
        post.setReazione(Validazione.reactionIsPresent(email,idStoria,reazioneDao));
        return post;
    }
    public VisualizzaPost(CommentoDao commentoDao,StoriaDao storiaDao,ReazioneDao reazioneDao){
        this.commentoDao = commentoDao;
        this.storiaDao = storiaDao;
        this.reazioneDao = reazioneDao;
    }
    public VisualizzaPost(){
        commentoDao = new CommentoDao();
        storiaDao = new StoriaDao();
        reazioneDao = new ReazioneDao();
    }
}
