package com.tildawn.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tildawn.Main;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Saving {
    public  void saveUserToJson(User user) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("allUsers.json");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<User> userList = new ArrayList<>();
        if (file.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                userList = gson.fromJson(reader, new TypeToken<List<User>>() {
                }.getType());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userList.removeIf(existingUser -> existingUser.getUsername().equals(user.getUsername()));
        userList.add(user);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            gson.toJson(userList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void readFile() {
        try (FileReader reader = new FileReader("allUsers.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Type userListType = new TypeToken<List<User>>() {}.getType();
            List<User> userList = gson.fromJson(reader, userListType);

            if (userList != null) {
                for (User user : userList) {
                    Main.getMain().getApp().getAllUsers().put(user.getUsername(), user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
