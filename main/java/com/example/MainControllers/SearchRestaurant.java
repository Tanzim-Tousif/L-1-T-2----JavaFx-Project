package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchRestaurant {
    ClientMain clientMain;
    Stage stage;
    void setStage(Stage stage){
        this.stage = stage;
    }
    public void name(ActionEvent actionEvent) throws IOException {
      clientMain.showRestaurantsByname();

    }

    public void score(ActionEvent actionEvent) throws IOException {
        clientMain.showRestaurantByscore();
    }

    public void category(ActionEvent actionEvent) throws IOException {
        clientMain.showRestaurantBycategory();
    }

    public void price(ActionEvent actionEvent) throws IOException {
        clientMain.showRestaurantByprice();
    }
    public void zip(ActionEvent actionEvent) throws IOException {
      clientMain.showRestaurantByzip();
    }

    public void setClient(ClientMain clientMain) {
        this.clientMain = clientMain;
    }

    public void back(ActionEvent actionEvent) throws IOException {
        stage.close();
        clientMain.showCustomermainpage();

    }


}
