package com.tildawn.Model;

import com.tildawn.Model.Language.LanguageSetting;

import java.util.HashMap;
public class App {
    private final HashMap<String,User> allUsers=new HashMap<>();
    private final Saving saving=new Saving();
    private User currentUser=null;
    private  LanguageSetting currentLanguage;
    private Game currentGame=new Game();
    public  void run(){
        saving.readFile();
    }
    public  HashMap<String, User> getAllUsers() {
        return allUsers;
    }

    public Saving getSaving() {
        return saving;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public LanguageSetting getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(LanguageSetting currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
}
