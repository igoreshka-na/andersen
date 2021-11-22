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
public class Group {
    @XmlElement
    private Integer id;
    @XmlElement
    private String name;
    @XmlElement
    private Integer teamLead;

    public Group(String name) {
        this(null, name, null);
    }

    public Group(Integer id, String name, Integer teamLead) {
        this.id = id;
        this.name = name;
        this.teamLead = teamLead;
    }

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
