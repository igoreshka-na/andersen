package service;

import model.Person;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SOAPService {
    @WebMethod
    Person setPerson(String id, String name, String role, String group);
    @WebMethod
    Person getPerson(String id);
}
