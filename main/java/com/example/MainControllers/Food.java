
package com.example.demo;

import java.io.Serializable;

public class Food implements Serializable{
    private int RestaurantId;
    private String Category;
    private String Name;
    private double Price;
    public Food(int RestaurantId,String Category,String Name,double Price){
        this.RestaurantId = RestaurantId;
        this.Category = Category;
        this.Name = Name;
        this.Price = Price;
    }
    public int getRestaurantId(){
        return RestaurantId;
    }
    public String getCategory(){
        return Category;
    }
    public String getName(){
        return Name;
    }
    public double getPrice(){
        return Price;
    }
    public void setRestaurentId(int RestaurantId){
        this.RestaurantId = RestaurantId;
    }
    public void setCategory(String Category){
        this.Category = Category;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public void setPrice(double Price){
        this.Price = Price;
    }
    public void ShowDetails(){
        System.out.println("Restaurant ID is: "+RestaurantId);
        System.out.println("Category :"+Category);
        System.out.println("Name: "+ Name);
        System.out.println("Price: "+Price);

    }
}
