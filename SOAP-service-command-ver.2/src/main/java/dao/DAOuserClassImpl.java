package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DAOuserClassImpl implements DAOuser {
    private final SessionFactory sessionFactory;
    private Session session;
    private EntityTransaction transaction;

    @Autowired
    public DAOuserClassImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAllUsers() {
        session = sessionFactory.openSession();
        List<User> list = session.createQuery("from models.User").list();
        session.close();
        return list;
    }

//    @Override
//    public List<User> findUsersInGroup(String group) {
//        session = sessionFactory.openSession();
//        transaction = session.beginTransaction();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
//        Root<User> root = criteriaQuery.from(User.class);
//        criteriaQuery.select(root);
//        criteriaQuery.where(criteriaBuilder.equal(root.get("group_name"), group));
//        List<User> users = session.createQuery(criteriaQuery).getResultList();
//        transaction.commit();
//        session.close();
//        return users;
//    }

    /**
     * Used the Session.merge() method to save new users or update old ones.
     */
    @Override
    public boolean save(User user) {
        session = sessionFactory.openSession();
        transaction = session.getTransaction();

        transaction.begin();
        session.merge(user);
        transaction.commit();
        session.close();
        return sessionFactory.isClosed();
    }

    @Override
    public boolean delete(User user) {
        session = sessionFactory.openSession();
        transaction = session.getTransaction();

        transaction.begin();
        session.delete(user);
        transaction.commit();
        session.close();
        return sessionFactory.isClosed();
    }

    /**
     * Used the Session.get() method to get a ready entity with loaded data.
     * @param id User's identification number
     * @return model User
     */
    @Override
    public User findById(Integer id) {
        session = sessionFactory.openSession();
        transaction = session.getTransaction();

        transaction.begin();
        User user = session.get(User.class, id);
        transaction.commit();
        session.close();
        return user;
    }
}
