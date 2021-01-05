package main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private ConnectionFactory() {}

    public static Connection getConnection() {

        Connection connection = null;

        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties")) {

            Properties properties = new Properties();
            properties.load(input);

            String driver = properties.getProperty("jdbc.driver");
            String databaseAddress = properties.getProperty("database.address");
            String databaseName = properties.getProperty("database.name");
            String user = properties.getProperty("database.user.login");
            String password = properties.getProperty("database.user.password");

            String urlConnection = "jdbc:" +
                    driver + "://" +
                    databaseAddress + "/" +
                    databaseName;

            connection = DriverManager.getConnection(urlConnection, user, password);

        } catch (IOException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return connection;
    }

}
