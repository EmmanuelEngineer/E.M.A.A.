package Application.UtenteTest;


import it.unisa.emaa.www.sito.Application.Utente.RegistrazioneUtente;
import it.unisa.emaa.www.sito.Data.dao.UtenteDao;
import it.unisa.emaa.www.sito.Data.entity.Utente;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

//Author MurielRossi

public class RegistrazioneUtenteTest {
    UtenteDao dao = Mockito.mock(UtenteDao.class);
    Utente utente = new Utente();

    @Test
    public void registrazioneTest() throws IOException, SQLException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("username", "emmavico");
        request.setParameter("email", "e.coppola37@studenti.unisa.it");
        request.setParameter("password", "Casdwa324");
        request.setParameter("passwordTest", "Casdwa324");

        request.setParameter("eula", "true");
        Utente utente = new Utente();
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
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Mockito.when(dao.doSave(utente)).thenReturn(true);

        RegistrazioneUtente controller = new RegistrazioneUtente(dao);

        request.addHeader("referer", "ciao");
        controller.registrazioneUtente(request, response);
        utente.setPassword("");

        assertTrue("L'utente non è registrato correttamente", utente.equals(request.getSession().getAttribute("utente")));
    }

    @Test
    public void registrazioneEmailErrataTest() throws IOException, SQLException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("username", "emmavico");
        request.setParameter("email", "emailerrata");
        request.setParameter("password", "Casdwa324");
        request.setParameter("passwordTest", "Casdwa324");

        request.setParameter("eula", "true");
        Utente utente = new Utente();
        utente.setUsername("emmavico");
        utente.setId("emailerrata");
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
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Mockito.when(dao.doSave(utente)).thenReturn(true);

        RegistrazioneUtente controller = new RegistrazioneUtente(dao);

        request.addHeader("referer", "ciao");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.registrazioneUtente(request, response);
        });
        assertEquals("Dati non Corretti",exception.getMessage());

    }

    @Test
    public void registrazioneEmailEsistenteTest() throws IOException, SQLException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("username", "emmavico");
        request.setParameter("email", "e.coppola37@studenti.unisa.it");
        request.setParameter("password", "Casdwa324");
        request.setParameter("passwordTest", "Casdwa324");

        request.setParameter("eula", "true");
        Utente utente = new Utente();
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
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Mockito.when(dao.doSave(utente)).thenReturn(false);

        RegistrazioneUtente controller = new RegistrazioneUtente(dao);

        request.addHeader("referer", "ciao");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.registrazioneUtente(request, response);
        });
        assertEquals("Utente già Presente",exception.getMessage());

    }

    @Test
    public void registrazionePasswordNonValidaTest() throws IOException, SQLException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("username", "emmavico");
        request.setParameter("email", "e.coppola37@studenti.unisa.it");
        request.setParameter("password", "passwordnonvalida");
        request.setParameter("passwordTest", "passwordnonvalida");

        request.setParameter("eula", "true");
        Utente utente = new Utente();
        utente.setUsername("emmavico");
        utente.setId("e.coppola37@studenti.unisa.it");
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest("passwordnonvalida".getBytes());
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
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Mockito.when(dao.doSave(utente)).thenReturn(true);

        RegistrazioneUtente controller = new RegistrazioneUtente(dao);

        request.addHeader("referer", "ciao");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.registrazioneUtente(request, response);
        });
        assertEquals("Dati non Corretti",exception.getMessage());
    }

    @Test
    public void registrazionePasswordTestNonCoincidenteTest() throws IOException, SQLException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("username", "emmavico");
        request.setParameter("email", "e.coppola37@studenti.unisa.it");
        request.setParameter("password", "Password1");
        request.setParameter("passwordTest", "Password2");

        request.setParameter("eula", "true");
        Utente utente = new Utente();
        utente.setUsername("emmavico");
        utente.setId("e.coppola37@studenti.unisa.it");
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest("Password1".getBytes());
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
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Mockito.when(dao.doSave(utente)).thenReturn(true);

        RegistrazioneUtente controller = new RegistrazioneUtente(dao);

        request.addHeader("referer", "ciao");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.registrazioneUtente(request, response);
        });
        assertEquals("Dati non Corretti",exception.getMessage());
    }

    @Test
    public void registrazioneUsernameEsistenteTest() throws IOException, SQLException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("username", "emmavico");
        request.setParameter("email", "e.coppola37@studenti.unisa.it");
        request.setParameter("password", "Casdwa324");
        request.setParameter("passwordTest", "Casdwa324");

        request.setParameter("eula", "true");
        Utente utente = new Utente();
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
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Mockito.when(dao.doSave(utente)).thenReturn(false);

        RegistrazioneUtente controller = new RegistrazioneUtente(dao);

        request.addHeader("referer", "ciao");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.registrazioneUtente(request, response);
        });
        assertEquals("Utente già Presente",exception.getMessage());
    }


}
