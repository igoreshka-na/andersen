import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {
    protected int id;
    protected String name;
    protected int id_company;

    public Employee(int id) {
        this.id = id;
    }

    public Employee(String name, int id_company) {
        this.name = name;
        this.id_company = id_company;
    }

    public Employee(int id, String name, int id_company) {
        this.id = id;
        this.name = name;
        this.id_company = id_company;
    }
}
