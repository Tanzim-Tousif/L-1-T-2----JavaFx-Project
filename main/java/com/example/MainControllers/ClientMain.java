package com.example.demo;
import Server.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class ClientMain extends Application {

    final String serverAddress = "127.0.0.1";
    final int serverPort = 12345;
    Stage stage;
    static int start = 0;
    static Stage servestage;
    static ArrayList<FakeFood> alreadyordered = new ArrayList<>();
   public  static ArrayList<FakeFood>pendingorders = new ArrayList<>();
   public static ArrayList<FakeFood>cartedfood = new ArrayList<>();
    public static SocketWrapper socketWrapper;
    static ArrayList<Food>menulist;
    static ArrayList<Restaurant>restaurantslist;
   static  String Restaurant_name;
   static String username;
   static int id;
   public static ObservableList<Food> allfooditems = FXCollections.observableArrayList();
   public static ObservableList<FakeFood>cartfoods = FXCollections.observableArrayList();



/*
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("MainRestaurant.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }



 */

    public static void main(String[] args){
        launch();
    }

    public  void doit() throws IOException {
        showserve();
    }

    @Override

    public void start(Stage stage) throws IOException {
        this.stage = stage;
        showfirstpage();
    }
    public void showfirstpage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("first.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 657, 446);
        First helloController = fxmlLoader.getController();
        helloController.setclient(this);
        stage.setTitle("First");
        stage.setScene(scene);
        stage.show();
    }

    public void showLoginPage() throws IOException {
        socketWrapper = new SocketWrapper(new Socket(serverAddress, serverPort));
        socketWrapper.write("As a Restaurant");
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("RestaurantLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 611);
        HelloController helloController = fxmlLoader.getController();
        helloController.setclient(this);
        stage.setTitle("LOGIN");
        stage.setScene(scene);
        stage.show();

    }
    void showmainpage() throws IOException {
        allfooditems.clear();
        for(Food x:menulist) allfooditems.add(x);
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("RestaurantMain.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 756, 544);
        RestaurantMain companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        companyHomeController.setstage(stage);
        //companyHomeController.setTable(allfooditems);
        stage.setTitle("HOME");
        stage.setScene(scene);
        stage.show();
    }
 void showmenulist() throws IOException {
     FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("MainRestaurant.fxml"));
     Scene scene = new Scene(fxmlLoader.load(), 721, 508);
     MainRestaurant companyHomeController = fxmlLoader.getController();
     //Stage stage = new Stage();
     companyHomeController.setClient(this);
     companyHomeController.setstage(stage);
     companyHomeController.setTable(allfooditems);
     stage.setTitle("HOME");
     stage.setScene(scene);
     stage.show();
 }
    void ShowAddRestaurant() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("addfood.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 611);
        HomeController companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        Stage stage = new Stage();
        stage.setTitle("Add Food");
        stage.setScene(scene);
        companyHomeController.setStage(stage);
        stage.show();
    }

    void showCustomerloginpage() throws IOException {
        socketWrapper = new SocketWrapper(new Socket(serverAddress, serverPort));
        socketWrapper.write("As a Customer");
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("Customerlogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 758, 500);
        CustomerLoginController helloController = fxmlLoader.getController();
        helloController.setclient(this);
        stage.setTitle("LOGIN");
        stage.setScene(scene);
        stage.show();
    }
    public void showCustomermainpage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("CustomerMainpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 664, 448);
        CustomerMainpageController helloController = fxmlLoader.getController();
        helloController.setClient(this);
        stage.setTitle("Main page");
        stage.setScene(scene);
        stage.show();
    }

    public void showSearchRestaurant() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("SearchRestaurant.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 734, 547);
        SearchRestaurant helloController = fxmlLoader.getController();
        helloController.setClient(this);
       // Stage stage = new Stage();
        helloController.setStage(stage);
        stage.setTitle("Main page");
        stage.setScene(scene);
        stage.show();
    }


    public void showRestaurantsByname() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("test.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 460);
        SearchRestaurantByname helloController = fxmlLoader.getController();
        helloController.setClient(this);
         Stage stage = new Stage();
        helloController.setStage(stage);
        stage.setTitle("Main page");
        stage.setScene(scene);
        stage.show();
    }

    public void showRestaurantByscore() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("SearchRestaurantByscore.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 460);
        SearchRestaurantByscore helloController = fxmlLoader.getController();
        helloController.setClient(this);
        Stage stage = new Stage();
        helloController.setStage(stage);
        stage.setTitle("Score page");
        stage.setScene(scene);
        stage.show();
    }
    public void showRestaurantBycategory() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("SearchRestaurantBycategory.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 460);
        SearchRestaurantBycategory helloController = fxmlLoader.getController();
        helloController.setClient(this);
        Stage stage = new Stage();
        helloController.setStage(stage);
        stage.setTitle("Score page");
        stage.setScene(scene);
        stage.show();
    }
    public void showRestaurantByprice() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("SearchRestaurantByPrice.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 460);
        SearchRestaurantByPrice helloController = fxmlLoader.getController();
        helloController.setClient(this);
        Stage stage = new Stage();
        helloController.setStage(stage);
        stage.setTitle("Score page");
        stage.setScene(scene);
        stage.show();
    }
    public  void showRestaurantByzip() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("SearchRestaurantByzip.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 460);
        SearchRestaurantByzip helloController = fxmlLoader.getController();
        helloController.setClient(this);
        Stage stage = new Stage();
        helloController.setStage(stage);
        stage.setTitle("Score page");
        stage.setScene(scene);
        stage.show();
    }

    public void showFoodByname() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("SearchFoodByName.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 451);
        SearchFoodByName helloController = fxmlLoader.getController();
        helloController.setClient(this);
         Stage stage = new Stage();
        helloController.setStage(stage);
        stage.setTitle("Main page");
        stage.setScene(scene);
        stage.show();
    }
    public void showFoodBycategory() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("SearchFoodByCategory.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 451);
        SearchFoodByCategory helloController = fxmlLoader.getController();
        helloController.setClient(this);
        Stage stage = new Stage();
        helloController.setStage(stage);
        stage.setTitle("Main page");
        stage.setScene(scene);
        stage.show();
    }

    public void showFoodByprice() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("SearchFoodByPrice.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 451);
        SearchFoodByPrice helloController = fxmlLoader.getController();
        helloController.setClient(this);
        // Stage stage = new Stage();
        helloController.setStage(stage);
        stage.setTitle("Main page");
        stage.setScene(scene);
        stage.show();
    }

    public void showFoodSearch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("SearchFood.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 671, 478);
        SearchFood helloController = fxmlLoader.getController();
        helloController.setClient(this);
        // Stage stage = new Stage();
        helloController.setStage(stage);
        stage.setTitle("Main page");
        stage.setScene(scene);
        stage.show();
    }

    public void showRestaurantFood() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("SpecificRestaurantFoodSearch.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 671, 478);
        SpecificRestaurantFood helloController = fxmlLoader.getController();
        helloController.setClient(this);
        // Stage stage = new Stage();
        helloController.setStage(stage);
        stage.setTitle("Main page");
        stage.setScene(scene);
        stage.show();
    }

    public void ShowRestaurantFoodName() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("SpecificRestaurantFoodName.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 451);
        SpecificRestaurantFoodName helloController = fxmlLoader.getController();
        helloController.setClient(this);
        Stage stage = new Stage();
        helloController.setStage(stage);
        stage.setTitle("Main page");
        stage.setScene(scene);
        stage.show();
    }

    public void ShowRestaurantFoodPrice() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("SpecificRestaurantFoodPrice.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 736, 536);
        SpecificRestaurantFoodPrice helloController = fxmlLoader.getController();
        helloController.setClient(this);
        Stage stage = new Stage();
        helloController.setStage(stage);
        stage.setTitle("Main page");
        stage.setScene(scene);
        stage.show();
    }

    public void ShowRestaurantFoodCategory() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("SpecificRestaurantFoodCategory.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 451);
        SpecificRestaurantFoodCategory helloController = fxmlLoader.getController();
        helloController.setClient(this);
        Stage stage = new Stage();
        helloController.setStage(stage);
        stage.setTitle("Main page");
        stage.setScene(scene);
        stage.show();
    }
    public  void showserve() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("PendingOrders.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 677, 456);
        PendingOrders helloController = fxmlLoader.getController();
        helloController.setClient(this);
        helloController.setStage(stage);
        stage.setTitle("Main page");
        stage.setScene(scene);
        stage.show();
    }



    public void close() {
        servestage.close();
    }


    public void showcarmenu() throws IOException {
        cartfoods.clear();
        for(FakeFood x:cartedfood) cartfoods.add(x);
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("cart.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 661, 479);
        Cart helloController = fxmlLoader.getController();
        helloController.setClient(this);
        helloController.setStage(stage);
        helloController.settable(cartfoods);
        stage.setTitle("Main page");
        stage.setScene(scene);
        stage.show();
    }
    public static int  idfromname(String restaurant_name){
        for(Restaurant x:restaurantslist){
            if(x.getName().equalsIgnoreCase(restaurant_name)) return x.getId();
        }
        return -1;
    }

    public void shownewrestaurantpage() throws IOException {
        socketWrapper = new SocketWrapper(new Socket(serverAddress, serverPort));
        socketWrapper.write("Add new Restaurant");
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("shownewrestaurant.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 802, 617);
        Shownewrestaurant helloController = fxmlLoader.getController();
        Stage stage = new Stage();
       // helloController.setClient(this);
        helloController.setStage(stage);
        //helloController.settable(cartfoods);
        stage.setTitle("Add Restaurant");
        stage.setScene(scene);
        stage.show();
    }
}