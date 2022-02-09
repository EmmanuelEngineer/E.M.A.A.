package Application.UtenteTest.Asincrono;

import it.unisa.emaa.www.sito.Application.Utente.Asincrono.ValidaEmail;
import it.unisa.emaa.www.sito.Data.dao.UtenteDao;
import it.unisa.emaa.www.sito.Data.entity.Utente;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidaEmailTest {

    @Test
    public void validaTest() throws IOException, SQLException {
        UtenteDao dao = Mockito.mock(UtenteDao.class);
        Utente utente = new Utente();

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("email", "e.coppola37@studenti.unisa.it");

        utente.setUsername("emmavico");
        utente.setId("e.coppola37@studenti.unisa.it");
        try {
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
        catch (
                NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Mockito.when(dao.doRetrieveByEmail("e.coppola37@studenti.unisa.it")).thenReturn(utente);

        ValidaEmail controller = new ValidaEmail(dao);

        request.addHeader("referer", "ciao");
        controller.validaEmail(request, response);

        Assert.assertEquals("L'email non è validata correttamente", response.getStatus(), 200);
        Assert.assertTrue(Boolean.parseBoolean(response.getContentAsString()));

    }


    @Test
    public void validaEmailNonPresenteTest() throws IOException, SQLException {
        UtenteDao dao = Mockito.mock(UtenteDao.class);
        Utente utente = new Utente();

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("email", "questaemailnonesiste@testing.unisa.it");

        utente.setUsername("emmavico");
        utente.setId("e.coppola37@studenti.unisa.it");
        try {
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
        catch (
                NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Mockito.when(dao.doRetrieveByEmail("questaemailnonesiste@testing.unisa.it")).thenReturn(null);

        ValidaEmail controller = new ValidaEmail(dao);

        request.addHeader("referer", "ciao");
        controller.validaEmail(request, response);

        Assert.assertEquals("L'email non corretta ha fatto crashare la funzione", response.getStatus(), 200);
        assertFalse(Boolean.parseBoolean(response.getContentAsString()));

    }

}
