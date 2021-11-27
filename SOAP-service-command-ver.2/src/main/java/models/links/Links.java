package models.links;

import models.Mentor;
import models.Student;

import javax.persistence.*;

@Entity
@Table(name = "links")
public class Links {

    public Links() {
    }

    @EmbeddedId
    private Key id;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student studentId;

    @ManyToOne
    @JoinColumn(name = "mentor_id", insertable = false, updatable = false)
    private Mentor mentorId;

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Mentor getMentorId() {
        return mentorId;
    }

    public void setMentorId(Mentor mentorId) {
        this.mentorId = mentorId;
    }
}
