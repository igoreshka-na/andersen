package db;

import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOperson implements DAOinterfacePerson {
    private final String driver = "org.postgresql.Driver";
    private final String url = "jdbc:postgresql://localhost:5432/wsdb?useSSL=false&useUnicode=true&serverTimezone=UTC";
    private final String username = "???";
    private final String password = "???";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public Person find(Long id) throws SQLException {
        Person person = null;
        final String sql = "SELECT * FROM wsdb.users WHERE id = ?";
        String name = "", surname = "", role = "", group = "";
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            name = resultSet.getString("name");
            surname = resultSet.getString("surname");
            role = resultSet.getString("role");
            group = resultSet.getString("group");
            person = new Person(id, name, surname, role, group);
        }
        return person;
    }

    @Override
    public List<Person> findAll() throws SQLException {
        List<Person> list = new ArrayList<>();
        final String sql = "SELECT * FROM wsdb.users";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String role = resultSet.getString("role");
                String group = resultSet.getString("group");

                Person person = new Person(id, name, surname, role, group);
                list.add(person);
            }
            return list;
        }
    }

    @Override
    public void insert(Person person) throws SQLException {
        final String sql = "INSERT INTO wsdb.users (id, name, surname, role, group) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, person.getId());
            statement.setString(2, person.getName());
            statement.setString(3, person.getSurname());
            statement.setString(4, person.getRole());
            statement.setString(5, person.getGroup());
            statement.executeUpdate();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(Person person) throws SQLException {
        final String sql = "UPDATE wsdb.users SET name = ?, surname = ?, role = ?, group = ? WHERE id = ?";
        boolean rowUpdated;
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, person.getName());
        statement.setString(2, person.getSurname());
        statement.setString(3, person.getRole());
        statement.setString(4, person.getGroup());
        statement.setLong(5, person.getId());
        rowUpdated = statement.executeUpdate() > 0;

        return rowUpdated;
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        final String sql = "DELETE FROM wsdb.users WHERE id = ?";
        boolean rowDeleted;

        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }

    public List<Person> findAllID() throws SQLException {
        List<Person> list = new ArrayList<>();
        final String sql = "SELECT wsdb.id FROM wsdb.users WHERE role = dev OR role = lead";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Person person = new Person(id);
                list.add(person);
            }
            return list;
        }
    }
}
