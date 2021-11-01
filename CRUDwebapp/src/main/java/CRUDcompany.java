import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that connects the web application to the database and sends queries to the database to return models of the Company class.
 * Implements the interface CompanyCRUD.
 */
@NoArgsConstructor
public class CRUDcompany implements CompanyCRUD {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/andersencrud?useSSL=false&useUnicode=true&serverTimezone=UTC";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "admadm45";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public Company find(String id) throws SQLException {
        String sql = "SELECT * FROM andersencrud.company WHERE id = ?;";
        String name = "", city = "", creator = "";
        Connection conn = getConnection();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            name = resultSet.getString("name");
            city = resultSet.getString("city");
            creator = resultSet.getString("creator");
        }
        return new Company(name, city, creator);
    }

    @Override
    public List<Company> findAll() throws SQLException {
        List<Company> listCompany = new ArrayList<>();
        String sql = "SELECT * FROM andersencrud.company;";
        try (Connection myconnection = getConnection();
             PreparedStatement preparedStatement = myconnection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                String creator = resultSet.getString("creator");

                Company company = new Company(id, name, city, creator);
                listCompany.add(company);
            }
            return listCompany;
        }
    }

    @Override
    public boolean save(Company company) throws SQLException {
        String sql = "INSERT into andersencrud.company (name, city, creator) VALUES (?, ?, ?);";
        boolean rowInserted;
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, company.getName());
        statement.setString(2, company.getCity());
        statement.setString(3, company.getCreator());
        rowInserted = statement.executeUpdate() > 0;

        return rowInserted;
    }

    @Override
    public boolean update(Company company) throws SQLException {
        String sql = "UPDATE andersencrud.company SET name = ?, city = ?, creator = ? WHERE id = ?;";
        boolean rowUpdated;
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, company.getName());
        statement.setString(2, company.getCity());
        statement.setString(3, company.getCreator());
        statement.setInt(4, company.getId());
        rowUpdated = statement.executeUpdate() > 0;

        return rowUpdated;
    }

    @Override
    public boolean delete(Company company) throws SQLException {
        String sql = "DELETE FROM andersencrud.company where id = ?;";
        boolean rowDeleted;

        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, company.getId());
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
