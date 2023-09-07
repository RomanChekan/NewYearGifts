package NewYearGifts;

import org.apache.logging.log4j.*;

import java.sql.*;

public class DataBaseConnection {
    private static final Logger logger = LogManager.getLogger();
    private static final String db_url = "jdbc:sqlserver://localhost:1433;" +
                                         "DatabaseName=NewYearGifts;" +
                                         "encrypt=true;" +
                                         "trustServerCertificate=true;";
    private static final String user = "JavaConnection";
    private static final String password = "3rc";
    private Connection connection;

    public DataBaseConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("System cannot connect to database(", e);
        } catch (ClassNotFoundException e) {
            logger.error("System cannot load a driver for database(", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Database connection failed(", e);
            }
        }
    }

    public Connection Connect() {
        try {
            return DriverManager.getConnection(db_url, "JavaConnection", password);
        } catch (SQLException e) {
            logger.error("System cannot connect to database(", e);
        }
        return null;
    }
}