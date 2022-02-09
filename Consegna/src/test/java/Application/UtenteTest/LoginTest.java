package Application.UtenteTest;

import it.unisa.emaa.www.sito.Application.Utente.Login;
import it.unisa.emaa.www.sito.Data.dao.UtenteDao;
import it.unisa.emaa.www.sito.Data.entity.Utente;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//Author MurielRossi

public class LoginTest {

    @Test
    public void loginEmailErrataTest() throws IOException, ServletException, SQLException {
        UtenteDao dao = Mockito.mock(UtenteDao.class);
        Utente utente = new Utente();
        utente.setUsername("emmavico");
        utente.setId("e.coppola37@studenti.unisa.it");
        try
        {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest("Casdwa324".getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            // return the HashText
            utente.setPassword(hashtext);
        }
        // For specifying wrong message digest algorithms
        catch(
                NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        /*
        HttpSession session = request.getSession();

        session.setAttribute("utente",utente);

         */
        request.setParameter("password", "Casdwa324");
        request.setParameter("email", "ciaociao@hotmail.it");
        Mockito.when(dao.doRetrieveByEmail("ciaociao@hotmail.it")).thenReturn(null);

        Login controller = new Login(dao);

        request.addHeader("referer", "ciao");
        controller.login(request, response);
        assertFalse("L'email errata nel login non è stata identificata correttamente",  ((boolean)request.getAttribute("LoginRiuscito")));
    }

    @Test
    public void loginPasswordErrataTest() throws IOException, ServletException, SQLException {
        UtenteDao dao = Mockito.mock(UtenteDao.class);
        Utente utente = new Utente();
        utente.setUsername("emmavico");
        utente.setId("e.coppola37@studenti.unisa.it");
        try
        {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest("Casdwa324".getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            // return the HashText
            utente.setPassword(hashtext);
        }
        // For specifying wrong message digest algorithms
        catch(
                NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        /*
        HttpSession session = request.getSession();

        session.setAttribute("utente",utente);

         */
        request.setParameter("password", "PasswordErrata1");
        request.setParameter("email", "e.coppola37@studenti.unisa.it");
        Mockito.when(dao.doRetrieveByEmail("e.coppola37@studenti.unisa.it")).thenReturn(utente);

        Login controller = new Login(dao);

        request.addHeader("referer", "ciao");
        controller.login(request, response);
        assertFalse("La password errata nel login non è stata identificata correttamente",  ((boolean)request.getAttribute("LoginRiuscito")));
    }


    @Test
    public void loginTest() throws ServletException, IOException, SQLException {
        UtenteDao dao = Mockito.mock(UtenteDao.class);
        Utente utente = new Utente();
        utente.setUsername("emmavico");
        utente.setId("e.coppola37@studenti.unisa.it");
        try
        {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest("Casdwa324".getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            // return the HashText
            utente.setPassword(hashtext);
        }
        // For specifying wrong message digest algorithms
        catch(
                NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        /*
        HttpSession session = request.getSession();

        session.setAttribute("utente",utente);

         */
        request.setParameter("password", "Casdwa324");
        request.setParameter("email", "e.coppola37@studenti.unisa.it");
        Mockito.when(dao.doRetrieveByEmail("e.coppola37@studenti.unisa.it")).thenReturn(utente);

        Login controller = new Login(dao);

        request.addHeader("referer", "ciao");
        controller.login(request, response);
        assertTrue("Il login non è stato eseguito correttamente",  ((boolean)request.getAttribute("LoginRiuscito")));
        /*
        MockHttpSession sessione = (MockHttpSession) request.getSession(true);
        Utente utente2 = recuperaUtente(email);
        sessione.setAttribute("utente",utente);
        resp.sendRedirect("./VisualizzaHome");

         */
    }
}
