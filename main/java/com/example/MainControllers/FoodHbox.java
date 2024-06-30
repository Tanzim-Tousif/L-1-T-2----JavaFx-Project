package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FoodHbox {
    @FXML
    private Label column1;
    @FXML
    private Label column2;
    @FXML
    private Button coloumn3;
    @FXML
    private HBox hBox;
    Food food;
    public void setFood(Food food) {
        this.food = food;
    }
   private String getRestaurantname(int id){
        for(Restaurant x:ClientMain.restaurantslist){
            if(x.getId() == id) return x.getName();
        }
        return null;
   }
    public void setFields(int i) {
        if(i % 2 == 0)
        {
            hBox.setStyle("-fx-background-color:  #2E8BC0");
        }
        column1.setStyle("-fx-text-fill: #ffffff");
        column2.setStyle("-fx-text-fill: #ffffff");
        coloumn3.setStyle("-fx-text-fill: #ffffff");;
        coloumn3.setStyle("-fx-background-radius: 50");;
        //coloumn3.setStyle();
        column1.setText(food.getName());
        column2.setText(getRestaurantname(food.getRestaurantId()));
    }

    public void more(ActionEvent actionEvent) {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FoodmoreInfo.fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();
             ((Foodmoreinfo)fxmlLoader.getController()).setFields(food, stage);
            stage.setTitle("More Info: ");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
