package com.example.demo;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchFood {
    ClientMain clientMain;

     Stage stage;
    public void price(ActionEvent actionEvent) throws IOException {
        clientMain.showFoodByprice();
    }

    public void back(ActionEvent actionEvent) throws IOException {
        stage.close();
        clientMain.showCustomermainpage();
    }

    public void name(ActionEvent actionEvent) throws IOException {
        clientMain.showFoodByname();
    }

    public void category(ActionEvent actionEvent) throws IOException {
        clientMain.showFoodBycategory();
    }

    public void setClient(ClientMain clientMain) {
        this.clientMain = clientMain;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
