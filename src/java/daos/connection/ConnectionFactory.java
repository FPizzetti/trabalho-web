package daos.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {
        //return DriverManager.getConnection("jdbc:mysql://localhost:3306/trabalho_final", "root", "pavilion");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/trabalho_final", "root", "root");
    }
}
