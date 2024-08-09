package lk.ijse.pos_backend.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.pos_backend.bo.BoFactory;
import lk.ijse.pos_backend.bo.custom.ItemBo;
import lk.ijse.pos_backend.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/item/*")
public class ItemController extends HttpServlet {

    static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    ItemBo itemBo = BoFactory.getInstance().getBo(BoFactory.BoType.ITEM);

    Jsonb jsonb = JsonbBuilder.create();

    @Override
    public void destroy() {
        logger.info("Item Servlet Destroyed");
    }

    @Override
    public void init() throws ServletException {
        logger.info("Item Servlet Initiated");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        resp.setContentType("application/json");

        try (var writer = resp.getWriter()) {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<ItemDTO> allItems = itemBo.getAllItems();
                jsonb.toJson(allItems, writer);
                writer.flush();
                resp.setStatus(HttpServletResponse.SC_FOUND);
                logger.info("All Items sent to ",req.getRequestURL());
            } else {

                String id = pathInfo.substring(1);
                System.out.println(id);
                ItemDTO item = itemBo.getItem(id);

                if (item == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    jsonb.toJson(item, writer);
                    writer.flush();
                    resp.setStatus(HttpServletResponse.SC_FOUND);
                    logger.info("Item sent - ", id , "to ",req.getRequestURL());
                }
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.error("Failed to get Item", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()) {
            ItemDTO item = jsonb.fromJson(req.getReader(), ItemDTO.class);
            item.setId(itemBo.generateNextId());
            writer.write(String.valueOf(itemBo.saveItem(item)));
            resp.setStatus(HttpServletResponse.SC_CREATED);
            logger.info("Item saved - ", item.getId());
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error("Failed to save Item", e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()) {
            ItemDTO item = jsonb.fromJson(req.getReader(), ItemDTO.class);
            writer.write(String.valueOf(itemBo.updateItem(item)));
            resp.setStatus(HttpServletResponse.SC_CREATED);
            logger.info("Item updated - ", item.getId());
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error("Failed to update item", e);
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
            writer.write(String.valueOf(itemBo.deleteItem(id)));
            resp.setStatus(HttpServletResponse.SC_CREATED);
            logger.info("Item deleted - ", id);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error("Failed to delete item", e);
        }
    }
}
