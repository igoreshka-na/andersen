package db;

import java.sql.SQLException;
import java.util.List;

public interface DAOinterface<T, ID> {
    T find(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

    boolean insert(T o) throws SQLException;

    boolean delete(ID id) throws SQLException;
}
