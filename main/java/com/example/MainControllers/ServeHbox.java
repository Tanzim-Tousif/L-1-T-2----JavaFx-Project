package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ServeHbox {
    @FXML
    private HBox hBox;
    FakeFood food;
    public javafx.scene.control.Label column1;
    public void details(ActionEvent actionEvent) {
        System.out.println("details button clicked");
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ordermoreInfo.fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();
            ((ordermoreInfo)fxmlLoader.getController()).setFields(food, stage);
            // stage.setTitle("More Info: " + ClientApplication.client.getName());
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // for(Food x:ClientMain.pendingorders) System.out.println(x.getName());
    }

    public void setMovie(FakeFood food) {
        this.food = food;
    }

    public void setFields(int i)
    {
        if(i % 2 == 0)
        {

            hBox.setStyle("-fx-background-color:  #2E8BC0");
            column1.setStyle("-fx-text-fill: #ffffff");
            // coloumn2.setStyle("-fx-text-fill: #ffffff");;
        }
        column1.setText(food.getName());
    }
}
