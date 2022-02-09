package Application.StoriaTest;

import it.unisa.emaa.www.sito.Application.Storia.InserisciCommento;
import it.unisa.emaa.www.sito.Data.dao.CommentoDao;
import it.unisa.emaa.www.sito.Data.entity.Commento;
import it.unisa.emaa.www.sito.Data.entity.Utente;
import org.junit.Test;

import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;


/**
 * @author Antonio Scotellaro
 * Testing per l'inserimento commento, vengono testati i casi di successo inserimento e di fallimento inserimento nel momento in cui
 * non è presente l'utente, l'id storia di riferimento, il commento e i casi dove il commento è minore di 3 e maggiore di 100
 *
 */



public class InserisciCommentoTest {

    @Test
    public void successoTest() throws IOException, SQLException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();

        Utente utente = new Utente();
        utente.setId("ciao@gmail.com");
        utente.setUsername("emmavico");

        Commento commento = new Commento();
        commento.setUsername(utente.getUsername());
        commento.setContenuto("Incredibile");
        commento.setIdStoria(1);

        CommentoDao dao = Mockito.mock(CommentoDao.class);
        Mockito.when(dao.doSave(commento)).thenReturn(true);

        request.addHeader("referer","ciao");
        session.setAttribute("utente",utente);

        request.setParameter("storia","1");
        request.setParameter("commento","Incredibile");

        InserisciCommento controller = new InserisciCommento(dao);
        controller.inserisciCommento(request, response);

        assertEquals(302,response.getStatus());

    }
    @Test
    public void noUtenteTest() throws IOException, SQLException {
        CommentoDao dao = Mockito.mock(CommentoDao.class);

        Commento commento = new Commento();
        commento.setUsername("nicola");
        commento.setContenuto("ciaoo");
        commento.setIdStoria(1);

        Mockito.when(dao.doSave(commento)).thenReturn(true);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer", "ciao");

        MockHttpServletResponse response = new MockHttpServletResponse();

        HttpSession session = request.getSession();
        request.setParameter("storia","1");
        request.setParameter("commento","ciaoo");

        InserisciCommento controller = new InserisciCommento(dao);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.inserisciCommento(request, response);
        });
        assertEquals("Utente non è loggato,Riprovare il login",exception.getMessage());


    }
    @Test
    public void noIdStoriaTest() throws IOException, SQLException {
        CommentoDao dao = Mockito.mock(CommentoDao.class);

        Utente utente = new Utente();
        Commento commento = new Commento();
        commento.setUsername("marco");
        commento.setContenuto("ciaoo");
        commento.setIdStoria(1);

        Mockito.when(dao.doSave(commento)).thenReturn(true);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer", "ciao");

        MockHttpServletResponse response = new MockHttpServletResponse();

        HttpSession session = request.getSession();
        session.setAttribute("utente",utente);
        request.setParameter("commento","hihi bellissimo");

        InserisciCommento controller = new InserisciCommento(dao);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.inserisciCommento(request, response);
        });
        assertEquals("ID storia o commento non Presente",exception.getMessage());


    }

    @Test
    public void noCommentoTest() throws IOException, SQLException {
        CommentoDao dao = Mockito.mock(CommentoDao.class);

        Utente utente = new Utente();
        Commento commento = new Commento();
        commento.setUsername("marco");
        commento.setContenuto("ciaoo");
        commento.setIdStoria(1);

        Mockito.when(dao.doSave(commento)).thenReturn(true);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer", "ciao");

        MockHttpServletResponse response = new MockHttpServletResponse();

        HttpSession session = request.getSession();
        session.setAttribute("utente",utente);
        request.setParameter("storia","1");

        InserisciCommento controller = new InserisciCommento(dao);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.inserisciCommento(request, response);
        });
        assertEquals("ID storia o commento non Presente",exception.getMessage());



    }

    @Test
    public void commentoMinore3() throws IOException, SQLException {
        CommentoDao dao = Mockito.mock(CommentoDao.class);

        Utente utente = new Utente();
        Commento commento = new Commento();
        commento.setUsername("emmavico");
        commento.setContenuto("hi");
        commento.setIdStoria(1);

        Mockito.when(dao.doSave(commento)).thenReturn(true);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer","ciao");

        MockHttpServletResponse response = new MockHttpServletResponse();

        HttpSession session = request.getSession();
        session.setAttribute("utente",utente);
        request.setParameter("storia","1");
        request.setParameter("commento","hi");

        InserisciCommento controller = new InserisciCommento(dao);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.inserisciCommento(request, response);
        });
        assertEquals("Errore nel salvataggio del commento",exception.getMessage());
    }

    @Test
    public void commentoMaggioreDi100() throws IOException, SQLException {
        CommentoDao dao = Mockito.mock(CommentoDao.class);

        Utente utente = new Utente();
        Commento commento = new Commento();
        commento.setUsername("emmavico");
        commento.setContenuto("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        commento.setIdStoria(1);

        Mockito.when(dao.doSave(commento)).thenReturn(true);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer","ciao");

        MockHttpServletResponse response = new MockHttpServletResponse();

        HttpSession session = request.getSession();
        session.setAttribute("utente",utente);
        request.setParameter("storia","1");
        request.setParameter("commento","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        InserisciCommento controller = new InserisciCommento(dao);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.inserisciCommento(request, response);
        });
        assertEquals("Errore nel salvataggio del commento",exception.getMessage());
    }




}
