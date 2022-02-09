package it.unisa.emaa.www.sito.Data.dao;

import it.unisa.emaa.www.sito.Data.entity.Commento;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia ICommentoDao contenente i metodi da far eseguire alla rispettiva classe CommentoDao
 *
 *
 *
 * @author Antonio Scotellaro
*/


public interface ICommentoDao {
    public List<Commento> doRetrieveAll() throws SQLException;
    public List<Commento> doRetrieveByStoria(int idStoria) throws SQLException;
    public boolean doSave(Commento commento) throws SQLException;




}
