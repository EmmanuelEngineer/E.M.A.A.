package it.unisa.emaa.www.sito.Data;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 *E' presente un singleton che ci permette di effettuare la connessione alla base di dati una sola volta
 * effettuando un pool di richieste al database per ottimizzare la creazione delle richieste e l'esecuzione del codice
 * rendendolo di più facile comprensione e più facile da modificare in futuro.
 *
 * Tramite l'if viene effettuato un controllo che verifica se è già presente un'istanza ed essendo questa classe una classe statica
 * può esser presente solamente un oggetto dell'istanza della classe in tutto il sistema rendendo quindi questa classe
 * un singleton.
 *
 *
 * @author Antonio Scotellaro
 */


    public class ConnPool {
        private static DataSource datasource;
        private static String connectionUrl ="jdbc:mysql://localhost:3306/storytelling?serverTimezone=";
        private static String driverClassName="com.mysql.cj.jdbc.Driver";
        private static String usernameUserDB="Storytelling";
        private static String passwordUserDB="Ciao.123";

        public static Connection getConnection() throws SQLException {
            if (datasource == null) {
                PoolProperties p = new PoolProperties();
                p.setUrl( connectionUrl + TimeZone.getDefault().getID());
                p.setDriverClassName(driverClassName);
                p.setUsername(usernameUserDB);
                p.setPassword(passwordUserDB);
                p.setMaxActive(100);
                p.setInitialSize(10);
                p.setMinIdle(10);
                p.setRemoveAbandonedTimeout(60);
                p.setRemoveAbandoned(true);
                datasource = new DataSource();
                datasource.setPoolProperties(p);
            }
            return datasource.getConnection();
        }
    }



