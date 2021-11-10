package service;

import db.DAOgroup.DAOclassGroupImp;
import db.DAOuser.DAOclassUserImp;
import model.Group;
import model.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

@WebService(endpointInterface = "service.SOAPService")
public class SOAPclassImpl implements SOAPService {
    private DAOclassUserImp users;
    private DAOclassGroupImp groups;

    public SOAPclassImpl() {
    }

    @Override
    @WebMethod
    public User getUser(int id) {
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
    public List<User> findAllInGroup(String group) {
        try {
            return users.findAllINGroup(group.toLowerCase(Locale.ROOT));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteUser(int id) {
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
    public boolean saveGroup(Group group) {
        return false;
    }

    @Override
    public boolean deleteGroup(int id) {
        return false;
    }

    @Override
    public boolean insertUserInGroup(int idUser, String idGroup) {
        return false;
    }

    @Override
    public boolean insertTeamLeadInGroup(int idUser, String idGroup) {
        return false;
    }

    @Override
    public boolean deleteUserInGroup(int idUser, String idGroup) {
        return false;
    }

    @Override
    public boolean deleteTeamLeadInGroup(int idUser, String idGroup) {
        return false;
    }

    @Override
    public boolean setAdmin(long idUser) {
        return false;
    }

    @Override
    public boolean setUser(long idUser) {
        return false;
    }
}
