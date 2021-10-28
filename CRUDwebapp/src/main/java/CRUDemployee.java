import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class CRUDemployee implements EmployeeCRUD {

    private static class SingletonHelper {
        private static final CRUDemployee INSTANCE = new CRUDemployee();
    }

    public static CRUDemployee getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Employee> find(String id) throws SQLException {
        String sql = "SELECT id_employee, name, id_company FROM andersencrud.employees WHERE id_employee = ?";
        int id_employee = 0, id_company = 0;
        String name = "";
        Connection conn = DataBaseConnectionsFactory.getConnection();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id_employee = resultSet.getInt("id_employee");
            name = resultSet.getString("name");
            id_company = resultSet.getInt("id_company");
        }
        return Optional.of(new Employee(id_employee, name, id_company));
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> listStuff = new ArrayList<>();
        String sql = "SELECT id_company, name, id_company FROM andersencrud.employees";

        Connection conn = DataBaseConnectionsFactory.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id_employee = resultSet.getInt("id_employee");
            String name = resultSet.getString("name");
            int id_company = resultSet.getInt("id_company");

            Employee employee = new Employee (id_employee, name, id_company);
            listStuff.add(employee);
        }
        return listStuff;
    }

    @Override
    public boolean save(Employee employee) throws SQLException {
        String sql = "INSERT into andersencrud.employee (name) VALUES (?)";
        boolean rowInserted;
        Connection conn = DataBaseConnectionsFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, employee.getName());
        rowInserted = statement.executeUpdate() > 0 ;

        return rowInserted;
    }

    @Override
    public boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE andersencrud.employee SET name = ?";
        sql += " WHERE id_employee = ?";
        boolean rowUpdated;
        Connection conn = DataBaseConnectionsFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, employee.getName());
        statement.setInt(2, employee.getId());
        rowUpdated = statement.executeUpdate() > 0;

        return rowUpdated;
    }

    @Override
    public boolean delete(Employee employee) throws SQLException {
        String sql = "DELETE FROM andersencrud.employee where id_company = ?";
        boolean rowDeleted;

        Connection conn = DataBaseConnectionsFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, employee.getId());
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
