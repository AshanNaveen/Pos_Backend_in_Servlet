package lk.ijse.pos_backend.bo.custom;

import lk.ijse.pos_backend.dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo {
    ItemDTO getItem(String id) throws SQLException;
    List<ItemDTO> getAllItems() throws SQLException;
    boolean saveItem(ItemDTO dto) throws SQLException;
    boolean updateItem(ItemDTO dto) throws SQLException;
    boolean deleteItem(String id) throws SQLException;
    String generateNextId() throws SQLException;
}
