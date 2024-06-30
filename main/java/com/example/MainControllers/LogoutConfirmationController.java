package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class LogoutConfirmationController {
    ClientMain client;
    Stage stage;
    @FXML
    public javafx.scene.control.Label messageLabel;

    public void setClient(ClientMain client) {
        this.client = client;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void logout(ActionEvent actionEvent) {
        try {
            stage.close();
            ClientMain.socketWrapper.closeConnection();
            client.showfirstpage();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void back(ActionEvent actionEvent) {
        stage.close();
    }
}
