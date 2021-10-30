import java.sql.SQLException;
import java.util.List;

public interface CRUDinterface<T, ID> {

    Company find(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

    boolean save (T o) throws SQLException;

    boolean update (T o) throws SQLException;

    boolean delete (T o) throws SQLException;
}
