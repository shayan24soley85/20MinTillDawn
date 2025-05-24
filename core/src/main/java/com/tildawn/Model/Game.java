package com.tildawn.Model;

import com.tildawn.View.GameView;

public class Game {
    private Boolean grayscaleToggle=false;
    private Boolean autoReload=false;
    private Boolean sfxToggle=true;
    private int gameTime;
    private Character character;
    private GameView gameView;
    private boolean isInPause=false;
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
}
