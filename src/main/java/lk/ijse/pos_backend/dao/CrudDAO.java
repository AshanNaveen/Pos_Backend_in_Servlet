package lk.ijse.pos_backend.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> {
    List<T> getAll() throws SQLException;

    boolean save(T dto) throws SQLException ;

    boolean update(T dto) throws SQLException;

    boolean delete(String id) throws SQLException;

    String generateNextId() throws SQLException ;

    T getById(String id) throws SQLException;

}
