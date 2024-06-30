package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class CustomerLoginController {
     ClientMain clientMain;
    public javafx.scene.control.TextField username;
    void setclient(ClientMain clientMain){
        this.clientMain =clientMain;
    }
    public void loginbtn(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        if(!username.getText().equals("")) {
            System.out.println("succes");
            ClientMain.username = username.getText();
            ArrayList<Restaurant> receivedlist = (ArrayList<Restaurant>) clientMain.socketWrapper.read();
            ClientMain.restaurantslist = receivedlist;
            ArrayList<Food>receive = (ArrayList<Food>)clientMain.socketWrapper.read();
            ClientMain.menulist = receive;
            clientMain.showCustomermainpage();
        }
    }

    public void resetbtn(ActionEvent actionEvent) {
        username.clear();
    }
}
