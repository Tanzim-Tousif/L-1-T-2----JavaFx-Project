package com.example.demo;

import Server.SocketWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.text.LabelView;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class HelloController {
    //SocketWrapper socketWrapper;
    @FXML
    private TextField password;
    @FXML
    private TextField name;
  ClientMain client;

    @FXML
    public void resetAction(ActionEvent event) {
        name.setText(null);
        //password.setText(null);
    }
    public void loginAction(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        //System.out.println(name.getText());
        ClientMain.Restaurant_name = name.getText();
       // System.out.println(ClientMain.Restaurant_name);
        try {

            ClientMain.socketWrapper.write(ClientMain.Restaurant_name);
           // System.out.println("here");

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        String messageFromServer = null;
        try {
            messageFromServer = (String) ClientMain.socketWrapper.read();
           // System.out.println(messageFromServer);
        } catch (ClassNotFoundException | IOException exception) {
            exception.printStackTrace();
        }
        System.out.println(messageFromServer);
        assert messageFromServer != null;
        if (messageFromServer.equals("No objection")){
            ClientMain.Restaurant_name = name.getText();
            //System.out.println(ClientMain.Restaurant_name);
            name.clear();
           // password.clear();
            System.out.println("all good");
            try {
                int id = (int)ClientMain.socketWrapper.read();
                ClientMain.id = id;
                System.out.println(id);
                ArrayList<Food>receivedlist = (ArrayList<Food>)ClientMain.socketWrapper.read();
                ClientMain.menulist = receivedlist;
                /*
                for(Food x:receivedlist){
                   FakeFood fakeFood = new FakeFood(x.getName(),x.getPrice(),x.getCategory());
                   ClientMain.allfooditems.add(fakeFood);
                }

                 */
            } catch (ClassNotFoundException exception) {
                System.out.println("eroor here");
            }
           // System.out.println("im here");

            client.showmainpage();
        }
        else{

            //System.out.println("on alert");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Page");
            alert.setHeaderText("Invalid Credentials");
            alert.setContentText("Plese Try again");
            alert.show();
            name.clear();
           // password.clear();
        }
    }



    public void setclient(ClientMain clientMain) {
        this.client = clientMain;
    }

}