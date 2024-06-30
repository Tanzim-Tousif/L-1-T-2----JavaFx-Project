package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;

public class OrderThread implements Runnable{
    SocketWrapper socketWrapper;
    ArrayList<FakeFood>temp;
    public OrderThread(SocketWrapper socketWrapper,ArrayList<FakeFood>list) {
        this.socketWrapper = socketWrapper;
        this.temp = list;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("on order customer thread");
        for(FakeFood x:temp) System.out.println(x.getName());
        try {
            socketWrapper.write("order");
            socketWrapper.write(temp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
