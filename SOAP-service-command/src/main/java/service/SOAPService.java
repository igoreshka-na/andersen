package service;

import model.Group;
import model.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface SOAPService {

    /**
     * Методы работы с участниками
     */
    @WebMethod
    User getUser(int id);

    @WebMethod
    List<User> getAllUsers();

    @WebMethod
    List<User> findAllInGroup(String group);

    @WebMethod
    boolean deleteUser(int id);

    @WebMethod
    boolean saveUser(User user);

    /**
     * Методы для работы с группами
     */
    @WebMethod
    List<Group> findAllGroups();

    @WebMethod
    boolean saveGroup(Group group);

    @WebMethod
    boolean deleteGroup(int id);

    @WebMethod
    boolean insertUserInGroup(int idUser, String idGroup);

    @WebMethod
    boolean insertTeamLeadInGroup(int idUser, String idGroup);

    @WebMethod
    boolean deleteUserInGroup(int idUser, String idGroup);

    @WebMethod
    boolean deleteTeamLeadInGroup(int idUser, String idGroup);

    /**
     * Методы для работы с ролями
     */
    @WebMethod
    boolean setAdmin(long idUser);

    @WebMethod
    boolean setUser(long idUser);
}
