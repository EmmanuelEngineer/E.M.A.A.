package it.unisa.emaa.www.sito.Data.entity;


import java.util.Objects;

/**
 *Classe entity Utente che è il rispettivo della tabella utente all'interno della base di dati
 * contiene gli attributi della tabella come variabili ed i metodi get e set utili per l'esecuzione
 * del design pattern dao
 *
 * @author Antonio Scotellaro
 *
 *

*/
public class Utente {
    private String id;
    private String password;
    private String username;



    @Override
    public boolean equals(Object o) {
        Utente utente = (Utente) o;
        return id.equals(utente.id) && Objects.equals(password, utente.password) && Objects.equals(username, utente.username);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}