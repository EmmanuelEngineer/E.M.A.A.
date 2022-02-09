package Data;

import it.unisa.emaa.www.sito.Data.ConnPool;
import it.unisa.emaa.www.sito.Data.dao.StoriaDao;
import it.unisa.emaa.www.sito.Data.entity.Storia;
import org.junit.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class StoriaDaoTest {
    private StoriaDao dao = new StoriaDao();
    private String utentequery1 = "INSERT INTO utente VALUES('e.coppola37@studenti.unisa.it','Casdwa324$','emmavico')";
    private String utentequery2 = "INSERT INTO utente VALUES('pippo@gmail.com','GIAcc7£','giaccarello')";

    private String storiaquery1 = "INSERT INTO storia(username,contenuto,nReazioni,nCommenti,dataCreazione) VALUES('emmavico','Questa è una bella storia',0,0,'"+"3000-12-11"+"')";
    private String storiaquery2 = "INSERT INTO storia(username,contenuto,nReazioni,nCommenti,dataCreazione) VALUES('emmavico','Una altra storia',0,0,'"+"3000-12-10"+"')";
    private String storiaquery3 = "INSERT INTO storia(username,contenuto,nReazioni,nCommenti,dataCreazione) VALUES('giaccarello','Una storia a parte',0,0,'"+"3000-12-09"+"')";
    @Test
    public void doRetrieveByPageTest() throws SQLException {
        Connection connection = ConnPool.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM utente WHERE username = 'emmavico'");
        statement.executeUpdate("DELETE FROM utente WHERE username = 'giaccarello'");
        Storia oracolo1 = new Storia();
        Storia oracolo2 = new Storia();
        Storia oracolo3 = new Storia();
        ArrayList<Storia> oracoli = new ArrayList<Storia>();
        ArrayList<Storia> storieTest;
        oracolo1.setUsername("emmavico");
        oracolo1.setContenuto("Questa è una bella storia");
        oracolo1.setNCommenti(0);
        oracolo1.setNReazioni(0);
        oracolo1.setDataCreazione(LocalDate.of(3000,12,11));
        oracolo2.setUsername("emmavico");
        oracolo2.setContenuto("Una altra storia");
        oracolo2.setNCommenti(0);
        oracolo2.setNReazioni(0);
        oracolo2.setDataCreazione(LocalDate.of(3000,12,10));
        oracolo3.setUsername("giaccarello");
        oracolo3.setContenuto("Una storia a parte");
        oracolo3.setNCommenti(0);
        oracolo3.setNReazioni(0);
        oracolo3.setDataCreazione(LocalDate.of(3000,12,9));
        statement.executeUpdate(utentequery1);
        statement.executeUpdate(utentequery2);
        statement.executeUpdate(storiaquery1);
        statement.executeUpdate(storiaquery2);
        statement.executeUpdate(storiaquery3);
        ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM storia");
        boolean test1 = rs.isBeforeFirst();
        rs.next();
        int lastId = rs.getInt(1);
        oracolo1.setId(lastId-2);
        oracolo2.setId(lastId-1);
        oracolo3.setId(lastId);
        oracoli.add(oracolo1);
        oracoli.add(oracolo2);
        oracoli.add(oracolo3);
        storieTest = (ArrayList<Storia>) dao.doRetrieveByPage(3,0);
        assertTrue("Errore con il recupero dell'ultimo id",test1);
        assertEquals("La lista di utenti non è restituita correttamente", oracoli,storieTest);
        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM utente WHERE username = 'emmavico'");
            statement.executeUpdate("DELETE FROM utente WHERE username = 'giaccarello'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void doRetrieveByIdTest() throws SQLException {
        Connection connection = ConnPool.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM utente WHERE username = 'emmavico'");
        statement.executeUpdate("DELETE FROM utente WHERE username = 'giaccarello'");
        Storia oracolo = new Storia();
        Storia storiaTest;
        oracolo.setUsername("emmavico");
        oracolo.setContenuto("Questa è una bella storia");
        oracolo.setNCommenti(0);
        oracolo.setNReazioni(0);
        oracolo.setDataCreazione(LocalDate.of(3000,12,11));
        statement.executeUpdate(utentequery1);
        statement.executeUpdate(utentequery2);
        statement.executeUpdate(storiaquery1);
        ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM storia");
        boolean test1 = rs.isBeforeFirst();
        rs.next();
        int lastId = rs.getInt(1);
        oracolo.setId(lastId);
        storiaTest = dao.doRetrieveById(lastId);
        assertTrue("Errore nel recupero dell'ultimo id",test1);
        assertEquals("Incongruenza tra risultato e oracolo", oracolo, storiaTest);
        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM utente WHERE username = 'emmavico'");
            statement.executeUpdate("DELETE FROM utente WHERE username = 'giaccarello'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void doSaveTest() throws SQLException {
        Connection connection = ConnPool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet risultati;
        statement.executeUpdate("DELETE FROM utente WHERE username = 'emmavico'");
        statement.executeUpdate("DELETE FROM utente WHERE username = 'giaccarello'");
        Storia oracolo = new Storia();
        Storia storiaTest = new Storia();
        boolean test1;
        boolean test2 = true;
        oracolo.setUsername("emmavico");
        oracolo.setContenuto("Questa è una bella storia");
        oracolo.setNCommenti(0);
        oracolo.setNReazioni(0);
        oracolo.setDataCreazione(LocalDate.of(3000,12,11));
        statement.executeUpdate(utentequery1);
        statement.executeUpdate(utentequery2);
        test1 = dao.doSave(oracolo);
        ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM storia");
        boolean testMax = rs.isBeforeFirst();
        rs.next();
        int lastId = rs.getInt(1);
        oracolo.setId(lastId);
        risultati = statement.executeQuery("SELECT * FROM storia WHERE username = 'emmavico'");
        if (!risultati.isBeforeFirst())
            test2 = false;
        else {
            risultati.next();
            storiaTest.setId(risultati.getInt("id"));
            storiaTest.setUsername(risultati.getString("username"));
            storiaTest.setDataCreazione(risultati.getDate("dataCreazione").toLocalDate());
            storiaTest.setNCommenti(risultati.getInt("nCommenti"));
            storiaTest.setNReazioni(risultati.getInt("nReazioni"));
            storiaTest.setContenuto(risultati.getString("contenuto"));
            risultati.close();
            assertTrue("Errore nel recupero dell'ultimo id",testMax);
            assertTrue("Errore nella doSave", test1);
            assertTrue("Tupla non trovata", test2);
            assertEquals("Incongruenza tra risultato e oracolo", oracolo, storiaTest);
        }
        assertTrue("La lista di utenti non è restituita correttamente", test2);
        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM utente WHERE username = 'emmavico'");
            statement.executeUpdate("DELETE FROM utente WHERE username = 'giaccarello'");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    @Test
    public void storiaNonTrovataTest() throws SQLException {
        StoriaDao storiaDao = new StoriaDao();
        Storia storia = storiaDao.doRetrieveById(-1);
        assertNull("Storia stranamente trovata",storia);
    }
}


