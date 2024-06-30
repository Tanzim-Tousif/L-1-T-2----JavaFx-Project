package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.awt.*;

public class moreInfo {

    public javafx.scene.control.Label id;
    public javafx.scene.control.Label name;
    public javafx.scene.control.Label score;
    public javafx.scene.control.Label price;
    public javafx.scene.control.Label zipcode;
    public javafx.scene.control.Label category;
    Stage stage;
    Restaurant show;
    void setFields(Restaurant restaurant,Stage stage){
        this.stage = stage;
        id.setText(Integer.toString(restaurant.getId()));
        name.setText(restaurant.getName());
       String res = restaurant.getPrice();
       if(res .equals("$")) price.setText("1$ to 10$");
       else if(res.equals("$$")) price.setText("11$ to 20$");
       else price.setText("21$ to 30$");
        score.setText(Double.toString(restaurant.getScore()));
        zipcode.setText(restaurant.getZipCode());
        String temp = "";
        for(String x:restaurant.getCategory()){
            temp +=x;
            temp += " , ";
        }

        category.setText(temp);
    }
    public void close(ActionEvent actionEvent) {
        stage.close();
    }
}
