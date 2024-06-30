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

public class SearchRestaurantBycategory implements Initializable {
    ClientMain clientMain;
    public javafx.scene.control.TextField textFieldName;
    public javafx.scene.control.Label errorMessageName;
    public javafx.scene.control.ScrollPane scrollPaneName;
    @FXML
    private VBox vBox;
    @FXML
    private HBox titleBar;

    private ArrayList<Restaurant> restaurants = ClientMain.restaurantslist;
    @FXML
    Stage stage;
    public void searchName(ActionEvent actionEvent) throws IOException {
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
        ArrayList<Restaurant>restaurantsToShow = new ArrayList<>();
        for(Restaurant x:restaurants){
            for(String it:x.getCategory()){
                if(it.toLowerCase().contains(name.toLowerCase())){
                    restaurantsToShow.add(x);
                    break;
                }
            }
        }
        if(restaurantsToShow.size() == 0){
            errorMessageName.setVisible(true);
            errorMessageName.setText("No Restaurant Found");
            return;
        }
        scrollPaneName.setVisible(true);
        titleBar.setVisible(true);
        int j = 1;
        for (int i = restaurantsToShow.size() - 1; i >= 0; i--)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hbox.fxml"));
            Node root;
            try {
                root = (Node) fxmlLoader.load();
                ((Hbox) fxmlLoader.getController()).setRestaurant(restaurantsToShow.get(i));
                ((Hbox) fxmlLoader.getController()).setFields(j);
                vBox.getChildren().add(root);
                j++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void back(ActionEvent actionEvent) throws IOException {
        clientMain.showSearchRestaurant();
        stage.close();
    }

    public void setClient(ClientMain clientMain) {
        this.clientMain =clientMain;
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
