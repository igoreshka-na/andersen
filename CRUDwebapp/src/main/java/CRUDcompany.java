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
    private final String jdbcPassword = "igoreshka_naadmadm45";
    private Connection connection;

    public CRUDcompany(Connection connection) {
        this.connection = connection;
    }

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
    public Company find(int id) throws SQLException {
        Company company = null;
        final String sql = "SELECT * FROM andersencrud.company WHERE id = ? JOIN andersencrud.creator creator ON company.id = creator.id_company;";
        String name = "", city = "", creator = "";
        Connection connection = getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            name = resultSet.getString("name");
            city = resultSet.getString("city");
            creator = resultSet.getString("creator");
            company = new Company(id, name, city, creator);
        }
        return company;
    }

    @Override
    public List<Company> findAll() throws SQLException {
        List<Company> listCompany = new ArrayList<>();
        final String sql = "SELECT * FROM andersencrud.company JOIN andersencrud.creator cr ON company.id = cr.id_company;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
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
    public boolean update(Company company) throws SQLException {
        final String sql = "UPDATE andersencrud.company SET name = ?, city = ? WHERE id = ?;";
        boolean rowUpdated;
        Connection connection = getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, company.getName());
        statement.setString(2, company.getCity());
        statement.setInt(3, company.getId());
        rowUpdated = statement.executeUpdate() > 0;

        return rowUpdated;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        final String sql = "DELETE FROM andersencrud.company where id = ?;";
        boolean rowDeleted;

        Connection connection = getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }

    @Override
    public void insert(Company company) throws SQLException {
        final String sql = "INSERT INTO andersencrud.company (name, city) VALUES (?, ?);";
        final String sqlC = "INSERT INTO andersencrud.creator (name) VALUES (?)";
        try (Connection connection = getConnection();
             PreparedStatement psCompany = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psCreator = connection.prepareStatement(sqlC)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            psCompany.setString(1, company.getName());
            psCompany.setString(2, company.getCity());
            psCompany.executeUpdate();

            try (ResultSet generatedKeys = psCompany.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    company.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException();
                }
            }
            psCreator.setString(3, company.getCreator());
            psCreator.executeUpdate();
            connection.setAutoCommit(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
