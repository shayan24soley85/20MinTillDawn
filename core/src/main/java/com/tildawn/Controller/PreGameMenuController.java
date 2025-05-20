package com.tildawn.Controller;

import com.tildawn.Main;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.Model.PreGame;
import com.tildawn.View.PreGameMenuView;

public class PreGameMenuController {
    private PreGameMenuView view;
    private PreGame pregame;


    public void setView(PreGameMenuView view) {
        this.view = view;
        this.pregame = new PreGame();
    }

    public void handlePreGameMenuButtons() {
        if (view != null) {
            Main.getMain().getScreen().dispose();
            //Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }

    public PreGameMenuView getView() {
        return view;
    }

    public PreGame getPregame() {
        return pregame;
    }

    public void setPregame(PreGame pregame) {
        this.pregame = pregame;
    }
}
