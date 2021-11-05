import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Web servlet for interaction with the application server.
 * Methods "doGet" and "doPost" for reading, saving, editing and deleting objects from the database are written.
 */
@WebServlet("/")
public class CompanyController extends HttpServlet {
    private final CRUDcompany crudCompany;
    private static final Logger LOGGER = Logger.getLogger(CompanyController.class.getName());

    public CompanyController() {
        crudCompany = new CRUDcompany();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getServletPath();
        try {
            switch (action) {
                case "/new" -> showNewForm(req, resp);
                case "/insert" -> insertCompany(req, resp);
                case "/delete" -> deleteCompany(req, resp);
                case "/edit" -> showEditForm(req, resp);
                case "/update" -> updateCompany(req, resp);
                default -> listCompany(req, resp);
            }
        } catch (SQLException e) {
            //For simplicity just Log the exceptions
            LOGGER.log(Level.SEVERE, "SQL ERROR", e);
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        Company existingCompany = crudCompany.find(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/company-form.jsp");
        req.setAttribute("company", existingCompany);
        dispatcher.forward(req, resp);
    }

    private void updateCompany(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        String creator = req.getParameter("creator");

        Company company = new Company(id, name, city, creator);
        crudCompany.update(company);
        resp.sendRedirect("list");
    }

    private void deleteCompany(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        crudCompany.delete(id);
        resp.sendRedirect("list");
    }

    private void insertCompany(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        String creator = req.getParameter("creator");

        Company company = new Company(name, city, creator);
        crudCompany.insert(company);
        resp.sendRedirect("list");
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/company-form.jsp");
        dispatcher.forward(req, resp);
    }

    private void listCompany(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        List<Company> listCompany = crudCompany.findAll();
        req.setAttribute("listCompany", listCompany);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/company-list.jsp");
        dispatcher.forward(req, resp);
    }
}
