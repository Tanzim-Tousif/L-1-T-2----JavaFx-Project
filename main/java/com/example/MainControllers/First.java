package com.example.demo;

import javafx.event.ActionEvent;

import java.io.IOException;

public class First {
    ClientMain clientMain;
    public void customer(ActionEvent actionEvent) throws IOException {

        clientMain.showCustomerloginpage();
    }

    public void Restaurant(ActionEvent actionEvent) throws IOException {
        clientMain.showLoginPage();
    }

    public void setclient(ClientMain clientMain) {
        this.clientMain = clientMain;
    }

    public void addrestaurant(ActionEvent actionEvent) throws IOException {
        clientMain.shownewrestaurantpage();
    }
}
