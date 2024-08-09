package lk.ijse.pos_backend.dao.custom.impl;

import lk.ijse.pos_backend.dao.custom.CustomerDAO;
import lk.ijse.pos_backend.dto.CustomerDTO;
import lk.ijse.pos_backend.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public List<CustomerDTO> getAll() throws SQLException {
        ResultSet rst = CrudUtil.crudUtil("SELECT * FROM Customer");
        List<CustomerDTO> list = new ArrayList<>();
        while (rst.next()){
            list.add(new CustomerDTO(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address")));
        }
        return list;
    }

    @Override
    public boolean save(CustomerDTO dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",dto.getId(),dto.getName(),dto.getAddress());
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE Customer SET name=?, address=? WHERE id=?",dto.getName(),dto.getAddress(),dto.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM Customer WHERE id=?",id);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet rst = CrudUtil.crudUtil("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public CustomerDTO getById(String id) throws SQLException {
        ResultSet rst = CrudUtil.crudUtil("SELECT * FROM Customer WHERE id=?",id);
        if(rst.next()){
            return new CustomerDTO(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address")
            );
        }
        return null;
    }
}
