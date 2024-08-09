package lk.ijse.pos_backend.dao.custom.impl;

import lk.ijse.pos_backend.dao.custom.OrderDetailDAO;
import lk.ijse.pos_backend.dto.OrderDetailDTO;
import lk.ijse.pos_backend.util.CrudUtil;

import java.sql.SQLException;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean save(OrderDetailDTO dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO Order_Detail (order_id,item_id,qty,unit_price) VALUES (?,?,?,?)",dto.getOrderId(),dto.getItemId(),dto.getQty(),dto.getUnitPrice());
    }
}
