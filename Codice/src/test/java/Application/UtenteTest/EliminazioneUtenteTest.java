package Application.UtenteTest;

import it.unisa.emaa.www.sito.Application.Utente.EliminazioneUtente;
import it.unisa.emaa.www.sito.Data.dao.UtenteDao;
import it.unisa.emaa.www.sito.Data.entity.Utente;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Author MurielRossi


public class EliminazioneUtenteTest {

    @Test
    public void sessioneNullaTest() throws IOException {
        UtenteDao dao = Mockito.mock(UtenteDao.class);
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.setParameter("password", "Ciaociao1");
        request.setParameter("email", "e.coppola37@studenti.unisa.it");
        request.setParameter("username", "emmavico");
        EliminazioneUtente controller = new EliminazioneUtente(dao);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.eliminazioneUtente(request, response);
        });
        Assert.assertEquals("Loggati prima di effetture l'eliminazione",exception.getMessage());

    }

    @Test
    public void usernameErrataTest() throws IOException, SQLException {

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
        catch(NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();

        session.setAttribute("utente",utente);

        request.setParameter("password", "PassDiversa1");
        request.setParameter("email", "e.coppola37@studenti.unisa.it");
        request.setParameter("username", "usernameErrata");

        Mockito.when(dao.doRetrieveByEmail("e.coppola37@studenti.unisa.it")).thenReturn(utente);
        Mockito.when(dao.doDelete("e.coppola37@studenti.unisa.it")).thenReturn(true);

        EliminazioneUtente controller = new EliminazioneUtente(dao);
        request.addHeader("referer", "ciao");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.eliminazioneUtente(request, response);
        });
        assertEquals("Dati inseriti non validi",exception.getMessage());
    }

    @Test
    public void emailErrataTest() throws IOException, SQLException {

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
        catch(NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();

        session.setAttribute("utente",utente);

        request.setParameter("password", "PassDiversa1");
        request.setParameter("email", "pippo@gmail.com");
        request.setParameter("username", "emmavico");

        EliminazioneUtente controller = new EliminazioneUtente();
        request.addHeader("referer", "ciao");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.eliminazioneUtente(request, response);
        });
        assertEquals("Inserisci i dati del tuo profilo",exception.getMessage());
    }

    @Test
    public void passwordErrataTest() throws IOException, SQLException {

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
        catch(NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();

        session.setAttribute("utente",utente);

        request.setParameter("password", "PassDiversa1");
        request.setParameter("email", "e.coppola37@studenti.unisa.it");
        request.setParameter("username", "emmavico");

        Mockito.when(dao.doRetrieveByEmail("e.coppola37@studenti.unisa.it")).thenReturn(utente);
        Mockito.when(dao.doDelete("e.coppola37@studenti.unisa.it")).thenReturn(true);

        EliminazioneUtente controller = new EliminazioneUtente(dao);
        request.addHeader("referer", "ciao");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.eliminazioneUtente(request, response);
        });
        assertEquals("Dati inseriti non validi",exception.getMessage());

    }

    @Test
    public void eliminazioneUtenteFunzionanteTest() throws IOException, SQLException {
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
        catch(NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();

        session.setAttribute("utente",utente);
        request.setParameter("username", "emmavico");

        request.setParameter("password", "Casdwa324");
        request.setParameter("email", "e.coppola37@studenti.unisa.it");
        Mockito.when(dao.doRetrieveByEmail("e.coppola37@studenti.unisa.it")).thenReturn(utente);
        Mockito.when(dao.doDelete("e.coppola37@studenti.unisa.it")).thenReturn(true);
        EliminazioneUtente controller = new EliminazioneUtente(dao);
        request.addHeader("referer", "ciao");
        controller.eliminazioneUtente(request, response);
        assertTrue("L'utente non è stato eliminato correttamente", response.getStatus() == 302 && (boolean)session.getAttribute("eliminato"));

    }
    @Test
    public void noSessionTest(){

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
        catch(NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();

        session.setAttribute("utente",utente);
        session.invalidate();
        request.setParameter("password", "PassDiversa1");
        request.setParameter("email", "pippo@gmail.com");
        request.setParameter("username", "emmavico");

        EliminazioneUtente controller = new EliminazioneUtente();
        request.addHeader("referer", "ciao");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.eliminazioneUtente(request, response);
        });
        assertEquals("Loggati prima di effetture l'eliminazione",exception.getMessage());
    }
    @Test
    public void noUtenteInSessioneTest(){

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
        catch(NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();

        session.setAttribute("utente",null);
        request.setParameter("password", "PassDiversa1");
        request.setParameter("email", "pippo@gmail.com");
        request.setParameter("username", "emmavico");

        EliminazioneUtente controller = new EliminazioneUtente();
        request.addHeader("referer", "ciao");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.eliminazioneUtente(request, response);
        });
        assertEquals("Loggati prima di effetture l'eliminazione",exception.getMessage());
    }

}
