import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Stuff {
    protected int id;
    protected String name;
    protected String description;
    protected int quantity;
    protected String location;

    public Stuff(int id) {
        this.id = id;
    }

    public Stuff (int id, String name, String description, int quantity, String location) {
        this(name, description, quantity, location);
        this.id = id;
    }

    public Stuff(String name, String description, int quantity, String location) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.location = location;
    }
}
