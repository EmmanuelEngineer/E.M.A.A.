package it.unisa.emaa.www.sito.Data.dao;

import it.unisa.emaa.www.sito.Data.entity.Reazione;

import java.sql.SQLException;
import java.util.List;


/**
 * Interfaccia IReazioneDao contenente i metodi da far eseguire alla rispettiva classe ReazioneDao
 *
 *
 *
 * @author Antonio Scotellaro
 */


public interface IReazioneDao {

    public List<Reazione> doRetrieveByStoria(int idStoria) throws SQLException;
    public List<Reazione> doRetrieveByEmail(String email) throws SQLException;
    public Reazione doRetrieve(String email,int idStoria) throws SQLException;
    public boolean doSave(Reazione reazione) throws SQLException;


}

