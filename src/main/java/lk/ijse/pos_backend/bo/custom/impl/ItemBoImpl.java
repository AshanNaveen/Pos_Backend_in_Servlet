package lk.ijse.pos_backend.bo.custom.impl;

import lk.ijse.pos_backend.bo.custom.ItemBo;
import lk.ijse.pos_backend.dao.custom.ItemDAO;
import lk.ijse.pos_backend.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos_backend.dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public class ItemBoImpl implements ItemBo {

    ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public ItemDTO getItem(String id) throws SQLException {
        return itemDAO.getById(id);
    }

    @Override
    public List<ItemDTO> getAllItems() throws SQLException {
        return itemDAO.getAll();
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException {
        return itemDAO.save(dto);
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException {
        return itemDAO.update(dto);
    }

    @Override
    public boolean deleteItem(String id) throws SQLException {
        return itemDAO.delete(id);
    }

    @Override
    public String generateNextId() throws SQLException {
        return itemDAO.generateNextId();
    }
}
