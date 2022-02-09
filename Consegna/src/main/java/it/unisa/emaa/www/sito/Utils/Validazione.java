package it.unisa.emaa.www.sito.Utils;

import it.unisa.emaa.www.sito.Data.dao.ReazioneDao;
import it.unisa.emaa.www.sito.Data.dao.UtenteDao;
import it.unisa.emaa.www.sito.Data.entity.Reazione;
import it.unisa.emaa.www.sito.Data.entity.Utente;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Pattern;


/**
 * Questa classe permette di usare dei metodi statici per effettuare controlli
 * @author Alessandro Marigliano
 */
public class Validazione {
    public static boolean emailIsPresent(String email,UtenteDao utenteDao) throws SQLException {
        Utente utente = utenteDao.doRetrieveByEmail(email);
        return utente != null;
    }

    /**
     * @param username
     * Il metodo prende come input un username.
     * @return Il metodo ritorna true o false a seconda se l'username dato in input si trova o meno nel database.
     */
    public static boolean usernameIsPresent(String username,UtenteDao utenteDao) throws SQLException {
        Utente utente = utenteDao.doRetrieveByUsername(username);
        return utente != null;
    }

    /**
     * Il metodo controlla se una reazione nel database è presente
     * @param email Il metodo prende in input un email
     * @param idStoria Il metodo prende in input un id storia
     * @return Ritorna se è presente una reazione di un utente da data email alla data storia nel database
     */
    public static boolean reactionIsPresent(String email,int idStoria,ReazioneDao reazioneDao) throws SQLException {
        Reazione reazione = reazioneDao.doRetrieve(email,idStoria);
        return reazione != null;
    }

    /**
     * @param email Il metodo prende in input una email.
     * @return Il metodo ritorna un valore true o false a seconda se l'email data in input rispetta o meno il pattern della regex per le email.
     */
    public static boolean emailRegex(String email){
        Pattern patternEmail = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        return patternEmail.matcher(email).matches();
    }

    /**
     * @param password Il metodo prende in input una password.
     * @return Il metodo ritorna un valore true o false a seconda se la password data in input rispetta o meno il pattern della regex per le password.
     */
    public static boolean passwordRegex(String password){
        Pattern patternPassword = Pattern.compile("^(?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,16}$");
        return patternPassword.matcher(password).matches();
    }

    /**
     * Il metodo confronta due stringhe passate.
     * @param password
     * @param passwordTest
     * @return Il metodo ritorna true o false a seconda se le password passate sono identiche.
     */
    /*
    public static boolean passwordTest(String password,String passwordTest){
        return password.equals(passwordTest);
    }*/

    /**
     * Il metodo controlla che l'email passata sia di un utente registrato e che la password inserita corrisponda alla sua.
     * @param email
     * @param password
     * @return Il metodo ritorna true o false a seconda se i dati sono corretti.
     */
    public static boolean datiCorrispondenti(String email,String username,String password,UtenteDao utenteDao) throws SQLException {
        String hashedPassword = passwordHasher(password);
        Utente utente = utenteDao.doRetrieveByEmail(email);
        return utente!=null && hashedPassword.equals(utente.getPassword()) && username.equals(utente.getUsername());
    }
    /**
     * Il metodo effettua l'hashing della password passata come input con l'algoritmo SHA-512.
     * @param password
     * @return Il metodo restituisce la password dopo che ne è stato effettuato l'hashing.
     */
    public static String passwordHasher(String password){
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(password.getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            // return the HashText
            return hashtext;
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
