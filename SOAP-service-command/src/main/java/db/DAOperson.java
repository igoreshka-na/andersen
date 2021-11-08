package db;

import model.Role;
import model.User;

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
    public User find(Long id) throws SQLException {
        User user = null;
        final String sql = "SELECT * FROM wsdb.users WHERE id = ?";
        String name = "", surname = "", group = "";
        Role role = null;
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            name = resultSet.getString("name");
            surname = resultSet.getString("surname");
            role = Role.valueOf(resultSet.getString("role"));
            group = resultSet.getString("group");
            user = new User(id, name, surname, role);
        }
        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> list = new ArrayList<>();
        final String sql = "SELECT * FROM wsdb.users FULL OUTER JOIN wsdb.group";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Role role = Role.valueOf(resultSet.getString("role"));
                String group = resultSet.getString("group");

                User person = new User(id, name, surname, role);
                list.add(person);
            }
            return list;
        }
    }

    @Override
    public boolean insert(User person) throws SQLException {
        final String sql = "INSERT INTO wsdb.users (id, name, surname, role, group) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, person.getId());
            statement.setString(2, person.getName());
            statement.setString(3, person.getSurname());
            statement.setString(4, person.getRole());
            statement.setString(5, person.getGroup());
            return statement.executeUpdate() > 0;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public boolean update(User person) throws SQLException {
//        final String sql = "UPDATE wsdb.users SET name = ?, surname = ?, role = ?, group = ? WHERE id = ?";
//        boolean rowUpdated;
//        Connection connection = getConnection();
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1, person.getName());
//        statement.setString(2, person.getSurname());
//        statement.setString(3, person.getRole());
//        statement.setString(4, person.getGroup());
//        statement.setLong(5, person.getId());
//        rowUpdated = statement.executeUpdate() > 0;
//
//        return rowUpdated;
//    }

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
}
