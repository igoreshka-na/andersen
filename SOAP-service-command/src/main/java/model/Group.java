package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Group {
    @XmlElement
    private Integer id;
    @XmlElement
    private String name;
    @XmlElement
    private User teamLead;
    @XmlElement
    private List<User> users;

    public Group(String name) {
        this(null, name, null, null);
    }

    public Group(Integer id, String name, User teamLead, List<User> users) {
        this.id = id;
        this.name = name;
        this.teamLead = teamLead;
        this.users = users;
    }
}
