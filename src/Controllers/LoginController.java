package Controllers;

import application.dataEngine;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DBConnection.DBHandler;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    public TextField username;

    @FXML
    private Label titleLabel;

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;

    @Override
    public void initialize(URL arg0 , ResourceBundle arg1) {
        handler = new DBHandler();
    }

    @FXML
    public void loginAction(ActionEvent e) {
        dataEngine d = new dataEngine();
        try {
            d.fillData(username.getText());
        } catch (Exception e1) {
            System.out.println(e1.getCause());
        }

        connection = handler.getConnection();
        String ql = "SELECT * from users where username=? and password=?";

        try {
            pst = connection.prepareStatement(ql);
            pst.setString(1, username.getText());
            pst.setString(2,password.getText());
            ResultSet rs = pst.executeQuery();
            int count=0;

            while(rs.next()) {
                count = count + 1;
            }
            if(count == 1) {
                login.getScene().getWindow().hide();

                Stage home = new Stage();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/HomePage.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    home.setResizable(false);
                    home.setScene(scene);
                    home.show();
                } catch (IOException e1) {
                    System.out.println(e1.getCause());
                }

            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Username or Password is Not Correct");
                alert.show();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
