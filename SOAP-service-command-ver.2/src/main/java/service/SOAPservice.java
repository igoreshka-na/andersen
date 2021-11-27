package service;

import models.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface SOAPservice {
    @WebMethod
    User getUser(@WebParam(name = "id") int id);

    @WebMethod
    List<User> getAllUsers();

    @WebMethod
    List<User> findAllInGroup(@WebParam(name = "group")String group);

    @WebMethod
    boolean deleteUser(@WebParam(name = "id") int id);

    @WebMethod
    boolean saveUser(@WebParam(name = "user") User user);
}
