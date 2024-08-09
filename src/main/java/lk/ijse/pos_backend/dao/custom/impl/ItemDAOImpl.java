package lk.ijse.pos_backend.dao.custom.impl;

import lk.ijse.pos_backend.dao.custom.ItemDAO;
import lk.ijse.pos_backend.dto.CustomerDTO;
import lk.ijse.pos_backend.dto.ItemDTO;
import lk.ijse.pos_backend.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public List<ItemDTO> getAll() throws SQLException {
        ResultSet rst = CrudUtil.crudUtil("SELECT * FROM Item");
        List<ItemDTO> list = new ArrayList<>();
        while (rst.next()){
            list.add(new ItemDTO(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getDouble("price"),
                    rst.getInt("qty")
            ));
        }
        return list;
    }

    @Override
    public boolean save(ItemDTO dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO Item (id,name,price,qty) VALUES (?,?,?,?)",dto.getId(),dto.getName(),dto.getPrice(),dto.getQty());
    }

    @Override
    public boolean update(ItemDTO dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE Item SET name=?, price=?, qty=? WHERE id=?",dto.getName(),dto.getPrice(),dto.getQty(),dto.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM Item WHERE id=?",id);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet rst = CrudUtil.crudUtil("SELECT id FROM Item ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newCustomerId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public ItemDTO getById(String id) throws SQLException {
        ResultSet rst = CrudUtil.crudUtil("SELECT * FROM Item WHERE id=?",id);
        if(rst.next()){
            return new ItemDTO(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getDouble("price"),
                    rst.getInt("qty")
            );
        }
        return null;
    }
}
