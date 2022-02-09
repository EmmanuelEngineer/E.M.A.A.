package Application.StoriaTest;

import it.unisa.emaa.www.sito.Application.Storia.VisualizzaPost;
import it.unisa.emaa.www.sito.Data.dao.CommentoDao;
import it.unisa.emaa.www.sito.Data.dao.ReazioneDao;
import it.unisa.emaa.www.sito.Data.dao.StoriaDao;
import it.unisa.emaa.www.sito.Data.entity.Storia;
import it.unisa.emaa.www.sito.Data.entity.Utente;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author Antonio Scotellaro
 * Testing per la visualizzazione di post, vengono testati i casi di successo, dove il post viene fatto visualizzare poiché
 * presenti sia l'utente che la storia e i casi di fallimento dove non sono presenti la storia e l'utente
 */


public class VisualizzaPostTest {
    @Test
    public void successoTest() throws ServletException, IOException, SQLException {


        CommentoDao dao = Mockito.mock(CommentoDao.class);
        StoriaDao dao2 = Mockito.mock(StoriaDao.class);
        ReazioneDao dao3 = Mockito.mock(ReazioneDao.class);

        Utente utente = new Utente();
        Storia storia = new Storia();
        storia.setUsername("antonio");
        storia.setContenuto("blablablablablablablablablablablalba");
        storia.setId(1);
        storia.setDataCreazione(LocalDate.now());;


        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer","ciao");

        MockHttpServletResponse response = new MockHttpServletResponse();

        HttpSession session = request.getSession();
        session.setAttribute("utente",utente);
        request.setParameter("username","antonio");
        request.setParameter("storia", "1");
        request.setParameter("contenuto","blablablablablablablablablablablalba");

        VisualizzaPost controller = new VisualizzaPost(dao,dao2,dao3);
        controller.visualizzaPost(request, response);
        assertEquals(response.getStatus(),200);




    }
    @Test
    public void noUtenteTest() throws ServletException, IOException {
        CommentoDao dao = Mockito.mock(CommentoDao.class);
        StoriaDao dao2 = Mockito.mock(StoriaDao.class);
        ReazioneDao dao3 = Mockito.mock(ReazioneDao.class);

        Storia storia = new Storia();
        storia.setUsername("antonio");
        storia.setContenuto("blablablablablablablablablablablalba");
        storia.setDataCreazione(LocalDate.now());

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer","ciao");

        MockHttpServletResponse response = new MockHttpServletResponse();

        HttpSession session = request.getSession();
        request.setParameter("username","antoio");
        request.setParameter("contenuto","blablablablablablablablablablablalba");

        VisualizzaPost controller = new VisualizzaPost(dao,dao2,dao3);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.visualizzaPost(request, response);
        });
        assertEquals("Utente non loggato",exception.getMessage());
    }

    @Test
    public void noStoriaTest() throws ServletException, IOException {
        CommentoDao dao = Mockito.mock(CommentoDao.class);
        StoriaDao dao2 = Mockito.mock(StoriaDao.class);
        ReazioneDao dao3 = Mockito.mock(ReazioneDao.class);
    Utente utente = new Utente();



    MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer","ciao");

    MockHttpServletResponse response = new MockHttpServletResponse();

    HttpSession session = request.getSession();
        session.setAttribute("utente",utente);

    VisualizzaPost controller = new VisualizzaPost(dao,dao2,dao3);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.visualizzaPost(request, response);
        });
        assertEquals("Id Storia non trovato",exception.getMessage());
}


}
