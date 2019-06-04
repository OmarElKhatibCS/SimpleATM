package Controllers;

import application.dataEngine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class myAccountController implements Initializable {
    @FXML
    private Label balanceLabel;

    @FXML
    private Label bankID;

    @FXML
    private Label address;

    @FXML
    private Label phoneNumber;

    @FXML
    private Label fullName;

    public void initialize(URL arg0 , ResourceBundle arg1) {
        dataEngine data = new dataEngine();
        balanceLabel.setText("Balance : " +String.valueOf(data.returnData().get("balance"))+ "$");
        bankID.setText("("+data.returnData().get("bankID") + ")");
        phoneNumber.setText(data.returnData().get("PhoneNumber"));
        address.setText(data.returnData().get("Address"));
        fullName.setText(data.returnData().get("FirstName")+" "+data.returnData().get("LastName"));
    }
}
