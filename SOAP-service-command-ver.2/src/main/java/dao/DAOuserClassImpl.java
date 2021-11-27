package dao;

import models.Mentor;
import models.Student;
import models.User;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Set;

public class DAOuserClassImpl implements DAOuser {
    private Session session;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @Override
    public List<User> findAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<User> list = session.createQuery("from models.User").list();
        session.close();
        return list;
    }

    @Override
    public User save(User user) {
        session = HibernateUtil.getSessionFactory().openSession();
        entityManager = session.getSessionFactory().createEntityManager();
        transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public User delete(User user) {
        return user;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    public Set<Mentor> getMentors(Student student) {
        session = HibernateUtil.getSessionFactory().openSession();
        Set<Mentor> mentors = student.getMentors();
        session.close();
        return mentors;
    }
}
