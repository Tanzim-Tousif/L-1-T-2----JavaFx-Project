
package com.example.demo;
import java.io.Serializable;
import java.util.*;

public class Restaurant implements Serializable {
    private int Id;
    private String Name;
    private double Score;
    private String Price;
    private String ZipCode;
    private List<String>Category;
    private List<Food> menulist;
    public Restaurant(int id,String Name,double Score,String price,String ZipCode,List<String>a){
        this.Id = id;
        this.Name = Name;
        this.Score = Score;
        this.Price = price;
        this.ZipCode = ZipCode;
        this.Category = a;
    }
    public int getId(){
        return Id;
    }
    public String getName(){
        return Name;
    }
    public  double getScore(){
        return Score;
    }
    public String getPrice(){
        return Price;
    }
    public String getZipCode(){
        return ZipCode;
    }

    public List<String> getCategory() {
        List<String> myList = new ArrayList<>();
        // Add elements to the list
        for(String x:Category){
            myList.add(x);
        }
        return myList;
    }


    public void setId(int id){
        this.Id = id;

    }
    public void setName(String name){
        this.Name = name;
    }
    public void setScore(double score){
        this.Score = score;
    }
    public void setPrice(String price){
        this.Price = price;
    }
    public void setZipCode(String code){
        this.ZipCode = code;
    }
    public void ShowDetails(){
        System.out.println("Id is: " + Id);
        System.out.println("Name: "+Name);
        System.out.println("Score: " + Score);
        if(Price.equals("$")){
            System.out.println("Price: $1 to $10");
        }
        else if(Price.equals("$$")) System.out.println("Price: $11 to $20");
        else System.out.println("Price: $21 to $30");
        System.out.println("Zip Code: " + ZipCode);
        System.out.println("Categories are :");
        for(int i = 0;i<Category.size();i++){
            if(i == (Category.size()-1)) System.out.print(Category.get(i) + " ");
            else System.out.print(Category.get(i) + " ,");
        }
        System.out.println();

    }
}