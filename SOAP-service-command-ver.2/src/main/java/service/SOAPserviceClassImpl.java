package service;

import models.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "service.SOAPService")
public class SOAPserviceClassImpl implements SOAPservice {

    @Override
    @WebMethod
    public User getUser(int id) {
        return null;
    }

    @Override
    @WebMethod
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    @WebMethod
    public List<User> findAllInGroup(String group) {
        return null;
    }

    @Override
    @WebMethod
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    @WebMethod
    public boolean saveUser(User user) {
        return false;
    }
}
