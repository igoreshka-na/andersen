package service;

import db.DAOgroup.DAOclassGroupImp;
import db.DAOrole.DAOclassRoleImp;
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
    private final DAOclassUserImp users;
    private final DAOclassGroupImp groups;
    private final DAOclassRoleImp roles;

    public SOAPclassImpl() {
        users = new DAOclassUserImp();
        groups = new DAOclassGroupImp();
        roles = new DAOclassRoleImp();
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
    @WebMethod
    public List<User> findAllInGroup(String group) {
        try {
            return users.findAllInGroup(group.toLowerCase(Locale.ROOT));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @WebMethod
    public boolean deleteUser(int id) {
        try {
            return users.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @WebMethod
    public boolean saveUser(User user) {
        try {
            return users.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @WebMethod
    public List<Group> findAllGroups() {
        try {
            return groups.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @WebMethod
    public boolean saveGroup(Group group) {
        try {
            return groups.insert(group);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @WebMethod
    public boolean deleteGroup(String name) {
        try {
            return groups.delete(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @WebMethod
    public boolean insertUserInGroup(int idUser, String groupName) {
        return groups.insertUserInGroup(idUser, groupName);
    }

    @Override
    @WebMethod
    public boolean insertTeamLeadInGroup(int idUser, String groupName) {
        return groups.insertTeamLeadInGroup(idUser, groupName);
    }

    @Override
    @WebMethod
    public boolean deleteUserInGroup(int idUser) {
        return groups.deleteUserInGroup(idUser);
    }

    @Override
    @WebMethod
    public boolean deleteTeamLeadInGroup(int idUser, String groupName) {
        return groups.deleteTeamLeadInGroup(idUser, groupName);
    }

    @Override
    @WebMethod
    public boolean setAdmin(int idUser) {
        return roles.setAdmin(idUser);
    }

    @Override
    @WebMethod
    public boolean setUser(int idUser) {
        return roles.setUser(idUser);
    }
}
