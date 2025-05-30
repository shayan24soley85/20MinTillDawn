package com.tildawn.Model;

import com.tildawn.View.GameView;

public class Game {
    private Boolean grayscaleToggle=false;
    private Boolean autoReload=false;
    private Boolean sfxToggle=true;
    private int id;
    private int gameTime;
    private Character character;
    private GameView gameView;
    private Boolean lost=false;
    private boolean isInPause=false;
    private Boolean isEnded=false;
    public Boolean getGrayscaleToggle() {
        return grayscaleToggle;
    }

    public void setGrayscaleToggle(Boolean grayscaleToggle) {
        this.grayscaleToggle = grayscaleToggle;
    }

    public Boolean getAutoReload() {
        return autoReload;
    }

    public void setAutoReload(Boolean autoReload) {
        this.autoReload = autoReload;
    }

    public Boolean getSfxToggle() {
        return sfxToggle;
    }

    public void setSfxToggle(Boolean sfxToggle) {
        this.sfxToggle = sfxToggle;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public boolean isInPause() {
        return isInPause;
    }

    public void setInPause(boolean inPause) {
        isInPause = inPause;
    }

    public Boolean getLost() {
        return lost;
    }

    public void setLost(Boolean lost) {
        this.lost = lost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Game{" +
            "grayscaleToggle=" + grayscaleToggle +
            ", autoReload=" + autoReload +
            ", sfxToggle=" + sfxToggle +
            ", id=" + id +
            ", gameTime=" + gameTime +
            ", character=" + character.getType().getName() +
            ", gameView=" + gameView +
            ", lost=" + lost +
            ", isInPause=" + isInPause +
            '}';
    }

    public Boolean getEnded() {
        return isEnded;
    }

    public void setEnded(Boolean ended) {
        isEnded = ended;
    }
}
