package listeners;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionListener;

import javax.naming.InitialContext;
import javax.sql.DataSource;

@WebListener
public class DatabaseConnectionListener implements HttpSessionListener {
    private Connection connection;
    public static final String DB_CONNECTION = "DB_CONNECTION";

    /**
     * Default constructor.
     */
    public DatabaseConnectionListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        InitialContext ic;
        try {
            ic = new InitialContext();
            DataSource ds;
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/Jee");
            connection = ds.getConnection();
            sce.getServletContext().setAttribute(DB_CONNECTION, connection);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        Connection connection = (Connection) sce.getServletContext().getAttribute(DB_CONNECTION);

        // Close the connection
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new IllegalStateException("Error closing the database connection", e);
            }
        }
    }
}
