package com.example.demo;

import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ReadThread implements Runnable{

    SocketWrapper socketWrapper;
    Object fromServer;
    ClientMain clientMain;
    static  boolean run = true;
    public static void stop(){
        run = false;
    }
    public static void start(){run = true;}
    int restaurantid;
    ReadThread(SocketWrapper socketWrapper,ClientMain clientMain,int id){
        this.socketWrapper = socketWrapper;
        this.clientMain = clientMain;
        this.restaurantid = id;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (run){
            try {
                synchronized (this)
                {
                    //System.out.println("on order thread");
                    socketWrapper.write("Show orders");
                   // System.out.println(fromServer);
                    fromServer = socketWrapper.read();
                    if (fromServer instanceof String) {
                    //fromServer = (String) socketWrapper.read();

                    //System.out.println(fromServer);
                    //socketWrapper.write("all good");
                    if (fromServer.equals("new orders")) {
                      //  notify();
                        ClientMain.pendingorders.clear();
                        ArrayList<FakeFood> temp = (ArrayList<FakeFood>) socketWrapper.read();
                        for(FakeFood x:temp){
                            if(x.getRestaurantId() == restaurantid) ClientMain.pendingorders.add(x);
                        }
                       // System.out.println("all orders received");
                        //socketWrapper.write("all done");
                       // for (FakeFood x : temp) System.out.println(x.getCustomer());
                        Platform.runLater(() -> {
                            try {
                                // if(ClientMain.start == 1) clientMain.close();
                                clientMain.showserve();

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                }
                else if(fromServer instanceof ArrayList<?>){
                    ArrayList<FakeFood>temp = (ArrayList<FakeFood>)fromServer;
                        ClientMain.pendingorders.clear();
                        //ArrayList<FakeFood> temp = (ArrayList<FakeFood>) socketWrapper.read();
                        for(FakeFood x:temp){
                            if(x.getRestaurantId() == restaurantid) ClientMain.pendingorders.add(x);
                        }
                        //ClientMain.pendingorders = temp;
                        Platform.runLater(() -> {
                            try {
                                // if(ClientMain.start == 1) clientMain.close();
                                clientMain.showserve();

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                }} catch(IOException e){
                    throw new RuntimeException(e);
                } catch(ClassNotFoundException e){
                    throw new RuntimeException(e);
                }
            }
            }
        }




