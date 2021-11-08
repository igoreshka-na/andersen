package service;

import db.DAOperson;
import model.Person;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "service.SOAPService")
public class SOAPclassComand implements SOAPService {
    private DAOperson persons;

    public SOAPclassComand() {
    }

    @Override
    @WebMethod
    public Person setPerson(long id, String name, String surname, String role, String group) throws SQLException {
        Person person = new Person(id, name, surname, role, group);
        persons.insert(person);
        return person;
    }

    @Override
    @WebMethod
    public Person getPerson(long id) throws SQLException {
        return persons.find(id);
    }

    @Override
    @WebMethod
    public boolean updatePerson(long id, String name, String surname, String role, String group) throws SQLException {
        Person person = new Person(id, name, surname, role, group);
        return persons.update(person);
    }

    @Override
    @WebMethod
    public boolean deletePerson(long id) throws SQLException {
        return persons.delete(id);
    }

    @Override
    @WebMethod
    public List<Person> getIdList() throws SQLException {
        return persons.findAllID();
    }
}
