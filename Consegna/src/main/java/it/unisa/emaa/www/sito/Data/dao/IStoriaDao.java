package it.unisa.emaa.www.sito.Data.dao;

import it.unisa.emaa.www.sito.Data.entity.Storia;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia IStoriaDao contenente i metodi da far eseguire alla rispettiva classe StoriaDao
 *
 *
 *
 * @author Antonio Scotellaro
 */



public interface IStoriaDao {
    Storia doRetrieveById(int id) throws SQLException;
    boolean doSave(Storia storia) throws SQLException;
    List<Storia> doRetrieveByPage(int limit, int offset) throws SQLException;





}
