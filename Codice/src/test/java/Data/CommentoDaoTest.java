package Data;

/*Questa classe si ccoupa del testing del DaoCommento.
 Tutti i testing non hanno riscontrato errori nei dao.

 * @author Muriel Rossi

*/
import it.unisa.emaa.www.sito.Data.ConnPool;
import it.unisa.emaa.www.sito.Data.dao.CommentoDao;
import it.unisa.emaa.www.sito.Data.entity.Commento;
import it.unisa.emaa.www.sito.Data.entity.Reazione;
import it.unisa.emaa.www.sito.Data.entity.Storia;
import it.unisa.emaa.www.sito.Data.entity.Utente;
import org.junit.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class CommentoDaoTest {
    private static Utente utente;
    private CommentoDao dao = new CommentoDao();
    private static Storia storia;
    private static Reazione reazione;
    Connection connection;


    private String data1 = "INSERT INTO utente VALUES('e.coppola37@studenti.unisa.it','Casdwa324$','emmavico')";
    private String data2 = "INSERT INTO utente VALUES('pippo@gmail.com','GIAcc7£','giaccarello')";


    private String storiaquery1 = "INSERT INTO storia VALUES(1, 'emmavico','Questa è una bella storia',0,0,'" + LocalDate.now() + "')";
    private String storiaquery2 = "INSERT INTO storia VALUES(2, 'emmavico','Una altra storia',0,0,'" + LocalDate.now() + "')";
    private String storiaquery3 = "INSERT INTO storia VALUES(3, 'giaccarello','Una storia a parte',0,0,'" + LocalDate.now() + "')";

    private String commentoquery1 = "INSERT INTO commento VALUES(1,'1', 'emmavico', 'Ciao io sono Emmanuele')";
    private String commentoquery2 = "INSERT INTO commento VALUES(2,'1', 'emmavico', 'Mi piace scrivere commenti')";
    private String commentoquery3 = "INSERT INTO commento VALUES(3,'2','giaccarello', 'Ciao, bella storia!')";

    @Test
    public void doRetrieveAllTest() throws SQLException {
       Connection connection;
        Statement statement;
        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
            statement.executeUpdate("DELETE FROM utente WHERE email = 'e.coppola37@studenti.unisa.it'");
            statement.executeUpdate("DELETE FROM storia WHERE id  = 1");

            statement.executeUpdate("DELETE FROM storia WHERE id  = 2");
            statement.executeUpdate("DELETE FROM storia WHERE id  = 3");

            statement.executeUpdate("DELETE FROM commento WHERE id = 1");
            statement.executeUpdate("DELETE FROM commento WHERE id = 2");
            statement.executeUpdate("DELETE FROM commento WHERE id = 3");

            statement.executeUpdate(data1);
            statement.executeUpdate(data2);
            statement.executeUpdate(storiaquery1);
            statement.executeUpdate(storiaquery2);
            statement.executeUpdate(storiaquery3);
            statement.executeUpdate(commentoquery1);
            statement.executeUpdate(commentoquery2);
            statement.executeUpdate(commentoquery3);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Commento> oracolo = new ArrayList<>();
        List<Commento> test;

        Commento commento1 = new Commento();
        Commento commento2 = new Commento();
        Commento commento3 = new Commento();


        commento1.setIdStoria(1);
        commento1.setId(1);
        commento1.setUsername("emmavico");
        commento1.setContenuto("Ciao io sono Emmanuele");

        commento2.setIdStoria(1);
        commento2.setId(2);
        commento2.setUsername("emmavico");
        commento2.setContenuto("Mi piace scrivere commenti");

        commento3.setIdStoria(2);
        commento3.setId(3);
        commento3.setUsername("giaccarello");
        commento3.setContenuto("Ciao, bella storia!");

        oracolo.add(commento1);
        oracolo.add(commento2);
        oracolo.add(commento3);


        test = dao.doRetrieveAll();
        boolean test2 = true;
        for (Commento x : oracolo) {
            test2 = false;
            if (test.contains(x))
                test2 = true;
            else
                break;
        }

        assertTrue("La lista di reazioni non è restituita correttamente", test2);

        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
            statement.executeUpdate("DELETE FROM utente WHERE email = 'e.coppola37@studenti.unisa.it'");
            statement.executeUpdate("DELETE FROM storia WHERE id  = 1");
            statement.executeUpdate("DELETE FROM storia WHERE id  = 2");

            statement.executeUpdate("DELETE FROM commento WHERE id = '1'");
            statement.executeUpdate("DELETE FROM commento WHERE id = '2'");
            statement.executeUpdate("DELETE FROM commento WHERE id = '3'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doRetrieveByStoriaTest() throws SQLException {
        Connection connection;
        Statement statement;
        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
            statement.executeUpdate("DELETE FROM utente WHERE email = 'e.coppola37@studenti.unisa.it'");
            statement.executeUpdate("DELETE FROM storia WHERE id  = 1");
            statement.executeUpdate("DELETE FROM storia WHERE id  = 2");

            statement.executeUpdate("DELETE FROM commento WHERE id = '1'");
            statement.executeUpdate("DELETE FROM commento WHERE id = '2'");
            statement.executeUpdate("DELETE FROM commento WHERE id = '3'");

            statement.executeUpdate(data1);
            statement.executeUpdate(data2);
            statement.executeUpdate(storiaquery1);
            statement.executeUpdate(storiaquery2);

            statement.executeUpdate(commentoquery1);
            statement.executeUpdate(commentoquery2);
            statement.executeUpdate(commentoquery3);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Commento> oracolo = new ArrayList<>();
        List<Commento> test;

        Commento commento1 = new Commento();
        Commento commento2 = new Commento();
        Commento commento3 = new Commento();


        commento1.setIdStoria(1);
        commento1.setId(1);
        commento1.setUsername("emmavico");
        commento1.setContenuto("Ciao io sono Emmanuele");

        commento2.setIdStoria(1);
        commento2.setId(2);
        commento2.setUsername("emmavico");
        commento2.setContenuto("Mi piace scrivere commenti");

        commento3.setIdStoria(2);
        commento3.setId(3);
        commento3.setUsername("giaccarello");
        commento3.setContenuto("Ciao, bella storia!");

        oracolo.add(commento1);
        oracolo.add(commento2);


        test = dao.doRetrieveByStoria(1);
        boolean test1;
        boolean test2 = true;
        for (Commento x : oracolo) {
            test2 = false;
            if (test.contains(x))
                test2 = true;

            else
                break;

        }

        assertTrue("La lista di commenti non è restituita correttamente", test2);

        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
            statement.executeUpdate("DELETE FROM utente WHERE email = 'e.coppola37@studenti.unisa.it'");
            statement.executeUpdate("DELETE FROM storia WHERE id  = 1");
            statement.executeUpdate("DELETE FROM storia WHERE id  = 2");

            statement.executeUpdate("DELETE FROM commento WHERE id = '1'");
            statement.executeUpdate("DELETE FROM commento WHERE id = '2'");
            statement.executeUpdate("DELETE FROM commento WHERE id = '3'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doSave() {

        Connection connection;
        Statement statement;
        ResultSet risultati;

        Commento oracolo = new Commento();
        Commento test = new Commento();
        boolean test2 = true;
        boolean test3 = true;
        boolean test4 = false;


        oracolo.setIdStoria(1);
        oracolo.setUsername("emmavico");
        oracolo.setContenuto("Ciao io sono Emmanuele");

        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
            statement.executeUpdate("DELETE FROM utente WHERE email = 'e.coppola37@studenti.unisa.it'");
            statement.executeUpdate("DELETE FROM storia WHERE id  = 1");
            statement.executeUpdate("DELETE FROM storia WHERE id  = 2");

            statement.executeUpdate("DELETE FROM commento WHERE contenuto = 'Ciao io sono Emmanuele'");

            statement.executeUpdate(data1);
            statement.executeUpdate(data2);
            statement.executeUpdate(storiaquery1);
            statement.executeUpdate(storiaquery2);

            test2 = dao.doSave(oracolo);


            risultati = statement.executeQuery("SELECT * FROM commento WHERE contenuto = 'Ciao io sono Emmanuele'");
            if (!risultati.isBeforeFirst())
                test3 = false;
            risultati.next();
            test.setId(risultati.getInt("id"));
            test.setIdStoria(risultati.getInt("idStoria"));
            test.setUsername(risultati.getString("username"));
            test.setContenuto(risultati.getString("contenuto"));


            risultati.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (oracolo.getContenuto().equals(test.getContenuto()) &&  oracolo.getIdStoria() == test.getIdStoria() && oracolo.getUsername().equals(test.getUsername()))
            test4 = true;


        assertTrue("La funzione doSave fallisce nell'aggiunta del commento e lo segnala", test2);
        assertTrue("Non risulta presente il commento all'interno del database", test3);
        assertTrue("Il commento non è aggiunto correttamente", test4);

        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
            statement.executeUpdate("DELETE FROM utente WHERE email = 'e.coppola37@studenti.unisa.it'");
            statement.executeUpdate("DELETE FROM storia WHERE id  = 1");
            statement.executeUpdate("DELETE FROM storia WHERE id  = 2");
            statement.executeUpdate("DELETE FROM reazione WHERE emailUtente = 'pippo@gmail.com'");
            statement.executeUpdate("DELETE FROM reazione WHERE emailUtente = 'e.coppola37@studenti.unisa.it'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
