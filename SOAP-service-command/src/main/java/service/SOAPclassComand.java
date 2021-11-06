package service;

import model.Person;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "service.SOAPService")
public class SOAPclassComand implements SOAPService {

    @Override
    @WebMethod
    public Person setPerson(String id, String name, String role, String group) {
        return new Person(id, name, role, group);
    }

    @Override
    @WebMethod
    public Person getPerson(String id) {
        return null;
    }
}
