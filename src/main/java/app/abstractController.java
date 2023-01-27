package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;

public class abstractController {
    protected Service service;

    void setService(Service s) {
        this.service = s;
    }
    public void alertWarning(String title, String context) {
        Alert err=new Alert(Alert.AlertType.WARNING);
        err.setTitle(title);
        err.setContentText(context);
        err.show();
    }
    public void confirmation(String title, String context) {
        Alert err=new Alert(Alert.AlertType.CONFIRMATION);
        err.setTitle(title);
        err.setContentText(context);
        err.show();
    }

    public void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage theStage = (Stage) source.getScene().getWindow();
        theStage.close();
    }
    public void openStage(String stageFML,String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(stageFML));
        Scene scene = new Scene(fxmlLoader.load());
        abstractController loader=fxmlLoader.getController();
        loader.setService(this.service);
        Stage stage=new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}
