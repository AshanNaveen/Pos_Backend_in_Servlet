package lk.ijse.pos_backend.dao.custom;

import lk.ijse.pos_backend.dto.CustomerDTO;
import lk.ijse.pos_backend.dto.OrderDetailDTO;

import java.sql.SQLException;

public interface OrderDetailDAO {

    public boolean save(OrderDetailDTO dto) throws SQLException;
}
