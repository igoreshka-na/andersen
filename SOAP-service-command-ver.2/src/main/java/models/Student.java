package models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("STUDENT")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Student extends User {

    @Column(name = "group_name")
    @XmlElement
    private String groupName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "links",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "mentor_id")})
    @XmlElement
    private Set<Mentor> mentors = new HashSet<>();

    public Student(int id, String name, String familyName, Role role, String groupName) {
        super(id, name, familyName, role);
        this.groupName = groupName;
    }

    public Student() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Mentor> getMentors() {
        return mentors;
    }

    public void setMentors(Set<Mentor> mentors) {
        this.mentors = mentors;
    }
}
