package lk.ijse.pos_backend.dao.custom.impl;

import lk.ijse.pos_backend.dao.custom.OrderDAO;
import lk.ijse.pos_backend.dto.OrderDTO;
import lk.ijse.pos_backend.dto.PlaceOrderDTO;
import lk.ijse.pos_backend.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean save(OrderDTO dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO Orders (id,date,total) VALUES (?,?,?)",dto.getOrderId(),dto.getDate(),dto.getTotal());
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet rst = CrudUtil.crudUtil("SELECT id FROM Orders ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("O00-", "")) + 1;
            return String.format("O00-%03d", newCustomerId);
        } else {
            return "O00-001";
        }
    }
}
