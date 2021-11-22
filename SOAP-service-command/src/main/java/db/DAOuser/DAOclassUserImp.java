package db.DAOuser;

import db.FactoryConnections;
import model.Role;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOclassUserImp implements DAOinterfaceUser {
    private final FactoryConnections factory;

    public DAOclassUserImp() {
        factory = new FactoryConnections();
    }

    @Override
    public User find(Integer id) throws SQLException {
        User user = null;
        final String sql = "SELECT * FROM wsdb.users " +
                "FULL OUTER JOIN wsdb.user_roles role ON users.id = role.user_id WHERE id = ?";
        String name = "", surname = "";
        Role role;
        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

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
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> list = new ArrayList<>();
        final String sql = "SELECT * FROM wsdb.users FULL OUTER JOIN wsdb.user_roles role ON users.id = role.user_id";
        try (Connection connection = factory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Role role = Role.valueOf(resultSet.getString("role"));

                User user = new User(id, name, surname, role);
                list.add(user);
            }
            return list;
        }
    }

    public List<User> findAllInGroup(String group) throws SQLException {
        List<User> list = new ArrayList<>();
        final String sql = "SELECT * FROM wsdb.users FULL OUTER JOIN wsdb.user_roles role ON users.id = role.user_id WHERE group_id = ?";
        final String sqlG = "SELECT id FROM wsdb.user_groups WHERE name = ?";

        try (Connection connection = factory.getConnection();
             PreparedStatement statement1 = connection.prepareStatement(sqlG);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            statement1.setString(1, group);
            ResultSet resultSet = statement1.executeQuery();
            int groupy = 0;
            if (resultSet.next()) {
                groupy = resultSet.getInt("id");
            }
            preparedStatement.setInt(1, groupy);

            ResultSet resultSet2 = preparedStatement.executeQuery();

            while (resultSet2.next()) {
                int id = resultSet2.getInt("id");
                String name = resultSet2.getString("name");
                String surname = resultSet2.getString("surname");
                Role role = Role.valueOf(resultSet2.getString("role"));

                User user = new User(id, name, surname, role);
                list.add(user);
            }
            return list;
        }
    }

    @Override
    public boolean insert(User user) throws SQLException {
        User users = find(user.getId());
        if (users == null) {
            final String sql1 = "INSERT INTO wsdb.users (id, name, surname) VALUES (?, ?, ?)";
            final String sql2 = "INSERT INTO wsdb.user_roles (user_id, role) VALUES (?, ?)";
            try (Connection connection = factory.getConnection();
                 PreparedStatement statement1 = connection.prepareStatement(sql1);
                 PreparedStatement statement2 = connection.prepareStatement(sql2)) {

                statement1.setInt(1, user.getId());
                statement1.setString(2, user.getName());
                statement1.setString(3, user.getSurname());

                statement2.setInt(1, user.getId());
                statement2.setString(2, String.valueOf(Role.USER));
                statement1.executeUpdate();
                return statement2.executeUpdate() > 0;
            } catch (NullPointerException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            final String sql = "UPDATE wsdb.users SET name = ?, surname = ? WHERE id = ?";
            boolean rowUpdated;
            try (Connection connection = factory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getSurname());
                statement.setInt(3, user.getId());
                rowUpdated = statement.executeUpdate() > 0;

                return rowUpdated;
            }
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        final String sql = "DELETE FROM wsdb.users WHERE id = ?";
        boolean rowDeleted;

        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            return rowDeleted;
        }
    }
}
