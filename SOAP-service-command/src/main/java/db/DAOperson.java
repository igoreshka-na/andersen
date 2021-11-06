package db;

import model.Person;

import java.sql.SQLException;
import java.util.List;

public class DAOperson implements DAOinterfacePerson {

    @Override
    public Person find(String s) throws SQLException {
        return null;
    }

    @Override
    public List<Person> findAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(Person o) throws SQLException {

    }

    @Override
    public boolean update(Person o) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }
}
