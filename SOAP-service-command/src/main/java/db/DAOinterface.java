package db;

import model.Person;

import java.sql.SQLException;
import java.util.List;

public interface DAOinterface<T, ID> {
    Person find(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

    void insert(T o) throws SQLException;

    boolean update (T o) throws SQLException;

    boolean delete (ID id) throws SQLException;
}
