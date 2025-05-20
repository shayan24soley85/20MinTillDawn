package com.tildawn.Model;

import java.util.HashMap;
public class App {
    private final HashMap<String,User> allUsers=new HashMap<>();
    private final Saving saving=new Saving();

    public  void run(){
        saving.readFile();
    }
    public  HashMap<String, User> getAllUsers() {
        return allUsers;
    }

    public Saving getSaving() {
        return saving;
    }
}
