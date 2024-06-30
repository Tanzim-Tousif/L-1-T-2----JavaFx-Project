package Server;

import com.example.demo.ClientMain;
import com.example.demo.FakeFood;
import com.example.demo.Food;

import java.io.IOException;
import java.util.ArrayList;

public class ReadWriteThread implements  Runnable{
    Object fromClient;
    Client client;
   // ArrayList<FakeFood>ordersoftherestaurant = new ArrayList<>();
    public ReadWriteThread(Client client){
        ServerMain.ClientList.add(client);
        this.client = client;
        //this.ordersoftherestaurant = order;
        new Thread(this).start();
    }



    @Override
    public void run() {


        try {
            while(true) {

                fromClient = (String) client.socketWrapper.read();
                 System.out.println(fromClient);
                if (fromClient.equals("Add Restaurant")) {
                    fromClient = (String) client.socketWrapper.read();
                    int id = ServerMain.getRestaurantIdfromName((String) fromClient);
                    client.socketWrapper.write(id);
                    fromClient = (Food) client.socketWrapper.read();
                    ServerMain.FoodDataBase.add((Food) fromClient);
                    ServerMain.wrtitetomenufile();
                } else if (fromClient.equals("Show orders")) {
                    if (ServerMain.hasnew) System.out.println("new orders passing to customer");
                    if (ServerMain.hasnew) client.socketWrapper.write("new orders");
                    else client.socketWrapper.write("no new orders");

                     /*
                    if(pendinglist.size() > 0) System.out.println("new orders passing to customer");
                    if(pendinglist.size() > 0) client.socketWrapper.write("new orders");
                    else client.socketWrapper.write("no new orders");
                    //fromClient = (String)client.socketWrapper.read();
                    //wait();


                      */
                    if (ServerMain.hasnew) {
                        /*
                        fromClient = (String) client.socketWrapper.read();
                        System.out.println(fromClient);
                        int id = ServerMain.getRestaurantIdfromName((String) fromClient);

                         */
                        /*
                        ArrayList<FakeFood> restore = new ArrayList<>();
                        for (FakeFood x : ServerMain.pending) {
                            if (x.getRestaurantId() == id) restore.add(x);
                        }

                         */
                        ArrayList<FakeFood>restore =ServerMain.pending;
                        client.socketWrapper.write(restore);
                        //ArrayList<FakeFood> updated = (ArrayList<FakeFood>) client.socketWrapper.read();
                        //ServerMain.pending = updated;
                        /*
                        fromClient = client.socketWrapper.read();
                        if(fromClient instanceof String){
                            String temp = (String)fromClient;
                            if(temp.equals("all done")) ServerMain.hasnew = false;
                        }

                         */
                        ServerMain.hasnew = false;

                    }

                }
//                else if(fromClient.equals("close")){
//                   // ServerMain.writetorestaurantfile();
//
//                    //client.socketWrapper.closeConnection();
//                    //client.socketWrapper.write("ending");
//                }
            }


            /*
            fromClient = (Food)client.socketWrapper.read();
            Food f = (Food)fromClient;
            System.out.println(f.getName()+""+f.getRestaurantId()+" "+f.getCategory()+" "+f.getPrice());
            boolean temp = ServerMain.isSameCategoryAndName(f.getRestaurantId(),f.getName(),f.getCategory(),f.getPrice());
            System.out.println(temp);
            client.socketWrapper.write(temp);

             */

        } catch (Exception exception){
            System.out.println("Connection lost: " + exception);
        }finally {
            try {
                client.socketWrapper.closeConnection();
               // ServerMain.clientList.remove(client);

            }catch (IOException exception){
                exception.printStackTrace();
            }
        }



        /*
        while(true){
            try {
                fromClient = (String) client.socketWrapper.read();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if(fromClient.equals("Add Food")){

            }
        }

         */
    }

    private ArrayList<FakeFood> georderlist() {
        ArrayList<FakeFood>temp = new ArrayList<>();
        for(FakeFood x:ServerMain.pending){
            if(x.getRestaurantId() == (Integer.valueOf(client.clientName))) {

                temp.add(x);
                ServerMain.pending.remove(x);
            }
        }
        return temp;
    }
}
