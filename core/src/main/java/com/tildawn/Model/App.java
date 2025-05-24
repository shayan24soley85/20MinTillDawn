package com.tildawn.Model;

import com.badlogic.gdx.audio.Music;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.tildawn.Model.Language.LanguageSetting;

import java.util.HashMap;
import java.util.Random;

public class App {
    private final HashMap<String,User> allUsers=new HashMap<>();
    private final Saving saving=new Saving();
    private User currentUser=null;
    private  LanguageSetting currentLanguage;
    private Game currentGame=new Game();
    private Random rand=new Random();
    public  void run(){
        saving.readFile();
    }
    public  HashMap<String, User> getAllUsers() {
        return allUsers;
    }
    private Music currentMusic;
    private String currentMusicPath=null;

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

    public Music getCurrentMusic() {
        return currentMusic;
    }

    public void setCurrentMusic(Music currentMusic) {
        this.currentMusic = currentMusic;
    }

    public String getCurrentMusicPath() {
        return currentMusicPath;
    }

    public void setCurrentMusicPath(String currentMusicPath) {
        this.currentMusicPath = currentMusicPath;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }
}
