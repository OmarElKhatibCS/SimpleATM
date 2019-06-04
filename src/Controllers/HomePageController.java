package Controllers;

import application.dataEngine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    AnchorPane holderPane;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button myAccountBTN;

    @FXML
    private Button historyBTN;

    @FXML
    private Button withdrawBTN;

    @FXML
    private Button sendBTN;

    AnchorPane myAccount;

    public void initialize(URL arg0 , ResourceBundle arg1){
        dataEngine d = new dataEngine();
        welcomeLabel.setText("Welcome "+d.returnData().get("username"));
        createPage();
    }

    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
    }

    private void createPage() {
        try {
            myAccount = FXMLLoader.load(getClass().getResource("/FXML/myAccount.fxml"));
            setNode(myAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void loadMyAccount(ActionEvent event) {
        try {
            myAccount = FXMLLoader.load(getClass().getResource("/FXML/myAccount.fxml"));
            setNode(myAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void loadWithdraw(ActionEvent event) {
        try {
            myAccount = FXMLLoader.load(getClass().getResource("/FXML/withdraw.fxml"));
            setNode(myAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void loadSend(ActionEvent event) {
        try {
            myAccount = FXMLLoader.load(getClass().getResource("/FXML/send.fxml"));
            setNode(myAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void loadHistory(ActionEvent event) {
        try {
            myAccount = FXMLLoader.load(getClass().getResource("/FXML/history.fxml"));
            setNode(myAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
