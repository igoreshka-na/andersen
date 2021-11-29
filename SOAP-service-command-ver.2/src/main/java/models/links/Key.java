package models.links;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Key implements Serializable {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "mentor_id")
    private int mentorId;

    public Key(int studentId, int courseId) {
        this.studentId = studentId;
        this.mentorId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return studentId == key.studentId && mentorId == key.mentorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, mentorId);
    }
}