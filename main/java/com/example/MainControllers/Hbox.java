package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class Hbox {


    @FXML
    private Label column1;
    @FXML
    private Button coloumn3;
    @FXML
    private HBox hBox;
    Restaurant restaurant;
    void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    public void more(ActionEvent actionEvent) {
        System.out.println("more button clicked");
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("moreInfo.fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();
            ((moreInfo)fxmlLoader.getController()).setFields(restaurant, stage);
            stage.setTitle("More Info: ");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFields(int i){
        if(i % 2 == 0)
        {
            hBox.setStyle("-fx-background-color:  #2E8BC0");
            column1.setStyle("-fx-text-fill: #ffffff");

        }
        coloumn3.setStyle("-fx-text-fill: #000000");;
        coloumn3.setStyle("-fx-background-radius: 50");;
        //coloumn3.setStyle();
        column1.setText(restaurant.getName());
    }


}
