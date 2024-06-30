package Server;
import  com.example.demo.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;
public class ServerMain {
    static boolean hasnew = false;


    static ArrayList<Restaurant>RestaurantDataBase;
    static ArrayList<Food>FoodDataBase;
    static ArrayList<FakeFood>pending = new ArrayList<>();
    static ArrayList<Food>pendingorders = new ArrayList<>();
    public static ArrayList<Client>ClientList;
    static ArrayList<String> Restaurant_name;
    public static final String Restaurant_File = "F:\\Term Project\\restaurant.txt";
    public static final String Menu_file = "F:\\Term Project\\menu.txt";

    public static void main(String[] args) throws IOException {
        new ServerMain();
    }

    ServerMain() throws IOException {
        ClientList = new ArrayList<>();
        RestaurantDataBase = readfromrestaurantfile(Restaurant_File);
        FoodDataBase = readfrommenufile(Menu_file);
        Restaurant_name = Restaurant_Name();
        //System.out.println(Restaurant_name.contains("IHOP"));
        connectClient();
        //for(Restaurant x : RestaurantDataBase) x.ShowDetails();
    }

    public static void writetorestaurantfile() throws IOException {

            ArrayList<Restaurant> all = RestaurantDataBase;
            FileWriter fileWriter = new FileWriter(Restaurant_File, false); // The 'false' argument overwrites the file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Restaurant it: all) {
                //System.out.println(it.getId());

                bufferedWriter.write(it.getId()+","+it.getName()+","+it.getScore()+","+it.getPrice()+","+it.getZipCode()+",");
                if(it.getCategory().size() == 0){
                    bufferedWriter.write(",,");
                }
                else if(it.getCategory().size() == 1){
                    bufferedWriter.write(it.getCategory().get(0)+",,");
                }
                else if(it.getCategory().size() == 2){
                    bufferedWriter.write(it.getCategory().get(0)+","+it.getCategory().get(1)+",");
                }
                else{
                    bufferedWriter.write(it.getCategory().get(0)+","+it.getCategory().get(1)+","+it.getCategory().get(2));
                }
                bufferedWriter.newLine();

                // System.out.println(it.getName());
            }
            //bufferedWriter.write("This will overwrite the existing content.");
            bufferedWriter.close();


    }

    public static void wrtitetomenufile() throws IOException {
        ArrayList<Food> foods = FoodDataBase;
        FileWriter fileWriter = new FileWriter(Menu_file, false); // The 'false' argument overwrites the file
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for(Food it: foods) {
            bufferedWriter.write(it.getRestaurantId()+","+it.getCategory()+","+it.getName()+","+it.getPrice());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    private ArrayList<Food> readfrommenufile(String menuFile) throws IOException {

        ArrayList<Food>temp = new ArrayList<>();

        BufferedReader bt = new BufferedReader(new FileReader(menuFile));
        while (true) {
            String line = bt.readLine();
            if (line == null)
                break;
            String[] array = line.split(",", -1);
            int id = Integer.valueOf(array[0]);
            double score = Double.valueOf(array[3]);
            Food food = new Food(id, array[1], array[2], score);
            //rds.addFooditem(food);
            temp.add(food);
        }
        bt.close();
        return temp;
    }

    private ArrayList<Restaurant> readfromrestaurantfile(String restaurantFile) throws IOException {
        ArrayList<Restaurant> temp = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(restaurantFile));
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            String[] array = line.split(",", -1);
            int id = Integer.valueOf(array[0]);
            double score = Double.valueOf(array[2]);
            ArrayList<String> categary = new ArrayList<>();
            if (!array[5].equals(""))
                categary.add(array[5]);
            if (!array[6].equals(""))
                categary.add(array[6]);
            if (!array[7].equals(""))
                categary.add(array[7]);
            // System.out.println(categary);
            Restaurant newreRestaurant = new Restaurant(id, array[1], score, array[3], array[4], categary);
            temp.add(newreRestaurant);
        }
        br.close();
        return temp;
    }


    private void connectClient()  {
        try{
            try(ServerSocket serverSocket = new ServerSocket(12345)) {
                while (true){
                    try {
                        Socket clientSocket = serverSocket.accept();
                        System.out.println("client connected");
                        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
                        String clientas = (String) socketWrapper.read();
                        System.out.println(clientas);
                        if(clientas.equalsIgnoreCase("Add new Restaurant")){
                            System.out.println("add restaurant");
                            String restaurantname = (String) socketWrapper.read();
                            int id = restaurantexist(restaurantname);
                            socketWrapper.write(id);
                            socketWrapper.write(getmxrestaurantid());
                            Restaurant newrestaurant = (Restaurant) socketWrapper.read();
                            RestaurantDataBase.add(newrestaurant);
                           Restaurant_name.add(newrestaurant.getName());
                           writetorestaurantfile();
                            for(Restaurant x:RestaurantDataBase) System.out.println(x.getName());

                        }
                        else if(clientas.equals("As a Restaurant")){
                            System.out.println("As restaurant");
                            serve(socketWrapper);
                        }
                        else {
                            System.out.println("AS a customer");
                            System.out.println("DSFSDFs");
                            Client client = new Client("tousif", socketWrapper);
                            new CustomerReadWriteThread(client);

                        }

                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }catch (Exception exception){
            System.out.println("Server starts: " + exception);
        }
    }

    private void serve(SocketWrapper socketWrapper) throws IOException, ClassNotFoundException {

        String clientName = (String) socketWrapper.read();
             //String clientName = (String)fromclient;
             while (!Restaurant_name.contains(clientName)) {
                socketWrapper.write("You are not registered.");
                clientName = (String) socketWrapper.read();
            }
            socketWrapper.write("No objection");
            int id = getRestaurantIdfromName(clientName);
            socketWrapper.write(id);
            ArrayList<Food> menu = getmenu(clientName);
            socketWrapper.write(menu);
            String clientid = Integer.toString(id);
            Client client = new Client(clientid, socketWrapper);

            new ReadWriteThread(client);


    }

    private ArrayList<FakeFood> getorders(int id) {
        ArrayList<FakeFood>temp = new ArrayList<>();
        for(FakeFood x:pending){
            if(x.getRestaurantId() == id) temp.add(x);
        }
        return temp;
    }

    private int getmxrestaurantid() {
        int mx = -1;
        for(int i = 0;i<RestaurantDataBase.size();i++) mx = Math.max(mx,RestaurantDataBase.get(i).getId());
        return mx+1;
    }

    private int restaurantexist(String name) {
        for(Restaurant x:RestaurantDataBase) {
            if(x.getName().equalsIgnoreCase(name)) return 1;
        }
        return 0;
    }

    private ArrayList<Food> getmenu(String clientName) {
        ArrayList<Food> temp = new ArrayList<>();
        for(Food x:FoodDataBase){
            int id = getRestaurantIdfromName(clientName);
            if(x.getRestaurantId() == id) temp.add(x);
        }
        return temp;
    }
    static int getRestaurantIdfromName(String name){
        for(int i = 0;i<RestaurantDataBase.size();i++){
            if(RestaurantDataBase.get(i).getName().equalsIgnoreCase(name)) return RestaurantDataBase.get(i).getId();

        }
        return -1;
    }
    @NotNull
private ArrayList<String>Restaurant_Name(){
        ArrayList<String> temp = new ArrayList<>();
        for(Restaurant x:RestaurantDataBase) temp.add(x.getName());
        return temp;
}

    public static boolean isSameCategoryAndName(int id,String name,String category,double price){
        for(int i = 0;i<FoodDataBase.size();i++){
            if(FoodDataBase.get(i).getRestaurantId() == id && FoodDataBase.get(i).getCategory().equalsIgnoreCase(category) && FoodDataBase.get(i).getName().equalsIgnoreCase(name) && FoodDataBase.get(i).getPrice() == price) return true;
        }
        return false;
    }
}
