package db.DAOrole;

import db.FactoryConnections;
import model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOclassRoleImp implements DAOinterfaceRole {
    private final FactoryConnections factory;

    public DAOclassRoleImp() {
        factory = new FactoryConnections();
    }

    @Override
    public boolean setAdmin(int id) {
        final String sql1 = "INSERT INTO wsdb.user_roles (user_id, role) VALUES (?, ?)";

        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql1)) {

            statement.setLong(1, id);
            statement.setString(2, String.valueOf(Role.ADMIN));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean setUser(int id) {
        final String sql1 = "INSERT INTO wsdb.user_roles (user_id, role) VALUES (?, ?)";

        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql1)) {

            statement.setLong(1, id);
            statement.setString(2, String.valueOf(Role.USER));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
