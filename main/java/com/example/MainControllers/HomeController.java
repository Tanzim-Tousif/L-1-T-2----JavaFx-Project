package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Callable;

public class HomeController {
    public Label exist;
    public Button addButton;
    Stage stage;
    ClientMain clientMain;
    public javafx.scene.control.Label foldable;
    @FXML
    private  TextField foodname;
   @FXML
    TextField pricetext;
    @FXML
    TextField categorytext;
    public javafx.scene.control.Label price;
    public javafx.scene.control.Label category;



    void setStage(Stage stage){
        this.stage = stage;
    }
    void setClient(ClientMain client){
        this.clientMain = client;
    }
    public void add(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        /*
        int flag1 = 0,flag2 = 0,flag3 = 0;
        String foodName = "";
        double foodPrice = 0;
        String foodCategory = "";
        exist.setText("");
            foldable.setText("");
            price.setText("");
            category.setText("");

                String nametext = foodname.getText();
                if (nametext.trim().isEmpty()) {
                    foldable.setText("Please enter a string");
                    flag1 = 0;
                } else {
                    flag1 = 1;
                }

            try {
                Integer.parseInt(foodname.getText());
                flag1 = 0;
                foldable.setText("Please enter a string");
                foodname.clear();
            } catch (NumberFormatException e) {
                flag1 = 1;
                foodName += foodname.getText();
            }
            String text = pricetext.getText();
            if (text.trim().isEmpty()) {
                price.setText("Please enter a Valid price");
            }
            else flag2 = 1;
            try {
                Double.parseDouble(pricetext.getText());
                flag2 = 1;
                foodPrice += Double.parseDouble(pricetext.getText());
            } catch (NumberFormatException e) {
                flag2 = 0;
                price.setText("Please enter a Valid price");
                pricetext.clear();

            }

            String text1 = categorytext.getText();
            if (text1.trim().isEmpty()) {
                flag3 = 0;
                category.setText("Please enter a string");
            }
            else flag3 = 1;
            try {
                Integer.parseInt(text1);
                flag3 = 0;
                category.setText("Please enter a string");
            } catch (NumberFormatException e) {
                 flag3 = 1;
                System.out.println("on flag 3");
                 foodCategory += categorytext.getText();
            }
        ClientMain.socketWrapper.write(ClientMain.Restaurant_name);
          int id = 0;
        int Restaurantexist = 0;
         if(flag3 == 1 && flag1 == 1 && flag2 == 1){
              id = (int)ClientMain.socketWrapper.read();
             System.out.println(id+" "+foodCategory+" "+foodName+" "+foodPrice);
             Food food = new Food(id,foodCategory,foodName,foodPrice);
             ClientMain.socketWrapper.write(food);
             boolean temp = (boolean)ClientMain.socketWrapper.read();
             System.out.println(temp);
             if(temp) {
                 Restaurantexist = 1;
                 exist.setText("Same Food Already Exist");
                 foodname.clear();
                 categorytext.clear();
                 pricetext.clear();
             }
         }

        if(flag3 == 1 && flag1 == 1 && flag2 == 1 && Restaurantexist == 0){

            foodname.clear();
            categorytext.clear();
            pricetext.clear();
            Food food = new Food(id,foodCategory,foodName,foodPrice);
            ClientMain.menulist.add(food);
           ClientMain.allfooditems.add(food);

        }

         */
        System.out.println("on add Restaurant");
        ClientMain.socketWrapper.write("Add Restaurant");
        foldable.setText("");
        exist.setText("");
        price.setText("");
        String[] attribute = new String[3];
        attribute[0] = foodname.getText();
        attribute[1] = pricetext.getText();
        attribute[2] = categorytext.getText();
        System.out.println();
        if(!attribute[0].equalsIgnoreCase("")){
                if(!attribute[1].isEmpty() && !attribute[2].isEmpty()){
                    try {
                        Double.parseDouble(attribute[1]);
                        if(added(attribute[0], Double.parseDouble(attribute[1]),attribute[2])) exist.setText("This Food item already exist");
                        else {
                            System.out.println("Successful");
                            ClientMain.socketWrapper.write(ClientMain.Restaurant_name);
                            int id = (int)ClientMain.socketWrapper.read();
                            System.out.println(id);
                            Food food = new Food(id,attribute[2],attribute[0],Double.parseDouble(attribute[1]));
                            ClientMain.socketWrapper.write(food);
                            ClientMain.menulist.add(food);
                            addButton.setText("Food Added");
                            clientMain.showmenulist();
                           // exist.setText("Food item added successfully");
                        }
                    }catch (NumberFormatException e){
                        price.setText("Enter a valid price");
                    }
                }
                else foldable.setText("All Attribute must be filled");
        }
        else foldable.setText("Food name can not be empty");

    }

    boolean added(String Foodname,double price,String category){
        for(Food food:ClientMain.menulist){
            if(food.getPrice() == price && food.getName().equalsIgnoreCase(Foodname) && food.getCategory().equalsIgnoreCase(category)) return true;
        }
        return false;
    }


    public void BackButton(ActionEvent actionEvent) {
            try {
                stage.close();
                clientMain.showmainpage();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

    }
}
