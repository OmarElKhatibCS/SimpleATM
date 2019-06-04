module AccountMangement {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;

    opens application;
    opens Controllers;
}