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

@WebServlet("/")
public class CompanyController extends HttpServlet {
    private CRUDcompany crudCompany = new CRUDcompany();
    private static final Logger LOGGER = Logger.getLogger(CompanyController.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/save":
                    insertStuff(req, resp);
                    break;
                case "/delete":
                    deleteStuff(req, resp);
                    break;
                case "/update":
                    updateStuff(req, resp);
                    break;
                default:
                    listCompany(req, resp);
                    break;
            }
        }catch (SQLException e) {
            //For simplicity just Log the exceptions
            LOGGER.log(Level.SEVERE, "SQL ERROR", e);
        }
    }

    private void updateStuff(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        String creator = req.getParameter("creator");

        Company company = new Company(id, name, city, creator);
        crudCompany.update(company);
        resp.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String id = req.getParameter("id");
        Company existingCompany = crudCompany.find(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/CompanyForm.jsp");
        req.setAttribute("user", existingCompany);
        dispatcher.forward(req, resp);
    }

    private void deleteStuff(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));

        Company company = new Company(id);
        crudCompany.delete(company);
        resp.sendRedirect("list");
    }

    private void insertStuff(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        String creator = req.getParameter("creator");

        Company newStuff = new Company(name, city, creator);
        crudCompany.save(newStuff);
        resp.sendRedirect("listCompany");
    }

    private void showNewForm (HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/CompanyForm.jsp");
        dispatcher.forward(req, resp);
    }

    private void listCompany(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        List<Company> listCompany = crudCompany.findAll();
        req.setAttribute("listCompany", listCompany);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/index.jsp");
        dispatcher.forward(req, resp);
    }
}
