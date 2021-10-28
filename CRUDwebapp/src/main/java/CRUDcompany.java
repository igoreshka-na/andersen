import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class CRUDcompany implements CompanyCRUD  {

    private static class SingletonHelper {
        private static final CRUDcompany INSTANCE = new CRUDcompany();
    }

    public static CRUDcompany getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Company> find(String id) throws SQLException {
        String sql = "SELECT id_company, name, city, creator FROM andersencrud.company WHERE id_company = ?";
        int id_company = 0;
        String name = "", city = "", creator = "";
        Connection conn = DataBaseConnectionsFactory.getConnection();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id_company = resultSet.getInt("id_company");
            name = resultSet.getString("name");
            city = resultSet.getString("city");
            creator = resultSet.getString("creator");
        }
        return Optional.of(new Company(id_company, name, city, creator));
    }

    @Override
    public List<Company> findAll() throws SQLException {
        List<Company> listStuff = new ArrayList<>();
        String sql = "SELECT id_company, name, city, creator FROM andersencrud.company";

        Connection conn = DataBaseConnectionsFactory.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("stuff_id");
            String name = resultSet.getString("name");
            String city = resultSet.getString("city");
            String creator = resultSet.getString("creator");

            Company company = new Company (id, name, city, creator);
            listStuff.add(company);
        }
        return listStuff;
    }

    @Override
    public boolean save(Company company) throws SQLException {
        String sql = "INSERT into andersencrud.company (name, city, creator) VALUES (?, ?, ?)";
        boolean rowInserted;
        Connection conn = DataBaseConnectionsFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, company.getName());
        statement.setString(2, company.getCity());
        statement.setString(3, company.getCreator());
        rowInserted = statement.executeUpdate() > 0 ;

        return rowInserted;
    }

    @Override
    public boolean update(Company company) throws SQLException {
        String sql = "UPDATE andersencrud.company SET name = ?, city = ?, creator = ?";
        sql += " WHERE id_company = ?";
        boolean rowUpdated;
        Connection conn = DataBaseConnectionsFactory.getConnection();
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
        String sql = "DELETE FROM andersencrud.company where id_company = ?";
        boolean rowDeleted;

        Connection conn = DataBaseConnectionsFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, company.getId());
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
