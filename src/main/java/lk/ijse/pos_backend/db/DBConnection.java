package lk.ijse.pos_backend.db;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private final Connection connection;

    private DBConnection() {
        InitialContext ctx = null;
        try {
            ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pos_servlet_ijse");
            this.connection = pool.getConnection();
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static DBConnection getDbConnection(){
        return dbConnection == null ? dbConnection= new DBConnection() : dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
