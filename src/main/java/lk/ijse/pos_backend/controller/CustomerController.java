package lk.ijse.pos_backend.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.pos_backend.bo.BoFactory;
import lk.ijse.pos_backend.bo.custom.CustomerBo;
import lk.ijse.pos_backend.bo.custom.impl.CustomerBoImpl;
import lk.ijse.pos_backend.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/customer/*")
public class CustomerController extends HttpServlet {

    static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    CustomerBo customerBo = BoFactory.getInstance().getBo(BoFactory.BoType.CUSTOMER);

    Jsonb jsonb = JsonbBuilder.create();

    @Override
    public void init() {
        logger.info("Customer Servlet Initiated");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        resp.setContentType("application/json");

        try (var writer = resp.getWriter()) {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<CustomerDTO> allCustomers = customerBo.getAllCustomers();
                jsonb.toJson(allCustomers, writer);
                writer.flush();
                resp.setStatus(HttpServletResponse.SC_FOUND);
                logger.info("All customers sent");
            } else {

                String id = pathInfo.substring(1);
                System.out.println(id);
                CustomerDTO customer = customerBo.getCustomer(id);

                if (customer == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    jsonb.toJson(customer, writer);
                    writer.flush();
                    resp.setStatus(HttpServletResponse.SC_FOUND);
                    logger.info("Customer sent - ", id);
                }
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.error("Failed to get customer", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()) {
            CustomerDTO customer = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            customer.setId(customerBo.generateNextId());
            writer.write(String.valueOf(customerBo.saveCustomer(customer)));
            resp.setStatus(HttpServletResponse.SC_CREATED);
            logger.info("Customer saved - ", customer.getId());
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error("Failed to save customer", e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()) {
            CustomerDTO customer = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            writer.write(String.valueOf(customerBo.updateCustomer(customer)));
            resp.setStatus(HttpServletResponse.SC_CREATED);
            logger.info("Customer updated - ", customer.getId());
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error("Failed to update customer", e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()) {
            String id = pathInfo.substring(1);
            writer.write(String.valueOf(customerBo.deleteCustomer(id)));
            resp.setStatus(HttpServletResponse.SC_CREATED);
            logger.info("Customer deleted - ", id);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error("Failed to delete customer", e);
        }
    }

    @Override
    public void destroy() {
        logger.info("Customer Servlet Destroyed");
    }
}
