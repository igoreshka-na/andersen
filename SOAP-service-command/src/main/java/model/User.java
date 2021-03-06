package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlElement
    private int id;
    @XmlElement
    private String name;
    @XmlElement
    private String surname;
    @XmlElement
    private Role role;

    public User(int id) {
        this(id, null, null, null);
    }

    public User(int id, String name, String surname) {
        this(id, name, surname, Role.USER);
    }

    public User(int id, String name, String surname, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }
}
