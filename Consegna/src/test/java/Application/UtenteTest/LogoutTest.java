package Application.UtenteTest;

import it.unisa.emaa.www.sito.Application.Utente.Logout;
import it.unisa.emaa.www.sito.Data.entity.Utente;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

//Author MurielRossi


public class LogoutTest  {


    @Test
    public void logoutSuccessoTest() throws IOException {
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

        MockHttpSession session = (MockHttpSession) request.getSession();

        session.setAttribute("utente", utente);

        Logout controller = new Logout();

        request.addHeader("referer", "ciao");
        controller.logout(request, response);
        session = (MockHttpSession) request.getSession();
        assertEquals(true,session.getAttribute("uscito"));
    }
    @Test
    public void logoutNoSessionSuccessoTest() throws IOException {
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

        MockHttpSession session = (MockHttpSession) request.getSession();

        session.setAttribute("utente", utente);
        session.invalidate();
        Logout controller = new Logout();

        request.addHeader("referer", "ciao");
        controller.logout(request, response);
        session = (MockHttpSession) request.getSession();
        assertEquals(true,session.getAttribute("uscito"));
    }

}
