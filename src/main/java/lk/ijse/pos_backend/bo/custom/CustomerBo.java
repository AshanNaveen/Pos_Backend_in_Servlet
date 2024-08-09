package lk.ijse.pos_backend.bo.custom;

import lk.ijse.pos_backend.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo {
    CustomerDTO getCustomer(String id) throws SQLException;
    List<CustomerDTO> getAllCustomers() throws SQLException;
    boolean saveCustomer(CustomerDTO dto) throws SQLException;
    boolean updateCustomer(CustomerDTO dto) throws SQLException;
    boolean deleteCustomer(String id) throws SQLException;
    String generateNextId() throws SQLException;
}
