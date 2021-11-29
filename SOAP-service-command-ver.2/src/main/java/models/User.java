package models;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "users")
@DiscriminatorColumn(name = "role")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Comparable<User> {

    @Id
    private int id;

    private String name;

    @Column(name = "family_name")
    private String familyName;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    private Role role;

    public User(int id, String name, String familyName, Role role) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.role = role;
    }

    public User(int id) {
        this.id = id;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int compareTo(User user) {
        return this.getId() > user.getId() ? 1 : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && name.equals(user.name) && familyName.equals(user.familyName) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, familyName, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", role=" + role +
                '}';
    }
}
