package it.unisa.emaa.www.sito.Data.dao;

import it.unisa.emaa.www.sito.Data.ConnPool;
import it.unisa.emaa.www.sito.Data.entity.Storia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe StoriaDao è il dao dell'entità storia la classe estende un interfaccia dao contenente i metodi da
 * effettuare
 * @author Antonio Scotellaro
 *
 *
 */




public class StoriaDao implements IStoriaDao {
    /**
     * effettua una query di selezione di ogni storia in base all'ID
     * @param id
     * @return
     */
    @Override
    public Storia doRetrieveById(int id) throws SQLException {
        Connection conn = ConnPool.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from storia  where id =?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if(!rs.isBeforeFirst())
            return null;
        rs.next();
        Storia storia = new Storia();
        storia.setContenuto(rs.getString("contenuto"));
        storia.setDataCreazione(rs.getDate("dataCreazione").toLocalDate());
        storia.setNCommenti(rs.getInt("nCommenti"));
        storia.setNReazioni(rs.getInt("nReazioni"));
        storia.setUsername(rs.getString("username"));
        storia.setId(rs.getInt("id"));
        rs.close();
        return storia;
    }

    /**
     * salva una storia nella base di dati
     * @param storia
     * @return
     */
    @Override
    public boolean doSave(Storia storia) throws SQLException {
        Connection conn = ConnPool.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT into storia (username,contenuto,nReazioni,nCommenti,dataCreazione) values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, storia.getUsername());
        ps.setString(2, storia.getContenuto());
        ps.setInt(3,storia.getNReazioni());
        ps.setInt(4,storia.getNCommenti());
        ps.setDate(5, Date.valueOf(storia.getDataCreazione()));
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        storia.setId(rs.getInt(1));  // viene preso l'id autoincrementato dopo l'insert e settato
        return true;
    }
    /**
     * effettua una query prendendo le storie in base ad un limit ed offset che indicano la pagina
     * @param limit
     * @param offset
     * @return
     */
    @Override
    public List<Storia> doRetrieveByPage(int limit, int offset) throws SQLException {
        Connection conn = ConnPool.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from storia order by dataCreazione DESC LIMIT ?,? "); // primo punto interrogativo offset, il secondo limit
        ps.setInt(1, offset);
        ps.setInt(2, limit);
        ResultSet rs = ps.executeQuery();
        ArrayList<Storia> list = new ArrayList<>();
        while (rs.next()) {
            Storia storia = new Storia();
            storia.setContenuto(rs.getString("contenuto"));
            storia.setDataCreazione(rs.getDate("dataCreazione").toLocalDate());
            storia.setNCommenti(rs.getInt("nCommenti"));
            storia.setNReazioni(rs.getInt("nReazioni"));
            storia.setId(rs.getInt("id"));
            storia.setUsername(rs.getString("username"));
            list.add(storia);
        }
        rs.close();
        return list;
    }
}

