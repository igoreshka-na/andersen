import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

@Testcontainers
public class CRUDcompanyTest {
    private static final String URL = "jdbc:mysql://localhost:3306/andersencrud?useSSL=false&useUnicode=true&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "igoreshka_naadmadm45";
    private static Connection connection;
    private static CRUDcompany crud;

    @BeforeAll
    public static void setUp() throws SQLException {
        connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        crud = new CRUDcompany(connection);
    }

    @BeforeEach
    public void refreshData() throws SQLException {
        Statement statement = connection.createStatement();
        statement.addBatch("DELETE FROM andersencrud.company;");
        statement.addBatch("ALTER TABLE andersencrud.company AUTO_INCREMENT = 1;");
        statement.addBatch("INSERT INTO andersencrud.company (id, name, city, creator) VALUES (1, 'KFC', 'New-York', 'Colonel Sanders' ), (2, 'Burger King', 'Moscow', 'Pushkin' ),(3, 'MacDonalds', 'London', 'Fred' );");
        statement.executeBatch();
    }


    @Test
    public void findAll() throws SQLException {
        List<Company> companies = crud.findAll();
        assertEquals(CRUDcompanyTestData.companies, companies);
    }

    @Test
    public void insert() throws SQLException {
        Company company = CRUDcompanyTestData.getNew();
        crud.insert(company);
        int id = company.getId();
        company.setId(id);
        assertEquals(company, crud.find(id));
    }

    @Test
    public void update() throws SQLException {
        Company company = CRUDcompanyTestData.getUpdated();
        crud.update(company);
        assertEquals(company, crud.find(CRUDcompanyTestData.COMPANY_1_ID));
    }

    @Test
    public void delete() throws SQLException {
        assertTrue(crud.delete(CRUDcompanyTestData.COMPANY_1_ID));
        assertNull(crud.find(CRUDcompanyTestData.company1.getId()));
    }

    @Test
    public void deleteNotFound() throws SQLException {
        assertFalse(crud.delete(CRUDcompanyTestData.NOT_FOUND_ID));
    }

    @Test
    public void get() throws SQLException {
        crud.find(CRUDcompanyTestData.COMPANY_1_ID);
        assertEquals(CRUDcompanyTestData.company1, crud.find(CRUDcompanyTestData.COMPANY_1_ID));
    }
}
