package lk.ijse.pos_backend.bo.custom.impl;

import lk.ijse.pos_backend.bo.custom.OrderBo;
import lk.ijse.pos_backend.dao.custom.ItemDAO;
import lk.ijse.pos_backend.dao.custom.OrderDAO;
import lk.ijse.pos_backend.dao.custom.OrderDetailDAO;
import lk.ijse.pos_backend.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos_backend.dao.custom.impl.OrderDAOImpl;
import lk.ijse.pos_backend.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.pos_backend.db.DBConnection;
import lk.ijse.pos_backend.dto.ItemDTO;
import lk.ijse.pos_backend.dto.OrderDTO;
import lk.ijse.pos_backend.dto.OrderDetailDTO;
import lk.ijse.pos_backend.dto.PlaceOrderDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrderBoImpl implements OrderBo {

    ItemDAO itemDAO = new ItemDAOImpl();
    OrderDAO orderDAO = new OrderDAOImpl();
    OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

    @Override
    public boolean placeOrder(PlaceOrderDTO placeOrderDTO) throws Exception {
        Connection connection = DBConnection.getDbConnection().getConnection();

        connection.setAutoCommit(false);
        boolean flag = false;
        double total = 0;

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(placeOrderDTO.getId());
        orderDTO.setTotal(total);
        orderDTO.setDate(Date.valueOf(LocalDate.now()));

        flag = orderDAO.save(orderDTO);

        List<ItemDTO> items = placeOrderDTO.getItems();


        if (flag) {
            flag = false;
            for (ItemDTO item : items) {
                ItemDTO preitem = itemDAO.getById(item.getId());

                if (preitem.getQty() > item.getQty()) {
                    double unitPrice = item.getPrice();
                    flag = itemDAO.updateqty(item.getId(), item.getQty());
                    if (flag)
                        flag = orderDetailDAO.save(new OrderDetailDTO(placeOrderDTO.getId(), item.getId(), item.getQty(), unitPrice));
                    total += item.getQty() * unitPrice;
                    if (!flag) {
                        connection.rollback();
                        throw new Exception("Cannot Save Item "+item.getId());
                    }
                }else{
                    throw new Exception("Invalid Qty");
                }
            }
        }

        if (!flag) {
            connection.rollback();
            throw new Exception();
        } else {
            connection.commit();
            connection.setAutoCommit(true);
        }
        throw new Exception("Order Rejected");
    }

    @Override
    public String generateNextId() throws SQLException {
        return orderDAO.generateNextId();
    }

}
