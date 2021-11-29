package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@DiscriminatorValue("ADMIN")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Admin extends User {
    public Admin() {
        super();
    }
}
