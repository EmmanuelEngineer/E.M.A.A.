package it.unisa.emaa.www.sito.Data.entity;


import java.util.Objects;

/**
 *Classe entity Commento che è il rispettivo della tabella commento all'interno della base di dati
 * contiene gli attributi della tabella come variabili ed i metodi get e set utili per l'esecuzione
 * del design pattern dao
 *
 * @author Antonio Scotellaro
 *
 *

 */



public class Commento {
    private int id;
    private int idStoria;
    private String username;
    private String contenuto;

    @Override
    public boolean equals(Object o) {
        Commento commento = (Commento) o;
        return id == commento.id && idStoria == commento.idStoria && username.equals(commento.username) && Objects.equals(contenuto, commento.contenuto);
    }
    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdStoria() {
        return idStoria;
    }

    public void setIdStoria(int idStoria) {
        this.idStoria = idStoria;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}