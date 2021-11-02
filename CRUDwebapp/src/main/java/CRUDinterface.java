import java.sql.SQLException;
import java.util.List;

/**
 * Interface for searching, saving, updating and deleting objects.
 * @param <T> generic class.
 * @param <ID> object identifier.
 */
public interface CRUDinterface<T, ID> {

    Company find(int id) throws SQLException;

    List<T> findAll() throws SQLException;

    void insert(T o) throws SQLException;

    boolean update (T o) throws SQLException;

    boolean delete (int id) throws SQLException;
}
