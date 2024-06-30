package com.example.demo;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ordermoreInfo {
    FakeFood food;
    Stage stage;
    public javafx.scene.control.Label price;
    public javafx.scene.control.Label from;
    public javafx.scene.control.Label category;

    public javafx.scene.control.Label name;
    public javafx.scene.control.Label amount;
    public void setFields(FakeFood food, Stage stage) {
        this.food = food;
        this.stage = stage;
        price.setText(String.valueOf(food.getPrice()));
        category.setText(food.getCategory());
        name.setText(food.getName());
        amount.setText(String.valueOf(food.getAmount()));
        from.setText(food.getCustomer());
    }


    public void close(ActionEvent actionEvent) {
        stage.close();
    }
}
