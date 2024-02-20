package listeners;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.SQLException;

@WebListener
public class DatabaseConnectionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        // Initialize database connection pool
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/teamapp");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setInitialSize(5); // Initial number of connections in the pool

        // Set the DataSource as an attribute in the ServletContext
        context.setAttribute("dbConnection", dataSource);

        System.out.println("Database connection initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        // Close the database connection pool
        BasicDataSource dataSource = (BasicDataSource) context.getAttribute("dbConnection");
        if (dataSource != null) {
            try {
                dataSource.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}