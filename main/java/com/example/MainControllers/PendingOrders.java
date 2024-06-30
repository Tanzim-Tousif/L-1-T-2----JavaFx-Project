package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class PendingOrders implements Initializable {
    ClientMain clientMain;
    @FXML
    private VBox vBox;
    @FXML
    private HBox titleBar;
    Stage stage;
    public javafx.scene.control.Label tag;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tag.setText(ClientMain.Restaurant_name);
        ClientMain.servestage = stage;
        ClientMain.start = 1;
       vBox.getChildren().clear();
        int j = 1;
        for (int i = ClientMain.pendingorders.size() - 1; i >= 0; i--)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("serveHbox.fxml"));
            Node root;
            try {
                root = (Node) fxmlLoader.load();
                ((ServeHbox) fxmlLoader.getController()).setMovie(ClientMain.pendingorders.get(i));
                ((ServeHbox) fxmlLoader.getController()).setFields(j);
                vBox.getChildren().add(root);
                j++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setClient(ClientMain clientMain) {
        this.clientMain = clientMain;
    }

    public void setStage(Stage stage) {this.stage = stage;
    }

    public void back(ActionEvent actionEvent) throws IOException {
        stage.close();
        ReadThread.stop();
        clientMain.showmainpage();
    }
}
