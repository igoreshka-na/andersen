package db.DAOgroup;

import db.FactoryConnections;
import model.Group;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOclassGroupImp implements DAOinterfaceGroup {
    private final FactoryConnections factory;

    public DAOclassGroupImp() {
        factory = new FactoryConnections();
    }

    @Override
    public Group find(String nameGroup) throws SQLException {
        Group group = null;
        User lead;
        final String sql = "SELECT DISTINCT user_groups.name FROM wsdb.user_groups WHERE user_groups.name = ?";

        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nameGroup);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nameGroup = resultSet.getString("name");

                group = new Group(nameGroup);
            }
            return group;
        }
    }

    @Override
    public List<Group> findAll() throws SQLException {
        List<Group> list = new ArrayList<>();
        final String sql = "SELECT DISTINCT name FROM wsdb.user_groups";

        try (Connection connection = factory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nameGroup = resultSet.getString("name");

                Group groups = new Group(nameGroup);
                list.add(groups);
            }
            return list;
        }
    }

    @Override
    public boolean insert(Group group) throws SQLException {
        Group groups = find(group.getName());
        if (groups == null) {
            final String sql = "INSERT INTO wsdb.user_groups (name) VALUES (?)";

            try (Connection connection = factory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, group.getName());
                return statement.executeUpdate() > 0;
            } catch (NullPointerException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            final String sql = "UPDATE wsdb.user_groups SET name = ? WHERE name = ?";
            boolean rowUpdated;

            try (Connection connection = factory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, group.getName());
                statement.setString(2, group.getName());
                rowUpdated = statement.executeUpdate() > 0;

                return rowUpdated;
            }
        }
    }

    @Override
    public boolean delete(String name) throws SQLException {
        final String sql = "DELETE FROM wsdb.user_groups WHERE name = ?";
        boolean rowDeleted;

        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            rowDeleted = statement.executeUpdate() > 0;

            return rowDeleted;
        }
    }

    public boolean insertUserInGroup(int idUser, String idGroup) {
        final String sql = "INSERT INTO wsdb.user_groups (name, user_id) VALUES (?, ?)";

        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, idGroup);
            statement.setInt(2, idUser);
            return statement.executeUpdate() > 0;
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertTeamLeadInGroup(int idUser, String idGroup) {
        final String sql = "INSERT INTO wsdb.user_groups (name, team_lead_id) VALUES (?, ?)";

        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, idGroup);
            statement.setInt(2, idUser);
            return statement.executeUpdate() > 0;
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUserInGroup(int idUser, String idGroup) {
        final String sql = "DELETE FROM wsdb.user_groups WHERE user_id = ?";
        boolean rowDeleted;

        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

//            statement.setString(1, idGroup);
            statement.setInt(1, idUser);
            rowDeleted = statement.executeUpdate() > 0;

            return rowDeleted;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTeamLeadInGroup(int idUser, String idGroup) {
        final String sql = "DELETE FROM wsdb.user_groups WHERE team_lead_id = ?";
        boolean rowDeleted;

        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

//            statement.setString(1, idGroup);
            statement.setInt(1, idUser);
            rowDeleted = statement.executeUpdate() > 0;

            return rowDeleted;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
