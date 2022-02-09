package it.unisa.emaa.www.sito.Data.dao;

import it.unisa.emaa.www.sito.Data.entity.Utente;

import java.sql.SQLException;
import java.util.List;


/**
 * Interfaccia IUtenteDao contenente i metodi da far eseguire alla rispettiva classe UtenteDao
 *
 *
 *
 * @author Antonio Scotellaro
 */



public interface IUtenteDao {
    public List<Utente> doRetrieveAll() throws SQLException;
    public Utente doRetrieveByUsername(String username) throws SQLException;
    public Utente doRetrieveByEmail(String email) throws SQLException;
    public boolean doSave(Utente utente) throws SQLException;
    public boolean doDelete(String email) throws SQLException;



}

