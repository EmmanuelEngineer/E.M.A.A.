package Data;

import it.unisa.emaa.www.sito.Data.ConnPool;
import it.unisa.emaa.www.sito.Data.dao.UtenteDao;
import it.unisa.emaa.www.sito.Data.entity.Utente;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/*
/**La classe si occupa di testare la classe UtenteDao nei metodi di communicazione con il database
 * @author Muriel Rossi
 *
 *
 */

public class UtenteDaoTest {
    private static Utente utente;
    private UtenteDao dao = new UtenteDao();

    private String data1 = "INSERT INTO utente VALUES('e.coppola37@studenti.unisa.it','Casdwa324','emmavico')";
    private String data2 = "INSERT INTO utente VALUES('pippo@gmail.com','GIAcc7','giaccarello')";

    @Test
    public void deleteTest() throws SQLException {
        Connection connection;
        connection = ConnPool.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(data1);
        statement.executeUpdate(data2);
        dao.doDelete("pippo@gmail.com");
        connection = ConnPool.getConnection();
        statement = connection.createStatement();
        String qry = "Select * FROM utente Where email = 'pippo@gmail.com'";
        ResultSet rs = statement.executeQuery(qry);
        assertFalse("Oggetto ancora presente nonostante la cancellazione", rs.isBeforeFirst());
    }


    @Test
    public void RetrieveByEmailTestPresent() throws SQLException {
        Connection connection;
        Utente utente2;
        connection = ConnPool.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
        utente2 = new Utente();
        utente2.setPassword("GIAcc7");
        utente2.setId("pippo@gmail.com");
        utente2.setUsername("giaccarello");
        connection = ConnPool.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate(data2);
        utente = dao.doRetrieveByEmail("pippo@gmail.com");
        assertEquals("Ritornato un utente Errato per email", utente, utente2);
        connection = ConnPool.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
    }
    @Test
    public void RetrieveByEmailTestNotPresent() throws SQLException {
        Connection connection;
        connection = ConnPool.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
        utente = dao.doRetrieveByEmail("pippo@gmail.com");
        assertNull(utente);
        statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
    }
    @Test
    public void RetrieveByUsernameTest() throws SQLException {
        Connection connection;
        Utente utente2;
        connection = ConnPool.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
        utente2 = new Utente();
        utente2.setPassword("GIAcc7");
        utente2.setId("pippo@gmail.com");
        utente2.setUsername("giaccarello");
        statement = connection.createStatement();
        statement.executeUpdate(data2);
        utente = dao.doRetrieveByUsername("giaccarello");
        assertEquals("Ritornato un utente Errato per username", utente, utente2);
        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void RetrieveByUsernameTestNotPresent() throws SQLException {
        Connection connection;
        Utente utente2;
        connection = ConnPool.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
        utente2 = new Utente();
        utente2.setPassword("GIAcc7");
        utente2.setId("pippo@gmail.com");
        utente2.setUsername("giaccarello");
        utente = dao.doRetrieveByUsername("giaccarello");
        assertNull(utente);
        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void doSaveTest() throws SQLException {

        Connection connection;
        Utente utente1, utente2;
        utente2 = new Utente();
        utente2.setPassword("GIAcc7");
        utente2.setId("pippo@gmail.com");
        utente2.setUsername("giaccarello");
        String username;
        String email;
        String password;
        Utente utente = new Utente();
        dao.doSave(utente2);
        try {
            connection = ConnPool.getConnection();
            Statement statement = connection.createStatement();
            //username = statement.executeQuery("SELECT username FROM utente WHERE email = 'pippo@gmail.com'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection = ConnPool.getConnection();
            PreparedStatement ps = connection.prepareStatement("Select * from utente  where email =?");
            ps.setString(1, "pippo@gmail.com");
            ResultSet rs = ps.executeQuery();
            boolean flag = true;
            if (!rs.isBeforeFirst())
                flag = false;
            else {
                rs.next();
                utente.setId(rs.getString("email"));
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                rs.close();
            }

            assertTrue("L'utente non è stato salvato correttamente", utente.equals(utente2) && flag);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection = ConnPool.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doRetrieveAllTest() throws SQLException {
        List<Utente> listaUtenti = new ArrayList<Utente>();
        List<Utente> listaUtentiTest;
        Connection connection;

        Utente utente1, utente2;
        utente1 = new Utente();
        utente2 = new Utente();

        try {
            connection = ConnPool.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
            statement.executeUpdate("DELETE FROM utente WHERE email = 'e.coppola37@studenti.unisa.it'");
            statement.executeUpdate(data1);
            statement.executeUpdate(data2);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        utente1.setId("e.coppola37@studenti.unisa.it");
        utente1.setPassword(null);
        utente1.setUsername("emmavico");
        utente2.setPassword(null);
        utente2.setId("pippo@gmail.com");
        utente2.setUsername("giaccarello");

        listaUtenti.add(utente1);
        listaUtenti.add(utente2);


        listaUtentiTest = dao.doRetrieveAll();
        boolean test2 = true;
        for(Utente x: listaUtenti)
        {
            test2 = false;

            if(listaUtentiTest.contains(x))
            {
                test2 = true;
            }
            else
            {
                break;
            }
        }

        assertTrue("La lista di utenti non è restituita correttamente", test2);

        try {
            connection = ConnPool.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
            statement.executeUpdate("DELETE FROM utente WHERE email = 'e.coppola37@studenti.unisa.it'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}