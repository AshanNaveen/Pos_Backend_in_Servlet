package lk.ijse.pos_backend.dao.custom;


import lk.ijse.pos_backend.dto.OrderDTO;
import lk.ijse.pos_backend.dto.PlaceOrderDTO;

import java.sql.SQLException;

public interface OrderDAO {
    boolean save(OrderDTO dto) throws SQLException;
    String generateNextId() throws SQLException ;
}
