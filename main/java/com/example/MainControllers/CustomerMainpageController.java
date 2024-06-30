package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerMainpageController {
    ClientMain clientMain;
    public javafx.scene.control.Button cart;
    void setClient(ClientMain client){
        this.clientMain =client;
    }
    public void logout(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogoutConfirmation.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        LogoutConfirmationController logoutConfirmationController = fxmlLoader.getController();
        logoutConfirmationController.setClient(clientMain);
        logoutConfirmationController.messageLabel.setText("ARE YOU CONFIRM?");
        stage.setTitle("LOGOUT CONFIRMATION");
        stage.setScene(scene);
        stage.show();
        logoutConfirmationController.setStage(stage);
    }


    public void namebutton(ActionEvent actionEvent) throws IOException {
         clientMain.showSearchRestaurant();

    }

    public void foodbutton(ActionEvent actionEvent) throws IOException {
        clientMain.showFoodSearch();
    }

    public void SpecificRestaurant(ActionEvent actionEvent) throws IOException {
        clientMain.showRestaurantFood();
    }

    public void cart(ActionEvent actionEvent) throws IOException {

        if(ClientMain.cartedfood.isEmpty()){
          Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Cart");//
            //alert.setHeaderText("Invalid Credentials");
             alert.setContentText("Plese Add Some food");
           alert.show();
            return;
        }
        clientMain.showcarmenu();


    }
}
