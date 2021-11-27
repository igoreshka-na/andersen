package server;

import dao.DAOuserClassImpl;
import models.Mentor;
import models.Role;
import models.Student;
import service.SOAPserviceClassImpl;
import utils.HibernateUtil;

import javax.xml.ws.Endpoint;

public class SOAPserver {
    private static final String url = "http://localhost:8081/teamService";

    public static void main(String[] args) {
        Endpoint.publish(url, new SOAPserviceClassImpl());
//        DAOuserClassImpl dao = new DAOuserClassImpl();
//        Student student = new Student(3411, "321", "123", Role.USER, "pink");
//        dao.save(student);
//        dao.save(new Student(4356, "123", "321", Role.USER, "pink"));
//        dao.save(new Student(5688, "231", "213", Role.USER, "pink"));
//        dao.save(new Mentor(4321, "Nikolay", "Lox", Role.MENTOR));
//        dao.save(new Mentor(2355, "Igor", "Lox", Role.MENTOR));
//        System.out.println(dao.findAll());
//        System.out.println(dao.getMentors(student));
//        HibernateUtil.close();
    }
}
