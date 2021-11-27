package models.links;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class Key implements Serializable {

    @Column(name = "student_id")
    @Getter
    @Setter
    private int studentId;

    @Column(name = "mentor_id")
    @Getter
    @Setter
    private int mentorId;

    public Key (int studentId, int courseId) {
        this.studentId = studentId;
        this.mentorId = courseId;
    }
}