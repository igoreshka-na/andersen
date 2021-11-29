package dao;

import java.util.List;

public interface DAO<T, ID> {
    List<T> findAllUsers();

//    List<T> findUsersInGroup(String group);

    boolean save(T o);

    boolean delete(T o);

    T findById(ID id);
}
