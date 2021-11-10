package db;

import model.Role;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOclassImp implements DAOinterfaceImp {
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
        final String sql = "SELECT * FROM wsdb.users WHERE id = ? " +
                "FULL OUTER JOIN wsdb.user_roles role ON users.id = role.user_id";
        String name = "", surname = "";
        Role role;
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            name = resultSet.getString("name");
            surname = resultSet.getString("surname");
            role = Role.valueOf(resultSet.getString("role"));
            user = new User(id, name, surname, role);
        }
        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> list = new ArrayList<>();
        final String sql = "SELECT * FROM wsdb.users FULL OUTER JOIN wsdb.user_roles wsdb.user_roles role ON users.id = role.user_id AND " +
                "FULL OUTER JOIN wsdb.groups group ON users.id = group.user_id";
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
    public List<User> findAllINGroup(String group) throws SQLException {
        List<User> list = new ArrayList<>();
        final String sql = "SELECT * FROM wsdb.users WHERE group = ? FULL OUTER JOIN wsdb.user_roles wsdb.user_roles role ON users.id = role.user_id AND " +
                "FULL OUTER JOIN wsdb.groups group ON users.id = group.user_id";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, group);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Role role = Role.valueOf(resultSet.getString("role"));

                User person = new User(id, name, surname, role);
                list.add(person);
            }
            return list;
        }
    }

    @Override
    public boolean insert(User person) throws SQLException {
        User user = find(person.getId());
        if (user == null) {
            final String sql1 = "INSERT INTO wsdb.users (id, name, surname) VALUES (?, ?, ?)";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql1)) {
                statement.setLong(1, person.getId());
                statement.setString(2, person.getName());
                statement.setString(3, person.getSurname());
                return statement.executeUpdate() > 0;
            } catch (NullPointerException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            final String sql = "UPDATE wsdb.users SET name = ?, surname = ? WHERE id = ?";
            boolean rowUpdated;
            Connection connection = getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, person.getName());
            statement.setString(2, person.getSurname());
            statement.setLong(3, person.getId());
            rowUpdated = statement.executeUpdate() > 0;

            return rowUpdated;
        }
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
}
