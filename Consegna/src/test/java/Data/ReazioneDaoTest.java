package Data;


import it.unisa.emaa.www.sito.Data.ConnPool;
import it.unisa.emaa.www.sito.Data.dao.ReazioneDao;
import it.unisa.emaa.www.sito.Data.entity.Reazione;
import it.unisa.emaa.www.sito.Data.entity.Storia;
import it.unisa.emaa.www.sito.Data.entity.Utente;
import org.junit.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/*
/**La classe si occupa di testare la classe ReazioneDao nei metodi di communicazione con il database
 * @author Muriel Rossi
 *
 * Questa classe di test � stata scritta secondo la
 * metodologia WHITE BOX.
 */

public class ReazioneDaoTest {
    private static Utente utente;
    private ReazioneDao dao = new ReazioneDao();
    private static Storia storia;
    private static Reazione reazione;
    Connection connection;


    private String data1 = "INSERT INTO utente VALUES('e.coppola37@studenti.unisa.it','Casdwa324$','emmavico')";
    private String data2 = "INSERT INTO utente VALUES('pippo@gmail.com','GIAcc7£','giaccarello')";


    private String storiaquery1 = "INSERT INTO storia VALUES(1, 'emmavico','Questa è una bella storia',0,0,'"+ LocalDate.now()+"')";
    private String storiaquery2 = "INSERT INTO storia VALUES(2, 'emmavico','Una altra storia',0,0,'"+LocalDate.now()+"')";
    private String storiaquery3 = "INSERT INTO storia VALUES(3, 'giaccarello','Una storia a parte',0,0,'"+LocalDate.now()+"')";

    /*
    private String idStoria1 = "SELECT idStoria FROM storia WHERE email = 'pippo@gmail.com'";
    private int id1 = Integer.parseInt(idStoria1);
    private String idStoria2 = "SELECT idStoria FROM storia WHERE email = 'e.coppola37@studenti.unisa.it'";
    private int id2 = Integer.parseInt(idStoria2);
    */

    private String reazionequery1 = "INSERT INTO reazione VALUES('1','e.coppola37@studenti.unisa.it')";
    private String reazionequery2 = "INSERT INTO reazione VALUES('1','pippo@gmail.com')";
    private String reazionequery3 = "INSERT INTO reazione VALUES('2','e.coppola37@studenti.unisa.it')";


    String qry = "Select * FROM utente Where email = 'pippo@gmail.com'";

    @Test
    public void doRetrieveByStoriaTest() throws SQLException {
        Connection connection;
        Statement statement;
        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
            statement.executeUpdate("DELETE FROM utente WHERE email = 'e.coppola37@studenti.unisa.it'");
            statement.executeUpdate(data1);
            statement.executeUpdate(data2);
            statement.executeUpdate(storiaquery1);
            statement.executeUpdate( reazionequery1);
            statement.executeUpdate( reazionequery2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Reazione> oracolo = new ArrayList<>();
        List<Reazione> test;

        Reazione reazione1 = new Reazione();
        Reazione reazione2 = new Reazione();

        reazione1.setIdStoria(1);
        reazione1.setEmailUtente("e.coppola37@studenti.unisa.it");
        reazione2.setEmailUtente("pippo@gmail.com");
        reazione2.setIdStoria(1);

        oracolo.add(reazione1);
        oracolo.add(reazione2);


        test = dao.doRetrieveByStoria(1);
        boolean test1;
        boolean test2 = true;
        for(Reazione x: oracolo)
        {
            test2 = false;
            if(test.contains(x))
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doRetrieveByEmailTest() throws SQLException {
        Connection connection;
        Statement statement;
        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
            statement.executeUpdate("DELETE FROM utente WHERE email = 'e.coppola37@studenti.unisa.it'");

            statement.executeUpdate(data1);
            statement.executeUpdate(data2);
            statement.executeUpdate(storiaquery1);
            statement.executeUpdate(storiaquery2);

            statement.executeUpdate( reazionequery1);
            statement.executeUpdate( reazionequery2);
            statement.executeUpdate( reazionequery3);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Reazione> oracolo = new ArrayList<>();
        List<Reazione> test;

        Reazione reazione1 = new Reazione();
        Reazione reazione2 = new Reazione();
        Reazione reazione3 = new Reazione();


        reazione1.setIdStoria(1);
        reazione1.setEmailUtente("e.coppola37@studenti.unisa.it");
        reazione2.setEmailUtente("pippo@gmail.com");
        reazione2.setIdStoria(1);
        reazione3.setIdStoria(2);
        reazione3.setEmailUtente("e.coppola37@studenti.unisa.it");

        oracolo.add(reazione1);
        oracolo.add(reazione3);


        test = dao.doRetrieveByEmail("e.coppola37@studenti.unisa.it");
        boolean test1;
        boolean test2 = true;
        for(Reazione x: oracolo)
        {
            test2 = false;
            if(test.contains(x))
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doRetrieveTest() throws SQLException {
        Connection connection;
        Statement statement;
        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
            statement.executeUpdate("DELETE FROM utente WHERE email = 'e.coppola37@studenti.unisa.it'");

            statement.executeUpdate(data1);
            statement.executeUpdate(data2);
            statement.executeUpdate(storiaquery1);
            statement.executeUpdate(storiaquery2);

            statement.executeUpdate( reazionequery1);
            statement.executeUpdate( reazionequery2);
            statement.executeUpdate( reazionequery3);


        } catch (SQLException e) {
            e.printStackTrace();
        }


        Reazione oracolo = new Reazione();
        Reazione test = new Reazione();

        oracolo.setIdStoria(1);
        oracolo.setEmailUtente("e.coppola37@studenti.unisa.it");



        test = dao.doRetrieve("e.coppola37@studenti.unisa.it", 1);

        assertEquals("La reazione non è restituita correttamente", oracolo,test);

        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
            statement.executeUpdate("DELETE FROM utente WHERE email = 'e.coppola37@studenti.unisa.it'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doSaveTest() {
        Connection connection;
        Statement statement;
        ResultSet risultati;

        Reazione oracolo = new Reazione();
        Reazione test = new Reazione();
        boolean test2 = true;
        boolean test3 = true;
        boolean test4 = false;



        oracolo.setIdStoria(1);
        oracolo.setEmailUtente("e.coppola37@studenti.unisa.it");

        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
            statement.executeUpdate("DELETE FROM utente WHERE email = 'e.coppola37@studenti.unisa.it'");

            statement.executeUpdate(data1);
            statement.executeUpdate(data2);
            statement.executeUpdate(storiaquery1);
            statement.executeUpdate(storiaquery2);

            test2 = dao.doSave(oracolo);


            risultati = statement.executeQuery("SELECT * FROM reazione WHERE emailUtente = 'e.coppola37@studenti.unisa.it' AND idStoria = 1");
            if(!risultati.isBeforeFirst())
                test3 = false;
            risultati.next();
            test.setEmailUtente(risultati.getString("emailUtente"));
            test.setIdStoria(risultati.getInt("idStoria"));
            risultati.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(oracolo.equals(test))
            test4 = true;


        assertTrue("La funzione doSave fallisce nell'aggiunta della reazione e lo segnala", test2 );
        assertTrue("Non risulta nessuna reazione all'interno del database", test3 );
        assertTrue("La reazione non è aggiunta correttamente", test4 );

        try {
            connection = ConnPool.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM utente WHERE email = 'pippo@gmail.com'");
            statement.executeUpdate("DELETE FROM utente WHERE email = 'e.coppola37@studenti.unisa.it'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}