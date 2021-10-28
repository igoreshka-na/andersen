import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Company {
    protected int id;
    protected String name;
    protected String city;
    protected String creator;

    public Company(int id, String name, String city, String creator) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.creator = creator;
    }

    public Company(String name, String city, String creator) {
        this.name = name;
        this.city = city;
        this.creator = creator;
    }
}