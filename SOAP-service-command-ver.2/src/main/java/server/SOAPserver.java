package server;

import dao.DAOuserClassImpl;
import models.Mentor;
import models.Role;
import models.Student;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.SOAPserviceClassImpl;
import spring.SpringConfig;
import utils.HibernateUtil;

import javax.xml.ws.Endpoint;

public class SOAPserver {
    private static final String url = "http://localhost:8081/teamService";

    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Endpoint.publish(url, new SOAPserviceClassImpl());
//        DAOuserClassImpl dao = new DAOuserClassImpl((SessionFactory) context.getBean("sessionFactory"));
//        Student student = new Student(3411, "321", "123", Role.STUDENT, "pink");
//        dao.save(student);
//        dao.save(new Student(4356, "123", "321", Role.STUDENT, "pink"));
//        dao.save(new Student(5688, "231", "213", Role.STUDENT, "pink"));
//        dao.save(new Mentor(4321, "Nikolay", "Lox", Role.MENTOR));
//        dao.save(new Mentor(2355, "Igor", "Lox", Role.MENTOR));
//        System.out.println(dao.findAllUsers());
//        System.out.println(dao.findById(5688));
//        dao.delete(student);
    }
}
