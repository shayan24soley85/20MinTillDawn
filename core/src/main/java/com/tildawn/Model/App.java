package com.tildawn.Model;

import com.badlogic.gdx.audio.Music;

import com.tildawn.Enums.SortBy;
import com.tildawn.Model.Language.LanguageSetting;

import java.util.HashMap;
import java.util.Random;

public class App {
    private final HashMap<String,User> allUsers=new HashMap<>();
    private SortBy lastSortBy=SortBy.killCount;
    private final SavingToJson saving=new SavingToJson();
    private User currentUser=null;
    private  LanguageSetting currentLanguage;
    private Game currentGame=new Game();
    private Random rand=new Random();
    public  void run(){
        saving.readFile();
        databaseManager.connect();
    }
    public  HashMap<String, User> getAllUsers() {
        return allUsers;
    }
    private Music currentMusic;
    private String currentMusicPath=null;
    private Game SavedGame=null;
    private DatabaseManager databaseManager=new DatabaseManager();

    public SavingToJson getSaving() {
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

    public SortBy getLastSortBy() {
        return lastSortBy;
    }

    public void setLastSortBy(SortBy lastSortBy) {
        this.lastSortBy = lastSortBy;
    }

    public Game getSavedGame() {
        return SavedGame;
    }

    public void setSavedGame(Game savedGame) {
        SavedGame = savedGame;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}
