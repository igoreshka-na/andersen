package service;

import dao.DAOuserClassImpl;
import models.User;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.SpringConfig;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "service.SOAPservice")
public class SOAPserviceClassImpl implements SOAPservice {
    private final DAOuserClassImpl dao;

    public SOAPserviceClassImpl() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        dao = new DAOuserClassImpl((SessionFactory) context.getBean("sessionFactory"));
    }

    @Override
    @WebMethod
    public User getUser(int id) {
        return dao.findById(id);
    }

    @Override
    @WebMethod
    public List<User> getAllUsers() {
        return dao.findAllUsers();
    }

    @Override
    @WebMethod
    public List<User> findAllInGroup(String group) {
//        return dao.findUsersInGroup(group);
        return null;
    }

    @Override
    @WebMethod
    public boolean deleteUser(int id) {
        return dao.delete(new User(id));
    }

    @Override
    @WebMethod
    public boolean saveUser(User user) {
        return dao.save(user);
    }
}
