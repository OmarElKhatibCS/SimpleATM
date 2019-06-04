package application;

import DBConnection.DBHandler;

import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class dataEngine {

    private static HashMap<String , String> data = new HashMap<>();
    private String username;
    private Double balance;
    private String fName;
    private String lName;
    private String phNum;
    private String bankID;
    private String Address;

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;

    public void fillData(String username) throws SQLException {
        resetData();
        handler = new DBHandler();
        data.put("username",username);
        connection = handler.getConnection();

        String ql = "SELECT * from users.usersDetails WHERE username="+"'"+username+"'";
        pst = connection.prepareStatement(ql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
            data.put("FirstName" , rs.getString("FirstName"));
            data.put("LastName" , rs.getString("LastName"));
            data.put("Address" , rs.getString("Address"));
            data.put("PhoneNumber" , rs.getString("PhoneNumber"));
            data.put("balance" , String.valueOf(rs.getDouble("balance")));
            data.put("bankID" , rs.getString("bankID"));
        }
        System.out.println(data);
    }

    public void resetData() {
        data.clear();
    }

    public HashMap<String , String> returnData() {
        return data;
    }

    public void updateElementInsideData(String key, String value,String userTarget) {
        data.replace(key,value);
        handler = new DBHandler();
        connection = handler.getConnection();
        String ql = "UPDATE usersDetails SET "+ String.valueOf(key) +"="+String.valueOf(value)+" WHERE username='"+userTarget+"'";
        try {
            pst = connection.prepareStatement(ql);
            // hon bst5dm executeUpdate mnchen eza bde 3del 3al data
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
