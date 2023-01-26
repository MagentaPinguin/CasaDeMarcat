module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens app to javafx.fxml;
    opens domain to javafx.fxml;
    exports domain;
    exports service;
    exports app;
}