package com.tildawn.Model;

import com.badlogic.gdx.audio.Music;

import com.tildawn.Enums.SortBy;
import com.tildawn.Model.Language.LanguageSetting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class App {
    private final HashMap<String,User> allUsers=new HashMap<>();
    private SortBy lastSortBy=SortBy.killCount;
    private final SavingToJson saving=new SavingToJson();
    private User currentUser=null;
    private  LanguageSetting currentLanguage;
    private Game currentGame=new Game();
    private Random rand=new Random();
    private Map<Integer,Game> allGames=new HashMap<>();
    private int savedGameId;
    private Game newGame=new Game();
    public  void run(){
        saving.readFile();
        databaseManager.connect();
    }
    public  HashMap<String, User> getAllUsers() {
        return allUsers;
    }
    private Music currentMusic;
    private String currentMusicPath=null;
    private DatabaseManager databaseManager=new DatabaseManager();

    public Game getNewGame() {
        return newGame;
    }

    public void setNewGame(Game newGame) {
        this.newGame = newGame;
    }

    public int getSavedGameId() {
        return savedGameId;
    }

    public void setSavedGameId(int savedGameId) {
        this.savedGameId = savedGameId;
    }

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

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public Map<Integer,Game> getAllGames() {
        return allGames;
    }

    public void setAllGames(Map<Integer,Game> allGames) {
        this.allGames = allGames;
    }
}
