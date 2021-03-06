package models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("MENTOR")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Mentor extends User {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "links",
            joinColumns = {@JoinColumn(name = "mentor_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    @XmlElement
    private Set<Student> students = new HashSet<>();

    public Mentor(int id, String name, String familyName, Role role) {
        super(id, name, familyName, role);
    }

    public Mentor() {
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
        student.getMentors().add(this);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
        student.getMentors().remove(this);
    }
}
