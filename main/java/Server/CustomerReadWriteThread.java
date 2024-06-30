package Server;

import com.example.demo.*;


import java.io.IOException;
import java.util.ArrayList;

public class CustomerReadWriteThread implements Runnable{

    Object fromClient;
    Client client;
    public CustomerReadWriteThread(Client client){
        ServerMain.ClientList.add(client);
        this.client = client;
        new Thread(this).start();
    }
    @Override
    public void run() {
        try {


            while (true) {
                ArrayList<Restaurant> temprestaurant = ServerMain.RestaurantDataBase;
                ArrayList<Food> tempFood = ServerMain.FoodDataBase;
                client.socketWrapper.write(temprestaurant);
                client.socketWrapper.write(tempFood);
                fromClient = (String) client.socketWrapper.read();
                if(fromClient.equals("order")){
                    System.out.println("order taken");
                    fromClient = (ArrayList<FakeFood>)client.socketWrapper.read();
                    ArrayList<FakeFood>got = (ArrayList<FakeFood>)fromClient;
                    for(FakeFood x:got) System.out.println(x.getName());
                    for(FakeFood x:got) ServerMain.pending.add(x);
                    System.out.println(ServerMain.pending.size());
                    ServerMain.hasnew = true;
                    ClientMain clientMain =new ClientMain();
                    //clientMain.showserve();
                    if(ServerMain.hasnew) System.out.println("new order received from server end");
                    for(FakeFood x:ServerMain.pending) System.out.println(x.getName());
                }
            }
        } catch (Exception exception) {
            System.out.println("Connection lost: " + exception);
        }finally {
            try {
                client.socketWrapper.closeConnection();
                // ServerMain.clientList.remove(client);

            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }
}
