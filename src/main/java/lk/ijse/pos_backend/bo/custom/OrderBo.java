package lk.ijse.pos_backend.bo.custom;

import lk.ijse.pos_backend.dto.PlaceOrderDTO;

import java.sql.SQLException;

public interface OrderBo {
    boolean placeOrder(PlaceOrderDTO placeOrderDTO) throws Exception;

    String generateNextId() throws SQLException;
}
