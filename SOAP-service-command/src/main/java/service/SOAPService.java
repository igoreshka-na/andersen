package service;

import model.Person;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService
public interface SOAPService {
    @WebMethod
    Person setPerson(long id, String name, String surname, String role, String group) throws SQLException;

    @WebMethod
    Person getPerson(long id) throws SQLException;

    @WebMethod
    List<Person> getIdList() throws SQLException;

    @WebMethod
    boolean updatePerson(long id, String name, String surname, String role, String group) throws SQLException;

    @WebMethod
    boolean deletePerson(long id) throws SQLException;
}
