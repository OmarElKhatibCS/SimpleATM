package Controllers;

import DBConnection.DBHandler;
import application.dataEngine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class sendController implements Initializable {

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;

    @FXML
    private TextField bankIDField;

    @FXML
    private Button sendBTN;

    @FXML
    private TextField amountField;

    public void initialize(URL arg0 , ResourceBundle arg1) {

    }

    @FXML
    void sendMoneyAction(ActionEvent event) {
        dataEngine d = new dataEngine();
        Double balance = Double.parseDouble(d.returnData().get("balance"));
        double ammount = Double.parseDouble(amountField.getText());
        if(amountField.getText().length() <= 0 || bankIDField.getText().length() <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Enter Your Ammount :)");
            alert.show();
        } else if(balance - ammount < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Insuffisant amount");
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Are you sure?");
            // He mn chan ma nswe chi abel ma yhot el bita3 Confirm
            Optional<ButtonType> option = alert.showAndWait();
            if(option.get() == ButtonType.OK){
                d.updateElementInsideData("balance" , String.valueOf(balance-ammount),d.returnData().get("username"));
                handler = new DBHandler();
                connection = handler.getConnection();
                String ql = "SELECT username,balance FROM usersDetails WHERE bankID='"+bankIDField.getText()+"'";
                try {
                    pst = connection.prepareStatement(ql);
                    // hon bst5dm executeUpdate mnchen eza bde 3del 3al data
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        d.updateElementInsideData("balance" , String.valueOf(Double.parseDouble(rs.getString("balance"))+ammount),rs.getString("username"));
                        d.fillData(d.returnData().get("username"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
