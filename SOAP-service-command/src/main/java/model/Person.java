package model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class Person {
    @XmlElement
    private long id;
    @XmlElement
    private String name;
    @XmlElement
    private String surname;
    @XmlElement
    private String role;
    @XmlElement
    private String group;

    public Person() {
    }

    public Person(long id, String name, String surname, String role, String group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.group = group;
    }

}
