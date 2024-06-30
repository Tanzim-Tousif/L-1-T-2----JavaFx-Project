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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchFoodByPrice implements Initializable {
    ClientMain clientMain;
    Stage stage;

    public javafx.scene.control.TextField firstscore ;
    public  javafx.scene.control.TextField secondscore;
    public javafx.scene.control.Label errorMessageName;
    public javafx.scene.control.ScrollPane scrollPaneName;
    @FXML
    private VBox vBox;
    @FXML
    private HBox titleBar;
    ArrayList<Food>foods = ClientMain.menulist;
    public void back(ActionEvent actionEvent) throws IOException {
        stage.close();
        clientMain.showFoodSearch();
    }

    public void searchName(ActionEvent actionEvent) {
        vBox.getChildren().clear();
        scrollPaneName.setVisible(false);
        titleBar.setVisible(false);
        errorMessageName.setVisible(false);
        String score1 = firstscore.getText();
        String score2 = secondscore.getText();
        if(score2.equalsIgnoreCase("") || score1.equalsIgnoreCase("")){
            errorMessageName.setVisible(true);
            errorMessageName.setText("Proper Input not given");
            return;
        }
      ArrayList<Food>foodstosthow = new ArrayList<>();

        double num1, num2;
        try {
            num1 = Double.parseDouble(score1);
            num2 = Double.parseDouble(score2);
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

        for(Food x:foods){
            if(x.getPrice() >= num1 && x.getPrice() <= num2){
                foodstosthow.add(x);
            }
        }

        if(foodstosthow.size() == 0){
            errorMessageName.setVisible(true);
            errorMessageName.setText("No Food item Found");
            return;
        }

        scrollPaneName.setVisible(true);
        titleBar.setVisible(true);
        int j = 1;
        for (int i = foodstosthow.size() - 1; i >= 0; i--)
        {
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

    public void setClient(ClientMain clientMain) {
        this.clientMain = clientMain;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrollPaneName.setVisible(false);
        titleBar.setVisible(false);
    }
}
