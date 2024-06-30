package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Shownewrestaurant {
    Stage stage;
    @FXML
    public javafx.scene.control.TextField category;
    @FXML
    public javafx.scene.control.TextField name;
    @FXML
    public javafx.scene.control.TextField price;
    @FXML
    public javafx.scene.control.TextField score;
    @FXML
    public javafx.scene.control.TextField zipcode;
    @FXML
    public javafx.scene.control.Label message;
    @FXML
    public javafx.scene.control.Label namemessage;
    @FXML
    public javafx.scene.control.Label pricemessage;
    @FXML
    public javafx.scene.control.Label scoremessage;
    public void setStage(Stage stage){
        this.stage =stage;
    }
    public void addrestaurant(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        message.setText("");
        namemessage.setText("");
        pricemessage.setText("");
        scoremessage.setText("");
        String temp = name.getText();
        if(temp.equalsIgnoreCase("")){
            namemessage.setText("Restaurant name can not be empty ");
            return;
        }
        ClientMain.socketWrapper.write(temp);
        int id = (int) ClientMain.socketWrapper.read();
        if(id == 1){
            message.setText("Restaurant with this name already exist");
            return;
        }
        String tempscore = score.getText();
        System.out.println("temp score is  "+tempscore);
        try {
            Double.parseDouble(tempscore);
        }catch (NumberFormatException e){
            scoremessage.setText("Invalid Input");
            return;
        }
        String tempprice = price.getText();
        System.out.println("temp price is "+tempprice);
        if(tempprice.equalsIgnoreCase("")){
            pricemessage.setText("Invalid Input");
        }
        String tempZipcode = zipcode.getText();
        System.out.println("temp zipcode is "+tempZipcode);
        try {
            Integer.parseInt(tempZipcode);
        }
        catch (NumberFormatException e){
            zipcode.setText("Invalid Input");
            return;
        }
        String tempcategory = category.getText();
        String[] array = tempcategory.split(",", -1);
        ArrayList<String> categary = new ArrayList<>();
        if (!array[0].equals(""))
            categary.add(array[0]);
        if (!array[1].equals(""))
            categary.add(array[1]);
        if (!array[2].equals(""))
            categary.add(array[2]);

        int maxrestaurant_id = (int) ClientMain.socketWrapper.read();
        System.out.println(maxrestaurant_id);
        Restaurant newreRestaurant = new Restaurant(maxrestaurant_id,temp,Double.parseDouble(tempscore),tempprice,tempZipcode,categary);
        ClientMain.socketWrapper.write(newreRestaurant);
        message.setText("Restaurant Added Successfully");


    }

    public void back(ActionEvent actionEvent) {
        stage.close();
    }
}
