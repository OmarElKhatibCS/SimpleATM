package Controllers;

import application.dataEngine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class withdrawController implements Initializable {


    @FXML
    private Button withdrawBTN;

    @FXML
    private TextField amountField;

    @FXML
    void withdrawAction(ActionEvent event) {
        dataEngine d = new dataEngine();
        Double balance = Double.parseDouble(d.returnData().get("balance"));
        double ammount = Double.parseDouble(amountField.getText());
        if(amountField.getText().length() <= 0) {
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
            }

        }

    }

    public void initialize(URL arg0 , ResourceBundle arg1) {

    }

}
