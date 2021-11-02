import java.util.List;

public class CRUDcompanyTestData {
    public static final Integer  COMPANY_1_ID = 1;
    public static final String COMPANY_2_ID = "2";
    public static final String COMPANY_3_ID = "3";
    public static final Integer  NOT_FOUND_ID = 50;
    public static final Company company1 = new Company(1, "KFC", "New-York", "Colonel Sanders");
    public static final Company company2 = new Company(2, "Burger King", "Moscow", "Pushkin");
    public static final Company company3 = new Company(3, "MacDonalds", "London", "Fred");
    public static final List<Company> companies = List.of(company1, company2, company3);

    public static Company getNew() {
        return new Company(4,"Company", "Kolomna", "Suren");
    }

    public static Company getUpdated() {
        return new Company(1,"Company", "Unknown", "Unknown");
    }
}
