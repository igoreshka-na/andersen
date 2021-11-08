package service;

import db.DAOperson;
import model.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "service.SOAPService")
public class SOAPclassImpl implements SOAPService {
    private DAOperson users;

    public SOAPclassImpl() {
    }

    @Override
    @WebMethod
    public User getUser(long id) {
        try {
            return users.find(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteUser(long id) {
        try {
            return users.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @WebMethod
    public List<User> getAllUsers() {
        try {
            return users.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveUser(User user) {
        try {
            return users.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
