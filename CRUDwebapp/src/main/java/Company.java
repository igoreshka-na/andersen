public class Company {
    protected int id;
    protected String name;
    protected String city;
    protected String creator;

    public Company() {
    }

    public Company(int id) {
        this.id = id;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
