package service;

import db.DAOclassImp;
import model.Group;
import model.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "service.SOAPService")
public class SOAPclassImpl implements SOAPService {
    private DAOclassImp users;

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
    public List<User> findAllForGroup(Group group) {
        try {
            return users.findAllINGroup(group.getName());
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
    public boolean saveUser(User user) {
        try {
            return users.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Group> findAllGroups() {
        return null;
    }

    @Override
    public boolean insertGroup(Group group) {
        return false;
    }

    @Override
    public boolean deleteGroup(int id) {
        return false;
    }

    @Override
    public boolean insertUserInGroup(long idUser, int idGroup) {
        return false;
    }

    @Override
    public boolean insertTeamLeadInGroup(long idUser, int idGroup) {
        return false;
    }

    @Override
    public boolean deleteUserInGroup(long idUser, int idGroup) {
        return false;
    }

    @Override
    public boolean deleteTeamLeadInGroup(long idUser, int idGroup) {
        return false;
    }

    @Override
    public boolean insertAdmin(long idUser) {
        return false;
    }

    @Override
    public boolean insertUser(long idUser) {
        return false;
    }
}
