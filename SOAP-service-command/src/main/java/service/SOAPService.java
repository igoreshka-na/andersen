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
    User getUser(long id);

    @WebMethod
    List<User> getAllUsers();

    @WebMethod
    List<User> findAllForGroup(Group group);

    @WebMethod
    boolean deleteUser(long id);

    @WebMethod
    boolean saveUser(User user);

    /**
     * Методы для работы с группами
     */
    @WebMethod
    List<Group> findAllGroups();

    @WebMethod
    boolean insertGroup(Group group);

    @WebMethod
    boolean deleteGroup(int id);

    @WebMethod
    boolean insertUserInGroup(long idUser, int idGroup);

    @WebMethod
    boolean insertTeamLeadInGroup(long idUser, int idGroup);

    @WebMethod
    boolean deleteUserInGroup(long idUser, int idGroup);

    @WebMethod
    boolean deleteTeamLeadInGroup(long idUser, int idGroup);

    /**
     * Методы для работы с ролями
     */
    @WebMethod
    boolean insertAdmin(long idUser);

    @WebMethod
    boolean insertUser(long idUser);
}
