package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainRestaurant implements Initializable {

ClientMain client;
@FXML
TableView<Food> table;
@FXML
TableColumn<Food, String> name;
@FXML
TableColumn<Food, String> category;
@FXML
TableColumn<Food, Double> price;
Stage stage;

    public void setClient(ClientMain clientMain) {
        this.client = clientMain;
    }

    public void setTable(ObservableList<Food> List){
        table.setItems(List);
        table.setFixedCellSize(40);
        table.setMaxHeight(List.size() * table.getFixedCellSize() + 40);
        table.setRowFactory(tv -> {
            TableRow<Food> row = new TableRow<>();
            return row;
        });

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setEditable(true);
        name.setCellValueFactory(new PropertyValueFactory<Food,String>("Name"));
        category.setCellValueFactory(new PropertyValueFactory<Food,String >("Category"));
        price.setCellValueFactory(new PropertyValueFactory<Food,Double>("Price"));

    }


    public void setstage(Stage stage) {
        this.stage = stage;
    }

    public void back(ActionEvent actionEvent) throws IOException {
        stage.close();
        client.showmainpage();

    }
}



