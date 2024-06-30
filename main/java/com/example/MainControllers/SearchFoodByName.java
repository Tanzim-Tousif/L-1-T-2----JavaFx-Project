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

public class SearchFoodByName implements Initializable {
    ClientMain clientMain;
    Stage stage;
    public javafx.scene.control.TextField textFieldName;
    public javafx.scene.control.Label errorMessageName;
    public javafx.scene.control.ScrollPane scrollPaneName;
    @FXML
    private VBox vBox;
    @FXML
    private HBox titleBar;
    ArrayList<Food>foodlist = ClientMain.menulist;
    public void searchName(ActionEvent actionEvent) {
        vBox.getChildren().clear();
        scrollPaneName.setVisible(false);
        titleBar.setVisible(false);
        errorMessageName.setVisible(false);
        String name = textFieldName.getText();
        if(name.equals("")){
            errorMessageName.setVisible(true);
            errorMessageName.setText("Proper Input not Given");
            return;
        }
        ArrayList<Food>foodstoShow = new ArrayList<>();
        for(Food it:foodlist){
            if(it.getName().toLowerCase().contains(name.toLowerCase())) {
                foodstoShow.add(it);
            }
        }
        if(foodstoShow.size() == 0){
            errorMessageName.setVisible(true);
            errorMessageName.setText("No Food item  Found");
            return;
        }
        scrollPaneName.setVisible(true);
        titleBar.setVisible(true);
        //for(Food x:foodstoShow) System.out.println(x.getName());
        int j = 1;
        for (int i = foodstoShow.size() - 1; i >= 0; i--)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FoodHbox.fxml"));
            Node root;
            try {
                root = (Node) fxmlLoader.load();
                ((FoodHbox) fxmlLoader.getController()).setFood(foodstoShow.get(i));
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
        clientMain.showFoodSearch();
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
