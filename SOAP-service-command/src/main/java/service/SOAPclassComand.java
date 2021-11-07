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

    @Override
    @WebMethod(operationName = "setPerson")
    public Person setPerson(long id, String name, String surname, String role, String group) throws SQLException {
        Person person = new Person(id, name, surname, role, group);
        persons.insert(person);
        return person;
    }

    @Override
    @WebMethod(operationName = "getPerson")
    public Person getPerson(long id) throws SQLException {
        return persons.find(id);
    }

    /**
     * Метод для нотификатора (ID участников, не админа и не кураторов)
     *
     * @return List
     * @throws SQLException
     */
    @Override
    @WebMethod(operationName = "getListID")
    public List<Long> getIdList() throws SQLException {
        return null;
    }
}
