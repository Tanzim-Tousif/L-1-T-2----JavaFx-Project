package com.example.demo;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Foodmoreinfo {
    ClientMain clientMain;
    public javafx.scene.control.Label name;
    public javafx.scene.control.Label price;
    public javafx.scene.control.Label category;
    public javafx.scene.control.TextField amount;
    public javafx.scene.control.Label errot;
    public javafx.scene.control.Button order;
    Stage stage;
    Food food;
    public void setFields(Food food, Stage stage) {
        this.stage = stage;
        this.food = food;
        name.setText(food.getName());
        category.setText(food.getCategory());
        price.setText(Double.toString(food.getPrice()) + "$");
    }

    public void orderbutton(ActionEvent actionEvent) throws IOException {
        errot.setText("");
        String temp = amount.getText();
        if(order.getText().equals("Added to cart")){
            errot.setText("Item already Added to cart");
            return;
        }
        if(temp.equals("")){
            errot.setText("Please Enter a valid number");
            return;
        }
        try {
            Integer.parseInt(temp);
            if(Integer.parseInt(temp) == 0) {
                errot.setText("Item number must be atleast 1"); return;
            }
            order.setText("Added to cart");
            FakeFood fakeFood = new FakeFood(food.getRestaurantId(),food.getName(),food.getCategory(), food.getPrice(), ClientMain.username,Integer.parseInt(temp));
            //new OrderThread(ClientMain.socketWrapper,fakeFood);
            int flag =0 ;
            for(FakeFood x:ClientMain.cartedfood){
                if(x.getRestaurantId() == fakeFood.getRestaurantId() && x.getName().equals(fakeFood.getName()) && x.getCategory().equals(fakeFood.getCategory()) && x.getCustomer().equals(fakeFood.getCustomer())){
                    fakeFood.setAmount(fakeFood.getAmount() + x.getAmount());
                    ClientMain.cartedfood.remove(x);
                    ClientMain.cartedfood.add(fakeFood);
                    flag = 1;

                }
            }
            if(flag == 0) ClientMain.cartedfood.add(fakeFood);

        }
        catch (NumberFormatException e){
            errot.setText("Please Enter a valid number");
            return;
        }

    }

    public void close(ActionEvent actionEvent) {
        stage.close();
    }
}
