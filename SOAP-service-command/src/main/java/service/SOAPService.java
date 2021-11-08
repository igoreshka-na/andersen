package service;

import model.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface SOAPService {

    @WebMethod
    User getUser(long id);

    @WebMethod
    boolean deleteUser(long id);

    @WebMethod
    List<User> getAllUsers();

    @WebMethod
    boolean saveUser(User user);
}
