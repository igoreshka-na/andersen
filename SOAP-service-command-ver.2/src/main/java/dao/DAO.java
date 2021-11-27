package dao;

import java.util.List;

public interface DAO<T, ID> {
    List<T> findAll();

    T save(T o);

    T delete(T o);

    T findById(ID id);
}
