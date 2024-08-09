package lk.ijse.pos_backend.dao.custom.impl;

import lk.ijse.pos_backend.dao.custom.ItemDAO;
import lk.ijse.pos_backend.dto.ItemDTO;
import lk.ijse.pos_backend.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public List<ItemDTO> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean save(ItemDTO dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO Item (id,name,price,qty) VALUES (?,?,?,?)",dto.getId(),dto.getName(),dto.getAddress());
    }

    @Override
    public boolean update(ItemDTO dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String generateNextId() throws SQLException {
        return "";
    }

    @Override
    public ItemDTO getById(String id) throws SQLException {
        return null;
    }
}
