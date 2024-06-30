package com.example.demo;

import Server.ReadWriteThread;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class RestaurantMain {
    Stage stage;
    ClientMain clientMain;
    static int state = 1;
    public void menu(ActionEvent actionEvent) throws IOException {
        clientMain.showmenulist();
    }

    public void orders(ActionEvent actionEvent) throws IOException, ClassNotFoundException, InterruptedException {
//            ClientMain.socketWrapper.write("Show orders");
//            ClientMain.socketWrapper.write(ClientMain.Restaurant_name);
//            ArrayList<FakeFood> temp = (ArrayList<FakeFood>) ClientMain.socketWrapper.read();
//            ClientMain.pendingorders = temp;
//            for (FakeFood x : temp) System.out.println(x.getCustomer());
        /*
        if(ClientMain.pendingorders.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Order List");//
            alert.setContentText("There is no order till now");
            alert.show();
            return;
        }

         */
            clientMain.showserve();
        System.out.println("order button clicked");
        ReadThread.start();
        //clientMain.showthread();
           new ReadThread(ClientMain.socketWrapper,clientMain,ClientMain.id);
//
//        new Thread(() -> {
//            Object fromServer;
//            try {
//                while(true){
//                    /*
//                            System.out.println("on order thread");
//                            ClientMain.socketWrapper.write("Show orders");
//                            fromServer = (String) ClientMain.socketWrapper.read();
//                            System.out.println(fromServer);
//                            //socketWrapper.write("all good");
//                            if (fromServer.equals("new orders")) {
//                                //  notify();
//                                ClientMain.socketWrapper.write(ClientMain.Restaurant_name);
//                                System.out.println("Restaurant name is" + ClientMain.Restaurant_name);
//                                ArrayList<FakeFood> temp = (ArrayList<FakeFood>)  ClientMain.socketWrapper.read();
//                                ClientMain.pendingorders = temp;
//                                System.out.println("all orders received");
//                                for (FakeFood x : temp) System.out.println(x.getCustomer());
//                                Platform.runLater(() -> {
//                                    try {
//                                        // if(ClientMain.start == 1) clientMain.close();
//                                        clientMain.showserve();
//
//                                    } catch (IOException e) {
//                                        throw new RuntimeException(e);
//                                    }
//                                });
//                            }
//                            */
//                    Thread.sleep(2000);
//                    Platform.runLater(() -> {
//                        try {
//                            // if(ClientMain.start == 1) clientMain.close();
//                            clientMain.showserve();
//
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//
//                }
//
//            }
//             catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }).start();

    }

    public void add(ActionEvent actionEvent) throws IOException {
        clientMain.ShowAddRestaurant();
    }

    public void setClient(ClientMain clientMain) {
        this.clientMain = clientMain;
    }

    public void setstage(Stage stage) {
        this.stage = stage;
    }

    public void logout(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        stage.close();
        //ClientMain.socketWrapper.write("close");
        //ClientMain.socketWrapper.read();
        ClientMain.socketWrapper.closeConnection();
        clientMain.showfirstpage();
    }
}
