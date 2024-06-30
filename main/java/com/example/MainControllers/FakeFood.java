package com.example.demo;

import java.io.Serializable;
import java.util.ArrayList;

public class FakeFood implements Serializable {
    String name;
    String category;
    int RestaurantId;
    double price;
    String customer;
    int amount;
    String RestaurantName;

    FakeFood(int id,String name,String category,double price,String customer,int amount){
        this.name = name;
        this.RestaurantId = id;
        this.price = price;
        this.category =category;
        this.customer = customer;
        this.amount = amount;
    }
    public int getRestaurantId(){
        return RestaurantId;
    }
    public String getName(){
        return name;
    }
    public String getCategory(){
        return category;
    }
    public double getPrice(){
        return price;
    }
    public String getCustomer(){
        return customer;
    }
    public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public  void setRestaurantName(String name){
        this.RestaurantName = name;
    }
    public String getRestaurantName(){
        return RestaurantName;
    }
}
