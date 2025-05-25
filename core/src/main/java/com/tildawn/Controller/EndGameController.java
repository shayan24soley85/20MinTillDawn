package com.tildawn.Controller;

import com.tildawn.Enums.SFX;
import com.tildawn.Main;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.View.EndGameView;
import com.tildawn.View.GameView;
import com.tildawn.View.MainMenuView;

public class EndGameController {
    private EndGameView view;
    public void setView(EndGameView view) {
        this.view = view;
    }

    public EndGameView getView() {
        return view;
    }
    public void  handleInput() {
        if (view!=null){
            if (view.getBackButton().isChecked()){
                SFX.CLICK_BUTTON.play();
                view.getBackButton().setChecked(false);
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
