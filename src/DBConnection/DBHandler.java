package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends Configs{
    Connection dbconnection;

    public Connection getConnection() {
        String connectionString = "jdbc:mysql://"+ Configs.dbhost + ":"+Configs.dbport + "/" +Configs.dbname+"?serverTimezone=EST5EDT";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.getCause();
        }
        try {
            dbconnection = DriverManager.getConnection(connectionString,Configs.dbuser,Configs.dbpass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbconnection;
    }
}
