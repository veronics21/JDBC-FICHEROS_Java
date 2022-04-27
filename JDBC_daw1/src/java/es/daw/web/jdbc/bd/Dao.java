package es.daw.web.jdbc.bd;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz genérica DAO
 * @author daw1a
 * @param <T>
 */
public interface Dao<T> {
    
    public T select(int id) throws SQLException;
    
    public List<T> selectAll() throws SQLException;
    
    public void insert(T t) throws SQLException;
    
    public void update(T t) throws SQLException;
    
    public void delete(T t) throws SQLException;
    
    public void delete(int id) throws SQLException;
    
    
}