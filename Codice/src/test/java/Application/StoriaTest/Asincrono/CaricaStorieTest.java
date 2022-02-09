package Application.StoriaTest.Asincrono;

import com.google.gson.Gson;
import it.unisa.emaa.www.sito.Application.Storia.Asincrono.CaricaStorie;
import it.unisa.emaa.www.sito.Data.dao.ReazioneDao;
import it.unisa.emaa.www.sito.Data.dao.StoriaDao;
import it.unisa.emaa.www.sito.Data.entity.Reazione;
import it.unisa.emaa.www.sito.Data.entity.Storia;
import it.unisa.emaa.www.sito.Data.entity.StoriaReazioni;
import it.unisa.emaa.www.sito.Data.entity.Utente;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * @autor Antonio Scotellaro
 * Testing per l'inserimento di storie, vengono gestiti i casi di successo caricamento storia e fallimento caricamento storia
 * nel caso in cui non siano presenti elementi nella pagina richiesta.
 *
 *
 */



import static org.junit.Assert.assertEquals;




public class CaricaStorieTest {

    @Test
    public void successoTest() throws IOException, SQLException {
        Utente utente = new Utente();
        Storia storia = new Storia();
        Storia storia1 = new Storia();
        Storia storia2 = new Storia();
        int pagina = 0;

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer", "ciao");

        MockHttpServletResponse response = new MockHttpServletResponse();

        javax.servlet.http.HttpSession session = request.getSession();
        session.setAttribute("utente", utente);
        request.setParameter("pagina", pagina + "");

        ArrayList<Storia> lista = new ArrayList<>();
        storia.setId(1);
        storia.setUsername("pippo");
        storia.setContenuto("AAAAAAAAAAAAAAAAAAAAAAAAAAHHHH");
        storia.setDataCreazione(LocalDate.now());
        storia.setNReazioni(0);
        storia.setNCommenti(1);


        lista.add(storia);

        utente.setPassword("");
        utente.setUsername("pippo2");
        utente.setId("gaiusgi@yagdaygs.com");

        Reazione reazione = new Reazione();
        reazione.setEmailUtente("gaiusgi@yagdaygs.com");
        reazione.setIdStoria(1);

        StoriaReazioni storiareazioni = new StoriaReazioni();
        storiareazioni.setStoria(storia);
        storiareazioni.setReazionata(true);


        ArrayList<StoriaReazioni> lista2 = new ArrayList<>();
        lista2.add(storiareazioni);


        Gson gson = new Gson();

        String risultato = gson.toJson(lista2);




        StoriaDao dao = Mockito.mock(StoriaDao.class);

        ReazioneDao dao2 = Mockito.mock(ReazioneDao.class);

        Mockito.when(dao.doRetrieveByPage(30,pagina*30)).thenReturn(lista);
        Mockito.when(dao2.doRetrieve(utente.getId(), storia.getId())).thenReturn(reazione);
        request.setCookies();
        CaricaStorie controller = new CaricaStorie(dao, dao2);
        controller.caricaStorie(request,response);

        assertEquals(risultato,response.getContentAsString());

    }


    @Test
    public void noElementiPaginaTest() throws IOException, SQLException {
        Utente utente = new Utente();
        Storia storia = new Storia();
        int pagina = 1;

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("referer", "ciao");

        MockHttpServletResponse response = new MockHttpServletResponse();

        javax.servlet.http.HttpSession session = request.getSession();
        session.setAttribute("utente", utente);
        request.setParameter("pagina", pagina + "");

        ArrayList<Storia> lista = new ArrayList<>();

        utente.setPassword("");
        utente.setUsername("pippo2");
        utente.setId("gaiusgi@yagdaygs.com");

        Reazione reazione = new Reazione();
        reazione.setEmailUtente("gaiusgi@yagdaygs.com");
        reazione.setIdStoria(1);

        StoriaReazioni storiareazioni = new StoriaReazioni();
        storiareazioni.setStoria(storia);
        storiareazioni.setReazionata(true);


        ArrayList<StoriaReazioni> lista2 = new ArrayList<>();



        Gson gson = new Gson();

        String risultato = gson.toJson(lista2);




        StoriaDao dao = Mockito.mock(StoriaDao.class);

        ReazioneDao dao2 = Mockito.mock(ReazioneDao.class);

        Mockito.when(dao.doRetrieveByPage(30,pagina*30)).thenReturn(lista);
        Mockito.when(dao2.doRetrieve(utente.getId(), storia.getId())).thenReturn(reazione);
        request.setCookies();
        CaricaStorie controller = new CaricaStorie(dao, dao2);
        controller.caricaStorie(request,response);

        assertEquals(risultato,response.getContentAsString());

    }

}
