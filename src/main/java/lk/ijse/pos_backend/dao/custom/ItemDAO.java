package lk.ijse.pos_backend.dao.custom;

import lk.ijse.pos_backend.dao.CrudDAO;
import lk.ijse.pos_backend.dto.ItemDTO;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<ItemDTO> {
    boolean updateqty(String id, int qty) throws SQLException;
}
