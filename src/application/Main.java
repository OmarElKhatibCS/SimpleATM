package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
            primaryStage.setTitle("Login Scene");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
            primaryStage.setFullScreen(false);
            primaryStage.setResizable(false);
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}