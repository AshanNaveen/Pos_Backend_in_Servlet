package lk.ijse.pos_backend.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.pos_backend.bo.BoFactory;
import lk.ijse.pos_backend.bo.custom.OrderBo;
import lk.ijse.pos_backend.dto.PlaceOrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(urlPatterns = "/order")
public class OrderController extends HttpServlet {

    static Logger logger = LoggerFactory.getLogger(OrderController.class);

    OrderBo orderBo = BoFactory.getInstance().getBo(BoFactory.BoType.ORDER);

    Jsonb jsonb = JsonbBuilder.create();

    @Override
    public void destroy() {
        logger.info("Order Servlet Destroyed");
    }

    @Override
    public void init() throws ServletException {
        logger.info("Order Servlet Initiated");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()) {
            PlaceOrderDTO order = jsonb.fromJson(req.getReader(), PlaceOrderDTO.class);
            order.setId(orderBo.generateNextId());
            System.out.println(order);
            writer.write(String.valueOf(orderBo.placeOrder(order)));
            resp.setStatus(HttpServletResponse.SC_CREATED);
            logger.info("Order saved - ", order.getId());
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.getWriter().write(e.getMessage());
            logger.error("Failed to save Order", e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
