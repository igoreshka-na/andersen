package service;

import model.Group;
import model.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface SOAPService {

    /**
     * Методы работы с участниками
     */
    @WebMethod
    User getUser(@WebParam(name = "id") int id);

    @WebMethod
    List<User> getAllUsers();

    // In development
//    @WebMethod
//    List<User> findAllInGroup(String group);

    @WebMethod
    boolean deleteUser(@WebParam(name = "id") int id);

    @WebMethod
    boolean saveUser(@WebParam(name = "user") User user);

    /**
     * Методы для работы с группами
     */
    @WebMethod
    List<Group> findAllGroups();

    @WebMethod
    boolean saveGroup(@WebParam(name = "group") Group group);

    @WebMethod
    boolean deleteGroup(@WebParam(name = "name") String name);

    @WebMethod
    boolean insertUserInGroup(@WebParam(name = "idUser") int idUser, @WebParam(name = "idGroup") String idGroup);

    @WebMethod
    boolean insertTeamLeadInGroup(@WebParam(name = "idUser") int idUser, @WebParam(name = "idGroup") String idGroup);

    @WebMethod
    boolean deleteUserInGroup(@WebParam(name = "idUser") int idUser, @WebParam(name = "idGroup") String idGroup);

    @WebMethod
    boolean deleteTeamLeadInGroup(@WebParam(name = "idUser") int idUser, @WebParam(name = "idGroup") String idGroup);

    /**
     * Методы для работы с ролями
     */
    @WebMethod
    boolean setAdmin(@WebParam(name = "idUser") int idUser);

    @WebMethod
    boolean setUser(@WebParam(name = "idUser") int idUser);
}
