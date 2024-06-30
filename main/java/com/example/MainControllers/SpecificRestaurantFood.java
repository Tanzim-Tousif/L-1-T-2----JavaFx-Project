package com.example.demo;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SpecificRestaurantFood {
    ClientMain clientMain;
    Stage stage;
    public void name(ActionEvent actionEvent) throws IOException {
        clientMain.ShowRestaurantFoodName();

    }

    public void category(ActionEvent actionEvent) throws IOException {
        clientMain.ShowRestaurantFoodCategory();
    }

    public void back(ActionEvent actionEvent) throws IOException {
        stage.close();
        clientMain.showCustomermainpage();
    }

    public void price(ActionEvent actionEvent) throws IOException {
        clientMain.ShowRestaurantFoodPrice();
    }

    public void setClient(ClientMain clientMain) {
        this.clientMain = clientMain;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
