package db.DAOgroup;

import db.FactoryConnections;
import model.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DAOclassGroupImp implements DAOinterfaceGroup {
    private final FactoryConnections factory;

    public DAOclassGroupImp() {
        factory = new FactoryConnections();
    }

    @Override
    public Group find(String nameGroup) throws SQLException {
        Group group = null;
        final String sql = "SELECT * FROM wsdb.user_groups WHERE user_groups.name = ?";

        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nameGroup);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                nameGroup = resultSet.getString("name");
                int lead = resultSet.getInt("team_lead_id");
                if (lead == 0) {
                    group = new Group(id, nameGroup);
                } else {
                    group = new Group(id, nameGroup, lead);
                }
            }
            return group;
        }
    }

    @Override
    public List<Group> findAll() throws SQLException {
        List<Group> list = new ArrayList<>();
        final String sql = "SELECT * FROM wsdb.user_groups";

        try (Connection connection = factory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameGroup = resultSet.getString("name");
                int lead = resultSet.getInt("team_lead_id");
                Group groups;
                if (lead == 0) {
                    groups = new Group(id, nameGroup);
                } else {
                    groups = new Group(id, nameGroup, lead);
                }
                list.add(groups);

            }
            return list;
        }
    }

    @Override
    public boolean insert(Group group) throws SQLException {
        Group groups = find(group.getName().toLowerCase(Locale.ROOT));
        if (groups == null) {
            final String sql = "INSERT INTO wsdb.user_groups (name) VALUES (?)";

            try (Connection connection = factory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, group.getName().toLowerCase(Locale.ROOT));
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

                statement.setString(1, group.getName().toLowerCase(Locale.ROOT));
                statement.setString(2, group.getName().toLowerCase(Locale.ROOT));
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

            statement.setString(1, name.toLowerCase(Locale.ROOT));
            rowDeleted = statement.executeUpdate() > 0;

            return rowDeleted;
        }
    }

    public boolean insertUserInGroup(int idUser, String groupName) {
        final String sqlG = "SELECT id FROM wsdb.user_groups WHERE name = ?";
        final String sql = "UPDATE wsdb.users SET group_id = ? WHERE id = ?";

        try (Connection connection = factory.getConnection();
             PreparedStatement statement1 = connection.prepareStatement(sqlG);
             PreparedStatement statement2 = connection.prepareStatement(sql)) {

            statement1.setString(1, groupName);
            ResultSet resultSet = statement1.executeQuery();
            int group = 0;
            if (resultSet.next()) {
                group = resultSet.getInt("id");
            }

            statement2.setInt(1, group);
            statement2.setInt(2, idUser);
            return statement2.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertTeamLeadInGroup(int idUser, String groupName) {
        final String sqlG = "SELECT id FROM wsdb.user_groups WHERE name = ?";
        final String sql = "UPDATE wsdb.user_groups SET team_lead_id = ? WHERE id = ?";

        try (Connection connection = factory.getConnection();
             PreparedStatement statement1 = connection.prepareStatement(sqlG);
             PreparedStatement statement2 = connection.prepareStatement(sql)) {

            statement1.setString(1, groupName);
            ResultSet resultSet = statement1.executeQuery();
            int group = 0;
            if (resultSet.next()) {
                group = resultSet.getInt("id");
            }

            statement2.setInt(1, idUser);
            statement2.setInt(2, group);
            return statement2.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUserInGroup(int idUser) {
        final String sql = "UPDATE wsdb.users SET group_id = ? WHERE id = ?";
        boolean rowDeleted;

        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int defaultValue = 0;

            statement.setInt(1, defaultValue);
            statement.setInt(2, idUser);
            rowDeleted = statement.executeUpdate() > 0;

            return rowDeleted;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTeamLeadInGroup(int idUser, String groupName) {
        final String sqlG = "SELECT id FROM wsdb.user_groups WHERE name = ?";
        final String sql = "UPDATE wsdb.user_groups SET team_lead_id = ? WHERE id = ?";
        boolean rowDeleted;

        try (Connection connection = factory.getConnection();
             PreparedStatement statement1 = connection.prepareStatement(sqlG);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement1.setString(1, groupName);
            ResultSet resultSet = statement1.executeQuery();
            int group = 0;
            if (resultSet.next()) {
                group = resultSet.getInt("id");
            }
            int defaultValue = 0;

            statement.setInt(1, defaultValue);
            statement.setInt(2, group);
            rowDeleted = statement.executeUpdate() > 0;

            return rowDeleted;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
