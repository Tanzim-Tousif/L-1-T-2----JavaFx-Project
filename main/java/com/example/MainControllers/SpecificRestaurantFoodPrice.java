package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SpecificRestaurantFoodPrice implements Initializable {

    ClientMain clientMain;
    Stage stage;

    public javafx.scene.control.TextField textFieldName ;
    public javafx.scene.control.TextField firstscore ;
    public  javafx.scene.control.TextField secondscore;
    public javafx.scene.control.Label errorMessageName;
    public javafx.scene.control.ScrollPane scrollPaneName;
    @FXML
    private VBox vBox;
    @FXML
    private HBox titleBar;
    ArrayList<Food> foods = ClientMain.menulist;
    ArrayList<Restaurant>restaurants = ClientMain.restaurantslist;
    public void searchName(ActionEvent actionEvent) {
        vBox.getChildren().clear();
        scrollPaneName.setVisible(false);
        titleBar.setVisible(false);
        errorMessageName.setVisible(false);
        String restaurantName = textFieldName.getText();
        String first = firstscore.getText();
        String  second = secondscore.getText();
        if(restaurantName.equalsIgnoreCase("") || first.equalsIgnoreCase("") || second.equals("")){
            errorMessageName.setVisible(true);
            errorMessageName.setText("Proper Input not given");
            return;
        }
        ArrayList<Food>foodstosthow = new ArrayList<>();
        double num1, num2;
        try {
            num1 = Double.parseDouble(first);
            num2 = Double.parseDouble(second);
        } catch (Exception e) {
            errorMessageName.setVisible(true);
            errorMessageName.setText("Please Enter a valid score");
            return;
        }
        if(num2 < num1) {
            double temp = num1;
            num1 = num2;
            num2 = temp;

        }
        for(int i = 0;i<restaurants.size();i++) {
            if (restaurants.get(i).getName().equalsIgnoreCase(restaurantName)) {
                int tempid = restaurants.get(i).getId();
                for (int j = 0; j < foods.size(); j++) {
                    if ((foods.get(j).getPrice() >= num1 && foods.get(j).getPrice() <= num2) && foods.get(j).getRestaurantId() == tempid) {
                        //flag = 1;
                        // foods.get(j).ShowDetails();
                        foodstosthow.add(foods.get(j));
                    }
                }
            }
        }

            if (foodstosthow.size() == 0) {
                errorMessageName.setVisible(true);
                errorMessageName.setText("No Food item Found");
                return;
            }

            scrollPaneName.setVisible(true);
            titleBar.setVisible(true);
            int j = 1;
            for (int i = foodstosthow.size() - 1; i >= 0; i--) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FoodHbox.fxml"));
                Node root;
                try {
                    root = (Node) fxmlLoader.load();
                    ((FoodHbox) fxmlLoader.getController()).setFood(foodstosthow.get(i));
                    ((FoodHbox) fxmlLoader.getController()).setFields(j);
                    vBox.getChildren().add(root);
                    j++;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    public void back(ActionEvent actionEvent) throws IOException {
        stage.close();
        clientMain.showRestaurantFood();
    }

    public void setClient(ClientMain clientMain) {
        this.clientMain = clientMain;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleBar.setVisible(false);
        scrollPaneName.setVisible(false);
    }
}
