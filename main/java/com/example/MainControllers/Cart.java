package com.example.demo;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Cart implements Initializable {
    Stage stage;
    static int placed  = 0;
    ClientMain clientMain;

    @FXML
    TableView<FakeFood> table;
    @FXML
    TableColumn<FakeFood, String> name;
    @FXML
    TableColumn<FakeFood, Integer> restaurant;
    @FXML
    TableColumn<FakeFood, Double> price;
    @FXML
    TableColumn<FakeFood, Integer> amount;
    public javafx.scene.control.Label amnt;
    public javafx.scene.control.Button place;
    public javafx.scene.control.Label error;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // if(placed == 1) place.setText("Order placed");
        double temp = 0;
        for(FakeFood x:ClientMain.cartedfood) temp += ((x.getAmount()) * (x.getPrice()));
        amnt.setText(new DecimalFormat("##.##").format(temp)+"$");
        table.setEditable(true);
        name.setCellValueFactory(new PropertyValueFactory<FakeFood,String>("name"));
        restaurant.setCellValueFactory(new PropertyValueFactory<FakeFood,Integer >("RestaurantId"));
        price.setCellValueFactory(new PropertyValueFactory<FakeFood,Double>("price"));
        amount.setCellValueFactory(new PropertyValueFactory<FakeFood,Integer>("amount"));
    }

    public void back(ActionEvent actionEvent) throws IOException {
        stage.close();
        //ClientMain.cartedfood.clear();
        clientMain.showCustomermainpage();

    }

    public void order(ActionEvent actionEvent) throws IOException {

        if(place.getText().equals("Order placed")){
            error.setText("Order already placed");
            return;
        }
        place.setText("Order placed");
       ArrayList<FakeFood>temp = new ArrayList<>();
       for(FakeFood x:ClientMain.cartedfood) temp.add(x);
        new OrderThread(ClientMain.socketWrapper,temp);
        ClientMain.cartedfood.clear();
       // Cart.placed = 1;
        //clientMain.showcarmenu();


    }

    public void setClient(ClientMain clientMain) {
        this.clientMain = clientMain;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void settable(ObservableList<FakeFood> cartfoods) {
        table.setItems(cartfoods);
        table.setFixedCellSize(40);
        table.setMaxHeight(cartfoods.size() * table.getFixedCellSize() + 40);
        table.setRowFactory(tv -> {
            TableRow<FakeFood> row = new TableRow<>();
            return row;
        });
    }
}
