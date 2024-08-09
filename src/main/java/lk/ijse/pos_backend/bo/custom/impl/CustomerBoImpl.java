package lk.ijse.pos_backend.bo.custom.impl;

import lk.ijse.pos_backend.bo.custom.CustomerBo;
import lk.ijse.pos_backend.dao.custom.CustomerDAO;
import lk.ijse.pos_backend.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos_backend.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public CustomerDTO getCustomer(String id) throws SQLException {
        return customerDAO.getById(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws SQLException {
        return customerDAO.getAll();
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.save(dto);
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.update(dto);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateNextId() throws SQLException {
        return customerDAO.generateNextId();
    }
}
