package Application.StoriaTest;

import it.unisa.emaa.www.sito.Application.Storia.VisualizzaBacheca;
import it.unisa.emaa.www.sito.Data.entity.Utente;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author Antonio Scotellaro
 * Testing per l'avvenuto successo di visualizzazione bacheca dove viene controllata la presenza dell'utente in sessione
 */


public class VisualizzaBachecaTest {
    @Test
    public void successoTest() throws ServletException, IOException {

        Utente utente = new Utente();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer","ciao");

        MockHttpServletResponse response = new MockHttpServletResponse();

        HttpSession session = request.getSession();
        session.setAttribute("utente",utente);
        request.setParameter("storia","1");
        VisualizzaBacheca controller = new VisualizzaBacheca();
        controller.visualizzaBacheca(request, response);
        assertTrue(response.getStatus()==200);

    }
    @Test
    public void noUtenteTest() throws ServletException, IOException {


        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer","ciao");

        MockHttpServletResponse response = new MockHttpServletResponse();
        request.setParameter("storia","1");

        VisualizzaBacheca controller = new VisualizzaBacheca();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.visualizzaBacheca(request, response);
        });
        assertEquals("Utente non è loggato",exception.getMessage());
    }

}
