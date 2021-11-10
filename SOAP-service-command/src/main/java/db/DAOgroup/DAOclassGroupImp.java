package db.DAOgroup;

import db.FactoryConnections;
import model.Group;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOclassGroupImp implements DAOinterfaceGroup {
    private FactoryConnections factory;

    @Override
    public Group find(String nameGroup) throws SQLException {
        Group group = null;
        User lead;
        final String sql = "SELECT DISTINCT name FROM wsdb.user_groups WHERE name = ? " +
                "FULL OUTER JOIN wsdb.users user ON user_groups.team_lead_id = user.id";
        int id;
        String nameLead = "", surnameLead = "";

        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nameGroup);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nameGroup = resultSet.getString("name");
                id = resultSet.getInt("id");
                nameLead = resultSet.getString("name");
                surnameLead = resultSet.getString("surname");

                lead = new User(id, nameLead, surnameLead);
                group = new Group(nameGroup, lead);
            }
            return group;
        }
    }

    @Override
    public List<Group> findAll() throws SQLException {
        List<Group> list = new ArrayList<>();
        final String sql = "SELECT DISTINCT name FROM wsdb.users FULL OUTER JOIN wsdb.users user ON user_groups.team_lead_id = user.id";

        try (Connection connection = factory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nameGroup = resultSet.getString("name");
                int id = resultSet.getInt("id");
                String nameLead = resultSet.getString("name");
                String surnameLead = resultSet.getString("surname");

                User lead = new User(id, nameLead, surnameLead);
                Group groups = new Group(nameGroup, lead);
                list.add(groups);
            }
            return list;
        }
    }

    @Override
    public boolean insert(Group group) throws SQLException {
        Group groups = find(group.getName());
        if (groups == null) {
            final String sql = "INSERT INTO wsdb.user_groups (name, team_lead_id) VALUES (?, ?)";

            try (Connection connection = factory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, group.getName());
                statement.setInt(2, group.getTeamLead().getId());
                return statement.executeUpdate() > 0;
            } catch (NullPointerException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            final String sql = "UPDATE wsdb.user_groups SET team_lead_id = ? WHERE name = ?";
            boolean rowUpdated;

            try (Connection connection = factory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, group.getName());
                statement.setInt(2, group.getTeamLead().getId());
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
}
